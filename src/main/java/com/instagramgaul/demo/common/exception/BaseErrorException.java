package com.instagramgaul.demo.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class BaseErrorException extends RuntimeException{

    public BaseErrorException(){
        super();
    }

    @Getter
    @Setter
    private String code = "500";

    @Getter
    @Setter
    private String message = "";

    @Getter
    @Setter
    private Object data = null;

    @Getter
    @Setter
    private HttpStatus status = HttpStatus.EXPECTATION_FAILED;
}
