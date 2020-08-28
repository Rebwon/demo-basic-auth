package com.rebwon.basicauth.common.exception;

import com.rebwon.basicauth.common.error.ErrorCode;

public class BusinessException extends RuntimeException {
  private ErrorCode errorCode;

  public BusinessException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
