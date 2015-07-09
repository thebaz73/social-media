package crossover.social.media.repository;

import crossover.social.media.domain.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * ContentRepository
 * Created by thebaz
 */
public interface ContentRepository extends MongoRepository<Content, String> {
    /**
     * Finds @Content given its site id and uri
     *
     * @param siteId site id
     * @param uri    post uri
     * @return list of @Content
     */
    List<Content> findBySiteIdAndUri(String siteId, String uri);

    /**
     * Finds @Content given its site id and uri
     *
     * @param siteId   site id
     * @param uri      post uri
     * @param pageable page info
     * @return page of @Content
     */
    Page<Content> findBySiteIdAndUri(String siteId, String uri, Pageable pageable);

    /**
     * Finds @CmsContents given its site id
     *
     * @param siteId site id
     * @return list of @Content
     */
    List<Content> findBySiteId(String siteId);

    /**
     * Finds @CmsContents given its site id
     *
     * @param siteId    site id
     * @param published published
     * @param pageable  page info
     * @return page of @Content
     */
    Page<Content> findBySiteIdAndPublished(String siteId, boolean published, Pageable pageable);

    /**
     * Counts @CmsContents given its site id
     *
     * @param siteId site id
     * @return number of @Content
     */
    int countBySiteId(String siteId);

    /**
     * Deletes @CmsContents given its site id
     *
     * @param siteId site id
     */
    List<Content> deleteBySiteId(String siteId);
}
