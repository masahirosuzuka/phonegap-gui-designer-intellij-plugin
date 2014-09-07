package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.github.masahirosuzuka.phonegapguidesigner.component.JQueryMobile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Map;
import java.util.Set;

/**
 * GUIBuilder3PalettePanel.java
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilderPalettePanel extends JPanel {

  private Project myProject;
  private ToolWindow myToolWindow;
  private JQueryMobile jQuery;
  private Map jQueryMap;
  private Tree tree;
  private DefaultMutableTreeNode key;

  public GUIBuilderPalettePanel(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    myProject = project;
    myToolWindow = toolWindow;

    initUI();
  }

  private void initUI() {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Component");

    jQuery = new JQueryMobile(myProject);
    jQueryMap = jQuery.map;
    DefaultMutableTreeNode jQueryRoot = new DefaultMutableTreeNode("jQuery Mobile");
    root.add(jQueryRoot);
    Set<String> stringSet = jQuery.map.keySet();
    String[] jQueryArray = stringSet.toArray(new String[stringSet.size()]);
    for (String widgetName : jQueryArray) {
      jQueryRoot.add(new DefaultMutableTreeNode(widgetName));
    }

    DefaultMutableTreeNode kendoUI = new DefaultMutableTreeNode("Kendo UI");
    root.add(kendoUI);

    tree = new Tree(root);
    tree.setDragEnabled(true);
    tree.setTransferHandler(new GUIBuilderTransferHandler());
    tree.setBackground(Color.white);
    JBScrollPane scrollPane = new JBScrollPane();
    scrollPane.setViewportView(tree);
    scrollPane.setLocation(0, 0);
    scrollPane.setPreferredSize(new Dimension(myToolWindow.getComponent().getWidth(), myToolWindow.getComponent().getHeight()));
    this.add(scrollPane);
  }

  private class GUIBuilderTransferHandler extends TransferHandler {

    @Nullable
    @Override
    protected Transferable createTransferable(JComponent c) {
      Tree tree = (Tree)c;
      String key = tree.getLastSelectedPathComponent().toString();
      if (key != null) {
        return new StringSelection((jQueryMap.get(key).toString()));
      }
      return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
      return COPY_OR_MOVE;
    }

    /*
     * ingore drag-and-drop form self
     */
    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
      return false;
    }
  }

  private class GUIBuilderMutableTreeNode extends DefaultMutableTreeNode {

  }

  private class GUIBuilderTreeCellRenderer extends DefaultTreeCellRenderer {

  }
}
