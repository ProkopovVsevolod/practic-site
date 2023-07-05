package external;

import com.finance.jwt.config.token.TokenMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExcludedContextConfig.class)
@AutoConfigureDataJpa
public class ExcludedContextTest {

  @Autowired
  private ApplicationContext context;

  @Test
  void shouldHaveExternalContext() {
    assertNotNull(context.getBean("jwtBasedSecurityConfigurer"));
    assertNotNull(context.getBean("authenticationManager"));
    assertNotNull(context.getBean("refreshSessionRepository"));
    assertNotNull(context.getBean("refreshSessionService"));
    assertNotNull(context.getBean("refreshTokenResolver"));
  }

  @Test
  void shouldInitTokenMetadata() {
    assertDoesNotThrow(TokenMetadata.ACCESS::getSecretKey);
    assertDoesNotThrow(TokenMetadata.REFRESH::getSecretKey);
  }
}
