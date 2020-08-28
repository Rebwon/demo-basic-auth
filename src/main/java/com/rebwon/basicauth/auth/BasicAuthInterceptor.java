package com.rebwon.basicauth.auth;

import com.rebwon.basicauth.common.exception.UnAuthenticationException;
import com.rebwon.basicauth.domain.Account;
import com.rebwon.basicauth.domain.AccountService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
public class BasicAuthInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private AccountService accountService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String authorization = request.getHeader("Authorization");
    log.debug("Authoirzation: {}", authorization);
    if(authorization == null || !authorization.startsWith("Basic")) {
      return true;
    }

    String base64Credentials = authorization.substring("Basic".length()).trim();
    String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
    final String[] values = credentials.split(":", 2);
    log.debug("email: {}", values[0]);
    log.debug("password: {}", values[1]);

    try {
      Account account = accountService.login(values[0], values[1]);
      log.debug("Login Success: {}", account);
      request.getSession().setAttribute(HttpSessionUtils.SESSION_KEY, account);
      return true;
    } catch (UnAuthenticationException e) {
      return true;
    }
  }
}
