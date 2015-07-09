package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Comment
 * Created by thebaz
 */
@Document
public class Comment {
    @Id
    private String id;
    private boolean approved;
    @Indexed
    private String siteId;
    @Indexed
    private String contentId;
    @Indexed
    private Date timestamp;
    private String title;
    private String content;
    @DBRef
    private User viewer;

    public Comment() {
    }

    @PersistenceConstructor
    public Comment(String siteId, String contentId, Date timestamp, String title, String content, User viewer) {
        this.siteId = siteId;
        this.contentId = contentId;
        this.timestamp = timestamp;
        this.title = title;
        this.content = content;
        this.viewer = viewer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }
}
