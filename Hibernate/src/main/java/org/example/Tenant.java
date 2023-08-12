package org.example;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Embedded
    private Data data;

    @ManyToMany
    private List<Apartment> livingPlaces;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Apartment> getLivingPlaces() {
        return livingPlaces;
    }

    public void setLivingPlaces(List<Apartment> livingPlaces) {
        this.livingPlaces = livingPlaces;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", livingPlaces=" + livingPlaces +
                '}';
    }
}
