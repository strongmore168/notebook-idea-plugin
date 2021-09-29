package com.strongmore.notebook.model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NoteRepository {

  private List<NoteInfo> noteInfoList = new LinkedList<>();
  private static final Object[] COLUMNS = {"title", "content", "fileName", "selectedText"};
  private DefaultTableModel tableModel = new DefaultTableModel(null, COLUMNS);

  private static final NoteRepository INSTANCE = new NoteRepository();

  public static void addNote(NoteInfo noteInfo) {
    INSTANCE.noteInfoList.add(noteInfo);
    refresh();
  }

  public static TableModel getTableModel() {
    return INSTANCE.tableModel;
  }

  public static List<NoteInfo> queryNoteList() {
    return INSTANCE.noteInfoList;
  }

  public static void clear() {
    INSTANCE.noteInfoList.clear();
    refresh();
  }

  private static void refresh() {
    INSTANCE.tableModel.setDataVector(null, COLUMNS);
    INSTANCE.noteInfoList.forEach(noteInfo -> INSTANCE.tableModel.addRow(createRowData(noteInfo)));
  }

  private static Object[] createRowData(NoteInfo noteInfo) {
    Object[] data = new Object[4];
    data[0] = noteInfo.getTitle();
    data[1] = noteInfo.getContent();
    data[2] = noteInfo.getFileName();
    data[3] = noteInfo.getSelectedText();
    return data;
  }
}
