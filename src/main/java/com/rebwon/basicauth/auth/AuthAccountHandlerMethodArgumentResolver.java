package com.rebwon.basicauth.auth;

import com.rebwon.basicauth.common.error.ErrorCode;
import com.rebwon.basicauth.common.exception.UnAuthorizedException;
import com.rebwon.basicauth.domain.Account;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthAccountHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.hasParameterAnnotation(AuthAccount.class);
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter,
      ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
      WebDataBinderFactory webDataBinderFactory) throws Exception {
    Account account = HttpSessionUtils.getAccountFromSession(nativeWebRequest);
    if(!account.isGuest()) {
      return account;
    }

    AuthAccount authAccount = methodParameter.getParameterAnnotation(AuthAccount.class);
    if(authAccount.required()) {
      throw new UnAuthorizedException(ErrorCode.UN_AUTHORIZED);
    }
    return account;
  }
}
