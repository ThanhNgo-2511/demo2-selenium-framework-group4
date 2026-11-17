
package testcases.course;

import base.BaseTest;
import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static helper.TestCaseHelper.navigateToLoginPage;

@Test(singleThreaded = true)
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupLoginPage() {
        WebDriver driver = DriverManager.getDriverOrNull();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void TC_LOGIN_001_VerifyLoginPageDisplayed() {
        navigateToLoginPage();

        Assert.assertEquals(
                loginPage.getNameForm(),
                "Đăng nhập",
                "Login page is not displayed"
        );
    }

    @Test
    public void TC_LOGIN_002_LoginSuccessfully() {

        // Mở trang Login và nhận đối tượng LoginPage
        loginPage = navigateToLoginPage();

        // Đăng nhập bằng thông tin giống hệt LogoutTest
        loginPage.login("thua358", "@123456Aa");

        // Kiểm tra đăng nhập thành công
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login was unsuccessful"
        );
    }

    @Test
    public void TC_LOGIN_003_VerifyLoginWithInvalidAccount() {
        navigateToLoginPage();

        loginPage.login("account_not_exist_123", "Auto@123456");

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_004_VerifyLoginWithWrongPassword() {
        navigateToLoginPage();

        loginPage.login("TAI_KHOAN_CUA_BAN", "WrongPassword123");

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_005_VerifyLoginWithEmptyAccount() {
        navigateToLoginPage();

        loginPage.login("", "Auto@123456");

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_006_VerifyLoginWithEmptyPassword() {
        navigateToLoginPage();

        loginPage.login("TAI_KHOAN_CUA_BAN", "");

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }


}
