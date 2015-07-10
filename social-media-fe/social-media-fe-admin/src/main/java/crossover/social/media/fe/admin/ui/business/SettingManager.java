package crossover.social.media.fe.admin.ui.business;

import crossover.social.media.domain.Setting;
import crossover.social.media.plugin.Plugin;
import crossover.social.media.repository.SettingRepository;
import crossover.social.media.service.SettingAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * SettingManager
 * Created by bazzoni on 10/07/2015.
 */
@Component
public class SettingManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private SettingRepository settingRepository;
    private Map<String, SettingAware> settingAwareMap;

    @PostConstruct
    private void initialize() {
        settingAwareMap = applicationContext.getBeansOfType(SettingAware.class);
    }

    public void reloadSettings() {
        for (Map.Entry<String, SettingAware> entry : settingAwareMap.entrySet()) {
            logger.debug("Reloading settings for bean {}", entry.getKey());
            final SettingAware cmsSettingAwareService = entry.getValue();
            cmsSettingAwareService.doSettingAwareReload(true);
        }
    }

    public List<Setting> findSettings() {
        return settingRepository.findAll();
    }

    public Map<String, Plugin> findPlugins() {
        return applicationContext.getBeansOfType(Plugin.class);
    }

    public void editSetting(Setting cmsSetting) {
        settingRepository.save(cmsSetting);
    }

    public Setting findSetting(String settingId) {
        return settingRepository.findOne(settingId);
    }
}
