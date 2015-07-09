package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Message
 * Created by bazzoni on 09/07/2015.
 */
@Document
public class Message {
    @Id
    private String id;
    private String replyId;
    private String text;

    public Message() {
    }

    public Message(String id, String replyId, String text) {
        this.id = id;
        this.replyId = replyId;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
