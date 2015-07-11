package crossover.social.media.repository;

import crossover.social.media.domain.CustomerData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CustomerDataRepository
 * Created by bazzoni on 11/07/2015.
 */
public interface CustomerDataRepository extends MongoRepository<CustomerData, String> {
    /**
     * Finds @ActivityLog given its user Id
     *
     * @param companyId user id
     * @param pageable  pageable
     * @return page of @ActivityLog
     */
    Page<CustomerData> findByCompanyId(String companyId, Pageable pageable);
}
