package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User
 * Created by bazzoni
 */
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    @Indexed
    private Date creationDate;
    @DBRef
    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
    }

    public User(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.roles.addAll(roles.stream().collect(Collectors.toList()));
    }

    @PersistenceConstructor
    public User(String id, String username, String password, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.roles.addAll(roles.stream().collect(Collectors.toList()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
