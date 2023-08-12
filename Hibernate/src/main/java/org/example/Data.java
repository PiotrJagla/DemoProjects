package org.example;


import javax.persistence.Embeddable;

@Embeddable
public class Data {
    private String weigth;
    private String age;
    private String phone;

    public String getWeigth() {
        return weigth;
    }

    public Data setWeigth(String weigth) {
        this.weigth = weigth;
        return this;
    }

    public String getAge() {
        return age;
    }

    public Data setAge(String age) {
        this.age = age;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Data setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "Data{" +
                "weigth='" + weigth + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
