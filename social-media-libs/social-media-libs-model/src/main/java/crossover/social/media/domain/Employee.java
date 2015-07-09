package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Employee
 * Created by bazzoni
 */
@Document
public class Employee {
    @Id
    private String id;
    private String code;
    @DBRef
    private Company company;

    public Employee() {
    }

    @PersistenceConstructor
    public Employee(String id, String code, Company company) {
        this.id = id;
        this.code = code;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
