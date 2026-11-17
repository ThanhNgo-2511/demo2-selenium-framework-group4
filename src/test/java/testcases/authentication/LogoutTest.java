
package testcases.course;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static helper.TestCaseHelper.navigateToLoginPage;

@Test(singleThreaded = true)
public class LogoutTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void loginBeforeTest() {

        // Mở trang Login
        loginPage = navigateToLoginPage();

        // Đăng nhập trước khi kiểm tra Logout
        loginPage.login("thua358", "@123456Aa");

        // Kiểm tra đăng nhập thành công
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Precondition failed: Login was not successful"
        );
    }

    @Test
    public void TC_LOGOUT_001_LogoutSuccessfully() {

        // Di chuyển chuột đến avatar và nhấn Logout
        loginPage.logout();

        // Kiểm tra link Đăng nhập xuất hiện sau khi Logout
        Assert.assertTrue(
                loginPage.isLoginLinkDisplayed(),
                "Logout failed: Login link was not displayed"
        );
    }

    @Test
    public void TC_LOGOUT_002_VerifyLogoutButtonDisplayed() {

        // Di chuyển chuột đến avatar
        loginPage.hoverAvatar();

        // Kiểm tra nút Logout hiển thị
        Assert.assertTrue(
                loginPage.isLogoutButtonDisplayed(),
                "Logout button was not displayed after hovering over avatar"
        );
    }


    @Test
    public void TC_LOGOUT_003_VerifyUserLoggedOutSuccessfully() {

        // Thực hiện Logout
        loginPage.logout();

        // Kiểm tra link Đăng nhập xuất hiện
        Assert.assertTrue(
                loginPage.isLoginLinkDisplayed(),
                "Logout failed: Login link was not displayed"
        );
    }

}
