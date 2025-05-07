package org.code.notificationapprove.infrastructure;

import org.code.notificationapprove.application.core.service.*;
import org.code.notificationapprove.application.port.interfaces.*;
import org.code.notificationapprove.application.port.repositories.NotificationPortDatabase;
import org.springframework.context.annotation.*;

@Configuration
public class BeanConfiguration {

  @Bean
  NotificationPortIn portIn(NotificationPortDatabase repositories, NotificationPortServices service) {
    return new NotificationService(repositories, service);
  }
}
