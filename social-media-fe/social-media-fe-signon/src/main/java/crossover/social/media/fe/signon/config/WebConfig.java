package crossover.social.media.fe.signon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebConfig
 * Created by bazzoni
 */
@Configuration
@EnableWebMvc
@ComponentScan({"crossover.social.media.repository"})
public class WebConfig extends WebMvcConfigurerAdapter {
}
