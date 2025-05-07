package org.code.notificationapprove.adapter.out.repositories;

import org.code.notificationapprove.adapter.out.entities.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {}
