package com.rebwon.basicauth.domain;

import com.rebwon.basicauth.web.payload.SignUpPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
  private final AccountRepository accountRepository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Account account = accountRepository.findByEmail(email)
        .orElseThrow(IllegalArgumentException::new);
    return new UserAccount(account);
  }

  public Account register(SignUpPayload payload) {
    return accountRepository.save(new Account(payload.getEmail(),
        payload.getPassword(), payload.getNickname()));
  }
}
