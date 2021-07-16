package org.kodluyoruz.projetakipsistemi.core.exception;

@SuppressWarnings("serial")
public class MusteriNotFoundException extends NotFoundException{
    public MusteriNotFoundException(int id) {
        super(String.format("%s id alanına sahip müşteri bulunamadı", id));
    }
}
