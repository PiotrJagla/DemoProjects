package org.example;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Seans seans1 = new Seans()
                .setDzien(new Date(2023, 10,26))
                .setGodzina(LocalTime.of(15,15))
                .setPegi(18)
                .setTytul("avengets")
                .setMiejsca(new HashMap<>() {{
                    put('a', new HashMap<>(){{put(1, true); put(2,true); put(3,true);}});
                    put('b', new HashMap<>(){{put(1, true); put(2,true); put(3,true);}});
                }});
        Seans seans2 = new Seans()
                .setDzien(new Date(2023, 11,13))
                .setGodzina(LocalTime.of(13,40))
                .setPegi(3)
                .setTytul("Ratatuj")
                .setMiejsca(new HashMap<>() {{
                    put('a', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);}});
                    put('b', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);}});
                    put('c', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);}});
                }});
        Seans seans3 = new Seans()
                .setDzien(new Date(2023, 12,19))
                .setGodzina(LocalTime.of(9,10))
                .setPegi(12)
                .setTytul("Jurassic park 1")
                .setMiejsca(new HashMap<>() {{
                    put('a', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);put(5,true);}});
                    put('b', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);put(5,true);}});
                    put('c', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);put(5,true);}});
                    put('d', new HashMap<>(){{put(1, true); put(2,true); put(3,true);put(4,true);put(5,true);}});
                }});

        Klient klient1 = KinoUtils.zarezerwuj("jagla", "piotr", "piotr.mail@java.com"
           ,"123456789", seans1, List.of(new Miejsce(1, 'a'), new Miejsce(2, 'a')));


        Klient klient2 = KinoUtils.zarezerwuj("jagla", "piotr", "piotr.mail@java.com"
                ,"123456789", seans2, List.of(new Miejsce(3, 'c')));



        KinoUtils.zapiszKlientow(List.of(klient1, klient2));

        List<Klient> klienci = KinoUtils.wczytajKlientow();
        for (int i = 0; i < klienci.size(); i++) {
            System.out.println(klienci.get(i));
            System.out.println();
        }

    }
}

class KinoUtils{

    public static void zapiszKlientow(List<Klient> klienci) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("kino.txt"));
            for (Klient k : klienci) {
                oos.writeObject(k);
            }
            oos.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static List<Klient> wczytajKlientow() {
        List<Klient> result = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("kino.txt"));

            Klient k;
            while((k = (Klient) ois.readObject()) != null) {
                result.add(k);
            }

            ois.close();
        } catch (IOException | ClassNotFoundException e) {

        }

        return result;
    }
    public static Klient zarezerwuj(String nazwisko, String imie, String emial, String telephone, Seans seans,
                                    List<Miejsce> zarezerwowaneMiejsca) {
        for (Miejsce m : zarezerwowaneMiejsca) {
            if(!seans.czyWolne(m)) {
                throw new ZarezerwowaneMiejsceException();
            }
            seans.zarezerwuj(m);
        }
        Klient res = new Klient()
                .setNazwisko(nazwisko)
                .setImie(imie)
                .setEmail(emial)
                .setTelephone(telephone)
                .setSeans(seans)
                .setMiejsca(zarezerwowaneMiejsca);
        return res;
    }
}

record Miejsce(int numer, char rzad) implements Serializable { }

class Klient implements Serializable {
    private String nazwisko;
    private String imie;
    private String email;
    private String telephone;
    private Seans seans;

    List<Miejsce> miejsca;

    public Klient() {

    }



    public List<Miejsce> getMiejsca() {
        return miejsca;
    }

    public Klient setMiejsca(List<Miejsce> miejsca) {
        this.miejsca = miejsca;
        return this;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Klient setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    public String getImie() {
        return imie;
    }

    public Klient setImie(String imie) {
        this.imie = imie;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Klient setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Klient setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Seans getSeans() {
        return seans;
    }

    public Klient setSeans(Seans seans) {
        this.seans = seans;
        return this;
    }

    @Override
    public String toString() {
        return "Kl{" +
                "naz='" + nazwisko + '\'' +
                ", im='" + imie + '\'' +
                ", em='" + email + '\'' +
                ", tel='" + telephone + '\n' +
                ", sea=" + seans + "\n" +
                ", mie=" + miejsca + "\n" +
                '}';
    }
}

class Seans implements Serializable{
    private String tytul;
    private Date dzien;
    private LocalTime godzina;
    private int pegi;
    private HashMap<Character, HashMap<Integer, Boolean>> miejsca;

    public Seans(){

    }

    public String getTytul() {
        return tytul;
    }

    public Seans setTytul(String tytul) {
        this.tytul = tytul;
        return this;
    }

    public Date getDzien() {
        return dzien;
    }

    public Seans setDzien(Date dzien) {
        this.dzien = dzien;
        return this;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public Seans setGodzina(LocalTime godzina) {
        this.godzina = godzina;
        return this;
    }

    public int getPegi() {
        return pegi;
    }

    public Seans setPegi(int pegi) {
        this.pegi = pegi;
        return this;
    }

    public HashMap<Character, HashMap<Integer, Boolean>> getMiejsca() {
        return miejsca;
    }

    public Seans setMiejsca(HashMap<Character, HashMap<Integer, Boolean>> miejsca) {
        this.miejsca = miejsca;
        return this;
    }

    public void zarezerwuj(Miejsce miejsce) {
        if(!miejsca.containsKey(miejsce.rzad())) {
            throw new NieMaTakiegoRzeduException();
        }
        if(!miejsca.get(miejsce.rzad()).containsKey(miejsce.numer())) {
            throw new NieMaTakiegoMiejscaException();
        }
        miejsca.get(miejsce.rzad()).put(miejsce.numer(), false);
    }

    public boolean czyWolne(Miejsce miejsce) {
        return miejsca.get(miejsce.rzad()).get(miejsce.numer());
    }

    @Override
    public String toString() {
        return "Se{" +
                "ty='" + tytul + '\'' +
                ", dz=" + dzien +
                ", godz=" + godzina +
                ", p=" + pegi + '\n' +
                ", mie=" + miejsca +
                '}';
    }
}


class ZarezerwowaneMiejsceException extends RuntimeException {}

class NieMaTakiegoMiejscaException extends RuntimeException {}

class NieMaTakiegoRzeduException extends RuntimeException {}


