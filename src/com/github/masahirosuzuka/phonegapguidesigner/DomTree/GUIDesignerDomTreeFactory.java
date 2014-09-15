package com.github.masahirosuzuka.phonegapguidesigner.DomTree;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

/**
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIDesignerDomTreeFactory implements ToolWindowFactory {
  @Override
  public void createToolWindowContent(Project project, ToolWindow toolWindow) {
    GUIBuilderDOMTreePanel treeAndAttributesInspector = new GUIBuilderDOMTreePanel(project, toolWindow);
    Content content = ContentFactory.SERVICE.getInstance().createContent(treeAndAttributesInspector, "", false);
    toolWindow.getContentManager().addContent(content);
  }
}
