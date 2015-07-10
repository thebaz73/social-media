package crossover.social.media.repository;

import crossover.social.media.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * AddressRepository
 * Created by bazzoni
 */
public interface AddressRepository extends MongoRepository<Address, String> {
}
