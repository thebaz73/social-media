package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Site
 * Created by thebaz
 */
@Document
public class Site {
    @Id
    private String id;
    private String name;
    @Indexed
    private Date creationDate;
    @Indexed(unique = true)
    private String address;
    private WorkflowType workflowType;
    private CommentApprovalMode commentApprovalMode;
    @DBRef
    @Indexed
    private User webMaster;
    @DBRef
    private List<User> authors;

    public Site() {
    }

    @PersistenceConstructor
    public Site(String name, Date creationDate, String address, WorkflowType workflowType, CommentApprovalMode commentApprovalMode, User webMaster) {
        this.name = name;
        this.creationDate = creationDate;
        this.address = address;
        this.workflowType = workflowType;
        this.commentApprovalMode = commentApprovalMode;
        this.webMaster = webMaster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WorkflowType getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(WorkflowType workflowType) {
        this.workflowType = workflowType;
    }

    public CommentApprovalMode getCommentApprovalMode() {
        return commentApprovalMode;
    }

    public void setCommentApprovalMode(CommentApprovalMode commentApprovalMode) {
        this.commentApprovalMode = commentApprovalMode;
    }

    public User getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(User webMaster) {
        this.webMaster = webMaster;
    }

    public List<User> getAuthors() {
        if (authors == null) {
            authors = new ArrayList<>();
        }
        return authors;
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }
}
