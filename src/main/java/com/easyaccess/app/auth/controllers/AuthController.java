package com.easyaccess.app.auth.controllers;

import com.easyaccess.app.auth.dto.LoginDto;
import com.easyaccess.app.auth.dto.RegistrationDto;
import com.easyaccess.app.auth.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  @ResponseBody
  public String login(@Valid @RequestBody LoginDto loginDto) {
    return authService.login(loginDto.getEmail(), loginDto.getPassword());
  }

  @PostMapping("/registration")
  @ResponseBody
  public String registration(@Valid @RequestBody RegistrationDto registrationDto) {
    return authService.register(registrationDto);
  }
}
