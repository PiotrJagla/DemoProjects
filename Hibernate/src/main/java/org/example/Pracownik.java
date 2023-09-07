package org.example;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pracownik")
public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String imie;
    private String stanowisko;
    private String nazwaOddzialu;

    @OneToOne
    private Pracownik przelozony;

    @OneToOne
    private OddzialBanku podopiecznyOddzial;

    @OneToMany(mappedBy = "opiekun")
    private List<Klient> klienci;

    public List<Klient> getKlienci() {
        return klienci;
    }

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public OddzialBanku getPodopiecznyOddzial() {
        return podopiecznyOddzial;
    }

    public void setPodopiecznyOddzial(OddzialBanku podopiecznyOddzial) {
        this.podopiecznyOddzial = podopiecznyOddzial;
    }

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

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public String getNazwaOddzialu() {
        return nazwaOddzialu;
    }

    public void setNazwaOddzialu(String nazwaOddzialu) {
        this.nazwaOddzialu = nazwaOddzialu;
    }

    public Pracownik getPrzelozony() {
        return przelozony;
    }

    public void setPrzelozony(Pracownik przelozony) {
        this.przelozony = przelozony;
    }
}
