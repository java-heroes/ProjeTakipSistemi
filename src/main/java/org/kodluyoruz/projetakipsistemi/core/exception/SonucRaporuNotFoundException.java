package org.kodluyoruz.projetakipsistemi.core.exception;

@SuppressWarnings("serial")
public class SonucRaporuNotFoundException extends NotFoundException {
    public SonucRaporuNotFoundException(){
        super("Sonuç Raporu Bulunamadı");
    }

    public SonucRaporuNotFoundException(int id){
        super(String.format("%id alanına sahip sonuç raporu bulunamadı"));
    }
}
