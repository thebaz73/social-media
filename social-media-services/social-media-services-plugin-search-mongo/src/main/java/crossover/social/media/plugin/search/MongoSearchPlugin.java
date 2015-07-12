package crossover.social.media.plugin.search;

import com.mongodb.Mongo;
import crossover.social.media.domain.*;
import crossover.social.media.plugin.PluginOperationException;
import crossover.social.media.plugin.PluginStatus;
import crossover.social.media.plugin.search.mongo.MongoTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MongoSearchPlugin
 * Created by bazzoni
 */
@Component
public class MongoSearchPlugin extends AbstractSearchPlugin<MongoSearchDocument> {

    @Value("classpath:/META-INF/mongo-plugin.properties")
    private Resource resource;
    private MongoTemplate mongoTemplate;
    @Autowired
    private Mongo mongo;

    /**
     * Get spring initialized resource
     *
     * @return resource
     */
    @Override
    public Resource getResource() {
        return resource;
    }

    /**
     * Initialize plugin settings
     *
     * @throws PluginOperationException if error
     */
    @Override
    protected void createSettings() throws PluginOperationException {
        settings.add(new Setting(getCompoundKey("activate"), getSetting("activate", Boolean.class, false), SettingType.BOOL));
        settings.add(new Setting(getCompoundKey("database.name"), getSetting("database.name", String.class, properties.getProperty("plugin.database.name")), SettingType.TEXT));
    }

    /**
     * Validates plugin
     *
     * @throws PluginOperationException if error
     */
    @Override
    protected void doValidate() throws PluginOperationException {
        String databaseName = getSetting("database.name", String.class, properties.getProperty("plugin.database.name"));
        if (databaseName.isEmpty()) {
            throw new PluginOperationException("Cannot define repository URL");
        }

        if (!databaseName.equals("<change me>")) {
            MongoTemplateFactory factory = new MongoTemplateFactory(mongo, databaseName);
            mongoTemplate = factory.createMongoTemplate();
            status = PluginStatus.ACTIVE;
        }
    }

    /**
     * Update a content in index
     *
     * @param id      document id
     * @param siteId  document siteId
     * @param name    document name
     * @param uri     document uri
     * @param date    document date
     * @param summary document summary
     * @param content document content
     */
    @Override
    public void addToIndex(String id, String siteId, String name, String uri, Long date, String summary, String content) {
        //ignore
    }

    /**
     * Delete an indexed document from Solr index
     *
     * @param id document id
     */
    @Override
    public void deleteFromIndex(String id) {
        //ignore
    }

    /**
     * Search index for specified term
     *
     * @param searchTerm search term
     * @param index search index
     * @return found documents
     */
    @Override
    public List<MongoSearchDocument> search(String searchTerm, SearchIndex index) {
        String[] words = searchTerm.split(" ");
        if (words.length == 0) return Collections.emptyList();
        Criteria conditions;
        Query search;
        switch (index) {
            case CONTENT:
                conditions = createSearchConditions(words, "content");
                search = new Query(conditions);
                final List<SocialContent> socialContents = mongoTemplate.find(search, SocialContent.class);
                return socialContents.stream().map(MongoSearchDocument::new).collect(Collectors.toList());
            case CUSTOMER:
                conditions = createSearchConditions(words, "data");
                search = new Query(conditions);
                final List<CustomerData> customerDataList = mongoTemplate.find(search, CustomerData.class);
                return customerDataList.stream().map(MongoSearchDocument::new).collect(Collectors.toList());
            case USER:
                conditions = createSearchConditions(words, "firstName");
                search = new Query(conditions);
                final List<Person> persons = mongoTemplate.find(search, Person.class);
                return persons.stream().map(MongoSearchDocument::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * Update a content in index
     *
     * @param id      document id
     * @param siteId  document siteId
     * @param name    document name
     * @param uri     document uri
     * @param date    document date
     * @param summary document summary
     * @param content document content
     */
    @Override
    public void update(String id, String siteId, String name, String uri, Long date, String summary, String content) {
        //ignore
    }

    private Criteria createSearchConditions(String[] words, String field) {
        Criteria conditions = null;

        for (String word : words) {
            word = ".*" + normalize(word) + ".*";
            if (conditions == null) {
                conditions = Criteria.where(field).regex(word, "i");
            } else {
                conditions = conditions.orOperator(Criteria.where(field).regex(word, "i"));
            }
        }

        return conditions;
    }

    private String normalize(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}|/|\\.]+", "_");
    }
}