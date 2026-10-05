package helper;

import base.BaseTest;
import drivers.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.CourseListPage;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;

import static config.urlConfig.BASE_URL_PATTERN;

public class TestHelper {
    private static final Logger LOG = LogManager.getLogger(TestHelper.class);

    public static void logAndReport(String message) {
        LOG.info(message);
        ExtentReportManager.info(message);
    }

    public static HomePage openApplication() {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL_PATTERN);
        return new HomePage(driver);
    }

    public static CourseListPage navigateToCourseListPage() {
        HomePage homePage = openApplication();
        logAndReport("User is at Course List page");
        homePage.getTopNavigation().navigateToCourseListPage();
        return new CourseListPage(DriverManager.getDriver());
    }

    public static LoginPage navigateToLoginPage() {
        HomePage homePage = openApplication();
        logAndReport("User is at Login page");
        homePage.getTopNavigation().navigateToLoginPage();
        return new LoginPage(DriverManager.getDriver());
    }
}
