package com.github.masahirosuzuka.phonegapguidesigner.ComponentTree;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilderTreeAndAttributesInspector extends JPanel {

  private Project project;
  private ToolWindow toolWindow;
  private DefaultMutableTreeNode rootTreeNode;

  public GUIBuilderTreeAndAttributesInspector(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    this.project = project;
    this.toolWindow = toolWindow;

    String currentFilePath = project.getBasePath() + "/" + "www/index.html";

    // Read HTML Document
    File file = new File(currentFilePath);
    if (!file.exists()) {
      return;
    }

    Document document = null;
    try {
      document = Jsoup.parse(file, "utf-8");
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<Node> nodelist = document.childNodes();
    rootTreeNode = new DefaultMutableTreeNode(file.getName());
    createTree(nodelist, rootTreeNode);

    Tree componentTree = new Tree(new DefaultTreeModel(rootTreeNode));
    componentTree.setPreferredSize(new Dimension(toolWindow.getComponent().getWidth(), toolWindow.getComponent().getHeight() / 2));
    componentTree.setBackground(toolWindow.getComponent().getBackground());
    this.add(componentTree);
  }

  private void createTree(List<Node> nodeList, DefaultMutableTreeNode root) {
    for (Node node : nodeList) {
      DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode(node.nodeName());
      root.add(newRoot);
      if (node.childNodeSize() != 0) {
        List<Node> newList = node.childNodes();
        createTree(newList, newRoot);
      }
    }
  }
}
