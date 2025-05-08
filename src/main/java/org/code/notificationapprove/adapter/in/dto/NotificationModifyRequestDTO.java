package org.code.notificationapprove.adapter.in.dto;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NotificationModifyRequestDTO(
     String name,
     String lastName,
     int age,
     String country
) {}
