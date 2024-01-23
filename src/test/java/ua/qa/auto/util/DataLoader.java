package ua.qa.auto.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<String> readFromFile(String filePath) {
        List<String> phoneNumbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] result = line.split("/");
                for (String token:result) {
                    System.out.println(token);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: ", e);
        }
        return phoneNumbers;
    }

    public static String readFromResources(String resourcePath) {
        try (InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: ", e);
        }
    }
}
