package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * CustomerData
 * Created by bazzoni on 11/07/2015.
 */
@Document
public class CustomerData extends Company {
    @Id
    private String id;
    private String customer;
    private String data;
    private String companyId;

    public CustomerData() {
    }

    public CustomerData(String id, String customer, String data, String companyId) {
        this.id = id;
        this.customer = customer;
        this.data = data;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
