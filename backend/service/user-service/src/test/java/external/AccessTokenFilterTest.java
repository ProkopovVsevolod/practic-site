package external;

import com.finance.jwt.security.filter.AccessTokenFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ExcludedContextConfig.class)
@AutoConfigureDataJpa
public class AccessTokenFilterTest {
  @Value("${token}")
  private String testToken;

  @MockBean
  private AuthenticationManager authenticationManager;

  @Autowired
  private SecurityFilterChain securityFilterChain;

  private Filter tokenFilter;

  @BeforeEach
  void setUp() {
    tokenFilter = securityFilterChain.getFilters().stream()
      .filter(filter -> filter.getClass().equals(AccessTokenFilter.class))
      .findAny()
      .orElseThrow(() -> new RuntimeException("AccetTokenFilter not present in SecurityFilterChain"));
  }

  @Test
  void shouldPutAuthenticationToSecurityContextHolder() throws ServletException, IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    FilterChain filterChain = mock(FilterChain.class);

    doReturn("Bearer " + testToken).when(request).getHeader(anyString());
    doAnswer(invoc -> {
      Authentication auth = invoc.getArgument(0, Authentication.class);
      auth.setAuthenticated(true);
      return auth;
    }).when(authenticationManager).authenticate(any(Authentication.class));

    tokenFilter.doFilter(request, response, filterChain);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    assertNotNull(authentication);
    assertTrue(authentication.isAuthenticated());
  }
}
