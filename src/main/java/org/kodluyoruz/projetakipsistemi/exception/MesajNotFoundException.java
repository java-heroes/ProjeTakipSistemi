package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class MesajNotFoundException extends NotFoundException {
    public MesajNotFoundException(){
        super("Mesaj Bulunamadı");
    }

    public MesajNotFoundException(int id){
        super(String.format("%s id alanına sahip mesaj bulunamadı.",id));
    }
}
