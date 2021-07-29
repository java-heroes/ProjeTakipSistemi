package org.kodluyoruz.projetakipsistemi.core.exception;

@SuppressWarnings("serial")
public class ProjeNotFoundException extends NotFoundException{
    public ProjeNotFoundException(){
        super("Proje bulunamadı");
    }
    public ProjeNotFoundException(int id){
        super(String.format("%s id numarasına ait proje bulunamadı",id));
    }
}

