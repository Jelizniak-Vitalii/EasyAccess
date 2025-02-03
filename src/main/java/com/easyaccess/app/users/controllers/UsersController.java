package com.easyaccess.app.users.controllers;

import com.easyaccess.app.auth.guards.AuthGuard;
import com.easyaccess.app.users.models.UserModel;
import com.easyaccess.app.users.services.UsersService;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{id}")
  public UserModel getUserById(@PathVariable Long id) {
    return usersService.getUserById(id);
  }

  @PutMapping("/{id}")
  public String updateUser(@PathVariable Long id, @RequestBody UserModel user) {
    user.setId(id);
    usersService.updateUser(user);
    return "User updated successfully";
  }
}
