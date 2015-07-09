package crossover.social.media.repository;

import crossover.social.media.domain.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * SiteRepository
 * Created by thebaz
 */
public interface SiteRepository extends MongoRepository<Site, String> {
    /**
     * Finds @EmsSite given its address
     *
     * @param address site address
     * @return list of @EmsSite
     */
    List<Site> findByAddress(String address);
    /**
     * Finds @EmsSite given its web master
     *
     * @param userId  site web master
     * @param pageable pageable
     * @return list of @EmsSite
     */
    Page<Site> findByWebMaster(@Param(value = "userId") String userId, Pageable pageable);
}
