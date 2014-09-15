package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.vfs.VirtualFile;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Element;
import org.w3c.dom.*;
//import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
//import javax.swing.text.html.HTMLDocument;
import javax.transaction.TransactionRequiredException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.StringWriter;

/**
 * GUIBuilder3FileEditorPanel.java
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIDesignerFileEditorPanel extends JPanel {

  private Project myProject;
  private VirtualFile myVirtualFile;
  private Document myDocument;
  private JFXPanel jfxPanel;
  private WebView webView;
  private ComboBox deviceSelector;

  public GUIDesignerFileEditorPanel(@NotNull Project project, @NotNull VirtualFile virtualFile) {
    myProject = project;
    myVirtualFile = virtualFile;
    myDocument = FileDocumentManager.getInstance().getDocument(myVirtualFile);
    initUI();
  }

  private void initUI() {
    final String url = myVirtualFile.getUrl();

    jfxPanel = new JFXPanel();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Scene scene = createScene();
        jfxPanel.setScene(scene);
      }

      private Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(url);
        //webEngine.executeScript("(function(F,i,r,e,b,u,g,L,I,T,E){if(F.getElementById(b))return;E=F[i+'NS']&&F.documentElement.namespaceURI;E=E?F[i+'NS'](E,'script'):F[i]('script');E[r]('id',b);E[r]('src',I+g+T);E[r](b,u);(F[e]('head')[0]||F[e]('body')[0]).appendChild(E);E=new Image;E[r]('src',I+L);})(document,'createElement','setAttribute','getElementsByTagName','FirebugLite','4','firebug-lite.js','releases/lite/latest/skin/xp/sprite.png','https://getfirebug.com/','#startOpened');");

        root.getChildren().add(webView);

        return scene;
      }
    });
    Platform.setImplicitExit(false);

    deviceSelector = new ComboBox(new String[]{"iPhone5/5s", "iPad", "Nexus5", "Nexus7"});
    deviceSelector.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (deviceSelector.getSelectedItem().equals("iPhone5/5s")) {
                webView.setPrefSize(960.0, 1130.0);
            }
        }
    });
    this.add(deviceSelector);

    JButton reloadButton = new JButton("reload");
    reloadButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    webView.getEngine().reload();
                }
            });
        }
    });
    this.add(reloadButton);

    new DropTarget(jfxPanel, DnDConstants.ACTION_COPY, new GUIBuilderDropTarget()); // DnD support
    jfxPanel.addMouseMotionListener(new GUIBuilderMouseAdapter()); // Decolate active widget
    this.add(jfxPanel);
  }

  private class GUIBuilderMouseAdapter extends MouseInputAdapter {

    private HTMLElement lastElement;

    @Override
    public void mouseReleased(MouseEvent e) {
      super.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      super.mouseMoved(e);
      final Point point = e.getPoint();

      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          if (lastElement != null && lastElement.getAttribute("style").equals("border-color:red")) {
            lastElement.removeAttribute("style");
          }
          String jsCode = String.format("window.document.elementFromPoint(%f,%f);", point.getX(), point.getY());
          JSObject jsObject = (JSObject)webView.getEngine().executeScript(jsCode);
          HTMLElement element = (HTMLElement)jsObject;
          lastElement = element;
          element.setAttribute("style", "border-color:red");
        }
      });
    }

  }

  private class GUIBuilderDropTarget extends DropTargetAdapter {

    private Point point;

    @Override
    public void drop(DropTargetDropEvent event) {
      event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
      point = event.getLocation();
      try {
        final Transferable transfer = event.getTransferable();
        final String content = transfer.getTransferData(DataFlavor.stringFlavor).toString();
        //System.out.println(content);

        final Document document = FileDocumentManager.getInstance().getDocument(myVirtualFile);
        //final org.jsoup.nodes.Document dom = Jsoup.parse(document.getText());

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            String insert = String.format("var temp = window.document.createElement('div');" +
                                          "temp.innerHTML='%s';" +
                                          "window.document.elementFromPoint(%f,%f).appendChild(temp);",
                                              content, point.getX(), point.getY());
            webView.getEngine().executeScript(insert);

            String getHtml = String.format("new XMLSerializer().serializeToString(window.document.documentElement);");
            String str = (String)webView.getEngine().executeScript(getHtml);

            System.out.println(str);
          }
        });

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
          @Override
          public void run() {
            Platform.runLater(new Runnable() {
              @Override
              public void run() {
                //webView.getEngine().reload();
              }
            });
          }
        });
      } catch (UnsupportedFlavorException ufe) {
          ufe.printStackTrace();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }
}

