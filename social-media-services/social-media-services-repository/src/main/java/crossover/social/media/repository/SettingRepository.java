package crossover.social.media.repository;

import crossover.social.media.domain.Setting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

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
    List<Setting> findByKey(String key);
}
