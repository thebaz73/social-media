package crossover.social.media.plugin.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

/**
 * OakAsset
 * Created by bazzoni
 */
public class OakAsset implements Asset {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String uri;
    private final ByteArrayOutputStream baos;

    public OakAsset(String uri, ByteArrayOutputStream baos) {
        this.uri = uri;
        this.baos = baos;
    }

    /**
     * Get SocialAsset URI
     *
     * @return asset URI
     */
    @Override
    public String getUri() {
        return uri;
    }

    /**
     * Get content as byte array
     *
     * @return byte array
     */
    @Override
    public byte[] getContent() {
        return baos.toByteArray();
    }
}
