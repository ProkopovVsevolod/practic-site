package com.finance.infrastructure.repository;

import com.finance.domain.Role;
import com.finance.domain.User;
import com.finance.infrastructure.log.LoggerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(LoggerConfig.class)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void shouldGiveData() {
    List<User> all = userRepository.findAll();
    assertNotNull(all);
  }

  @Test
  void shouldSaveUser() {
    User expected = User.builder()
      .name("test")
      .email("test@mail.com")
      .password("testpassword")
      .role(Role.USER)
      .build();

    userRepository.save(expected);
    User actual = userRepository.findAll().stream().findAny().orElse(null);

    assertEquals(expected, actual);
  }
}