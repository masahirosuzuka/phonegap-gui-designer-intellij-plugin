package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Condition;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilderFileEditorProvider implements FileEditorProvider, Condition<Project>{

  @Override
  public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
    return file.getFileType() == StdFileTypes.HTML;
  }

  @NotNull
  @Override
  public com.intellij.openapi.fileEditor.FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
    return new GUIDesignerFileEditor(project, file);
  }

  @Override
  public void disposeEditor(@NotNull com.intellij.openapi.fileEditor.FileEditor editor) {
    Disposer.dispose(editor);
  }

  @NotNull
  @Override
  public FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
    return FileEditorState.INSTANCE;
  }

  @Override
  public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {

  }

  @NotNull
  @Override
  public String getEditorTypeId() {
    return "GUIBuilder3";
  }

  @NotNull
  @Override
  public FileEditorPolicy getPolicy() {
    return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
  }

  @Override
  public boolean value(Project project) {
    return false;
  }
}
