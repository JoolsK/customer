package ua.qa.auto.util;

import java.util.Random;

public class DataGenerator {

    public static String generatePhoneNumber() {
        String[] codes = {"919", "918", "999"};
        String randomCode = codes[new Random().nextInt(codes.length)];
        return "+7" + randomCode + new Random().nextInt(10_000_000);
    }
}
