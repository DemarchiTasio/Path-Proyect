package com.Manager.pathExamination.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class Encripted {

        public static void main(String[] args) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = "123456";
            String encodedPassword = passwordEncoder.encode(password);

            System.out.println(encodedPassword);
        }
}
