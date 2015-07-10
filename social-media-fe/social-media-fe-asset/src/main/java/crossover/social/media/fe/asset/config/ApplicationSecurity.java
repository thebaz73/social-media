package crossover.social.media.fe.asset.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * ApplicationSecurity
 * Created by bazzoni
 */
@Configuration
@ImportResource({"classpath:webSecurityConfig.xml"})
@ComponentScan({"crossover.social.media.security"})
public class ApplicationSecurity {
}
