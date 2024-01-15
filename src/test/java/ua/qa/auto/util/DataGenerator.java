package ua.qa.auto.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    private static final List<String> codes = Arrays.asList("919", "918", "999");
    private static final Random random = new Random();

    public static String generatePhoneNumber() {
        String randomCode = codes.get(random.nextInt(codes.size()));
        return generatePhoneNumber(randomCode);
    }

    public static String generatePhoneNumber(String code) {
        if (!codes.contains(code)) {
            throw new IllegalArgumentException("Invalid phone code: " + code);
        }
        String randomNumber = String.format("%07d", random.nextInt(10_000_00));
        return "+7" + code + randomNumber;
    }
}
