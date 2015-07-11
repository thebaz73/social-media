package crossover.social.media.repository;

import crossover.social.media.domain.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ActivityLogRepository
 * Created by bazzoni on 11/07/2015.
 */
public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
    /**
     * Finds @ActivityLog given its user Id
     *
     * @param userId   user id
     * @param pageable pageable
     * @return page of @ActivityLog
     */
    Page<ActivityLog> findByUserId(String userId, Pageable pageable);
}
