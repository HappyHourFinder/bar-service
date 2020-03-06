package com.mathieuaime.hhf.bar.model;

import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BarDocument {

  @Id
  private String uuid = UUID.randomUUID().toString();
  private String name;

  public BarDocument() {
  }

  public BarDocument(String uuid, String name) {
    this.uuid = uuid;
    this.name = name;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BarDocument bar = (BarDocument) o;
    return Objects.equals(uuid, bar.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
