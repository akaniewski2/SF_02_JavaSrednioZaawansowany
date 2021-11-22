package pl.kurs.advanced.jpa.JPA.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable //dane z tej klasy wpisywane sa w inna encje
public class Address {


    private String  street;
    private String  apartment;
    private String  postalCode;
    private String  city;


    public Address(String street, String apartment, String postalCode, String city) {
        this.street = street;
        this.apartment = apartment;
        this.postalCode = postalCode;
        this.city = city;
    }
}
