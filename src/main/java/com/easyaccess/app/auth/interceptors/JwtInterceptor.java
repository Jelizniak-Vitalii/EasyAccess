package com.easyaccess.app.auth.interceptors;

import com.easyaccess.app.auth.guards.AuthGuard;
import com.easyaccess.app.common.constants.Messages;
import com.easyaccess.app.common.exceptions.UnauthorizedException;
import com.easyaccess.app.common.providers.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
  private final JwtTokenProvider jwtTokenProvider;

  public JwtInterceptor(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println("🔍 JwtInterceptor: Checking JWT for request - " + request.getRequestURI());

    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    HandlerMethod method = (HandlerMethod) handler;

    AuthGuard authGuard = method.getMethodAnnotation(AuthGuard.class);
    AuthGuard classAuthGuard = method.getBeanType().getAnnotation(AuthGuard.class);

    if (authGuard == null && classAuthGuard == null) {
      return true;
    }

    String token = jwtTokenProvider.resolveToken(request);
    if (token == null || !jwtTokenProvider.validateToken(token)) {
      throw new UnauthorizedException(Messages.AUTH_INVALID_TOKEN);
    }

    String role = jwtTokenProvider.extractRole(token);
    request.setAttribute("role", role);
    request.setAttribute("userId", jwtTokenProvider.extractUserId(token));

    return true;
  }
}
