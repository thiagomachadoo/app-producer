package org.code.notificationapprove.adapter.in.controller;

import org.code.notificationapprove.adapter.in.dto.*;
import org.code.notificationapprove.application.port.interfaces.*;
import org.code.notificationapprove.mapper.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
  @ResponseStatus(HttpStatus.ACCEPTED)
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

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<NotificationResponseDTO> findAllNotifications() {
    var responseData = portIn.findAllNotifications();

    return notificationMapper.toDtoList(responseData);
  }

  @PatchMapping
  @ResponseStatus(HttpStatus.OK)
  public NotificationResponseDTO updateNotification(@RequestBody NotificationModifyRequestDTO notification,
                                                    @RequestParam String id) {
    var data = notificationMapper.fromDtoModifyToDomain(notification);
    var responseData = portIn.modify(data, id);

    return notificationMapper.fromDomainToDto(responseData);
  }

}
