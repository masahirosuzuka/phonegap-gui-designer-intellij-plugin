package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

/**
 * GUIBuilder3FileEditorPanel.java
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class FileEditorPanel extends JPanel {

  private Project myProject;
  private VirtualFile myVirtualFile;
  //private JPanel container;
  //private JFXPanel myJfxPanel;
  private JFXPanel jfxPanel;

  public FileEditorPanel(@NotNull Project project, @NotNull VirtualFile virtualFile) {
    myProject = project;
    myVirtualFile = virtualFile;
    final String url = virtualFile.getUrl();

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

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);

        root.getChildren().add(webView);

        return scene;
      }
    });
    Platform.setImplicitExit(false);

    this.add(new JButton("reload"));
    this.add(jfxPanel);

    GlassLayerUI glassLayer = new GlassLayerUI();

    // DnD support

  }

  /*
  private void initUI(String url) {
    System.out.println("initUI");
    this.setLayout(new BorderLayout());

    //container = new JPanel();
    //container.setBackground(Color.LIGHT_GRAY);

    // Set WebView
    final JFXPanel myJfxPanel = new JFXPanel();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Scene scene = createScene();
        myJfxPanel.setScene(scene);
        System.out.println("run");
      }

      private Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        //webEngine.load("http://google.com");
        webEngine.load(myVirtualFile.getUrl());

        root.getChildren().add(webView);

        return scene;
      }
    });
    Platform.setImplicitExit(false);
    container.add(jfxPanel);
    this.add(container);

    // Set ActiveDecolateLayer

    // Set GlassLayer (DnD support)
    LayerUI<JComponent> glassLayerUI = new GlassLayerUI();
    JLayer<JComponent> glassLayer = new JLayer<JComponent>(myJfxPanel, glassLayerUI);
    new DropTarget(glassLayer, DnDConstants.ACTION_NONE, new GlassLayerDropTargetListener(FileDocumentManager.getInstance().getDocument(myVirtualFile)));
    myJfxPanel.add(glassLayer);

    //this.add(myJfxPanel);
  }*/
}
