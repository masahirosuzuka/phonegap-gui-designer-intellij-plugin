package com.github.masahirosuzuka.phonegapguidesigner.Palette;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;

/**
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilderToolWindowConditions implements Condition<Project>{
  @Override
  public boolean value(Project project) {
    // ToDo : Enable only project is PhoneGap/Cordova project
    return true;
  }
}
