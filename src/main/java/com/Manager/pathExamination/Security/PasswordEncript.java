package com.Manager.pathExamination.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncript {

    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }
    
}
