package com.easyaccess.app;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.easyaccess.app.common.configs.AppConfig;

import java.io.File;

public class EasyAccessApplication implements WebApplicationInitializer {
  public static void main(String[] args) throws Exception {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);

    // Disable TLD scanning (speeds up startup if there is no JSP)
    System.setProperty("org.apache.catalina.startup.TldConfig.jarsToSkip", "*");

    Connector connector = tomcat.getConnector();
    connector.setProperty("maxThreads", "500");
    connector.setProperty("minSpareThreads", "20");
    connector.setProperty("acceptCount", "200");
    connector.setProperty("connectionTimeout", "30000");
    connector.setProperty("keepAliveTimeout", "15000");
    connector.setProperty("maxKeepAliveRequests", "100");

    tomcat.addWebapp("", new File(".").getAbsolutePath());

    tomcat.start();
    tomcat.getServer().await();
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(AppConfig.class);

    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");

    MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
      "",
      10485760L,
      10485760L,
      1048576
    );
    dispatcher.setMultipartConfig(multipartConfigElement);
  }
}
