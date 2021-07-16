package org.kodluyoruz.projetakipsistemi.model.enums;

import java.util.stream.Stream;

public enum Durum {
    Aktif(1),
    Islemde(2),
    Pasif(3);

    private int durum;

    private Durum(int durum) {
        this.durum = durum;
    }

    public int getDurum(){
        return  durum;
    }

    public static Durum of(int durum) {
        return Stream.of(Durum.values())
                .filter(g -> g.getDurum() == durum)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
