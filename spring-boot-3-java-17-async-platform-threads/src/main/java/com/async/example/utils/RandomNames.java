package com.async.example.utils;

import java.util.Random;

public class RandomNames {
    private static final String[] firstNames = {
        "Mateo", "Sofía", "Valentín", "Renata", "Thiago", "Isabella", "Tomás", "Martina", "Bautista", "Mía"
    };

    private static final String[] lastNames = {
        "González", "Rodríguez", "Gómez", "Fernández", "López", "Díaz", "Martínez", "Pérez", "García", "Sánchez"
    };

    public static String generateRandomName() {
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }
}