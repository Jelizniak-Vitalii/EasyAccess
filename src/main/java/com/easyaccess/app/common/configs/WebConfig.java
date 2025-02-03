package com.easyaccess.app.common.configs;

import com.easyaccess.app.auth.interceptors.JwtInterceptor;
import com.easyaccess.app.auth.interceptors.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
  private final JwtInterceptor jwtInterceptor;
  private final RoleInterceptor roleInterceptor;

  public WebConfig(JwtInterceptor jwtInterceptor, RoleInterceptor roleInterceptor) {
    this.jwtInterceptor = jwtInterceptor;
    this.roleInterceptor = roleInterceptor;
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.addPathPrefix("/api", clazz -> true);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:4200")
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowCredentials(true);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJackson2HttpMessageConverter());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtInterceptor)
      .addPathPatterns("/api/**")
      .excludePathPatterns("/public/**");

    registry.addInterceptor(roleInterceptor)
      .addPathPatterns("/api/**");
  }
}
