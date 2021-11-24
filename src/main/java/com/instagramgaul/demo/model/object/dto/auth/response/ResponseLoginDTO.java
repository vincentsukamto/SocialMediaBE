package com.instagramgaul.demo.model.object.dto.auth.response;

import com.instagramgaul.demo.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLoginDTO {
    private String accessToken;
    private User userData;
}
