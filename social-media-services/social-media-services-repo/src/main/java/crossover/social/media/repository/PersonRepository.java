package crossover.social.media.repository;

import crossover.social.media.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * PersonRepository
 * Created by bazzoni on 09/07/2015.
 */
public interface PersonRepository extends MongoRepository<Person, String> {
    /**
     * Find a @Person given its user id
     *
     * @param userId user id
     * @return @Person
     */
    List<Person> findByUserId(@Param(value = "userId") String userId);
}
