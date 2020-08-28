package com.rebwon.basicauth.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rebwon.basicauth.domain.Account;
import com.rebwon.basicauth.domain.Account.AccountRole;
import com.rebwon.basicauth.domain.AccountRepository;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    Account account = new Account();
    account.setEmail("rebwon@gmail.com");
    account.setPassword(passwordEncoder.encode("123456789"));
    account.setRoles(Set.of(AccountRole.USER));
    accountRepository.save(account);
  }

  @Test
  @DisplayName("회원 정보 조회")
  void findOne() throws Exception {
    mockMvc.perform(get("/api/accounts/1")
        .with(httpBasic("rebwon@gmail.com", "123456789"))
    )
        .andDo(print())
        .andExpect(status().isOk());
  }
}