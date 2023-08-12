package org.example;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    private List<Tenant> locators;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tenant> getLocators() {
        return locators;
    }

    public void setLocators(List<Tenant> locators) {
        this.locators = locators;
    }


    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locators=" + locators +
                '}';
    }
}
