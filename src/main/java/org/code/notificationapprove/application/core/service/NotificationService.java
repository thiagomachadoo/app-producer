package org.code.notificationapprove.application.core.service;

import org.code.notificationapprove.application.core.domain.*;
import org.code.notificationapprove.application.port.interfaces.*;
import org.code.notificationapprove.application.port.repositories.*;
import org.code.notificationapprove.infrastructure.exceptions.*;

public class NotificationService implements NotificationPortIn {

  private final NotificationPortDatabase notificationPortDatabase;
  private final NotificationPortServices notificationPortServices;

  public NotificationService(NotificationPortDatabase notificationPortDatabase, NotificationPortServices notificationPortServices) {
    this.notificationPortDatabase = notificationPortDatabase;
    this.notificationPortServices = notificationPortServices;
  }

  @Override
  public NotificationDomain sendNotification(NotificationDomain data) {
    var isSaved = notificationPortDatabase.saveNotification(data);

    if (isSaved.isEmpty()) throw new CannotSaveNotificationException("Failed to save notification! Try again!");

    var notificationData = isSaved.get();
    var isDeliveryMessage = notificationPortServices.sendNotification(data);

    if (!isDeliveryMessage) throw new CannotSaveNotificationException("Failed to send notification! Try again!");

    return notificationData;
  }
}
