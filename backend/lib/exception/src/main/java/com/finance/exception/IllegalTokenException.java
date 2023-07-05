package com.finance.exception;

public class IllegalTokenException extends IllegalArgumentException {
  public IllegalTokenException(String message, String token) {
    super(message + ": " + token.substring(0, 10) + "..." + token.substring(token.length() - 10));
  }

  public IllegalTokenException() {
  }

  public IllegalTokenException(String s) {
    super(s);
  }
}
