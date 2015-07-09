package crossover.social.media.repository;

import crossover.social.media.domain.Site;
import crossover.social.media.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

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
     * @param user site web master
     * @return list of @EmsSite
     */
    List<Site> findByWebMaster(User user);

    /**
     * Finds @EmsSite given its web master
     *
     * @param cmsUser  site web master
     * @param pageable pageable
     * @return list of @EmsSite
     */
    Page<Site> findByWebMaster(User cmsUser, Pageable pageable);
}
