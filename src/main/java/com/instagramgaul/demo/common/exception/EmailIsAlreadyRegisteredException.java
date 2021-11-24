package com.instagramgaul.demo.common.exception;

import org.springframework.http.HttpStatus;

public class EmailIsAlreadyRegisteredException extends BaseErrorException {
    private static final long serialVersionUID = 1L;

    public EmailIsAlreadyRegisteredException(String email){
        this.setCode("UMA0002");
        this.setMessage(email + " is already registered.");
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
