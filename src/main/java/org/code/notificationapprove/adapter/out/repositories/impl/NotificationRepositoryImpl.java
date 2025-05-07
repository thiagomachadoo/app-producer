package org.code.notificationapprove.adapter.out.repositories.impl;

import org.code.notificationapprove.adapter.out.repositories.*;
import org.code.notificationapprove.application.core.domain.*;
import org.code.notificationapprove.application.port.repositories.*;
import org.code.notificationapprove.mapper.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class NotificationRepositoryImpl implements NotificationPortDatabase {

  private final NotificationRepository repository;
  private final NotificationMapper notificationMapper;

  public NotificationRepositoryImpl(@Lazy NotificationRepository repository, NotificationMapper notificationMapper) {
    this.repository = repository;
    this.notificationMapper = notificationMapper;
  }

  @Override
  public Optional<NotificationDomain> saveNotification(NotificationDomain domainData) {

    var entityData = notificationMapper.fromDomainToEntity(domainData);
    var savedData = repository.save(entityData);

    return Optional.of(notificationMapper.fromEntityToDomain(savedData));
  }
}
