package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Content
 * Created by thebaz
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "site_uri_idx", def = "{'siteId' : 1, 'uri' : 1}")
})

public class Content {

    private final String type;
    @Id
    private String id;
    private String siteId;
    private String name;
    private String title;
    private String uri;
    @Indexed
    private Date modificationDate;
    private boolean published;
    private String summary;
    private String content;
    @DBRef
    private List<Asset> assets;
    @DBRef
    private List<Tag> tags;

    public Content() {
        this.type = "CONTENT";
    }

    public Content(String type) {
        this.type = type;
    }

    @PersistenceConstructor
    public Content(String siteId, String name, String title, String uri, Date modificationDate, String summary, String content) {
        this("CONTENT");
        this.siteId = siteId;
        this.name = name;
        this.title = title;
        this.uri = uri;
        this.modificationDate = modificationDate;
        this.summary = summary;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Asset> getAssets() {
        if (assets == null) {
            assets = new ArrayList<>();
        }
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }
}
