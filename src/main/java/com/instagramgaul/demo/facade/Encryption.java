package com.instagramgaul.demo.facade;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Encryption {
    private static final Integer SALT = 12;

    public String make(String toHash){
        return BCrypt.hashpw(toHash, BCrypt.gensalt(Encryption.SALT));
    }

    public Boolean checkHash(String string, String hashed) {
        return BCrypt.checkpw(string, hashed);
    }
}
