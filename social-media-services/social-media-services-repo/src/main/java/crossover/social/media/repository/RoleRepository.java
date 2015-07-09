package crossover.social.media.repository;

import crossover.social.media.domain.Role;
import crossover.social.media.domain.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * RoleRepository
 * Created by bazzoni on 09/07/2015.
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    /**
     * Finds @Role given its role
     *
     * @param role role name
     * @return list of @Role
     */
    List<Role> findByRole(RoleName role);
}
