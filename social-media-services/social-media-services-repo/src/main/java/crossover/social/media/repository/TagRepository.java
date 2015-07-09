package crossover.social.media.repository;

import crossover.social.media.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * TagRepository
 * Created by thebaz
 */
public interface TagRepository extends MongoRepository<Tag, String> {
    /**
     * Finds @Tag given its address
     *
     * @param siteId site id
     * @param uri    tag uri
     * @return list of @Tag
     */
    List<Tag> findBySiteIdAndUri(String siteId, String uri);

    /**
     * Finds @Tag given its address
     *
     * @param siteId site id
     * @return list of @Tag
     */
    List<Tag> findBySiteId(String siteId);
}
