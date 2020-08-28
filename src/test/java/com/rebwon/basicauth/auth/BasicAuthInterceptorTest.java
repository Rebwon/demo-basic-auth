package com.rebwon.basicauth.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.rebwon.basicauth.domain.Account;
import com.rebwon.basicauth.domain.AccountService;
import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

@ExtendWith(MockitoExtension.class)
class BasicAuthInterceptorTest {
  @Mock private AccountService accountService;
  @InjectMocks private BasicAuthInterceptor basicAuthInterceptor;

  @Test
  @DisplayName("로그인 성공 검증")
  void preHandle_Login_Success() throws Exception {
    String email = "rebwon@gmail.com";
    String password = "123456789";
    MockHttpServletRequest request = basicAuthHttpRequest(email, password);
    Account account = new Account(email, password, "rebwon");
    when(accountService.login(email, password)).thenReturn(account);

    basicAuthInterceptor.preHandle(request, null, null);
    assertThat(request.getSession().getAttribute(HttpSessionUtils.SESSION_KEY)).isEqualTo(account);
  }

  private MockHttpServletRequest basicAuthHttpRequest(String email, String password) {
    String encodedBasicAuth = Base64.getEncoder()
        .encodeToString(String.format("%s:%s", email, password).getBytes());
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addHeader("Authorization", "Basic " + encodedBasicAuth);
    return request;
  }
}