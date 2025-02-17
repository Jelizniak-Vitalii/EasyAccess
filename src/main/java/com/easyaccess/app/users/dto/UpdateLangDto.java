package com.easyaccess.app.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateLangDto {
  @NotBlank(message = "User lang must not be empty")
  @Size(max = 10, message = "User lang must not exceed 10 characters")
  private String lang;

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }
}

