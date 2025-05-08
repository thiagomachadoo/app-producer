package org.code.notificationapprove.adapter.out.repositories.impl;

import com.mongodb.client.*;
import org.bson.*;
import org.code.notificationapprove.adapter.out.repositories.*;
import org.code.notificationapprove.application.core.domain.*;
import org.code.notificationapprove.application.port.repositories.*;
import org.code.notificationapprove.infrastructure.exceptions.*;
import org.code.notificationapprove.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Component
public class NotificationRepositoryImpl implements NotificationPortDatabase {

  @Value("${spring.data.mongodb.uri}")
  private String databaseUrl;
  @Value("${spring.data.mongodb.database}")
  private String databaseName;

  private final NotificationRepository repository;
  private final NotificationMapper notificationMapper;

  public NotificationRepositoryImpl(@Lazy NotificationRepository repository, NotificationMapper notificationMapper) {
    this.repository = repository;
    this.notificationMapper = notificationMapper;
  }

  @Override
  public Optional<NotificationDomain> save(NotificationDomain domainData) {

    var entityData = notificationMapper.fromDomainToEntity(domainData);
    var savedData = repository.save(entityData);

    return Optional.of(notificationMapper.fromEntityToDomain(savedData));
  }

  @Override
  public Optional<NotificationDomain> find(String id) {

    var data = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Person not found for id: " + id));

    return Optional.of(notificationMapper.fromEntityToDomain(data));
  }

  @Override
  @Transactional
  public List<NotificationDomain> findAll() {
    MongoCollection<Document> collection = getDocumentMongoCollection();

    var data = collection.find().into(new ArrayList<>());

    return ManualMapUtil.mapFromDocuments(data);
  }

  @Override
  public Optional<NotificationDomain> modify(NotificationDomain data, String id) {
    var entity = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Person not found!"));

    if (data.getName() != null) entity.setName(data.getName());
    if (data.getLastName() != null) entity.setLastName(data.getLastName());
    if (data.getCountry() != null) entity.setCountry(data.getCountry());
    if (data.getAge() != 0) entity.setAge(data.getAge());

    var savedData = repository.save(entity);
    return Optional.of(notificationMapper.fromEntityToDomain(savedData));
  }

  public MongoCollection<Document> getDocumentMongoCollection() {
    MongoClient mongoClient = MongoClients.create(databaseUrl);
    MongoDatabase database = mongoClient.getDatabase(databaseName);
    return database.getCollection("notification");
  }
}
