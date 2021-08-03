package org.kodluyoruz.projetakipsistemi.exception;

import org.kodluyoruz.projetakipsistemi.core.exception.NotFoundException;

@SuppressWarnings("serial")
public class PersonelNotFoundException extends NotFoundException {
    public PersonelNotFoundException(int id) {
        super(String.format("%s id alanına sahip personel bulunamadı", id));
    }
    public PersonelNotFoundException(String mail) {
        super(String.format("%s mail hesabına sahip kullanıcı bulunamadı", mail));
    }
    public PersonelNotFoundException(){
        super("Kullanıcı adı veya şifre yanlış");
    }

}