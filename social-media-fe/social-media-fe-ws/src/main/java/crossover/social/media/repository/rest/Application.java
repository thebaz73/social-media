package crossover.social.media.repository.rest;

import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import crossover.social.media.domain.User;
import crossover.social.media.repository.RoleRepository;
import crossover.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Application
 * Created by bazzoni on 09/07/2015.
 */
@Configuration
@ComponentScan({"crossover.social.media.repository"})
@EnableAutoConfiguration
public class Application implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("social.media.admin.usernname")
    private String username = "admin";
    @Value("social.media.admin.usernname")
    private String password = "admin";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        for (RoleName role : RoleName.ALL) {
            List<Role> byRole = roleRepository.findByRole(role);
            if (byRole.isEmpty()) {
                Role Role = new Role(role);
                roleRepository.save(Role);
            }
        }
        final List<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            User admin = new User(username, password, Arrays.asList(roleRepository.findByRole(RoleName.ROLE_USER).get(0), roleRepository.findByRole(RoleName.ROLE_ADMIN).get(0)));
            userRepository.save(admin);
        }
    }
}

