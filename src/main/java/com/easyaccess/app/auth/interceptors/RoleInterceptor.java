package com.easyaccess.app.auth.interceptors;

import com.easyaccess.app.auth.guards.RoleGuard;
import com.easyaccess.app.common.constants.Messages;
import com.easyaccess.app.common.exceptions.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class RoleInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    HandlerMethod method = (HandlerMethod) handler;
    RoleGuard roleGuard = method.getMethodAnnotation(RoleGuard.class);
    RoleGuard classRoleGuard = method.getBeanType().getAnnotation(RoleGuard.class);

    if (roleGuard == null && classRoleGuard == null) {
      return true;
    }

    String userRole = (String) request.getAttribute("role");

    if (userRole == null || Arrays.stream(roleGuard.value()).noneMatch(userRole::equals)) {
      throw new ForbiddenException(Messages.AUTH_PERMISSION_FORBIDDEN);
    }

    return true;
  }
}
