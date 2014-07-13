package com.github.masahirosuzuka.phonegapguidesigner;

import com.github.masahirosuzuka.phonegapguidesigner.MainView.FileEditorPanel;
import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.codeHighlighting.HighlightingPass;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

/**
 *
 * Created by Masahiro Suzuka on 2014/05/14.
 */
public class GUIBuilder3FileEditor extends UserDataHolderBase implements FileEditor {

  private BackgroundEditorHighlighter backgroundEditorHighlighter;

  private Project project;
  private VirtualFile virtualFile;

  public GUIBuilder3FileEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
    this.project = project;
    this.virtualFile = virtualFile;
  }

  @NotNull
  @Override
  public JComponent getComponent() {
    return new FileEditorPanel(project, virtualFile);
  }

  @Nullable
  @Override
  public JComponent getPreferredFocusedComponent() {
    return null;
  }

  @NotNull
  @Override
  public String getName() {
    return "GUIBuilder3";
  }

  @NotNull
  @Override
  public FileEditorState getState(@NotNull FileEditorStateLevel level) {
    return FileEditorState.INSTANCE;
  }

  @Override
  public void setState(@NotNull FileEditorState state) {

  }

  @Override
  public boolean isModified() {
    return false;
  }

  @Override
  public boolean isValid() {
    return virtualFile.getFileType() == StdFileTypes.HTML;
  }

  @Override
  public void selectNotify() {

  }

  @Override
  public void deselectNotify() {

  }

  @Override
  public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

  }

  @Override
  public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

  }

  @Nullable
  @Override
  public BackgroundEditorHighlighter getBackgroundHighlighter() {
    if (backgroundEditorHighlighter == null) {
      backgroundEditorHighlighter = new BackgroundEditorHighlighter() {
        @NotNull
        @Override
        public HighlightingPass[] createPassesForEditor() {
          return HighlightingPass.EMPTY_ARRAY;
        }

        @NotNull
        @Override
        public HighlightingPass[] createPassesForVisibleArea() {
          return HighlightingPass.EMPTY_ARRAY;
        }
      };
    }
    return backgroundEditorHighlighter;
  }

  @Nullable
  @Override
  public FileEditorLocation getCurrentLocation() {
    return null;
  }

  @Nullable
  @Override
  public StructureViewBuilder getStructureViewBuilder() {
    return null;
  }

  @Override
  public void dispose() {
    Disposer.dispose(this);
  }
}
