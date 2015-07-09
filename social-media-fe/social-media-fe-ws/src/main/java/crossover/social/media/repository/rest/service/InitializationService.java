package crossover.social.media.repository.rest.service;

import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import crossover.social.media.domain.User;
import crossover.social.media.repository.RoleRepository;
import crossover.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * InitializeService
 * Created by bazzoni
 */
@Service
public class InitializationService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Role> findByRole(RoleName role) {
        return roleRepository.findByRole(role);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
