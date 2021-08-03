package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class ProjeTaslagiNotFoundException extends NotFoundException {
    public ProjeTaslagiNotFoundException(int id) {
        super(String.format("%s id alanına sahip proje taslağı bulunamadı", id));
    }

    public ProjeTaslagiNotFoundException(){
        super("Proje Taslağı Buluanamadı");
    }

}
