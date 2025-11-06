package com.deltacapita.clientsapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class Client {

    @Id
    private String id;
    private String name;
    private String email;
    private String country;

    public Client() {}

    public Client(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
