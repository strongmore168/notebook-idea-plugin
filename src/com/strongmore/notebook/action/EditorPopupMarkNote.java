package com.strongmore.notebook.action;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.strongmore.notebook.dialog.AddNoteDialog;
import com.strongmore.notebook.util.NotificationUtility;
import org.apache.commons.lang3.StringUtils;

public class EditorPopupMarkNote extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    SelectionModel selectionModel = editor.getSelectionModel();
    String selectedText = selectionModel.getSelectedText();
    if (StringUtils.isBlank(selectedText)) {
      NotificationUtility.createNotification("No content is selected!", NotificationType.WARNING);
      return;
    }
    String fileName = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile()
        .getName();
    new AddNoteDialog(fileName, selectedText).show();
  }

}
