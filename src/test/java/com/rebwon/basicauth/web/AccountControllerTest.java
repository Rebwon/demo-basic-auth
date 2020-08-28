package com.rebwon.basicauth.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("회원 정보 조회")
  void findOne() throws Exception {
    String encodedBasicAuth = Base64.getEncoder()
        .encodeToString(String.format("%s:%s", "rebwon@gmail.com", "123456789").getBytes());

    mockMvc.perform(get("/api/accounts/1")
        .header("Authorization", "Basic " + encodedBasicAuth)
    )
        .andDo(print())
        .andExpect(status().isOk());
  }
}