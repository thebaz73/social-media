package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Address
 * Created by bazzoni
 */
@Document
public class Address {
    @Id
    private String id;
    private String address;
    private String city;
    private String zipCode;
    private String nationality;

    public Address() {
    }

    @PersistenceConstructor
    public Address(String id, String address, String city, String zipCode, String nationality) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.nationality = nationality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
