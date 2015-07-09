package crossover.social.media.plugin.search;

import crossover.social.media.plugin.PluginImpl;
import crossover.social.media.plugin.PluginOperationException;
import crossover.social.media.plugin.PluginType;

/**
 * AbstractSearchPlugin
 * Created by bazzoni
 */
public abstract class AbstractSearchPlugin<T extends SearchDocument> extends PluginImpl implements SearchPlugin<T> {
    public AbstractSearchPlugin() {
        super(PluginType.SEARCH);
    }

    /**
     * Executes crossover.social.media.plugin default start up tasks
     *
     * @throws PluginOperationException if error
     */
    @Override
    public void doExecuteStartupTasks() throws PluginOperationException {
        //do nothing
    }

    /**
     * Executes crossover.social.media.plugin default shutdown tasks
     *
     * @throws PluginOperationException if error
     */
    @Override
    public void doExecuteShutdownTasks() throws PluginOperationException {
        //do nothing
    }
}
