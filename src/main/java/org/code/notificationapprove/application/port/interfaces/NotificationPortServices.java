package org.code.notificationapprove.application.port.interfaces;

import org.code.notificationapprove.application.core.domain.*;

public interface NotificationPortServices {
  boolean sendNotification(NotificationDomain data);
}
