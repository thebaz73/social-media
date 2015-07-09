package crossover.social.media.plugin.search;

/**
 * SearchDocument
 * Created by bazzoni
 */
public interface SearchDocument {
    /**
     * Get Document id
     *
     * @return the id
     */
    String getId();

    /**
     * Get Document name
     *
     * @return name
     */
    String getName();

    /**
     * Get Document uri
     *
     * @return uri
     */
    String getUri();

    /**
     * Get Document date in millis
     *
     * @return date
     */
    Long getDate();

    /**
     * Get Document summary
     *
     * @return summary
     */
    String getSummary();

    /**
     * Get Document content
     *
     * @return content
     */
    String getContent();
}
