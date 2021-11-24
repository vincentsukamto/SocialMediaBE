package com.instagramgaul.demo.common.exception;

import org.springframework.http.HttpStatus;

public class RegisterUserException extends BaseErrorException{
    public RegisterUserException(String message){
        this.setCode("UMA0001");
        this.setMessage(message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
