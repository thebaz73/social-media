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
    private Office office;

    public Employee() {
    }

    @PersistenceConstructor
    public Employee(String id, String code, Office office) {
        this.id = id;
        this.code = code;
        this.office = office;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
