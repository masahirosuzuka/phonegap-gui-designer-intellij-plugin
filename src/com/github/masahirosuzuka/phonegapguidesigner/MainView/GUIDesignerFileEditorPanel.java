package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
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
import org.w3c.dom.html.HTMLElement;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
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
import java.io.IOException;

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
    private String htmlString;

    @Override
    public void drop(DropTargetDropEvent event) {
      event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
      point = event.getLocation();
      try {
        final Transferable transfer = event.getTransferable();
        final String content = transfer.getTransferData(DataFlavor.stringFlavor).toString();
        //final String htmlString;
        final Document document = FileDocumentManager.getInstance().getDocument(myVirtualFile);

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            String insert = String.format("var temp = window.document.createElement('div');" +
                                          "temp.innerHTML='%s';" +
                                          "window.document.elementFromPoint(%f,%f).appendChild(temp);",
                                              content, point.getX(), point.getY());
            webView.getEngine().executeScript(insert);

            String getHtml = String.format("new XMLSerializer().serializeToString(window.document.documentElement);");
            htmlString = (String)webView.getEngine().executeScript(getHtml);
          }
        });

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
          @Override
          public void run() {
            if (htmlString != null) {
              document.setText(htmlString);
              FileDocumentManager.getInstance().saveDocument(document);// Save file
              Platform.runLater(new Runnable() {
                @Override
                public void run() {
                  webView.getEngine().reload();
                }
              });
            }
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
