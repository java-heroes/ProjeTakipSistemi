package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class MusteriNotFoundException extends NotFoundException {
    public MusteriNotFoundException(int id) {
        super(String.format("%s id alanına sahip müşteri bulunamadı", id));
    }
    public MusteriNotFoundException(String mail) {
        super(String.format("%s mail hesabına sahip kullanıcı bulunamadı", mail));
    }
    public MusteriNotFoundException(){
        super("Kullanıcı adı veya şifre yanlış");
    }

}
