package org.code.notificationapprove.adapter.in.controller;

import org.code.notificationapprove.adapter.in.dto.*;
import org.code.notificationapprove.application.port.interfaces.*;
import org.code.notificationapprove.mapper.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotificationController {

  private final NotificationMapper notificationMapper;
  private final NotificationPortIn portIn;

  public NotificationController(NotificationMapper notificationMapper, NotificationPortIn portIn) {
    this.notificationMapper = notificationMapper;
    this.portIn = portIn;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public NotificationResponseDTO sendNotification(@RequestBody NotificationRequestDTO notification) {
    var data = notificationMapper.fromDtoToDomain(notification);
    var responseData = portIn.sendNotification(data);

    return notificationMapper.fromDomainToDto(responseData);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public NotificationResponseDTO findNotification(@RequestParam String id) {
    var responseData = portIn.findNotification(id);

    return notificationMapper.fromDomainToDto(responseData);
  }
}
