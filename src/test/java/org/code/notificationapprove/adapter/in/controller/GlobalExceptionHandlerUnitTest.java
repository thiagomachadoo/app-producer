package org.code.notificationapprove.adapter.in.controller;

import jakarta.servlet.http.*;
import org.code.notificationapprove.infrastructure.*;
import org.code.notificationapprove.infrastructure.exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerUnitTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  @DisplayName("Deve lancar excecao por falhar ao tentar salvar.")
  void shouldHandleCannotSaveNotificationException() {
    var ex = new CannotSaveNotificationException("Falha ao salvar");
    var req = mock(HttpServletRequest.class);
    when(req.getRequestURI()).thenReturn("/fake");

    var response = handler.handleErrorDatabase(ex, req);

    assertEquals("Falha ao salvar", response.getMessage());
    assertEquals(500, response.getStatus());
    assertEquals("/fake", response.getPath());
  }

  @Test
  @DisplayName("Deve lancar excecao por falhar ao tentar entregar mensagem.")
  void shouldHandleCannotDeliveryMessageToQueueException() {

    var exception = new CannotDeliveryMessageToQueue("Queue delivery failed");
    var request = mock(HttpServletRequest.class);
    when(request.getRequestURI()).thenReturn("/api/notify");


    var response = handler.handleErrorQueue(exception, request);


    assertEquals("Queue delivery failed", response.getMessage());
    assertEquals(500, response.getStatus());
    assertEquals("/api/notify", response.getPath());
  }
}
