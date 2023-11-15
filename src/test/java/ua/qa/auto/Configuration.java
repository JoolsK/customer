package ua.qa.auto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new IOException("CAn't find test.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error", ex);
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
