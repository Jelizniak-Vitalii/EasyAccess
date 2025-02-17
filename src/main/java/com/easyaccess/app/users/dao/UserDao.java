package com.easyaccess.app.users.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.easyaccess.app.users.mappers.UserRowMapper;
import com.easyaccess.app.users.models.UserModel;

import java.util.List;

@Repository
public class UserDao {
  private final JdbcTemplate jdbcTemplate;

  public UserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<UserModel> findAll() {
    String sql = "SELECT * FROM users";
    return jdbcTemplate.query(sql, new UserRowMapper());
  }

  public UserModel findById(Long id) {
    String sql = "SELECT * FROM users WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
  }

  public UserModel findByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";

    List<UserModel> users = jdbcTemplate.query(sql, new UserRowMapper(), email);

    return users.isEmpty() ? null : users.get(0);
  }

  public int createUser(UserModel user) {
    String sql = "INSERT INTO users (email, password, phone, first_name, last_name) VALUES (?, ?, ?, ?, ?)";

    return jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getPhone(),
      user.getFirstName(), user.getLastName());
  }

  public void updateLang(Long userId, String lang) {
    String sql = "UPDATE users SET lang = ? WHERE id = ?";
    jdbcTemplate.update(sql, lang, userId);
  }

  public void updateUserImage(Long userId, String filePath) {
    String sql = "UPDATE users SET image = ? WHERE id = ?";
    jdbcTemplate.update(sql, filePath, userId);
  }

  public void update(UserModel user) {
    String sql = "INSERT INTO users (email, phone, first_name, last_name, image) VALUES (?, ?, ?, ?, ?)";

    jdbcTemplate.update(sql, user.getEmail(), user.getPhone(), user.getFirstName(), user.getLastName(), user.getImage());
  }
}
