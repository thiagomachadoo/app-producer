package org.code.notificationapprove.infrastructure.exceptions;

public class NotificationNotFoundException extends RuntimeException {
  public NotificationNotFoundException(String message) {
    super(message);
  }
}
