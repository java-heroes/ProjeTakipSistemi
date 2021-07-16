package org.kodluyoruz.projetakipsistemi.model.enums;

import java.util.stream.Stream;

public enum Yetki {
    YONETICI(1),
    PERSONEL(2),
    MUSTERI(3);

    private int yetki;

    private Yetki(int yetki) {
        this.yetki = yetki;
    }

    public int getYetki(){
        return  yetki;
    }

    public static Yetki of(int yetki) {
        return Stream.of(Yetki.values())
                .filter(y -> y.getYetki() == yetki)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
