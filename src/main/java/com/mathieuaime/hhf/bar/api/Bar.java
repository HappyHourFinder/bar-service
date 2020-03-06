package com.mathieuaime.hhf.bar.api;

import java.util.Objects;

public class Bar {

  private String uuid;
  private String name;

  public Bar() {
  }

  public Bar(String uuid, String name) {
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
    Bar bar = (Bar) o;
    return Objects.equals(uuid, bar.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
