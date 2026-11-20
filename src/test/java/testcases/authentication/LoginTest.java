
package testcases.authentication;

import base.BaseTest;
import helper.PropertyHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

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

    @AfterMethod(alwaysRun = true)
    public void cleanUpLoginPage() {
        loginPage.remove();
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
        logAndReport("Step 1: Enter account");
        getLoginPage().enterAccount(username);

        logAndReport("Step 2: Enter password");
        getLoginPage().enterPassword(password);

        logAndReport("Step 3: Click on the Login button");
        getLoginPage().clickLogin();

        logAndReport("Verify Avatar display after login successfully");
        Assert.assertTrue(
                getLoginPage().isLoginSuccessful(),
                "Login was unsuccessful"
        );
    }

    @Test
    public void TC_LOGIN_003_VerifyLoginWithInvalidAccount() {
        logAndReport("Step 1: Enter account");
        getLoginPage().enterAccount("Test" + username);

        logAndReport("Step 2: Enter password");
        getLoginPage().enterPassword(password);

        logAndReport("Step 3: Click on the Login button");
        getLoginPage().clickLogin();

        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_004_VerifyLoginWithWrongPassword() {
        logAndReport("Step 1: Enter account");
        getLoginPage().enterAccount(username);

        logAndReport("Step 2: Enter password");
        getLoginPage().enterPassword("Test"+password);

        logAndReport("Step 3: Click on the Login button");
        getLoginPage().clickLogin();

        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_005_VerifyLoginWithEmptyAccount() {
        logAndReport("Step 1: Blank on the account field");

        logAndReport("Step 2: Enter password");
        getLoginPage().enterPassword(password);

        logAndReport("Step 3: Click on the Login button");
        getLoginPage().clickLogin();

        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }

    @Test
    public void TC_LOGIN_006_VerifyLoginWithEmptyPassword() {
        logAndReport("Step 1: Enter account");
        getLoginPage().enterAccount(username);

        logAndReport("Step 2: Blank on the password field ");

        logAndReport("Step 3: Click on the Login button");
        getLoginPage().clickLogin();

        logAndReport("Verify the message: 'Tài khoản hoặc mật khẩu không đúng!' displays");
        Assert.assertEquals(
                getLoginPage().getLoginErrorMessage(),
                "Tài khoản hoặc mật khẩu không đúng!",
                "Login error message is incorrect"
        );
    }
}
