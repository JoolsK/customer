package ua.qa.auto.util;

import java.util.Arrays;
import java.util.Random;

public class DataGenerator {
    public DataGenerator() {
    }
    
    static String[] codes = {"919", "918", "999"};
    public static String generatePhoneNumberWithoutWxception() {
        Random random = new Random();
        String randomCode = codes[random.nextInt(codes.length)];
        String randomNumber = String.format("%07d", random.nextInt(10_000_00));

        return "+7" + randomCode + randomNumber;
    }

    public static String generatePhoneNumber(String code) {
        if (!Arrays.asList(codes).contains(code)) {
            throw new IllegalArgumentException("Invalid phone code: " + code);
        }
        String randomNumber = String.format("%07d", new Random().nextInt(10_000_00));
        return "+7" + code + randomNumber;
    }

}
