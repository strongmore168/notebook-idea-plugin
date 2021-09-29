package com.strongmore.notebook.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class NotificationUtility {

  public static void createNotification(String content, NotificationType type) {
    NotificationGroup notebookNotificationGroup = new NotificationGroup("NoteBook",
        NotificationDisplayType.BALLOON, true);
    Notification notification = notebookNotificationGroup.createNotification(content, type);
    Notifications.Bus.notify(notification);
  }
}
