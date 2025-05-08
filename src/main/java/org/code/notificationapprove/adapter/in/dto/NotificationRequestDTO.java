package org.code.notificationapprove.adapter.in.dto;

import lombok.*;

public record NotificationRequestDTO(
     @NonNull
     String name,
     @NonNull
     String lastName,
     @NonNull
     Integer age,
     @NonNull
     String country
) {}
