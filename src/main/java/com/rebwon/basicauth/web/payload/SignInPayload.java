package com.rebwon.basicauth.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInPayload {
  @NotBlank
  @Email
  private String email;
  @NotBlank
  private String password;
}
