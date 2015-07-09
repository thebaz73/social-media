package crossover.social.media.repository;

import crossover.social.media.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * CmsPageRepository
 * Created by thebaz
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    /**
     * Finds list of @Comment given its contentId
     *
     * @param siteId site id
     * @return list of @Comment
     */
    List<Comment> findBySiteId(String siteId);

    /**
     * Finds list of @Comment given its siteId
     *
     * @param siteId   site id
     * @param approved approved flag
     * @return list of @Comment
     */
    List<Comment> findBySiteIdAndApproved(String siteId, boolean approved);

    /**
     * Finds list of @Comment given its contentId
     *
     * @param siteId   site id
     * @param pageable page info
     * @return list of @Comment
     */
    Page<Comment> findBySiteId(String siteId, Pageable pageable);

    /**
     * Finds list of @Comment given its siteId
     *
     * @param siteId   site id
     * @param approved approved flag
     * @param pageable page info
     * @return list of @Comment
     */
    Page<Comment> findBySiteIdAndApproved(String siteId, boolean approved, Pageable pageable);

    /**
     * Deletes @CmsComments given its site id
     *
     * @param siteId site id
     */
    List<Comment> deleteBySiteId(String siteId);

    /**
     * Count  total site comments
     *
     * @param siteId site id
     * @return comment count
     */
    int countBySiteId(String siteId);

    /**
     * Finds list of @Comment given its contentId
     *
     * @param contentId content id
     * @return list of @Comment
     */
    List<Comment> findByContentId(String contentId);

    /**
     * Finds list of @Comment given its contentId
     *
     * @param contentId content id
     * @param approved  approved flag
     * @return list of @Comment
     */
    List<Comment> findByContentIdAndApproved(String contentId, boolean approved);

    /**
     * Finds page of @Comment given its contentId
     *
     * @param contentId content id
     * @param pageable  page info
     * @return list of @Comment
     */
    Page<Comment> findByContentId(String contentId, Pageable pageable);

    /**
     * Finds page of @Comment given its contentId
     *
     * @param contentId content id
     * @param approved  approved flag
     * @param pageable  page info
     * @return list of @Comment
     */
    Page<Comment> findByContentIdAndApproved(String contentId, boolean approved, Pageable pageable);

    /**
     * Deletes @CmsComments given its site id
     *
     * @param contentId content id
     */
    List<Comment> deleteByContentId(String contentId);
}
