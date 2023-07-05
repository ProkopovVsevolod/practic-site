package com.finance.jwt.resolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;

@Value
public class RequestMetadata {
  String remoteAddress;
  String userAgent;

  public RequestMetadata(HttpServletRequest request) {
    this.remoteAddress = request.getRemoteAddr();
    this.userAgent = request.getHeader("user-agent");
  }
}