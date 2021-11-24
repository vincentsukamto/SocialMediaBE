package com.instagramgaul.demo.model.object;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse<T> {
    private Boolean status = false;
    private String code = "500";
    private String message = "Internal Server Error";
    private T data = null;

    public BaseResponse() {

    }

    public BaseResponse(Boolean status, String i, String message, T data) {
        this.status = status;
        this.code = i;
        this.message = message;
        this.data = data;
    }
}
