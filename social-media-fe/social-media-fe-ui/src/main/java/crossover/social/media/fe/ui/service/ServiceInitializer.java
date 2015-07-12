package crossover.social.media.fe.ui.service;

import crossover.social.media.fe.ui.data.ConfigurableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

/**
 * ServiceInitializer
 * Created by bazzoni on 12/07/2015.
 */
@Service
public class ServiceInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected
    ApplicationContext applicationContext;
    protected Map<String, ConfigurableRepository> configurableRepositoryMap;

    /**
     * Initializes all CacheRepository with Web services uri
     *
     * @param properties properties
     */
    public void initialize(Properties properties) {
        configurableRepositoryMap = applicationContext.getBeansOfType(ConfigurableRepository.class);

        for (Map.Entry<String, ConfigurableRepository> entry : configurableRepositoryMap.entrySet()) {
            logger.debug("Configuring: {}", entry.getKey());
            final ConfigurableRepository configurableRepository = entry.getValue();
            configurableRepository.setProperties(properties);
            configurableRepository.initialize();
        }
    }
}
