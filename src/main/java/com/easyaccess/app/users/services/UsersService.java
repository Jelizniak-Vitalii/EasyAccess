package com.easyaccess.app.users.services;

import com.easyaccess.app.users.dao.UserDao;
import com.easyaccess.app.users.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {
  private final UserDao userDao;

  public UsersService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<UserModel> getAllUsers() {
    return userDao.findAll().stream().peek(user -> user.setPassword(null))
      .collect(Collectors.toList());
  }

  public UserModel getUserById(Long id) {
    return userDao.findById(id);
  }

  public UserModel getUserByEmail(String email) {
    return userDao.findByEmail(email);
  }

  public void createUser(UserModel user) {
    userDao.createUser(user);
  }

  public void updateUser(UserModel user) {
    userDao.update(user);
  }
}
