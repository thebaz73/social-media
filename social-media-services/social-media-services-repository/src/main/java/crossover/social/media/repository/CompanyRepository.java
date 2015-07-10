package crossover.social.media.repository;

import crossover.social.media.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CompanyRepository
 * Created by bazzoni
 */
public interface CompanyRepository extends MongoRepository<Company, String> {
}
