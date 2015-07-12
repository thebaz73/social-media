package crossover.social.media.fe.ui.business;

import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import crossover.social.media.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * UserRepository
 * Created by bazzoni on 12/07/2015.
 */
@Component
public class UserRepository {
    public List<User> findByUsername(String username) {
        User user = new User("admin", "q1w2e34r", Arrays.asList(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_ADMIN)));
        return Collections.singletonList(user);
    }
}
