package com.easyaccess.app.users.services;

import com.easyaccess.app.files.services.FileStorageService;
import com.easyaccess.app.users.dao.UserDao;
import com.easyaccess.app.users.models.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {
  private final UserDao userDao;
  private final FileStorageService fileStorageService;

  private final static String uploadDir = "images/users";

  public UsersService(
    UserDao userDao,
    FileStorageService fileStorageService
  ) {
    this.userDao = userDao;
    this.fileStorageService = fileStorageService;
  }

  public List<UserModel> getAllUsers() {
    return userDao.findAll().stream().peek(user -> user.setPassword(null))
      .collect(Collectors.toList());
  }

  public UserModel getUserById(Long id) {
    UserModel user = userDao.findById(id);
    user.setPassword(null);

    return user;
  }

  public UserModel getUserByEmail(String email) {
    return userDao.findByEmail(email);
  }

  public int createUser(UserModel user) {
    return userDao.createUser(user);
  }

  public void updateUser(UserModel user) {
    userDao.update(user);
  }

  public void updateLang(Long userId, String lang) {
    userDao.updateLang(userId, lang);
  }

  public void uploadUserImage(Long userId, MultipartFile file) {
    UserModel user = userDao.findById(userId);
    String oldImage = user.getImage();
    String filePath;

    if (oldImage == null || oldImage.isEmpty()) {
      filePath = fileStorageService.saveFile(file, uploadDir);
    } else {
      filePath = fileStorageService.updateFile(file, oldImage, uploadDir);
    }

    userDao.updateUserImage(userId, filePath);
  }
}
