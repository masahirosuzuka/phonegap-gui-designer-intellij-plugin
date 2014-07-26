package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.openapi.editor.Document;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

/**
 * GlassLayerDropTargetListener.java
 *
 * Created by Masahiro Suzuka on 2014/05/28.
 */
public class GlassLayerDropTargetListener implements DropTargetListener {

  private Document myDocument;

  public GlassLayerDropTargetListener(Document document) {
    myDocument = document;
  }

  @Override
  public void drop(DropTargetDropEvent dtde) {
    System.out.println("drop");
    Transferable transferable = dtde.getTransferable();
    try {
      Object data = transferable.getTransferData(DataFlavor.stringFlavor);
      System.out.println(data);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void dragEnter(DropTargetDragEvent dtde) {

  }

  @Override
  public void dragOver(DropTargetDragEvent dtde) {

  }

  @Override
  public void dropActionChanged(DropTargetDragEvent dtde) {

  }

  @Override
  public void dragExit(DropTargetEvent dte) {

  }
}
