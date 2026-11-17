
package testcases.course;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;

import static helper.TestCaseHelper.navigateToLoginPage;

@Test(singleThreaded = true)
public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    public void openRegisterPage() {
        LoginPage loginPage = navigateToLoginPage();

        registerPage = new RegisterPage(loginPage.getDriver());

        registerPage.openRegisterForm();
    }

    @Test
    public void TC_REGISTER_001_VerifyRegisterFormDisplayed() {

        Assert.assertTrue(
                registerPage.isRegisterFormDisplayed(),
                "Register form should be displayed"
        );

        Assert.assertEquals(
                registerPage.getRegisterTitle(),
                "ĐĂNG KÝ",
                "Register title is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_002_RegisterSuccessfully() {

        long uniqueId = System.currentTimeMillis();

        String account = "auto" + String.valueOf(uniqueId).substring(8);
        String password = "Auto@123456";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "09" + String.valueOf(uniqueId).substring(5);

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        Assert.assertEquals(
                registerPage.getRegisterSuccessMessage(),
                "Đăng kí thành công",
                "Register was not successful"
        );
    }

    @Test
    public void TC_REGISTER_003_VerifyAccountExceeds16Characters() {

        long uniqueId = System.currentTimeMillis();

        String account = "abcdefghijklmnopq";
        String password = "Auto@123456";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "09" + String.valueOf(uniqueId).substring(5);

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        Assert.assertEquals(
                registerPage.getAccountErrorMessage(),
                "Tài khoản quá 16 kí tự",
                "The account length validation message is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_004_VerifyAccountRequired() {

        long uniqueId = System.currentTimeMillis();

        String password = "Auto@123456";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "09" + String.valueOf(uniqueId).substring(5);

        registerPage.register(
                "",
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        Assert.assertEquals(
                registerPage.getAccountErrorMessage(),
                "Tài khoản không được để trống",
                "The account required validation message is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_005_VerifyPasswordInvalid() {

        long uniqueId = System.currentTimeMillis();

        String account = "auto" + String.valueOf(uniqueId).substring(8);
        String password = "123";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "09" + String.valueOf(uniqueId).substring(5);

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        Assert.assertEquals(
                registerPage.getPasswordErrorMessage(),
                "Mật khẩu phải ít nhất 8 tự gồm chữ, số, và kí tự đặc biệt",
                "The password validation message is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_006_VerifyPasswordRequired() {

        long uniqueId = System.currentTimeMillis();

        String account = "auto" + String.valueOf(uniqueId).substring(8);
        String password = "";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "09" + String.valueOf(uniqueId).substring(5);

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        String actualMessage = registerPage.getPasswordErrorMessage();

        Assert.assertFalse(
                actualMessage.isEmpty(),
                "Password required error message was not displayed"
        );
    }

    @Test
    public void TC_REGISTER_007_VerifyEmailInvalid() {

        long uniqueId = System.currentTimeMillis();

        String account = "auto" + String.valueOf(uniqueId).substring(8);
        String password = "Auto@123456";
        String fullname = "Test Automation";
        String email = "abc123";
        String phone = "09" + String.valueOf(uniqueId).substring(5);

        // Cho phép website tự kiểm tra email sai định dạng
        registerPage.setEmailInputTypeText();

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        Assert.assertEquals(
                registerPage.getEmailErrorMessage(),
                "Email không hợp lệ",
                "The email validation message is incorrect"
        );
    }
    @Test
    public void TC_REGISTER_008_VerifyPhoneInvalid() {

        long uniqueId = System.currentTimeMillis();

        String account = "auto" + String.valueOf(uniqueId).substring(8);
        String password = "Auto@123456";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "123";

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        Assert.assertEquals(
                registerPage.getPhoneErrorMessage(),
                "Số điện thoại chưa đúng định đạng",
                "The phone validation message is incorrect"
        );
    }
    @Test
    public void TC_REGISTER_009_VerifyPhoneRequired() {

        long uniqueId = System.currentTimeMillis();

        String account = "auto" + String.valueOf(uniqueId).substring(8);
        String password = "Auto@123456";
        String fullname = "Test Automation";
        String email = "auto" + uniqueId + "@gmail.com";
        String phone = "";

        registerPage.register(
                account,
                password,
                fullname,
                email,
                phone,
                "GP01"
        );

        String actualMessage = registerPage.getPhoneRequiredErrorMessage();

        Assert.assertFalse(
                actualMessage.isEmpty(),
                "Phone required error message was not displayed"
        );
    }
}
