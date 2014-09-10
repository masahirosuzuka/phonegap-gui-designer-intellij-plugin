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
import org.jsoup.nodes.Element;

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
import java.beans.PropertyChangeListener;
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

    // DnD support
    new DropTarget(jfxPanel, DnDConstants.ACTION_COPY, new GUIBuilderDropTarget());

    // Decolate active widget
    jfxPanel.addMouseMotionListener(new GUIBuilderMouseAdapter());

    this.add(jfxPanel);
  }

  private class GUIBuilderMouseAdapter extends MouseInputAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {
      super.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      super.mouseMoved(e);
      final Point point = e.getLocationOnScreen();
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          String jsCode = String.format("window.document.elementFromPoint( %f,  %f);", point.getX(), point.getY());
          JSObject jsObject = (JSObject)webView.getEngine().executeScript(jsCode);
          System.out.println(jsObject);
        }
      });
    }

  }

  private class GUIBuilderDropTarget extends DropTargetAdapter {

    @Override
    public void drop(DropTargetDropEvent event) {
      event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
      try {
        Transferable transfer = event.getTransferable();
        String content = transfer.getTransferData(DataFlavor.stringFlavor).toString();

        final Document document = FileDocumentManager.getInstance().getDocument(myVirtualFile);
        final org.jsoup.nodes.Document dom = Jsoup.parse(document.getText());
        Element body = dom.body();
        body.append(content);

        //System.out.println(body.html());
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
          @Override
          public void run() {
            document.setText(dom.html());
            Platform.runLater(new Runnable() {
              @Override
              public void run() {
                webView.getEngine().reload();
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

