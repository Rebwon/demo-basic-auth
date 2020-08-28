package com.rebwon.basicauth.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import com.rebwon.basicauth.common.exception.UnAuthorizedException;
import com.rebwon.basicauth.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
class AuthAccountHandlerMethodArgumentResolverTest {
  @Mock private MethodParameter parameter;
  @Mock private NativeWebRequest request;
  @Mock private AuthAccount annotedAuthAccount;
  private AuthAccountHandlerMethodArgumentResolver authAccountHandlerMethodArgumentResolver;

  @BeforeEach
  void setUp() {
    authAccountHandlerMethodArgumentResolver = new AuthAccountHandlerMethodArgumentResolver();
  }

  @Test
  @DisplayName("로그인 사용자 요청 검증")
  void authAccount_specification() throws Exception {
    Account account = new Account("rebwon@gmail.com", "123456789", "rebwon");
    when(request.getAttribute(HttpSessionUtils.SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(account);

    Account loginAccount = (Account) authAccountHandlerMethodArgumentResolver
        .resolveArgument(parameter, null, request, null);

    assertThat(loginAccount).isEqualTo(account);
  }

  @Test
  @DisplayName("로그인 사용자 요청이 아닌 요청 검증")
  void authAccount_required_guest() throws Exception {
    when(annotedAuthAccount.required()).thenReturn(true);
    when(parameter.getParameterAnnotation(AuthAccount.class)).thenReturn(annotedAuthAccount);
    when(request.getAttribute(HttpSessionUtils.SESSION_KEY, WebRequest.SCOPE_SESSION))
        .thenReturn(Account.GUEST_ACCOUNT);

    assertThatExceptionOfType(UnAuthorizedException.class)
        .isThrownBy(() -> authAccountHandlerMethodArgumentResolver.resolveArgument(parameter, null, request, null));
  }

  @Test
  @DisplayName("Guest 사용자 요청 검증")
  void authAccount_not_required_guest() throws Exception {
    when(annotedAuthAccount.required()).thenReturn(false);
    when(parameter.getParameterAnnotation(AuthAccount.class)).thenReturn(annotedAuthAccount);
    when(request.getAttribute(HttpSessionUtils.SESSION_KEY, WebRequest.SCOPE_SESSION))
        .thenReturn(Account.GUEST_ACCOUNT);

    Account loginAccount = (Account) authAccountHandlerMethodArgumentResolver
        .resolveArgument(parameter, null, request, null);
    assertThat(loginAccount).isEqualTo(Account.GUEST_ACCOUNT);
  }

  @Test
  void supportsParameter_false() {
    when(parameter.hasParameterAnnotation(AuthAccount.class)).thenReturn(false);

    assertThat(authAccountHandlerMethodArgumentResolver.supportsParameter(parameter)).isEqualTo(false);
  }

  @Test
  void supportsParameter_true() {
    when(parameter.hasParameterAnnotation(AuthAccount.class)).thenReturn(true);

    assertThat(authAccountHandlerMethodArgumentResolver.supportsParameter(parameter)).isEqualTo(true);
  }
}