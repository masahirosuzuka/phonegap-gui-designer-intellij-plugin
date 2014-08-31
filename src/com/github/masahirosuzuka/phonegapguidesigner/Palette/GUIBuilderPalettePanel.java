package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.github.masahirosuzuka.phonegapguidesigner.component.JQueryMobile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
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
  private JQueryMobile jQuery = new JQueryMobile();
  private Map jQueryMap = jQuery.map;

  public GUIBuilderPalettePanel(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    myProject = project;
    myToolWindow = toolWindow;

    initUI();
  }

  private void initUI() {
    JBList list = new JBList();
    DefaultListModel model = new DefaultListModel();
    list.setModel(model);
    //Map jQueryMap = jQuery.map;
    Set<String> stringSet = jQuery.map.keySet();
    String[] stringArray = stringSet.toArray(new String[stringSet.size()]);
    list.setListData(stringArray);
    list.setDragEnabled(true);
    list.setTransferHandler(new GUIBuilderTransferHandler());

    //list.setFont();
    list.setBackground(myToolWindow.getComponent().getBackground());
    list.setLocation(0, 0);
    list.setPreferredSize(new Dimension(myToolWindow.getComponent().getWidth(), myToolWindow.getComponent().getHeight()));
    this.add(list);
  }

  private class GUIBuilderTransferHandler extends TransferHandler {

    @Nullable
    @Override
    protected Transferable createTransferable(JComponent c) {
      JBList list = (JBList)c;
      String key = list.getSelectedValue().toString();
      if (key != null) {
        return new StringSelection(jQueryMap.get(key).toString());
      }
      return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
      return COPY_OR_MOVE;
    }

    /*
     * ingore drag-over form self
     */
    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
      return false;
    }
  }
}
