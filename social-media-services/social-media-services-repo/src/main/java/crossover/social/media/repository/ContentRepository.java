package crossover.social.media.repository;

import crossover.social.media.domain.SocialContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * ContentRepository
 * Created by thebaz
 */
public interface ContentRepository extends MongoRepository<SocialContent, String> {

    /**
     * Finds @SocialContent given its site id and uri
     *
     * @param siteId   site id
     * @param uri      post uri
     * @param pageable page info
     * @return page of @SocialContent
     */
    Page<SocialContent> findBySiteIdAndUri(String siteId, String uri, Pageable pageable);
    /**
     * Finds @CmsContents given its site id
     *
     * @param siteId    site id
     * @param published published
     * @param pageable  page info
     * @return page of @SocialContent
     */
    Page<SocialContent> findBySiteIdAndPublished(String siteId, boolean published, Pageable pageable);

    /**
     * Counts @CmsContents given its site id
     *
     * @param siteId site id
     * @return number of @SocialContent
     */
    int countBySiteId(String siteId);

    /**
     * Deletes @CmsContents given its site id
     *
     * @param siteId site id
     */
    List<SocialContent> deleteBySiteId(String siteId);
}
