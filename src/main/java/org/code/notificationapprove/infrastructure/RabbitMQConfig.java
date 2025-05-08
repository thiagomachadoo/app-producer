package org.code.notificationapprove.infrastructure;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Queue delayQueue() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-message-ttl", 120000);
    args.put("x-dead-letter-exchange", "");
    args.put("x-dead-letter-routing-key", "notification-queue");
    return new Queue("delay.queue", true, false, false, args);
  }

  @Bean
  public Queue notificationQueue() {
    return new Queue("notification-queue", true);
  }
}
