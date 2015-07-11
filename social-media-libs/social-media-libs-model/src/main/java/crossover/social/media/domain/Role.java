package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Role
 * Created by bazzoni
 */
@Document
public class Role implements Serializable {
    @Id
    private String id;
    private RoleName role;

    public Role() {
    }

    public Role(String name) {
        this.role = RoleName.forName(name);
    }

    public Role(RoleName role) {
        this.role = role;
    }

    @PersistenceConstructor
    public Role(String id, RoleName role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}
