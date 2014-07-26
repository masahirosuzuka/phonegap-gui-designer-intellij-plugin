package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.icons.AllIcons;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.psi.*;
import com.intellij.psi.html.HtmlTag;
import com.intellij.psi.impl.file.impl.FileManager;
import com.intellij.psi.impl.source.html.HtmlTagImpl;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.meta.PsiMetaData;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.xml.*;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomManager;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import com.intellij.xml.util.IncludedXmlTag;
import com.sun.jna.platform.mac.MacFileUtils;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

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
  private WebView webView;

  public FileEditorPanel(@NotNull Project project, @NotNull VirtualFile virtualFile) {
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

        //WebView webView = new WebView();
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);
        //webEngine.load("http://localhost:8080/");

        root.getChildren().add(webView);

        return scene;
      }
    });
    Platform.setImplicitExit(false);

    this.add(new ComboBox(new String[]{"HTML", "Kengo UI", "Sencha Touch"}));

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
    //GlassLayerUI glassLayer = new GlassLayerUI();
    //DropTarget dropTarget = new DropTarget(jfxPanel, new MyDropTargetAdapter());
    new DropTarget(jfxPanel, DnDConstants.ACTION_COPY, new MyDropTargetAdapter());

    this.add(jfxPanel);
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
          System.out.println(document.getText());
          PsiFile psiFile = PsiDocumentManager.getInstance(myProject).getPsiFile(document);
          XmlFile xmlFile = (XmlFile) PsiDocumentManager.getInstance(myProject).getPsiFile(document);
          XmlTag rootTag = xmlFile.getRootTag();

          XmlTag buttonTag = XmlElementFactoryImpl.getInstance(myProject).createHTMLTagFromText("button");

          rootTag.addSubTag(buttonTag, false);

          final String text = xmlFile.getText();
          ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
              document.setText(text + "file edited");
            }
          });
          //document.setText(text + "file edited");
          //System.out.println(xmlFile.getRootTag());
          /*XmlTag rootTag = xmlFile.getRootTag();
          rootTag.createChildTag("button", "", "", true);

          xmlFile.accept(new XmlElementVisitor() {

          });*/
        }
     } catch (UnsupportedFlavorException ufe) {
        ufe.printStackTrace();
     } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }
}

