package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.github.masahirosuzuka.phonegapguidesigner.KendoUISettings;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

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

    // Set Kendo UI core
    String[] kendoUIData = KendoUISettings.components;
    JBList list = new JBList();
    list.setListData(kendoUIData);
    list.setBackground(toolWindow.getComponent().getBackground());
    list.setLocation(0, 0);
    list.setPreferredSize(new Dimension(toolWindow.getComponent().getWidth(), toolWindow.getComponent().getHeight()));

    this.add(list);

    // Set SenchaTouch

    // Set OnsenUI
  }

}
