package org.example;

public class House {
    private boolean isThereField;
    private String name;
    private double area;
    private int rooms;

    public House(boolean isThereField, String name, double area, int rooms) {
        this.isThereField = isThereField;
        this.name = name;
        this.area = area;
        this.rooms = rooms;
    }

    public House() {
    }

    public boolean isThereField() {
        return isThereField;
    }

    public void setThereField(boolean thereField) {
        isThereField = thereField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
