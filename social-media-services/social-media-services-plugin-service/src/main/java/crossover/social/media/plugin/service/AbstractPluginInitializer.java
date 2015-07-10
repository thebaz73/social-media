package crossover.social.media.plugin.service;

import crossover.social.media.domain.Setting;
import crossover.social.media.domain.SettingType;
import crossover.social.media.repository.SettingRepository;
import crossover.social.media.service.SettingAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * AbstractPluginInitializer
 * Created by bazzoni on 10/07/2015.
 */
public abstract class AbstractPluginInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected
    ApplicationContext applicationContext;
    @Autowired
    protected SettingRepository settingRepository;
    protected Map<String, SettingAware> settingAwareMap;

    protected abstract void initialize();

    public void reloadSettings() {
        for (Map.Entry<String, SettingAware> entry : settingAwareMap.entrySet()) {
            logger.debug("Reloading settings for bean {}", entry.getKey());
            final SettingAware settingAwareService = entry.getValue();
            settingAwareService.doSettingAwareReload(true);
        }
    }

    protected void changeSetting(String key, SettingType settingType, Object value) {
        Setting entity;
        List<Setting> settingList = settingRepository.findByKey(key);
        if (settingList.isEmpty()) {
            entity = new Setting(key, value, settingType);
        } else {
            entity = settingList.get(0);
            entity.setValue(value);
        }
        settingRepository.save(entity);
    }

    /**
     * Do actually execute plugin activation
     */
    public abstract void activate();
}
