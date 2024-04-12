package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Secrets {

    public static String getSecretByName(String secretName) {

        final Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/test/resources/test-automation-secrets.properties"));
        } catch (IOException ioe) {
            System.err.println("Could not load properties");
        }

        return properties.getProperty(secretName);
    }
}
