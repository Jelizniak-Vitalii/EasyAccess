package com.easyaccess.app.common.configs;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan(basePackages = "com.easyaccess.app")
@Import({
  WebConfig.class,
  DataBaseConfig.class,
  JacksonConfig.class,
  SecurityConfig.class
})
public class AppConfig {
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    Resource resource = new ClassPathResource("application.properties");
    configurer.setLocation(resource);

    return configurer;
  }
}
