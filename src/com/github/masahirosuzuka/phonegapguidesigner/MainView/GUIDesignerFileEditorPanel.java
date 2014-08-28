package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.xml.XmlFile;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.xerces.parsers.DOMParser;
import org.java.ayatana.ApplicationMenu;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
//import org.w3c.dom.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * GUIBuilder3FileEditorPanel.java
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIDesignerFileEditorPanel extends JPanel {

  private Project myProject;
  private VirtualFile myVirtualFile;
  //private JPanel container;
  //private JFXPanel myJfxPanel;
  private JFXPanel jfxPanel;
  private WebView webView;
  private ComboBox deviceSelector;

  public GUIDesignerFileEditorPanel(@NotNull Project project, @NotNull VirtualFile virtualFile) {
    myProject = project;
    myVirtualFile = virtualFile;
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
            //webView.getEngine().reload();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    webView.getEngine().reload();
                }
            });
        }
    });
    this.add(reloadButton);

    // DnD support
    new DropTarget(jfxPanel, DnDConstants.ACTION_COPY, new MyDropTargetAdapter());

    this.add(jfxPanel);

    // Collect position data
    Document document = FileDocumentManager.getInstance().getDocument(myVirtualFile);
    String htmlString = document.getText();
    Jsoup.parse(htmlString);

    document.addDocumentListener(new MyDocumentListener());
  }

  private class MyDocumentListener implements DocumentListener
  {
      @Override
      public void beforeDocumentChange(DocumentEvent event) {

      }

      @Override
      public void documentChanged(DocumentEvent event) {
        // Rebuild "information tree"
      }
  }

  private class MyDropTargetAdapter extends DropTargetAdapter {

    @Override
    public void drop(DropTargetDropEvent event) {
      event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
      try {
          Transferable transfer = event.getTransferable();
          String key = transfer.getTransferData(DataFlavor.stringFlavor).toString();

          if (key.equals("Button")) {
              final Document document = FileDocumentManager.getInstance().getDocument(myVirtualFile);
              XmlFile xmlFile = (XmlFile)PsiDocumentManager.getInstance(myProject).getPsiFile(document);
              String xmlSting = xmlFile.getText();

              final org.jsoup.nodes.Document domDocument = Jsoup.parse(xmlSting);
              Element buttonElement = domDocument.createElement("button");
              Element bodyElement = domDocument.body();
              bodyElement.appendChild(buttonElement);

              //System.out.println(domDocument.html());
              ApplicationManager.getApplication().runWriteAction(new Runnable() {
                  @Override
                  public void run() {
                      document.setText(domDocument.html());
                      Platform.runLater(new Runnable() {
                          @Override
                          public void run() {
                              webView.getEngine().reload();
                              webView.getEngine().executeScript("alert(\"reloaded\");");
                          }
                      });
                  }
              });
          }
      } catch (UnsupportedFlavorException ufe) {
          ufe.printStackTrace();
      } catch (IOException ioe) {
          ioe.printStackTrace();
      }
    }
  }
}

