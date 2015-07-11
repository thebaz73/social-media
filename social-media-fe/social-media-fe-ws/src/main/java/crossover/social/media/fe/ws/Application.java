package crossover.social.media.fe.ws;

import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import crossover.social.media.fe.ws.service.ServiceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

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
    private String adminUsername = "admin";
    @Value("${social.media.admin.password}")
    private String adminPassword = "admin";

    @Value("${social.media.viewer.username}")
    private String viewerUsername = "viewer";
    @Value("${social.media.viewer.password}")
    private String viewerPassword = "viewer";

    @Value("${social.media.author.username}")
    private String authorUsername = "author";
    @Value("${social.media.author.password}")
    private String authorPassword = "author";

    @Value("${social.media.manager.username}")
    private String managerUsername = "manager";
    @Value("${social.media.manager.password}")
    private String managerPassword = "manager";

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
        initializer.addCompany();

        for (RoleName role : RoleName.ALL) {
            List<Role> byRole = initializer.findByRole(role);
            if (byRole.isEmpty()) {
                Role Role = new Role(role);
                initializer.saveRole(Role);
            }
        }
        initializer.addUser(adminUsername, adminPassword, RoleName.ROLE_ADMIN);
        initializer.addUser(viewerUsername, viewerPassword, RoleName.ROLE_VIEWER);
        initializer.addUser(authorUsername, authorPassword, RoleName.ROLE_AUTHOR);
        initializer.addUser(managerUsername, managerPassword, RoleName.ROLE_MANAGER);

    }
}

