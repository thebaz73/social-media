package crossover.social.media.plugin;

import crossover.social.media.domain.Setting;
import crossover.social.media.repository.SettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * PluginImpl
 * Created by bazzoni
 */
public abstract class PluginImpl implements Plugin {
    private static final String VERSION = "1.0";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PluginType type;

    protected String id;
    protected String name;
    protected PluginStatus status;
    protected Properties properties;
    protected List<Setting> settings;
    protected String filter;
    @Autowired
    protected SettingRepository settingRepository;

    public PluginImpl(PluginType type) {
        this.type = type;
        settings = new ArrayList<>();
    }

    /**
     * Get crossover.social.media.plugin identification code
     *
     * @return id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Get crossover.social.media.plugin name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get crossover.social.media.plugin type
     *
     * @return type
     */
    @Override
    public PluginType getType() {
        return type;
    }

    /**
     * Get actual status
     *
     * @return status
     */
    @Override
    public PluginStatus getStatus() {
        return status;
    }

    /**
     * Get VERSION
     *
     * @return VERSION
     */
    @Override
    public String getVERSION() {
        return VERSION;
    }

    /**
     * Get Settings
     *
     * @return crossover.social.media.plugin settings
     */
    @Override
    public List<Setting> getSettings() {
        return settings;
    }

    /**
     * Set filter
     *
     * @param filter filter
     */
    @Override
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Get spring initialized resource
     *
     * @return resource
     */
    public abstract Resource getResource();

    /**
     * Activates crossover.social.media.plugin
     *
     * @throws PluginOperationException if error
     */
    @Override
    public void doActivate() throws PluginOperationException {
        status = PluginStatus.INSTALLED;
        if (getSetting("activate", Boolean.class, Boolean.parseBoolean(properties.getProperty("crossover.social.media.plugin.activate")))) {
            status = PluginStatus.NOT_READY;
            doValidate();
        }
    }

    /**
     * Deactivates crossover.social.media.plugin
     *
     * @throws PluginOperationException if error
     */
    @Override
    public void doDeactivate() throws PluginOperationException {
        status = PluginStatus.INACTIVE;
    }

    /**
     * Initialize crossover.social.media.plugin settings
     *
     * @throws PluginOperationException if error
     */
    @PostConstruct
    protected void initialize() throws PluginOperationException {
        try {
            properties = PropertiesLoaderUtils.loadProperties(getResource());
            if (!properties.containsKey("crossover.social.media.plugin.id")) {
                id = UUID.randomUUID().toString();
            } else {
                id = properties.getProperty("crossover.social.media.plugin.id");
            }
            name = properties.getProperty("crossover.social.media.plugin.name");
        } catch (IOException e) {
            logger.error("Cannot load properties", e);
        }
        createSettings();
    }

    /**
     * Initialize crossover.social.media.plugin settings
     *
     * @throws PluginOperationException if error
     */
    protected abstract void createSettings() throws PluginOperationException;

    /**
     * Validates crossover.social.media.plugin
     *
     * @throws PluginOperationException if error
     */
    protected abstract void doValidate() throws PluginOperationException;

    /**
     * Get a setting according the type chosen
     *
     * @param key   setting key
     * @param clazz class parameter
     * @param <T>   type
     * @return setting value
     * @throws PluginOperationException
     */
    protected <T> T getSetting(String key, Class<T> clazz, T defaultValue) throws PluginOperationException {
        String compoundKey = getCompoundKey(key);
        List<Setting> settings = settingRepository.findByKeyAndUserId(compoundKey, filter, null).getContent();
        if (!settings.isEmpty() && settings.get(0).getKey().equals(compoundKey)) {
            return clazz.cast(settings.get(0).getValue());
        }
        if (defaultValue != null) {
            return defaultValue;
        }

        throw new PluginOperationException("Setting not found");
    }

    protected String getCompoundKey(String key) {
        return String.format("%s.%s", id, key);
    }
}
