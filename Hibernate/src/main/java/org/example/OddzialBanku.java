package org.example;


import javax.persistence.*;

@Entity
@Table(name = "oddzial")
public class OddzialBanku {

    @Id
    private String nazwaOddialu;

    @OneToOne
    @JoinColumn(name = "id_kierownika")
    private Pracownik kierownik;

    public String getNazwaOddialu() {
        return nazwaOddialu;
    }

    public void setNazwaOddialu(String nazwaOddialu) {
        this.nazwaOddialu = nazwaOddialu;
    }

    public Pracownik getKierownik() {
        return kierownik;
    }

    public void setKierownik(Pracownik kierownik) {
        this.kierownik = kierownik;
    }
}
