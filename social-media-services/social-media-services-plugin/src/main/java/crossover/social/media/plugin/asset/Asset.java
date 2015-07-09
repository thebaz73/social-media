package crossover.social.media.plugin.asset;

/**
 * SocialAsset
 * Created by bazzoni
 */
public interface Asset {
    /**
     * Get SocialAsset URI
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
