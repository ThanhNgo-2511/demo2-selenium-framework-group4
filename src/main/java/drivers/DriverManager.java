package drivers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    protected static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriverOrNull() {
        return DRIVER.get();
    }

    public static WebDriver getDriver() {
        WebDriver driver = getDriverOrNull();
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized for this thread");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = getDriverOrNull();
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }

    public abstract void createWebDriver();

}
