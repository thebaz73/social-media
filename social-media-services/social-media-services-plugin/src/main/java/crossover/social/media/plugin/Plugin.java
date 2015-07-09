package crossover.social.media.plugin;


import crossover.social.media.domain.Setting;

import java.util.List;

/**
 * Plugin
 * Created by bazzoni
 */
public interface Plugin {
    /**
     * Get crossover.social.media.plugin identification code
     *
     * @return id
     */
    String getId();

    /**
     * Get crossover.social.media.plugin name
     *
     * @return name
     */
    String getName();

    /**
     * Get crossover.social.media.plugin type
     *
     * @return type
     */
    PluginType getType();

    /**
     * Get actual status
     *
     * @return status
     */
    PluginStatus getStatus();

    /**
     * Get VERSION
     *
     * @return VERSION
     */
    String getVERSION();

    /**
     * Get Settings
     *
     * @return crossover.social.media.plugin settings
     */
    List<Setting> getSettings();

    /**
     * Set filter
     *
     * @param filter filter
     */
    void setFilter(String filter);

    /**
     * Activates crossover.social.media.plugin
     *
     * @throws PluginOperationException if error
     */
    void doActivate() throws PluginOperationException;

    /**
     * Deactivates crossover.social.media.plugin
     *
     * @throws PluginOperationException if error
     */
    void doDeactivate() throws PluginOperationException;

    /**
     * Executes crossover.social.media.plugin default start up tasks
     *
     * @throws PluginOperationException if error
     */
    void doExecuteStartupTasks() throws PluginOperationException;

    /**
     * Executes crossover.social.media.plugin default shutdown tasks
     *
     * @throws PluginOperationException if error
     */
    void doExecuteShutdownTasks() throws PluginOperationException;
}
