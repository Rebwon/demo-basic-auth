package com.rebwon.basicauth.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {
  BAD_REQUEST(400, "C001", "Bad Request"),
  UN_AUTHORIZED(401, "C002", "UnAuthorized"),
  INVALID_INPUT(403, "C003", "Invalid input");

  private int status;
  private String code;
  private String message;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
