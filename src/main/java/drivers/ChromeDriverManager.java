package drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        //WebDriver chỉ chờ HTML và cây DOM hoàn thành. Bỏ qua việc chờ hình ảnh, css, stylesheets.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //options.setBrowserVersion("149");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        setDriver(new ChromeDriver(options));
    }
}
