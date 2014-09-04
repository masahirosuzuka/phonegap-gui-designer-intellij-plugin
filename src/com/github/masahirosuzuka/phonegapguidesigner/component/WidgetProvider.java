package com.github.masahirosuzuka.phonegapguidesigner.component;

import com.intellij.spring.SpringModelProvider;
import com.intellij.spring.facet.SpringFacet;
import com.intellij.spring.facet.SpringFileSet;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by masahiro on 2014/09/03.
 */
public class WidgetProvider implements SpringModelProvider {
  @NotNull
  @Override
  public List<SpringFileSet> getFilesets(@NotNull SpringFacet facet) {
    return null;
  }
}
