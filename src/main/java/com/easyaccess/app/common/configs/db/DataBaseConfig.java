package com.easyaccess.app.common.configs.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {
  @Value("${db.url}")
  private String URL;

  @Value("${db.user}")
  private String USER;

  @Value("${db.password}")
  private String PASSWORD;

  public void initializeDatabase(DataSource dataSource) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

    populator.addScript(new ClassPathResource("db/schema.sql"));
//    populator.addScript(new ClassPathResource("db/data.sql"));
    populator.execute(dataSource);
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl(URL);
    dataSource.setUsername(USER);
    dataSource.setPassword(PASSWORD);

    initializeDatabase(dataSource);

    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
