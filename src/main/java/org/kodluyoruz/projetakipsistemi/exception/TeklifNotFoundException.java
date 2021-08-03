package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class TeklifNotFoundException extends NotFoundException {
    public TeklifNotFoundException(){
        super("Teklif Bulunamadı");
    }
    public TeklifNotFoundException(int id){
        super(String.format("%s id numarasına ait teklif bulunamadı",id));
    }
}
