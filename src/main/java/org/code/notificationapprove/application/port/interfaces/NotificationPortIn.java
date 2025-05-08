package org.code.notificationapprove.application.port.interfaces;

import org.code.notificationapprove.adapter.in.dto.*;
import org.code.notificationapprove.application.core.domain.*;

import java.util.*;

public interface NotificationPortIn {
  NotificationDomain sendNotification(NotificationDomain data);

  NotificationDomain findNotification(String id);

  List<NotificationDomain> findAllNotifications();

  NotificationDomain modify(NotificationDomain notification, String id);
}
