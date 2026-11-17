
package testcases.authentication;

import base.BaseTest;
import drivers.DriverManager;
import helper.PropertyHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import javax.sound.midi.Soundbank;

import static helper.TestCaseHelper.logAndReport;
import static helper.TestCaseHelper.navigateToLoginPage;


public class LoginTest extends BaseTest {
    
    String username = PropertyHelper.get("authen.username");
    String password = PropertyHelper.get("authen.password");

    // Dùng ThreadLocal để cô lập LoginPage cho từng luồng riêng biệt
    private final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();

    @BeforeMethod
    public void setupLoginPage() {
        LoginPage page = navigateToLoginPage();
        loginPage.set(page); // Lưu instance vào luồng hiện tại
    }

    // Hàm lấy LoginPage của đúng luồng đang chạy
    public LoginPage getLoginPage() {
        return loginPage.get();
    }

    @Test
    public void TC_LOGIN_001_VerifyLoginPageDisplayed() {
        logAndReport("Verify Login Page Displays");
        Assert.assertEquals(
                getLoginPage().getNameForm(),
                "Đăng nhập",
                "Login page is not displayed"
        );
    }

    @Test
    public void TC_LOGIN_002_LoginSuccessfully() {
        getLoginPage().login(username, password);
        logAndReport("Verify Avatar display after login successfully");
        Assert.assertTrue(
                getLoginPage().isLoginSuccessful(),
                "Login was unsuccessful"
        );
    }

    @Test
    public void TC_LOGIN_003_VerifyLoginWithInvalidAccount() {
        getLoginPage().login("Test" + username , password);
        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_004_VerifyLoginWithWrongPassword() {
        getLoginPage().login(username, "Test"+ password);
        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_005_VerifyLoginWithEmptyAccount() {
        getLoginPage().login("", password);
        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_006_VerifyLoginWithEmptyPassword() {
        getLoginPage().login(username, "");
        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }
}
