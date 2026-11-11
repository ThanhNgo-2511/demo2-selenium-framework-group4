
package testcases.course;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

import static helper.TestCaseHelper.navigateToLoginPage;

public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeMethod
    public void loginBeforeTest() {

        // mở trang Login
        loginPage = navigateToLoginPage();

        // lấy Webdriver
        driver = loginPage.getDriver();

        // dăng nhập trước khi kiểm tra Logout
        loginPage.login("thua358", "@123456Aa");

        // chờ avatar xuất hiện
        loginPage.getTopNavigation().waitAvatarAppear();
    }
    @Test
    public void TC_LOGOUT_001_LogoutSuccessfully() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
        // tìm avatar
        By avatarLocator = By.cssSelector("img.avatar");

        WebElement avatar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(avatarLocator)
        );

        // di chuột vào avatar để hiển thị nút Logout
        Actions actions = new Actions(driver);

        actions.moveToElement(avatar)
                .pause(Duration.ofSeconds(1))
                .perform();

        // tìm nút logout theo HTML thực tế
        By logoutLocator = By.cssSelector(
                "span.logout a[href='/trangchu']"
        );

        WebElement logoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(logoutLocator)
        );

        // click logout
        logoutButton.click();

        // chờ avatar biến mất sau khi đăng xuất
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        avatarLocator
                )
        );

        // kiểm tra link đăng nhập xuất hiện
        By loginLocator = By.xpath("//a[text()='Đăng nhập']");

        boolean loginDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginLocator)
        ).isDisplayed();

        Assert.assertTrue(
                loginDisplayed,
                "Logout failed: Login link is not displayed"
        );
    }
}
