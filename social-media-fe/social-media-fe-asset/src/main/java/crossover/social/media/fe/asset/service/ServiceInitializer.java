package crossover.social.media.fe.asset.service;

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
    @Value("${social.media.oak.dbName}")
    private String dbName;
    @Value("${social.media.oak.dbHost}")
    private String dbHost;
    @Value("${social.media.oak.dbPort}")
    private String dbPort;
    @Value("${social.media.oak.username}")
    private String username;
    @Value("${social.media.oak.password}")
    private String password;

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
        changeSetting("oak.dbName", SettingType.TEXT, dbName);
        changeSetting("oak.dbHost", SettingType.INET, dbHost);
        changeSetting("oak.dbPort", SettingType.INTEGER, Integer.parseInt(dbPort));
        changeSetting("oak.username", SettingType.TEXT, username);
        changeSetting("oak.password", SettingType.TEXT, password);
        //activate
        final List<Setting> byKey = settingRepository.findByKey("oak.activate");
        if (!byKey.isEmpty()) {
            Setting activeSetting = byKey.get(0);
            if ((Boolean) activeSetting.getValue()) {
                activeSetting.setValue(Boolean.TRUE);
                settingRepository.save(activeSetting);
            }
            //reload
            reloadSettings();
        }
    }

}
