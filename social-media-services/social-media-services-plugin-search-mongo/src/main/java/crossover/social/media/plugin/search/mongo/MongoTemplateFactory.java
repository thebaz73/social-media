package crossover.social.media.plugin.search.mongo;

import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * MongoTemplateFactory
 * Created by bazzoni
 */
public class MongoTemplateFactory {
    private final Mongo mongo;
    private final String databaseName;

    public MongoTemplateFactory(Mongo mongo, String databaseName) {
        this.mongo = mongo;
        this.databaseName = databaseName;
    }

    public MongoTemplate createMongoTemplate() {
        return new MongoTemplate(mongo, databaseName);
    }
}
