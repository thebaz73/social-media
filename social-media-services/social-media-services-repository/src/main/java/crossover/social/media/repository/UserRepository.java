package crossover.social.media.repository;

import crossover.social.media.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UserRepository
 * Created by bazzoni
 */
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Finds @EmsUser given its username
     *
     * @param username user name
     * @return list of @EmsUser
     */
    List<User> findByUsername(@Param(value = "username") String username);
}
