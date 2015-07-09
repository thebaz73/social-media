package crossover.social.media.plugin.asset;

import crossover.social.media.domain.Site;
import crossover.social.media.plugin.PluginImpl;
import crossover.social.media.plugin.PluginOperationException;
import crossover.social.media.plugin.PluginType;
import crossover.social.media.repository.AssetRepository;
import crossover.social.media.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * AbstractAssetManagementPlugin
 * Created by bazzoni
 */
public abstract class AbstractAssetManagementPlugin<C extends Container, A extends Asset> extends PluginImpl implements AssetManagementPlugin<C, A> {
    @Autowired
    protected SiteRepository siteRepository;

    @Autowired
    protected AssetRepository assetRepository;

    public AbstractAssetManagementPlugin() {
        super(PluginType.ASSET_MGMT);
    }

    /**
     * Executes crossover.social.media.plugin default start up tasks
     *
     * @throws PluginOperationException if error
     */
    @Override
    public void doExecuteStartupTasks() throws PluginOperationException {
        assetRepository.deleteAll();
        List<Site> Sites = siteRepository.findAll();
        for (Site Site : Sites) {
            final C siteRepository = findSiteRepository(Site.getId());
            if (siteRepository == null) {
                createSiteRepository(Site.getId());
            } else {
                if (siteRepository.hasChildren()) {
                    loadChildren(Site.getId(), siteRepository);
                }
            }
        }
    }

    /**
     * Executes crossover.social.media.plugin default shutdown tasks
     *
     * @throws PluginOperationException if error
     */
    @Override
    public void doExecuteShutdownTasks() throws PluginOperationException {
        finalizeObjects();
    }

    /**
     * Load all repository assets into central  database
     *
     * @param siteId         site id
     * @param siteRepository container repository
     * @throws PluginOperationException if error
     */
    protected abstract void loadChildren(String siteId, C siteRepository) throws PluginOperationException;

    /**
     * Executes specific finalization tasks
     *
     * @throws PluginOperationException
     */
    protected abstract void finalizeObjects() throws PluginOperationException;
}
