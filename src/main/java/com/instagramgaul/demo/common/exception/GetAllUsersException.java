package com.instagramgaul.demo.common.exception;

import org.springframework.http.HttpStatus;

public class GetAllUsersException extends BaseErrorException{

    private static final long serialVersionUID = 1L;

    public GetAllUsersException(String message) {
        this.setCode("UMA0004");
        this.setMessage("Retrieve users data error: " + message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
