package com.rebwon.basicauth.support;

import static org.junit.jupiter.api.Assertions.*;

import com.rebwon.basicauth.web.AuthController;
import com.rebwon.basicauth.web.payload.SignInPayload;
import com.rebwon.basicauth.web.payload.SignUpPayload;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

@Disabled
@SpringBootTest
class LoggingAspectTest {

  @Autowired
  private AuthController authController;

  @Test
  @DisplayName("로깅 테스트")
  void logging() {
    SignUpPayload payload = new SignUpPayload("kitty@gmail.com", "123456789", "kitty");
    authController.register(payload);
  }
}