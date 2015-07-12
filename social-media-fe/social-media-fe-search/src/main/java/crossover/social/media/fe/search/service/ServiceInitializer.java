package crossover.social.media.fe.search.service;

import crossover.social.media.domain.Setting;
import crossover.social.media.domain.SettingType;
import crossover.social.media.plugin.service.AbstractPluginInitializer;
import crossover.social.media.service.SettingAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * ServiceInitializer
 * Created by bazzoni on 10/07/2015.
 */
@Service
public class ServiceInitializer extends AbstractPluginInitializer {
    @Value("${social.media.admin.username}")
    private String username = "admin";
    @Value("${social.media.search.dbName}")
    private String dbName;


    /**
     * Initializes common data structures
     */
    @Override
    @PostConstruct
    protected void initialize() {
        settingAwareMap = applicationContext.getBeansOfType(SettingAware.class);
    }

    /**
     * Do actually execute plugin activation
     */
    @Override
    public void activate() {
        //load setting from props
        changeSetting("mongo.database.name", SettingType.TEXT, dbName);
        //activate
        final List<Setting> byKey = settingRepository.findByKey("mongo.activate");
        if (!byKey.isEmpty()) {
            Setting activeSetting = byKey.get(0);
            if (!(Boolean) activeSetting.getValue()) {
                activeSetting.setValue(Boolean.TRUE);
                settingRepository.save(activeSetting);
            }
            //reload
            reloadSettings();
        }
    }
}
