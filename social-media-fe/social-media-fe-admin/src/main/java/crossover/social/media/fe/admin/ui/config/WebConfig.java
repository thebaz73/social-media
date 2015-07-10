package crossover.social.media.fe.admin.ui.config;

import crossover.social.media.security.ReadOnlyUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Collections;

/**
 * WebConfig
 * Created by bazzoni
 */
@Configuration
@EnableWebMvc
@ComponentScan({"crossover.social.media.repository"})
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String[] RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};
    @Autowired
    private ReadOnlyUserManager userManager;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/schema/**")) {
            registry.addResourceHandler("/schema/**").addResourceLocations(
                    "classpath:/schema/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
        }
    }

    @Bean
    public SimpleUrlHandlerMapping myFaviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("/favicon.ico", myFaviconRequestHandler()));
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler myFaviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        requestHandler.setLocations(Collections.singletonList(new ClassPathResource("/")));
        requestHandler.setCacheSeconds(0);
        return requestHandler;
    }

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Bean
    public AuthenticationSecurity authenticationSecurity() {
        return new AuthenticationSecurity();
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected class ApplicationSecurity extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/webjars/**", "/dist/**").permitAll()
                    .anyRequest().authenticated();
            http
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .and()
                    .rememberMe();
        }
    }

    @Order(Ordered.HIGHEST_PRECEDENCE + 10)
    protected class AuthenticationSecurity extends
            GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userManager);//.passwordEncoder(userManager.getEncoder());
        }
    }
}
