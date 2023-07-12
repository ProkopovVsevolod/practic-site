package external;

import com.finance.budget.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(SecurityConfig.class)
@PropertySource("classpath:test-jwt.properties")
public class ExcludedContextConfig {
}
