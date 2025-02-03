package com.easyaccess.app.auth.filters;

import com.easyaccess.app.common.providers.JwtTokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

  private final JwtTokenProvider jwtTokenProvider;

  public JwtFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String token = jwtTokenProvider.resolveToken(httpRequest);

    if (token != null && jwtTokenProvider.validateToken(token)) {
      String role = jwtTokenProvider.extractRole(token);
      httpRequest.setAttribute("role", role);
    }

    chain.doFilter(request, response);
  }
}
