package crossover.social.media.service;

/**
 * SettingAware
 * Created by bazzoni.
 */
public interface SettingAware {
    /**
     * Do reload all setting aware objects
     *
     * @param force force setting reload
     */
    void doSettingAwareReload(boolean force);
}
