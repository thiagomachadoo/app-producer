package org.code.notificationapprove.application.port.repositories;

import org.code.notificationapprove.application.core.domain.*;

import java.util.*;

public interface NotificationPortDatabase {
  Optional<NotificationDomain> saveNotification(NotificationDomain data);

}
