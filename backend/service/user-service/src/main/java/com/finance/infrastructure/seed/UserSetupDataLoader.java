package com.finance.infrastructure.seed;

import com.finance.domain.User;
import com.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserSetupDataLoader {
  private boolean beenOnceCalled = false;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @Autowired
  public UserSetupDataLoader(PasswordEncoder passwordEncoder, UserService userService) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
  }

  @EventListener(ContextRefreshedEvent.class)
  public void initData() {
    if (!beenOnceCalled) {
      beenOnceCalled = true;
      List<User> users = Arrays.stream(SetupUser.values())
        .map(setupUser -> User.builder()
          .name(setupUser.getName())
          .email(setupUser.getEmail())
          .password(passwordEncoder.encode(setupUser.getPassword()))
          .role(setupUser.getRole())
          .build())
        .toList();
      userService.saveAllIfEmpty(users);
    }
  }
}
