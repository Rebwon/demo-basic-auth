package com.rebwon.basicauth.domain;

import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @NoArgsConstructor @Getter @Setter
@EqualsAndHashCode @ToString(exclude = { "password" })
public class Account {
  public static final GuestAccount GUEST_ACCOUNT = new GuestAccount();

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;
  private String nickname;
  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<AccountRole> roles;

  public boolean isGuest() {
    return false;
  }

  public Account(String email, String password, String nickname) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
  }

  public boolean matchPassword(String password) {
    return this.password.equals(password);
  }

  public enum AccountRole {
    ADMIN, USER
  }

  private static class GuestAccount extends Account {

    @Override
    public boolean isGuest() {
      return true;
    }
  }
}
