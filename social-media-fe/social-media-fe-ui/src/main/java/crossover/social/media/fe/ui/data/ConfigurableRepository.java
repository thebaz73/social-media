package crossover.social.media.fe.ui.data;

import java.util.Properties;

/**
 * ConfigurableRepository
 * Created by bazzoni on 12/07/2015.
 */
public interface ConfigurableRepository {

    /**
     * Set all CacheRepository with Web services uri
     *
     * @param properties properties
     */
    void setProperties(Properties properties);

    /**
     * Initializes all CacheRepository with Web services uri
     */
    void initialize();
}
