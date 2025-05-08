package org.code.notificationapprove.application.core.service;

import org.code.notificationapprove.application.core.domain.*;
import org.code.notificationapprove.application.port.interfaces.*;
import org.code.notificationapprove.application.port.repositories.*;
import org.code.notificationapprove.infrastructure.exceptions.*;

import java.util.*;

public class NotificationService implements NotificationPortIn {

  private final NotificationPortDatabase notificationPortDatabase;
  private final NotificationPortServices notificationPortServices;

  public NotificationService(NotificationPortDatabase notificationPortDatabase, NotificationPortServices notificationPortServices) {
    this.notificationPortDatabase = notificationPortDatabase;
    this.notificationPortServices = notificationPortServices;
  }

  @Override
  public NotificationDomain sendNotification(NotificationDomain data) {
    var isSaved = notificationPortDatabase.save(data);

    if (isSaved.isEmpty()) throw new CannotSaveNotificationException("Failed to save notification! Try again!");

    var notificationData = isSaved.get();
    var isDeliveryMessage = notificationPortServices.sendNotification(data);

    if (!isDeliveryMessage) throw new CannotSaveNotificationException("Failed to send notification! Try again!");

    return notificationData;
  }

  @Override
  public NotificationDomain findNotification(String id) {
    var existsData = notificationPortDatabase.find(id);

    if (existsData.isEmpty()) throw new NotificationNotFoundException("Notification with id " + id + " not found!");

    return existsData.get();
  }

  @Override
  public List<NotificationDomain> findAllNotifications() {
    var existsData = notificationPortDatabase.findAll();

    if (existsData.isEmpty()) throw new NotificationNotFoundException("No data found!");

    return existsData;
  }
}
