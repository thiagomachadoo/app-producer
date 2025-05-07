package org.code.notificationapprove.infrastructure;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Queue notificationQueue() {
    return new Queue("notification-queue", true);
  }
}
