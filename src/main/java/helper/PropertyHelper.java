package helper;

import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyHelper.class
                .getClassLoader()
                .getResourceAsStream("accounts.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Cannot find accounts.properties in resources!"
                );
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load accounts.properties", e
            );
        }
    }

    public static String get(String key) {

        // 1. Get value from command line first
        String value = System.getProperty(key);

        // 2. Fallback to properties file
        if (value == null || value.trim().isEmpty()) {
            value = properties.getProperty(key);
        }

        // 3. Validate property
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException(
                    "Missing property: " + key
            );
        }

        return value.trim();
    }
}