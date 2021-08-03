package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class YoneticiNotFoundException extends NotFoundException {
    public YoneticiNotFoundException(int id) {
        super(String.format("%s id alanına sahip yönetici bulunamadı", id));
    }
    public YoneticiNotFoundException(String mail) {
        super(String.format("%s mail hesabına sahip kullanıcı bulunamadı", mail));
    }
    public YoneticiNotFoundException(){
        super("Kullanıcı adı veya şifre yanlış");
    }

}