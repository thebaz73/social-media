package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Tag
 * Created by thebaz
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "tag_site_uri_idx", def = "{'siteId' : 1, 'uri' : 1}")
})
public class Tag {
    @Id
    private String id;
    private String siteId;
    private String tag;
    private String uri;
    private Integer popularity;
    private Set<String> contentIds;

    public Tag() {
        this.popularity = 0;
    }

    @PersistenceConstructor
    public Tag(String siteId, String tag, String uri) {
        this();
        this.siteId = siteId;
        this.tag = tag;
        this.uri = uri;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Set<String> getContentIds() {
        if (contentIds == null) {
            contentIds = new HashSet<>();
        }
        return contentIds;
    }

    public void setContentIds(Set<String> contentIds) {
        this.contentIds = contentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return !(id != null ? !id.equals(tag.id) : tag.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
