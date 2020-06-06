package com.reacher.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword (String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="123";
        System.out.println(password.toUpperCase());

        String s = encodePassword(password);
        //$2a$10$b3cXsL00txzR6egQf2Ke6u9PCoAdl0lJrTkcp4GLvNmuhIvN47G5e
        //$2a$10$oAhpaC/tph6ZaeCYO4VtQe0VKyEADMw3DCO7PvJeUaIezOJrqeoMW
        System.out.println(s);
    }

}
