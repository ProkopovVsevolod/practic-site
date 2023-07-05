package external;

import com.finance.jwt.security.configurer.SecurityConfigurer;
import com.finance.jwt.security.filter.AccessTokenFilter;
import jakarta.servlet.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExcludedContextConfig.class)
@AutoConfigureDataJpa
public class SecurityChainTest {
  @Autowired
  private SecurityConfigurer configurer;

  @Autowired
  private HttpSecurity httpSecurity;

  @Test
  void shouldConfigureSecurityChain() throws Exception {
    SecurityFilterChain chain = configurer.createChain(httpSecurity, registry -> {});

    assertNotNull(chain);
  }


  @Test
  void shouldPresentAccessTokenFilterInChain() throws Exception {
    SecurityFilterChain chain = configurer.createChain(httpSecurity, registry -> {});
    Filter tokenFilter = chain.getFilters().stream()
      .filter(filter -> filter.getClass().equals(AccessTokenFilter.class))
      .findAny().orElseThrow(() -> new RuntimeException("AccessTokenFilter not present in context"));

    assertNotNull(tokenFilter);
  }
}
