package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Company
 * Created by bazzoni
 */
@Document
public class Company {
    @Id
    private String id;
    private String name;
    @DBRef
    private List<Office> offices;

    public Company() {
        offices = new ArrayList<>();
    }

    @PersistenceConstructor
    public Company(String id, String name, List<Office> offices) {
        this.id = id;
        this.name = name;
        this.offices = offices;
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

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}
