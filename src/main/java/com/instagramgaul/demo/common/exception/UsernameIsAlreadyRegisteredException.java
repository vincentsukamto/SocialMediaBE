package com.instagramgaul.demo.common.exception;

import org.springframework.http.HttpStatus;

public class UsernameIsAlreadyRegisteredException extends BaseErrorException {
    public UsernameIsAlreadyRegisteredException(String message) {
        this.setCode("UMA0003");
        this.setMessage(message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
