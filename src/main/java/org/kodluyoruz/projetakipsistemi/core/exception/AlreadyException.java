package org.kodluyoruz.projetakipsistemi.core.exception;

import javax.naming.AuthenticationException;

public class AlreadyException extends AuthenticationException {
    public AlreadyException(String msg){
        super(msg);
    }
}
