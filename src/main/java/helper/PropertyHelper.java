package helper;

import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyHelper.class
                .getClassLoader()
                .getResourceAsStream("data.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Cannot find data.properties in resources!"
                );
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load data.properties", e
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

    public static int getIntProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException(
                    "Property '" + key + "' was not found or its value is empty."
            );
        }

        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    "Property '" + key + "' must be a valid integer. Actual value: " + value,
                    e
            );
        }
    }
}