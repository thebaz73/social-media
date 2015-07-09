package crossover.social.media.plugin.service;


import crossover.social.media.domain.RoleName;
import crossover.social.media.domain.Setting;
import crossover.social.media.domain.User;
import crossover.social.media.plugin.Plugin;
import crossover.social.media.plugin.PluginOperationException;
import crossover.social.media.plugin.PluginStatus;
import crossover.social.media.plugin.asset.Asset;
import crossover.social.media.plugin.asset.AssetManagementPlugin;
import crossover.social.media.plugin.asset.Container;
import crossover.social.media.plugin.search.SearchDocument;
import crossover.social.media.plugin.search.SearchPlugin;
import crossover.social.media.repository.SettingRepository;
import crossover.social.media.repository.UserRepository;
import crossover.social.media.service.AbstractSettingAwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * PluginService
 * Created by bazzoni.
 */
@Component
public class PluginService extends AbstractSettingAwareService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserRepository cmsUserRepository;
    @Autowired
    private SettingRepository cmsSettingRepository;
    private AssetManagementPlugin<? extends Container, ? extends Asset> assetManagementPlugin;
    private SearchPlugin<? extends SearchDocument> searchPlugin;

    private Map<String, Plugin> pluginMap;

    public AssetManagementPlugin<? extends Container, ? extends Asset> getAssetManagementPlugin() {
        return assetManagementPlugin;
    }

    public SearchPlugin<? extends SearchDocument> getSearchPlugin() {
        return searchPlugin;
    }

    @PostConstruct
    private void initialize() {
        pluginMap = applicationContext.getBeansOfType(Plugin.class);
        doSettingAwareReload(false);
    }

    /**
     * Actually executes reload activities
     *
     * @param force true forces reload assets into repository
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void doActualReload(boolean force) {
        for (Map.Entry<String, Plugin> entry : pluginMap.entrySet()) {
            logger.debug("Processing bean {}", entry.getKey());
            Plugin plugin = entry.getValue();
            try {
                plugin.doActivate();
                if (plugin.getStatus().equals(PluginStatus.ACTIVE)) {
                    if (force) {
                        plugin.doExecuteStartupTasks();
                    }
                    if (AssetManagementPlugin.class.isAssignableFrom(plugin.getClass())) {
                        assetManagementPlugin = (AssetManagementPlugin<? extends Container, ? extends Asset>) plugin;
                    } else if (SearchPlugin.class.isAssignableFrom(plugin.getClass())) {
                        searchPlugin = (SearchPlugin<? extends SearchDocument>) plugin;
                    }
                } else if (plugin.getStatus().equals(PluginStatus.INSTALLED)) {
                    if (force) {
                        plugin.doExecuteShutdownTasks();
                        plugin.doDeactivate();
                    }
                }
            } catch (PluginOperationException e) {
                logger.error("Unable to activate {}: cause {}", plugin.getName(), e.getMessage());
            }
        }
    }

    /**
     * Set-up service default settings
     */
    @Override
    protected void setDefaultSettings() {
        for (Map.Entry<String, Plugin> entry : pluginMap.entrySet()) {
            logger.debug("Handling settings for bean {}", entry.getKey());
            Plugin plugin = entry.getValue();
            final List<User> cmsUsers = cmsUserRepository.findAll();
            for (User cmsUser : cmsUsers) {
                if (cmsUser.getRoles().stream().anyMatch(r -> r.getRole().equals(RoleName.ROLE_MANAGER))) {
                    plugin.setFilter(cmsUser.getId());
                    for (Setting cmsSetting : plugin.getSettings()) {
                        cmsSetting.setUserId(cmsUser.getId());
                        if (cmsSettingRepository.findByKeyAndUserId(cmsSetting.getKey(), cmsSetting.getUserId(), null).getContent().isEmpty()) {
                            cmsSettingRepository.save(cmsSetting);
                        }
                    }
                }
            }
        }
        initialized = true;
    }
}
