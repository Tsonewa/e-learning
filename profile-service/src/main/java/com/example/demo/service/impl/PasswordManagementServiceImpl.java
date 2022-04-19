package com.example.demo.service.impl;

import com.example.demo.service.PasswordManagementService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordManagementServiceImpl implements PasswordManagementService {
    private static final String SYMBOL = "-/.^&*_!@%=+>)";
    private static final String CAP_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SMALL_LETTER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String CHARACTERS = SYMBOL + CAP_LETTER + SMALL_LETTER + NUMBERS;
    private static final int ATOMIC_INCREMENT = 3;
    private static final int LENGTH = 8;
    private static final int SALT_LENGTH = 1;

    public static String generateRandomSalt(int length) {

        Random random = new Random();

        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < length; i++) {
            salt.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return salt.toString();
    }

    @Override
    public String decodePassword(String encodePassword) {
        StringBuilder sbEncoded = new StringBuilder();
        sbEncoded.append(encodePassword, LENGTH, encodePassword.length() - LENGTH);

        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < sbEncoded.length(); i++) {
            int atomic = 1;
            if (i % 2 == 0) {
                int charInt = sbEncoded.charAt(i) + atomic;
                atomic += ATOMIC_INCREMENT;
                pass.append((char) charInt);
            }
        }
        return pass.toString();
    }

    @Override
    public String encodePassword(String password) {
        StringBuilder sb = new StringBuilder();
        String start = generateRandomSalt(LENGTH);
        sb.append(start);

        for (int i = 0; i < password.length(); i++) {
            int atomic = 1;
            int charInt = password.charAt(i) - atomic;
            atomic += ATOMIC_INCREMENT;
            sb.append((char) charInt);
            sb.append(generateRandomSalt(SALT_LENGTH));

        }

        sb.append(generateRandomSalt(LENGTH));
        return sb.toString();
    }
}
