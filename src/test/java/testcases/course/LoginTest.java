
package testcases.course;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static helper.TestCaseHelper.navigateToLoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        loginPage = navigateToLoginPage();
    }

    @Test
    public void TC_LOGIN_001_VerifyLoginPageDisplayed() {
        Assert.assertEquals(
                loginPage.getNameForm(),
                "Đăng nhập",
                "Login page should be displayed"
        );
    }

    @Test
    public void TC_LOGIN_002_LoginSuccessfully() {
        loginPage.login("thua358", "@123456Aa");

        loginPage.getTopNavigation().waitAvatarAppear();

        loginPage.getTopNavigation().assertAvatarAppear();
    }
}
