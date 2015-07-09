package crossover.social.media.repository;

import crossover.social.media.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CompanyRepository
 * Created by bazzoni on 09/07/2015.
 */
public interface CompanyRepository extends MongoRepository<Company, String> {
}
