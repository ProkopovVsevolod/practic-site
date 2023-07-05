package com.finance.jwt.security.authentication;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsExtend extends UserDetails {
  Long getId();
}
