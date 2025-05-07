package org.code.notificationapprove.infrastructure.exceptions;

public class CannotDeliveryMessageToQueue extends RuntimeException {
  public CannotDeliveryMessageToQueue(String message) {
    super(message);
  }
}
