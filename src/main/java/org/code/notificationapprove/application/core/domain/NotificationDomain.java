package org.code.notificationapprove.application.core.domain;

public class NotificationDomain {
  private String id;
  private String name;
  private String lastName;
  private int age;
  private String country;

  public NotificationDomain(String id, String name, String lastName, int age, String country) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.age = age;
    this.country = country;
  }

  public NotificationDomain() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
