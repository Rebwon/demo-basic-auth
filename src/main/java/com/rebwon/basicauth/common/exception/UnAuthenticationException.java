package com.rebwon.basicauth.common.exception;

import com.rebwon.basicauth.common.error.ErrorCode;

public class UnAuthenticationException extends BusinessException {

  public UnAuthenticationException(ErrorCode errorCode) {
    super(errorCode);
  }
}
