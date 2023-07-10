package com.finance.user.service;

import com.finance.user.domain.User;
import com.finance.user.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findUserByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
  }

  @Override
  public void saveAllIfEmpty(List<User> userList) {
    if (userRepository.count() == 0) {
      userRepository.saveAll(userList);
    }
  }

  @Override
  public User save(User user) {
    if (userRepository.findUserByEmail(user.getUsername()).isEmpty()) {
      return userRepository.save(user);
    }
    throw new IllegalArgumentException("User with email " + user.getUsername() + " is already present");
  }
}
