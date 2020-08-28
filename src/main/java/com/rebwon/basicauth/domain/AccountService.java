package com.rebwon.basicauth.domain;

import com.rebwon.basicauth.common.error.ErrorCode;
import com.rebwon.basicauth.common.exception.UnAuthenticationException;
import com.rebwon.basicauth.web.payload.SignUpPayload;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;

  @Transactional(readOnly = true)
  public Account login(String email, String password) {
    Optional<Account> optionalAccount = accountRepository.findByEmail(email);
    if(!optionalAccount.isPresent()) {
      throw new UnAuthenticationException(ErrorCode.UN_AUTHORIZED);
    }

    Account account = optionalAccount.get();
    if(!account.matchPassword(password)) {
      throw new UnAuthenticationException(ErrorCode.UN_AUTHORIZED);
    }

    return account;
  }

  public Account register(SignUpPayload payload) {
    return accountRepository.save(new Account(payload.getEmail(),
        payload.getPassword(), payload.getNickname()));
  }
}
