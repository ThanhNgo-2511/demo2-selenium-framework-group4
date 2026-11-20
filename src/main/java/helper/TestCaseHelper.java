package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import drivers.DriverManager;
import models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.*;
import reports.ExtentReportManager;

import static config.urlConfig.*;

public class TestCaseHelper {
    private static final Logger LOG = LogManager.getLogger(TestCaseHelper.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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
        logAndReport("Open the Course List Page");
        homePage.getTopNavigation().navigateToCourseListPage();
        return new CourseListPage(DriverManager.getDriver());
    }

    public static CourseDetailPage navigateToCourseDetailPage(String courseId) {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        String url = String.format(BASE_URL_PATTERN + COURSE_DETAIL, courseId);
        driver.get(url);
        return new CourseDetailPage(driver);
    }

    public static LoginPage navigateToLoginPage() {
        HomePage homePage = openApplication();
        logAndReport("Open the Login Page");
        homePage.getTopNavigation().navigateToLoginPage();
        return new LoginPage(DriverManager.getDriver());
    }

    public static ProfilePage navigateToProfilePage() {
        HomePage homePage = openApplication();
        homePage.getTopNavigation().navigateToProfilePage();
        return new ProfilePage(DriverManager.getDriver());
    }

    public static void injectUserAndSyncUI(UserModel user) {
        if (user == null) {
            throw new IllegalArgumentException("UserModel cannot be null");
        }
        WebDriver driver = DriverManager.getDriver();
        driver.get(BASE_URL_PATTERN); // Dùng URL chuẩn từ config

        logAndReport("Set user credentials into localStorage for user: " + user.getTaiKhoan());
        try {
            String userJson = OBJECT_MAPPER.writeValueAsString(user);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.localStorage.setItem('credentials', arguments[0]);", userJson);

            driver.navigate().refresh();
            HomePage homePage = new HomePage(driver);
            homePage.getTopNavigation().waitAvatarAppear();
        } catch (JsonProcessingException e) {
            LOG.error("Failed to serialize UserModel to JSON", e);
            throw new RuntimeException("Không thể chuyển đổi UserModel sang chuỗi JSON", e);
        }
    }
}
