package org.code.notificationapprove.infrastructure;

import lombok.*;

import java.time.*;

@Getter
@Setter
public class ErrorResponse {
  private String message;
  private String error;
  private int status;
  private String path;
  private LocalDateTime timestamp;

  public ErrorResponse(String message, String error, int status, String path) {
    this.message = message;
    this.error = error;
    this.status = status;
    this.path = path;
    this.timestamp = LocalDateTime.now();
  }
}