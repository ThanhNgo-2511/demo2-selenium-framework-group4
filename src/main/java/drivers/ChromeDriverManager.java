package drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        //WebDriver chỉ chờ HTML và cây DOM hoàn thành. Bỏ qua việc chờ hình ảnh, css, stylesheets.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //options.setBrowserVersion("149");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Tắt popup hỏi lưu mật khẩu (Save Password)
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        setDriver(new ChromeDriver(options));
    }
}
