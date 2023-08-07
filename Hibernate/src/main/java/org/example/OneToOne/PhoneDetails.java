package org.example.OneToOne;

import javax.persistence.*;

@Entity
@Table(name="phonedetails")
public class PhoneDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;

    private String technology;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="phone_id")
    private Phone phone;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "PhoneDetails{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", technology='" + technology + '\'' +
                ", phone=" + phone +
                '}';
    }
}
