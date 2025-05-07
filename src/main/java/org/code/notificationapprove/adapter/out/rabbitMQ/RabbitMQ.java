package org.code.notificationapprove.adapter.out.rabbitMQ;

import lombok.*;
import org.code.notificationapprove.application.core.domain.*;
import org.code.notificationapprove.application.port.interfaces.*;
import org.code.notificationapprove.infrastructure.exceptions.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class RabbitMQ implements NotificationPortServices {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public boolean sendNotification(NotificationDomain data) {

    try {
      var fullMessage = messageRabbitMQ(data);
      rabbitTemplate.convertAndSend("notification-queue",fullMessage);
      return true;
    } catch (Exception e) {
      throw new CannotSaveNotificationException("Failed to send notification! Try again!");
    }
  }

  private String messageRabbitMQ(NotificationDomain data) {
    return "Olá " + data.getName() + "! Tudo bem?" +
        " esse email está sendo enviado para confirmação de cadastro do servidor.";
  }
}
