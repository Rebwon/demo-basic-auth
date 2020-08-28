package com.rebwon.basicauth.auth;

import com.rebwon.basicauth.domain.Account;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

public class HttpSessionUtils {
  public static final String SESSION_KEY = "loginKeyValue";

  private HttpSessionUtils() {}

  public static boolean isLogin(NativeWebRequest webRequest) {
    Object logined = webRequest.getAttribute(SESSION_KEY, WebRequest.SCOPE_SESSION);
    return logined != null;
  }

  public static Account getAccountFromSession(NativeWebRequest webRequest) {
    if(!isLogin(webRequest))
      return Account.GUEST_ACCOUNT;
    return (Account) webRequest.getAttribute(SESSION_KEY, RequestAttributes.SCOPE_SESSION);
  }

  public static boolean isLogin(HttpSession session) {
    Object sessionedAccount = session.getAttribute(SESSION_KEY);
    if(session == null)
      return false;
    return true;
  }

  public static Account getAccountFromSession(HttpSession session) {
    if(!isLogin(session))
      return null;
    return (Account) session.getAttribute(SESSION_KEY);
  }
}
