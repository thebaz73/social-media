package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Chat
 * Created by bazzoni
 */
@Document
public class Chat {
    @Id
    private String id;
    @Indexed
    private String subject;
    @DBRef
    private List<Message> messages;

    public Chat() {
        messages = new ArrayList<>();
    }

    @PersistenceConstructor
    public Chat(String id, String subject, List<Message> messages) {
        this.id = id;
        this.subject = subject;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
