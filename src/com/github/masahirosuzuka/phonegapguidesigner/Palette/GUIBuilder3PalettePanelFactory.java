package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.Content;

/**
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilder3PalettePanelFactory implements ToolWindowFactory {
  @Override
  public void createToolWindowContent(Project project, ToolWindow toolWindow) {
    GUIBuilder3PalettePanel palette = new GUIBuilder3PalettePanel(project, toolWindow);
    Content content = ContentFactory.SERVICE.getInstance().createContent(palette, "", false);
    toolWindow.getContentManager().addContent(content);
  }
}
