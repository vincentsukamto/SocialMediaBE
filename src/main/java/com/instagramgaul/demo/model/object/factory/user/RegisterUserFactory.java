package com.instagramgaul.demo.model.object.factory.user;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserFactory {
    @Valid
    private int id = 0;

    @Valid
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    private String email = null;

    @Valid
    @NotNull
    @NotBlank
    @Size(min = 6, max = 30)
    private String username = null;

    @Valid
    @NotNull
    @NotBlank
    @Size(min = 6)
    private String password = null;
}
