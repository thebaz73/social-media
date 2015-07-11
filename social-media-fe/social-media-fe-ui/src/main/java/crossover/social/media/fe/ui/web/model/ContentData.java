package crossover.social.media.fe.ui.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * ContentData
 * Created by bazzoni on 11/07/2015.
 */
public class ContentData implements Serializable {
    private String id;
    private String title;
    private Date timestamp;
    private String authorId;
    private String author;
    private String post;

    public ContentData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
