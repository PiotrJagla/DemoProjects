package org.example;


import javax.persistence.*;

@Entity
@Table(name ="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roomName;
    private float area;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", area=" + area +
                '}';
    }

}
