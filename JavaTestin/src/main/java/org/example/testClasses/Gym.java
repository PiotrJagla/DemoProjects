package org.example.testClasses;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="gyms")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String gymName;

    @OneToMany(mappedBy = "gym")
    private List<Member> members;

    public Gym(int id, String gymName, List<Member> members) {
        this.id = id;
        this.gymName = gymName;
        this.members = members;
    }
    public Gym() {

    }

    public int getId() {
        return id;
    }

    public String getGymName() {
        return gymName;
    }

    public List<Member> getMembers() {
        return members;
    }
}
