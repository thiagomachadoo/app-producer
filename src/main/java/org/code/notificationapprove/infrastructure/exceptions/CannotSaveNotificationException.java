package org.code.notificationapprove.infrastructure.exceptions;

public class CannotSaveNotificationException extends RuntimeException {
  public CannotSaveNotificationException(String message) {
    super(message);
  }
}
