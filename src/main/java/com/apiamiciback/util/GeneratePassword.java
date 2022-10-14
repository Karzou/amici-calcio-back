package com.apiamiciback.util;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Random;

@Slf4j
public class GeneratePassword {

    public static String generatePassword(int length){

        log.info("Generate psw length {}", length);

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String spl = "@!@#?^$*";

        String pwd = letters + numbers + spl;
        Random random = new Random();
        char[] newPassword = new char[length];

        for(int i=0; i<length; i++){
            newPassword[i] = pwd.charAt(random.nextInt(pwd.length()));
        }
        log.info("Return password : {}", String.valueOf(newPassword));
        return String.valueOf(newPassword);
    }
}
