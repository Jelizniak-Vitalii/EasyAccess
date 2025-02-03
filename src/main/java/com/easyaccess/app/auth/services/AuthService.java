package com.easyaccess.app.auth.services;

import com.easyaccess.app.auth.dto.RegistrationDto;
import com.easyaccess.app.common.providers.JwtTokenProvider;
import com.easyaccess.app.users.models.UserModel;
import com.easyaccess.app.users.services.UsersService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
  private final UsersService usersService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthService(
    UsersService usersService,
    PasswordEncoder passwordEncoder,
    JwtTokenProvider jwtTokenProvider
  ) {
    this.usersService = usersService;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Transactional
  public String login(String email, String password) {
    UserModel user = usersService.getUserByEmail(email);

    if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalStateException("Email or password is incorrect");
    }

    return jwtTokenProvider.generateToken(email, "Admin", user.getId());
  }

  @Transactional
  public String register(RegistrationDto registrationDto) {
    UserModel user = usersService.getUserByEmail(registrationDto.getEmail());

    if (user != null) {
      throw new IllegalStateException("User with email " + registrationDto.getEmail() + " already exists");
    }

    UserModel candidate = new UserModel();
    candidate.setEmail(registrationDto.getEmail());
    candidate.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
    candidate.setFirstName(registrationDto.getFirstName());
    candidate.setLastName(registrationDto.getLastName());

    usersService.createUser(candidate);

    return jwtTokenProvider.generateToken(registrationDto.getEmail(), "Admin", candidate.getId());
  }
}
