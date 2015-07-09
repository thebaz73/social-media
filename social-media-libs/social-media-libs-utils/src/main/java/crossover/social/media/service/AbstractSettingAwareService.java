package crossover.social.media.service;

/**
 * AbstractSettingAwareService
 * Created by bazzoni.
 */
public abstract class AbstractSettingAwareService implements SettingAware {
    protected boolean initialized = false;

    /**
     * Do reload all setting aware objects
     *
     * @param force force setting reload
     */
    @Override
    public void doSettingAwareReload(boolean force) {
        if (!initialized || force) {
            setDefaultSettings();
        }
        doActualReload(force);
    }

    /**
     * Actually executes reload
     *
     * @param force true forces reload assets into repository
     */
    protected abstract void doActualReload(boolean force);

    /**
     * Set-up service default settings
     */
    protected abstract void setDefaultSettings();
}
