package org.code.notificationapprove.application.port.repositories;

import org.code.notificationapprove.adapter.in.dto.*;
import org.code.notificationapprove.application.core.domain.*;

import java.util.*;

public interface NotificationPortDatabase {
  Optional<NotificationDomain> save(NotificationDomain data);

  Optional<NotificationDomain> find(String id);

  List<NotificationDomain> findAll();

  Optional<NotificationDomain> modify(NotificationDomain data, String id);
}
