package com.easyaccess.app.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UpdateUserDto {
  private Long id;

  @Email(message = "Некорректный формат email")
  @Size(max = 255, message = "Email должен быть не длиннее 255 символов")
  private String email;

  @Size(min = 6, max = 255, message = "Пароль должен быть от 6 до 255 символов")
  private String password;

  @Size(max = 50, message = "Имя должно быть не длиннее 50 символов")
  private String firstName;

  @Size(max = 50, message = "Фамилия должна быть не длиннее 50 символов")
  private String lastName;

  @Size(max = 20, message = "Телефон должен быть не длиннее 20 символов")
  private String phone;

  @Size(max = 255, message = "URL изображения должен быть не длиннее 255 символов")
  private String image;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
