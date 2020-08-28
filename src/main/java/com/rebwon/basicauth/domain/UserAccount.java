package com.rebwon.basicauth.domain;

import com.rebwon.basicauth.domain.Account.AccountRole;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class UserAccount extends User {
  private final Account account;

  public UserAccount(Account account) {
    super(account.getEmail(), account.getPassword(), authorities(account.getRoles()));
    this.account = account;
  }

  private static Collection<? extends GrantedAuthority> authorities(Set<AccountRole> roles) {
    return roles.stream()
        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
        .collect(Collectors.toSet());
  }
}
