package drivers;

public class DriverManagerFactory {
    public static DriverManager getDriverManager (String browser) {
        return switch (browser) {
            case "chrome" -> new ChromeDriverManager();
            case "firefox" -> new FireFoxDriverManager();
            case "safari" -> new SafariDriverManager();
            case "edge" -> new EdgeDriverManager();
            default -> null;
        };
    }
}
