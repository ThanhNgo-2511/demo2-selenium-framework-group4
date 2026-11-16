package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static helper.TestCaseHelper.logAndReport;

public class PropertyHelper {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyHelper.class
                .getClassLoader()
                .getResourceAsStream("accounts.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Cannot find accounts.properties"
                );
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load accounts.properties", e
            );
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Missing property: " + key
            );
        }

        return value.trim();
    }
}
