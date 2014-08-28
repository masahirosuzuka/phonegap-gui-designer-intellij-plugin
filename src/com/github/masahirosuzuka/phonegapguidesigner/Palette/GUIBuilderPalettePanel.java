package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.github.masahirosuzuka.phonegapguidesigner.component.JQueryMobile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * GUIBuilder3PalettePanel.java
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilderPalettePanel extends JPanel {

  private Project myProject;
  private ToolWindow myToolWindow;

  public GUIBuilderPalettePanel(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    myProject = project;
    myToolWindow = toolWindow;

    initUI();
  }

  private void initUI() {
    JQueryMobile jQuery = new JQueryMobile();
    Set<String> stringSet = jQuery.map.keySet();
    String[] stringArray = stringSet.toArray(new String[stringSet.size()]);

    JBList list = new JBList();
    DefaultListModel model = new DefaultListModel();
    list.setModel(model);
    list.setListData(stringArray);
    list.setDragEnabled(true);
    list.setBackground(myToolWindow.getComponent().getBackground());
    list.setLocation(0, 0);
    list.setPreferredSize(new Dimension(myToolWindow.getComponent().getWidth(), myToolWindow.getComponent().getHeight()));
    this.add(list);
    list.setDragEnabled(true);
  }
}
