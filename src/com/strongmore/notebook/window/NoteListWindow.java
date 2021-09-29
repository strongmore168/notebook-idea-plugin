package com.strongmore.notebook.window;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.strongmore.notebook.model.DocumentInfo;
import com.strongmore.notebook.model.NoteRepository;
import com.strongmore.notebook.processor.MarkDownProcessor;
import com.strongmore.notebook.util.NotificationUtility;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;

public class NoteListWindow {

  private JPanel contentPanel;
  private JTextField documentTitleField;
  private JLabel documentTitleLabel;
  private JButton createBtn;
  private JButton clearBtn;
  private JButton closeBtn;
  private JTable contentTable;

  public NoteListWindow(Project project,
      ToolWindow toolWindow) {
    contentTable.setModel(NoteRepository.getTableModel());
    contentTable.setEnabled(true);
    createBtn.addActionListener(e -> {
      String documentTitle = documentTitleField.getText();
      if (StringUtils.isBlank(documentTitle)) {
        documentTitleField.requestFocus();
        return;
      }
      if (NoteRepository.queryNoteList().isEmpty()) {
        NotificationUtility.createNotification("No content!", NotificationType.WARNING);
        return;
      }
      VirtualFile chooseFile = FileChooser
          .chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project,
              project.getProjectFile());
      if (Objects.nonNull(chooseFile)) {
        String filePath =
            chooseFile.getPath() + "/" + documentTitle + ".md";
        DocumentInfo documentInfo = new DocumentInfo()
            .setFileName(filePath)
            .setTitle(documentTitle)
            .setNoteInfoList(NoteRepository.queryNoteList());
        try {
          new MarkDownProcessor().process(documentInfo);
          NotificationUtility
              .createNotification("create document success!", NotificationType.INFORMATION);
          documentTitleField.setText("");
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    clearBtn.addActionListener(e -> NoteRepository.clear());
    closeBtn.addActionListener(e -> toolWindow.hide(null));
  }

  public JPanel getContentPanel() {
    return contentPanel;
  }
}
;