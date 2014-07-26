package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.github.masahirosuzuka.phonegapguidesigner.KendoUISettings;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.*;

/**
 * GUIBuilder3PalettePanel.java
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilder3PalettePanel extends JPanel {

  private Project myProject;
  private ToolWindow myToolWindow;

  public GUIBuilder3PalettePanel(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    myProject = project;
    myToolWindow = toolWindow;
    initUI();
  }

  private void initUI() {
    // Set Kendo UI core
    String[] kendoUIData = KendoUISettings.components;

    JBList list = new JBList();
    DefaultListModel model = new DefaultListModel();
    list.setModel(model);
    list.setListData(kendoUIData);
    list.setDragEnabled(true);

    list.setBackground(myToolWindow.getComponent().getBackground());
    list.setLocation(0, 0);
    list.setPreferredSize(new Dimension(myToolWindow.getComponent().getWidth(), myToolWindow.getComponent().getHeight()));

    this.add(list);

    list.setDragEnabled(true);

    // Set SenchaTouch

    // Set OnsenUI
  }
}
