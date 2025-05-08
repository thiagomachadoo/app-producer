package org.code.notificationapprove.mapper;

import org.bson.*;
import org.code.notificationapprove.application.core.domain.*;

import java.util.*;

public class ManualMapUtil {
  public static List<NotificationDomain> mapFromDocuments(List<Document> documents) {
    List<NotificationDomain> domainArrayList = new ArrayList<>();

    for (Document doc : documents) {
      NotificationDomain notificationDomain = new NotificationDomain();
      notificationDomain.setName(doc.getString("name"));
      notificationDomain.setLastName(doc.getString("lastName"));
      notificationDomain.setCountry(doc.getString("country"));
      notificationDomain.setAge(doc.getInteger("age"));
      notificationDomain.setId(String.valueOf(doc.getObjectId("_id")));
      domainArrayList.add(notificationDomain);
    }

    return domainArrayList;
  }
}
