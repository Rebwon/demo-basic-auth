package com.rebwon.basicauth.common.exception;

import com.rebwon.basicauth.common.error.ErrorCode;

public class UnAuthorizedException extends BusinessException {

  public UnAuthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }
}
