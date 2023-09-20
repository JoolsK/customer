package ua.qa.auto.util;

import java.util.Random;

public class DataGenerator {
    static String[] codes = {"919", "918", "999"};
    public static String generatePhoneNumber() {
        Random random = new Random();
        String randomCode = codes[random.nextInt(codes.length)];
        String randomNumber = String.format("%07d", random.nextInt(10_000_00));

        return "+7" + randomCode + randomNumber;
    }
}
