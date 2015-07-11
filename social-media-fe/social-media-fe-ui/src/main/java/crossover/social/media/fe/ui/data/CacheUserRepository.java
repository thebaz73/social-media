package crossover.social.media.fe.ui.data;

import crossover.social.media.domain.Person;
import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import crossover.social.media.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CacheUserRepository
 * Created by bazzoni on 10/07/2015.
 */
@Component
public class CacheUserRepository {

    @Cacheable("user")
    public List<User> findByUsername(String username) {
        final User user = new User(username, "q1w2e3r4", Arrays.asList(new Role(RoleName.ROLE_MANAGER), new Role(RoleName.ROLE_MANAGER)));
        return Collections.singletonList(user);
    }

    public Person findPerson(String authorId) {
        final Person person = new Person();
        person.setFirstName("Marco");
        person.setLastName("Bazzoni");
        return person;
    }
}
