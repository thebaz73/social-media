package crossover.social.media.plugin.asset;

/**
 * Asset
 * Created by bazzoni
 */
public interface Asset {
    /**
     * Get Asset URI
     *
     * @return asset URI
     */
    String getUri();

    /**
     * Get content as byte array
     *
     * @return byte array
     */
    byte[] getContent();
}
