package crossover.social.media.repository;

import crossover.social.media.domain.Setting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TagRepository
 * Created by thebaz
 */
public interface SettingRepository extends MongoRepository<Setting, String> {
    /**
     * Finds @Setting given its key
     *
     * @param key key key
     * @return page of @Setting
     */
    Page<Setting> findByKey(String key, Pageable pageable);

    /**
     * Finds @Setting given its filter
     *
     * @param filter filter
     * @return page of @Setting
     */
    Page<Setting> findByUserId(String filter, Pageable pageable);

    /**
     * Finds @Setting given its address
     *
     * @param key    key key
     * @param filter filter
     * @return list of @Setting
     */
    Page<Setting> findByKeyAndUserId(String key, String filter, Pageable pageable);
}
