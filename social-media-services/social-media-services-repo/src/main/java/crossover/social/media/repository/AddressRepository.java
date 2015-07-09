package crossover.social.media.repository;

import crossover.social.media.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * AddressRepository
 * Created by bazzoni on 09/07/2015.
 */
public interface AddressRepository extends MongoRepository<Address, String> {
}
