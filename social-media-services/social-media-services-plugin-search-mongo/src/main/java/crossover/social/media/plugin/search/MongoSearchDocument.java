package crossover.social.media.plugin.search;


import crossover.social.media.domain.CustomerData;
import crossover.social.media.domain.Person;
import crossover.social.media.domain.SocialContent;

/**
 * MongoSearchDocument
 * Created by bazzoni
 */
public class MongoSearchDocument implements SearchDocument {
    private final String id;
    private final String content;

    public MongoSearchDocument(SocialContent socialContent) {
        this(socialContent.getId(), socialContent.getContent());
    }

    public MongoSearchDocument(CustomerData customerData) {
        this(customerData.getId(), customerData.getData());
    }

    public MongoSearchDocument(Person person) {
        this.id = person.getId();
        this.content = person.getFirstName() + " " + person.getFirstName();
    }

    public MongoSearchDocument(String id, String content) {
        this.id = id;
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
     * Get Document content
     *
     * @return content
     */
    @Override
    public String getContent() {
        return content;
    }
}