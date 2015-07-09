package crossover.social.media.plugin.search;

import com.mongodb.Mongo;
import crossover.social.media.domain.Setting;
import crossover.social.media.domain.SettingType;
import crossover.social.media.domain.SocialContent;
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
public class MongoSearchPlugin extends AbstractSearchPlugin<MongoSparkleDocument> {

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
     * @param siteId     site id
     * @param searchTerm search term
     * @return found documents
     */
    @Override
    public List<MongoSparkleDocument> search(String siteId, String searchTerm) {
        String[] words = searchTerm.split(" ");
        if (words.length == 0) return Collections.emptyList();
        Criteria conditions = Criteria.where(MongoSparkleDocument.FIELD_SITEID).is(siteId).andOperator(createSearchConditions(words));
        Query search = new Query(conditions);
        final List<SocialContent> socialContents = mongoTemplate.find(search, SocialContent.class);
        return socialContents.stream().map(MongoSparkleDocument::new).collect(Collectors.toList());
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

    private Criteria createSearchConditions(String[] words) {
        Criteria conditions = null;

        for (String word : words) {
            word = ".*" + normalize(word) + ".*";
            if (conditions == null) {
                conditions = Criteria.where(MongoSparkleDocument.FIELD_TITLE).regex(word, "i")
                        /*.orOperator(Criteria.where(MongoSparkleDocument.FIELD_SUMMARY).regex(word, "im"))
                        .orOperator(Criteria.where(MongoSparkleDocument.FIELD_CONTENT).regex(word, "im"))*/;
            } else {
                conditions = conditions.orOperator(Criteria.where(MongoSparkleDocument.FIELD_TITLE).regex(word, "i")
                        /*.orOperator(Criteria.where(MongoSparkleDocument.FIELD_SUMMARY).regex(word, "im"))
                        .orOperator(Criteria.where(MongoSparkleDocument.FIELD_CONTENT).regex(word, "im")*/);
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