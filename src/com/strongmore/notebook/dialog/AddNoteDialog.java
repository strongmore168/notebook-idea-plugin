package com.strongmore.notebook.dialog;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import com.strongmore.notebook.model.NoteInfo;
import com.strongmore.notebook.model.NoteRepository;
import com.strongmore.notebook.util.NotificationUtility;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

public class AddNoteDialog extends DialogWrapper {

  private EditorTextField noteTitleField;
  private EditorTextField noteContentField;
  private String fileName;
  private String selectedText;

  public AddNoteDialog(String fileName, String selectedText) {
    super(true);
    setTitle("Add Note");
    init();
    this.fileName = fileName;
    this.selectedText = selectedText;
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    JPanel addNotePanel = new JPanel(new BorderLayout());
    noteTitleField = new EditorTextField();
    noteTitleField.setPlaceholder("title");
    noteContentField = new EditorTextField();
    noteContentField.setPlaceholder("content");
    noteContentField.setPreferredSize(new Dimension(200, 100));
    addNotePanel.add(noteTitleField, BorderLayout.NORTH);
    addNotePanel.add(noteContentField, BorderLayout.CENTER);
    return addNotePanel;
  }

  @Override
  protected JComponent createSouthPanel() {
    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("add to list");
    addButton.addActionListener((e) -> {
      String title = noteTitleField.getText();
      if (StringUtils.isBlank(title)) {
        noteTitleField.requestFocus();
        return;
      }
      String content = noteContentField.getText();
      if (StringUtils.isBlank(content)) {
        noteContentField.requestFocus();
        return;
      }
      NoteInfo noteInfo = new NoteInfo()
          .setTitle(title)
          .setContent(content)
          .setFileName(fileName)
          .setSelectedText(selectedText);
      NoteRepository.addNote(noteInfo);
      NotificationUtility.createNotification("add to list success!", NotificationType.INFORMATION);
      AddNoteDialog.this.dispose();
    });
    buttonPanel.add(addButton);
    return buttonPanel;
  }
}
