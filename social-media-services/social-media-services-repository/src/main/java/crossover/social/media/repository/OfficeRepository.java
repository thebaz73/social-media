package crossover.social.media.repository;

import crossover.social.media.domain.Office;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * OfficeRepository
 * Created by bazzoni
 */
public interface OfficeRepository extends MongoRepository<Office, String> {
}
