package org.code.notificationapprove.mapper;

import org.code.notificationapprove.adapter.in.dto.*;
import org.code.notificationapprove.adapter.out.entities.*;
import org.code.notificationapprove.application.core.domain.*;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {

  @Mapping(target = "id", ignore = true)
  NotificationDomain fromDtoToDomain(NotificationRequestDTO dto);

  NotificationEntity fromDomainToEntity(NotificationDomain domain);

  NotificationResponseDTO fromDomainToDto(NotificationDomain domain);

  NotificationDomain fromEntityToDomain(NotificationEntity entity);
}
