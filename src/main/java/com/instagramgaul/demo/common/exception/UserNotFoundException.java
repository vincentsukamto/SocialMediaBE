package com.instagramgaul.demo.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseErrorException{
    public UserNotFoundException(String message){
        this.setCode("UMA0005");
        this.setMessage(message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
