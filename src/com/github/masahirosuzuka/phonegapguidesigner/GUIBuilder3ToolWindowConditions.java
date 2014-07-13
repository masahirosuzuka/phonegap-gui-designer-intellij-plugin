package com.github.masahirosuzuka.phonegapguidesigner;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;

/**
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilder3ToolWindowConditions implements Condition<Project>{
  @Override
  public boolean value(Project project) {
    // Get files under srcRoot
    // if .html files are there return true
    return true;
  }
}
