package com.easyaccess.app.users.models;

public enum Language {
  EN,
  RU,
  UK;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
