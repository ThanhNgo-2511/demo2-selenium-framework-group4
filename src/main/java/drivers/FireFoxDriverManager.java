package drivers;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverManager extends DriverManager {
    @Override
    public void createWebDriver() {
        setDriver(new FirefoxDriver());
    }
}
