package com.strongmore.notebook.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactory.SERVICE;
import org.jetbrains.annotations.NotNull;

public class NoteListWindowFactory implements ToolWindowFactory {

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    NoteListWindow noteListWindow = new NoteListWindow(project, toolWindow);
    ContentFactory contentFactory = SERVICE.getInstance();
    Content content = contentFactory.createContent(noteListWindow.getContentPanel(), "", false);
    toolWindow.getContentManager().addContent(content);
  }
}
