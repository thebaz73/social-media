package crossover.social.media.repository;

import crossover.social.media.domain.AssetType;
import crossover.social.media.domain.SocialAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * CmsPageRepository
 * Created by thebaz
 */
public interface AssetRepository extends MongoRepository<SocialAsset, String> {

    /**
     * Load all site assets
     *
     * @param siteId site id
     * @return page of asset
     */
    Page<SocialAsset> findBySiteId(String siteId, Pageable pageable);

    /**
     * Load a site assets by uri
     *
     * @param siteId site id
     * @param uri    asset uri
     * @return list of asset
     */
    List<SocialAsset> findBySiteIdAndUri(String siteId, String uri);

    /**
     * Load a site asset by type
     *
     * @param siteId site  id
     * @param type   assert type
     * @return page of asset
     */
    Page<SocialAsset> findBySiteIdAndType(String siteId, AssetType type, Pageable pageable);
}
