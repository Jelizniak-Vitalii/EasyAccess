package com.easyaccess.app.users.mappers;

import com.easyaccess.app.users.models.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserModel> {
  @Override
  public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserModel user = new UserModel();

    user.setId(rs.getInt("id"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    user.setPhone(rs.getString("phone"));
    user.setFirstName(rs.getString("first_name"));
    user.setLastName(rs.getString("last_name"));
    user.setImage(rs.getString("image"));
    user.setLang(rs.getString("lang"));

    return user;
  }
}
