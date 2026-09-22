package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.component.TopNavigation;
import pages.modals.CommonModal;

public class TC02_Login extends BaseTest {

    @Test
    public void Verify_Valid_Login() {
        // Mo full man hinh
        driver.manage().window().maximize();
        // Mo trang https://demo1.cybersoft.edu.vn/
        driver.get("https://demo1.cybersoft.edu.vn");

        //Khoi tao cho page
        HomePage homePage = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        CommonModal commonModal = new CommonModal(driver);
        TopNavigation menu = new TopNavigation(driver);

        //Pre-condition: Click to "Đănh Nhập" link
        homePage.getTopNavigation().navigateToLoginPage();

        //Login account and password
        String account = "dba9161e-9bbd-41ce-9fbb-bd40f040fc1e";
        login.login(account,"123456");

        // Verify login successfully
        //VP1: "Đăng nhập thành công" dialog message displays

        String recordedTextLogin = commonModal.getMessageText();
        Assert.assertEquals(recordedTextLogin, "Đăng nhập thành công", "Register message is incorrect");
        commonModal.waitModalDisappear();// Đợi popup đóng để có thể tương tác tiếp

        //VP2: User profile dislays on the top right
        menu.waitAvatarAppear();
        menu.assertAvatarAppear();

        //VP3: Logout link displays
        menu.waitLogoutButtonAppear();
        menu.assertLogoutButtonAppear();

    }

    @Test
    public void Verify_Invalid_Login() {
        // Mo full man hinh
        driver.manage().window().maximize();
        // Mo trang https://demo1.cybersoft.edu.vn/
        driver.get("https://demo1.cybersoft.edu.vn");

        //Khoi tao cho page
        HomePage homePage = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        CommonModal commonModal = new CommonModal(driver);
        TopNavigation menu = new TopNavigation(driver);

        //Pre-condition: Click to "Đănh Nhập" link
        homePage.getTopNavigation().navigateToLoginPage();

        //Login account and password
        String account = "invalid";
        login.login(account,"123456");

        //Verify

    }


}