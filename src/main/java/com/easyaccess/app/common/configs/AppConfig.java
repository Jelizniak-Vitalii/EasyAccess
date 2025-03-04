package com.easyaccess.app.common.configs;

import com.easyaccess.app.common.configs.db.DataBaseConfig;
import com.easyaccess.app.common.configs.db.FlywayConfig;
import com.easyaccess.app.common.configs.security.SecurityConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@ComponentScan(basePackages = "com.easyaccess.app")
@Import({
  WebConfig.class,
  DataBaseConfig.class,
  JacksonConfig.class,
  SecurityConfig.class,
  FlywayConfig.class
})
public class AppConfig {
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    Resource resource = new ClassPathResource("application.properties");
    configurer.setLocation(resource);

    return configurer;
  }

  @Bean
  public StandardServletMultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }
}
