package com.easyaccess.app.users.services;

import com.easyaccess.app.users.dao.UserDao;
import com.easyaccess.app.users.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
  private final UserDao userDao;

  public UsersService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<UserModel> getAllUsers() {
    return userDao.findAll();
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
