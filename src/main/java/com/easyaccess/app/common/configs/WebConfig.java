package com.easyaccess.app.common.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // Включаем Spring MVC
public class WebConfig implements WebMvcConfigurer {
//  @Override
//  public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
//    registry.addMapping("/api/**")
//      .allowedOrigins("http://localhost:4200") // URL фронтенда
//      .allowedMethods("GET", "POST", "PUT", "DELETE");
//  }
}
