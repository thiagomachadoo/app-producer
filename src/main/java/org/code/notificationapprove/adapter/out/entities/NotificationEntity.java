package org.code.notificationapprove.adapter.out.entities;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity {

  @Id
  private String id;
  private String name;
  private String lastName;
  private int age;
  private String country;

}
