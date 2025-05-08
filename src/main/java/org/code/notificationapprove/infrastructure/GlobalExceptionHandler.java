package org.code.notificationapprove.infrastructure;

import jakarta.servlet.http.*;
import org.code.notificationapprove.infrastructure.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CannotSaveNotificationException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleErrorDatabase(CannotSaveNotificationException ex, HttpServletRequest request) {
    return new ErrorResponse(
        ex.getMessage(),
        "Cannot save person notification!",
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        request.getRequestURI()
    );
  }

  @ExceptionHandler(CannotDeliveryMessageToQueue.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleErrorQueue(CannotDeliveryMessageToQueue ex, HttpServletRequest request) {
    return new ErrorResponse(
        ex.getMessage(),
        "Cannot sent person notification to queue!",
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        request.getRequestURI()
    );
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleErrorQueue(NotFoundException ex, HttpServletRequest request) {
    return new ErrorResponse(
        ex.getMessage(),
        "Cannot find person data!",
        HttpStatus.NOT_FOUND.value(),
        request.getRequestURI()
    );
  }

}
