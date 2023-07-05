package com.finance.jwt.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;

public interface UsernamePasswordAuthentication extends Authentication, CredentialsContainer {
  Long getUserId();
}
