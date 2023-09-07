package org.example;


import javax.persistence.*;

@Entity
@Table(name ="klient")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String imie;

    @ManyToOne
    @JoinColumn(name="id_opiekuna")
    private Pracownik opiekun;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Pracownik getOpiekun() {
        return opiekun;
    }

    public void setOpiekun(Pracownik opiekun) {
        this.opiekun = opiekun;
    }
}
