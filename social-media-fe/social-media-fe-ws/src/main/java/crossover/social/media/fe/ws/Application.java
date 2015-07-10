package crossover.social.media.fe.ws;

import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import crossover.social.media.domain.User;
import crossover.social.media.fe.ws.service.ServiceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;

/**
 * Application
 * Created by bazzoni
 */
@ComponentScan({"crossover.social"})
@EnableAutoConfiguration
public class Application implements CommandLineRunner {
    @Autowired
    private ServiceInitializer initializer;

    @Value("${social.media.admin.username}")
    private String username = "admin";
    @Value("${social.media.admin.password}")
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
            List<Role> byRole = initializer.findByRole(role);
            if (byRole.isEmpty()) {
                Role Role = new Role(role);
                initializer.saveRole(Role);
            }
        }
        final List<User> byUsername = initializer.findByUsername(username);
        if (byUsername.isEmpty()) {
            User admin = new User(username, password, Arrays.asList(initializer.findByRole(RoleName.ROLE_USER).get(0), initializer.findByRole(RoleName.ROLE_ADMIN).get(0)));
            initializer.saveUser(admin);
        }
    }
}

