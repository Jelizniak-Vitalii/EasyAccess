package com.easyaccess.app.users.models;

public class UserModel {
  private int id;
  private String email;
  private String password;
  private String firstName;
  private String phone;
  private String lastName;
  private String image;
  private String lang;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", phone='" + phone + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", image='" + image + '\'' +
      ", lang='" + lang + '\'' +
      '}';
  }
}

