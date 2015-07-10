package crossover.social.media.fe.signon.business;

import crossover.social.media.domain.*;
import crossover.social.media.fe.signon.web.domain.UserData;
import crossover.social.media.repository.PersonRepository;
import crossover.social.media.repository.RoleRepository;
import crossover.social.media.repository.SiteRepository;
import crossover.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RegistrationManager
 * Created by bazzoni on 10/07/2015.
 */
@Component
public class RegistrationManager {
    private Role roleUser;
    private Role roleAdmin;
    private Role roleManager;
    private Role roleAuthor;
    private Role roleViewer;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SiteRepository siteRepository;

    public static UserType getUserType(String type) throws RegistrationException {
        if (type.equalsIgnoreCase("ADMIN")) return UserType.ADMIN;
        if (type.equalsIgnoreCase("MANAGER")) return UserType.MANAGER;
        if (type.equalsIgnoreCase("AUTHOR")) return UserType.AUTHOR;
        if (type.equalsIgnoreCase("VIEWER")) return UserType.VIEWER;

        throw new RegistrationException("Wrong user type");
    }

    public void initialize() {
        for (RoleName role : RoleName.ALL) {
            List<Role> byRole = roleRepository.findByRole(role);
            if (byRole.isEmpty()) {
                Role cmsRole = new Role(role.getName());
                roleRepository.save(cmsRole);
            }
        }
        roleUser = roleRepository.findByRole(RoleName.ROLE_USER).get(0);
        roleAdmin = roleRepository.findByRole(RoleName.ROLE_ADMIN).get(0);
        roleManager = roleRepository.findByRole(RoleName.ROLE_MANAGER).get(0);
        roleAuthor = roleRepository.findByRole(RoleName.ROLE_AUTHOR).get(0);
        roleViewer = roleRepository.findByRole(RoleName.ROLE_VIEWER).get(0);
    }

    public void createUser(UserType userType, UserData userData) throws RegistrationException {
        if (!userRepository.findByUsername(userData.getUsername()).isEmpty()) {
            throw new RegistrationException("Username already registered");
        }

        List<Role> roles = doGuessRoles(userType);
        User user = new User(userData.getUsername(), userData.getPassword(), roles);
        user.setCreationDate(new Date());
        userRepository.save(user);

        Person person = new Person();
        person.setFirstName(userData.getFirstName());
        person.setLastName(userData.getLastName());
        person.setUser(user);
        person.getEmails().add(new Email(userData.getEmail(), true));

        personRepository.save(person);
    }

    private List<Role> doGuessRoles(UserType userType) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        switch (userType) {
            case ADMIN:
                roles.add(roleAdmin);
                break;
            case MANAGER:
                roles.add(roleManager);
                break;
            case AUTHOR:
                roles.add(roleAuthor);
                break;
            case VIEWER:
                roles.add(roleViewer);
                break;
        }

        return roles;
    }

    public enum UserType {
        ADMIN, MANAGER, AUTHOR, VIEWER
    }
}
