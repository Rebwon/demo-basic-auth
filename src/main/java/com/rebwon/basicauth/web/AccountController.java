package com.rebwon.basicauth.web;

import com.rebwon.basicauth.auth.AuthAccount;
import com.rebwon.basicauth.domain.Account;
import com.rebwon.basicauth.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
  @Autowired
  private AccountRepository accountRepository;

  @GetMapping("/{id}")
  public ResponseEntity findOne(@PathVariable Long id, @AuthAccount Account auth) {
    Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    return ResponseEntity.ok(account);
  }
}
