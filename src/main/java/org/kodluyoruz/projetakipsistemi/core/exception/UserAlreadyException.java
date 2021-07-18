package org.kodluyoruz.projetakipsistemi.core.exception;

public class UserAlreadyException extends AlreadyException{
    public UserAlreadyException(String userName, String mail){
        super(String.format("%s kullanıcı adı veya %s mail adresi kullanımda", userName, mail));
    }
}
