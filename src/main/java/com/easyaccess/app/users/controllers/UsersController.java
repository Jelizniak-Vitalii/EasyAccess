package com.easyaccess.app.users.controllers;

import com.easyaccess.app.auth.guards.AuthGuard;
import com.easyaccess.app.users.dto.UpdateLangDto;
import com.easyaccess.app.users.models.UserModel;
import com.easyaccess.app.users.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AuthGuard
@RestController
@RequestMapping("/users")
public class UsersController {
  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping
  public List<UserModel> getAllUsers() {
    return usersService.getAllUsers();
  }

  @GetMapping("/user")
  public UserModel getUserById(@RequestAttribute("userId") Long userId) {
    return usersService.getUserById(userId);
  }

  @PostMapping("/lang")
  public ResponseEntity<String> updateUserLang(@RequestAttribute("userId") Long userId, @RequestBody UpdateLangDto langDto) {
    usersService.updateLang(userId, langDto.getLang());
    return ResponseEntity.ok("User Lang updated successfully");
  }

  @PostMapping("/image")
  public ResponseEntity<String> updateUserImage(@RequestAttribute("userId") Long userId, @RequestParam("file") MultipartFile file) {
    usersService.uploadUserImage(userId, file);
    return ResponseEntity.ok("Image updated successfully");
  }
}
