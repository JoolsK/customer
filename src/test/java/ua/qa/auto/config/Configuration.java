package ua.qa.auto.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final Properties properties = new Properties();
    private static final String configurationFilePath = "test.properties";

    static {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream(configurationFilePath)) {
            if (input == null) {
                throw new ConfigurationException("Can't find " + configurationFilePath);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new ConfigurationException("Error loading configuration", ex);
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
