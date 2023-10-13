package org.example.testClasses;


import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name="gymid", nullable = false)
    private Gym gym;

    public Member(String name, int age, int id) {
        this.name = name;
        this.age = age;
    }
    public Member() {

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
