package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class GorevNotFoundException extends NotFoundException {
    public GorevNotFoundException(){
        super("Görev Bulunamadı");
    }

    public GorevNotFoundException(int id){
        super(String.format("%s id alanına sahip görev bulunamadı.",id));
    }
}
