package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * ActivityLog
 * Created by bazzoni on 11/07/2015.
 */
@Document
public class ActivityLog {
    @Id
    private String id;
    private Date timestamp;
    private String activity;
    private String userId;

    public ActivityLog() {
    }

    @PersistenceConstructor
    public ActivityLog(String id, Date timestamp, String activity, String userId) {
        this.id = id;
        this.timestamp = timestamp;
        this.activity = activity;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
