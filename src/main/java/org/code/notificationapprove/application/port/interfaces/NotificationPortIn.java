package org.code.notificationapprove.application.port.interfaces;

import org.code.notificationapprove.application.core.domain.*;

public interface NotificationPortIn {
  NotificationDomain sendNotification(NotificationDomain data);

  NotificationDomain findNotification(String id);
}
