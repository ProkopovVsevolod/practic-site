package external;

import com.finance.user.infrastructure.repository.UserRepository;
import com.finance.user.config.SecurityConfig;
import com.finance.user.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.mock;

@Configuration
@EnableWebMvc
@Import(SecurityConfig.class)
@PropertySource("classpath:test-jwt.properties")
@EnableAutoConfiguration
public class ExcludedContextConfig {
  @Bean
  UserServiceImpl userService() {
    return new UserServiceImpl(mock(UserRepository.class));
  }
}
