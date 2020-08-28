package com.rebwon.basicauth.web;

import com.rebwon.basicauth.domain.Account;
import com.rebwon.basicauth.domain.AccountService;
import com.rebwon.basicauth.web.payload.SignUpPayload;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AccountService accountService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid SignUpPayload payload) {
    Account account = accountService.register(payload);
    return ResponseEntity.ok(account);
  }
}
