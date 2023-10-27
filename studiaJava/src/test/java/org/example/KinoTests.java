package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KinoTests {

    @Test
    public void rezerwowanieZajetegoMiejsca() {

        Seans seans1 = new Seans()
                .setDzien(new Date(2023, 10,26))
                .setGodzina(LocalTime.of(15,15))
                .setPegi(18)
                .setTytul("avengets")
                .setMiejsca(new HashMap<>() {{
                    put('a', new HashMap<>(){{put(1, true); put(2,true); put(3,true);}});
                    put('b', new HashMap<>(){{put(1, true); put(2,true); put(3,true);}});
                }});
        assertThrows(ZarezerwowaneMiejsceException.class, () -> {
            Klient klient1 = KinoUtils.zarezerwuj("jagla", "piotr", "piotr.mail@java.com"
                    ,"123456789", seans1, List.of(new Miejsce(1, 'a')));
            Klient klient2 = KinoUtils.zarezerwuj("ktos", "innt", "piotr.mail@java.com"
                    ,"123456789", seans1, List.of(new Miejsce(1, 'a')));
        });
    }

    @Test
    public void rezerwowanieNieistniejacegoMiejsca() {
        Seans seans1 = new Seans()
                .setDzien(new Date(2023, 10,26))
                .setGodzina(LocalTime.of(15,15))
                .setPegi(18)
                .setTytul("avengets")
                .setMiejsca(new HashMap<>() {{
                    put('a', new HashMap<>(){{put(1, true); put(2,true); put(3,true);}});
                    put('b', new HashMap<>(){{put(1, true); put(2,true); put(3,true);}});
                }});
        assertThrows(NieMaTakiegoMiejscaException.class, () -> {
            Klient klient1 = KinoUtils.zarezerwuj("jagla", "piotr", "piotr.mail@java.com"
                    ,"123456789", seans1, List.of(new Miejsce(4, 'a')));
        });

    }

}