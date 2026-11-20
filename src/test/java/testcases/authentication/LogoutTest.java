
package testcases.authentication;

import base.BaseTest;
import helper.PropertyHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static helper.TestCaseHelper.navigateToLoginPage;


public class LogoutTest extends BaseTest {

    // Dùng ThreadLocal để cô lập LoginPage cho từng luồng riêng biệt
    private final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();

    @BeforeMethod
    public void setupLoginPage() {
        LoginPage page = navigateToLoginPage();
        page.login(PropertyHelper.get("authen.username"), PropertyHelper.get("authen.password"));
        Assert.assertTrue(
                page.isLoginSuccessful(),
                "Precondition failed: Login was not successful"
        );
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
    public void TC_LOGOUT_001_LogoutSuccessfully() {

        getLoginPage().logout();
        // Kiểm tra link Đăng nhập xuất hiện sau khi Logout
        Assert.assertTrue(
                getLoginPage().isLoginLinkDisplayed(),
                "Logout failed: Login link was not displayed"
        );
    }

    @Test
    public void TC_LOGOUT_002_VerifyLogoutButtonDisplayed() {

        // Di chuyển chuột đến avatar
        getLoginPage().hoverAvatar();

        // Kiểm tra nút Logout hiển thị
        Assert.assertTrue(
                getLoginPage().isLogoutButtonDisplayed(),
                "Logout button was not displayed after hovering over avatar"
        );
    }
}
