package helper;

import apiHelper.APIAction;
import drivers.DriverManager;
import models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.CourseDetailPage;
import pages.CourseListPage;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;

import static config.urlConfig.*;

public class TestCaseHelper {
    private static final Logger LOG = LogManager.getLogger(TestCaseHelper.class);

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

    public static CourseDetailPage navigateToCourseDetailPage(String courseId) {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        String url = String.format(BASE_URL_PATTERN + COURSE_DETAIL, courseId);
        driver.get(url);
        return new CourseDetailPage(driver);
    }

    public static LoginPage navigateToLoginPage() {
        HomePage homePage = openApplication();
        homePage.getTopNavigation().navigateToLoginPage();
        return new LoginPage(DriverManager.getDriver());
    }

    public static void loginViaAPIAndSyncUI(UserModel user) {
        LOG.info("Performing fast login via API and syncing to UI for user: {}", user.getTaiKhoan());

        WebDriver driver = DriverManager.getDriver();
        driver.get(BASE_URL_PATTERN); // Dùng URL chuẩn từ config

        String userJson = "{"
                + "\"accessToken\":\"" + user.getAccessToken() + "\","
                + "\"taiKhoan\":\"" + user.getTaiKhoan() + "\","
                + "\"email\":\"" + user.getEmail() + "\","
                + "\"soDT\":\"" + user.getSoDT() + "\","
                + "\"maNhom\":\"" + user.getMaNhom() + "\","
                + "\"maLoaiNguoiDung\":\"" + user.getMaLoaiNguoiDung() + "\","
                + "\"hoTen\":\"" + user.getHoTen() + "\""
                + "}";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('credentials', arguments[0]);", userJson);

        driver.navigate().refresh();

        HomePage homePage = new HomePage(driver);
        homePage.getTopNavigation().waitAvatarAppear();
    }

}
