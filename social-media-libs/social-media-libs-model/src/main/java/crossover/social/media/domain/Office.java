package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Office
 * Created by bazzoni
 */
@Document
public class Office {
    @Id
    private String id;
    private String name;
    private String department;
    @DBRef
    private Address address;

    public Office() {
    }

    @PersistenceConstructor
    public Office(String id, String name, String department, Address address) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.address = address;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
