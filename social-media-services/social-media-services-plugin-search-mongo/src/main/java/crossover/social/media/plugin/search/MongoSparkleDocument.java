package crossover.social.media.plugin.search;


import crossover.social.media.domain.SocialContent;

/**
 * MongoSparkleDocument
 * Created by bazzoni
 */
public class MongoSparkleDocument implements SearchDocument {
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_SUMMARY = "summary";
    public static final String FIELD_CONTENT = "content";
    public static final String FIELD_SITEID = "siteId";
    private final String id;
    private final String name;
    private final String uri;
    private final Long date;
    private final String summary;
    private final String content;

    public MongoSparkleDocument(SocialContent socialContent) {
        this(socialContent.getId(), socialContent.getTitle(), socialContent.getUri(), socialContent.getModificationDate().getTime(), socialContent.getSummary(), socialContent.getContent());
    }

    public MongoSparkleDocument(String id, String title, String uri, Long date, String summary, String content) {
        this.id = id;
        this.name = title;
        this.uri = uri;
        this.date = date;
        this.summary = summary;
        this.content = content;
    }

    /**
     * Get Document id
     *
     * @return the id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Get Document name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get Document uri
     *
     * @return uri
     */
    @Override
    public String getUri() {
        return uri;
    }

    /**
     * Get Document date in millis
     *
     * @return date
     */
    @Override
    public Long getDate() {
        return date;
    }

    /**
     * Get Document summary
     *
     * @return summary
     */
    @Override
    public String getSummary() {
        return summary;
    }

    /**
     * Get Document content
     *
     * @return content
     */
    @Override
    public String getContent() {
        return content;
    }
}