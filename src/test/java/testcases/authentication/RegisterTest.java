
package testcases.authentication;

import base.BaseTest;
import helper.PropertyHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;


import static helper.DataHelper.*;
import static helper.TestCaseHelper.*;

public class RegisterTest extends BaseTest {

    String password = PropertyHelper.get("register.password");
    String fullname = PropertyHelper.get("register.fullname");
    String groupId = PropertyHelper.get("register.groupId");

    // Dùng ThreadLocal để cô lập RegisterPage cho từng luồng riêng biệt
    private final ThreadLocal<RegisterPage> registerPage =
            new ThreadLocal<>();

    @BeforeMethod
    public void openRegisterPage() {
        LoginPage loginPage = navigateToLoginPage();
        RegisterPage page = new RegisterPage(loginPage.getDriver());
        page.openRegisterForm();
        registerPage.set(page);// Lưu instance vào luồng hiện tại
    }

    // Hàm lấy RegisterPage của đúng luồng đang chạy
    public RegisterPage getRegisterPage() {
        return registerPage.get();
    }

    @Test
    public void TC_REGISTER_001_VerifyRegisterFormDisplayed() {

        Assert.assertTrue(
                getRegisterPage().isRegisterFormDisplayed(),
                "Register form should be displayed"
        );

        Assert.assertEquals(
                getRegisterPage().getRegisterTitle(),
                "ĐĂNG KÝ",
                "Register title is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_002_RegisterSuccessfully() {
        String account = createRandomAccount();

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(account);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(password);

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(account));

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(createRandomPhone());

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the 'Đăng kí thành công' message displays");
        Assert.assertEquals(
                getRegisterPage().getRegisterSuccessMessage(),
                "Đăng kí thành công",
                "Register was not successful"
        );
    }

    @Test
    public void TC_REGISTER_003_VerifyAccountExceeds16Characters() {
        String invaildAccount = generateAccount(PropertyHelper.getIntProperty("register.account.maxLenght") + 1);

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(invaildAccount);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(password);

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(invaildAccount));

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(createRandomPhone());

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the 'Tài khoản quá 16 kí tự' error message displays");
        Assert.assertEquals(
                getRegisterPage().getAccountErrorMessage(),
                "Tài khoản quá 16 kí tự",
                "The account length validation message is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_004_VerifyAccountRequired() {
        String account = createRandomAccount();
        
        logAndReport("Step 1: Blank on Account");

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(password);

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(account));

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(createRandomPhone());

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the 'Tài khoản quá 16 kí tự' error message displays");

        Assert.assertEquals(
                getRegisterPage().getAccountErrorMessage(),
                "Tài khoản không được để trống",
                "The account required validation message is incorrect"
        );
    }

    @DataProvider(name = "invalidPasswords")
    public Object[][] invalidPasswords() {
        return new Object[][]{
                {"Abc@123"},       // Less than 8 characters
                {"Abcdefgh"},      // Missing number and special character
                {"12345678"},      // Missing letters and special character
                {"Abcdef12"},      // Missing special character
                {"Abc@defg"},      // Missing number
                {"123456@!"},      // Missing letters
                {"!!!!!!!!"},      // Missing letters and numbers
                {"Ab@1"},          // Less than 8 characters
                {"        "},      // Only whitespace
                {"1234567@"},      // Missing letters
                {"abcdefg@"}       // Missing numbers
        };
    }

    @Test(dataProvider = "invalidPasswords")
    public void TC_REGISTER_005_VerifyPasswordInvalid(String invalidPassword) {
        String account = createRandomAccount();

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(account);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(invalidPassword);

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(account));

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(createRandomPhone());

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the 'Mật khẩu phải ít nhất 8 tự gồm chữ, số, và kí tự đặc biệt");
        Assert.assertEquals(
                getRegisterPage().getPasswordErrorMessage(),
                "Mật khẩu phải ít nhất 8 tự gồm chữ, số, và kí tự đặc biệt",
                "The password validation message is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_006_VerifyPasswordRequired() {
        String account = createRandomAccount();

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(account);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Blank on the Password field");

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(account));

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(createRandomPhone());

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the Password required error message displays");
        String actualMessage = getRegisterPage().getPasswordErrorMessage();

        Assert.assertFalse(
                actualMessage.isEmpty(),
                "Password required error message was not displayed"
        );
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] invalidEmails() {
        return new Object[][]{
                {"name"},
                {"name@"},
                {"@gmail.com"},
                {"name@gmail"},
                {"name..ngo@gmail.com"},
                {"name.@gmail.com"},
                {"name ngo@gmail.com"},
                {"name@gmail..com"},
                {"name@.com"}
        };
    }
    @Test(dataProvider = "invalidEmails")
    public void TC_REGISTER_007_VerifyEmailInvalid(String invalidEmail) {
        String account = createRandomAccount();

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(account);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(password);

        logAndReport("Step 4: Enter Email");
        // Cho phép website tự kiểm tra email sai định dạng
        getRegisterPage().setEmailInputTypeText();
        getRegisterPage().enterEmail(invalidEmail);

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(createRandomPhone());

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the 'Email không hợp lệ' error message displays");
        Assert.assertEquals(
                getRegisterPage().getEmailErrorMessage(),
                "Email không hợp lệ",
                "The email validation message is incorrect"
        );
    }

    @DataProvider(name = "invalidPhones")
    public Object[][] invalidPhones() {
        return new Object[][]{
                {"0000000000"},
                {"1"},
                {"abc"}
        };
    }

    @Test(dataProvider = "invalidPhones")
    public void TC_REGISTER_008_VerifyPhoneInvalid(String invalidPhone) {
        String account = createRandomAccount();

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(account);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(password);

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(account));

        logAndReport("Step 5: Enter Phone");
        getRegisterPage().enterPhone(invalidPhone);

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the 'Số điện thoại chưa đúng định đạng' error message displays");
        Assert.assertEquals(
                getRegisterPage().getPhoneErrorMessage(),
                "Số điện thoại chưa đúng định đạng",
                "The phone validation message is incorrect"
        );
    }

    @Test
    public void TC_REGISTER_009_VerifyPhoneRequired() {
        String account = createRandomAccount();

        logAndReport("Step 1: Enter Account");
        getRegisterPage().enterAccount(account);

        logAndReport("Step 2: Enter FullName");
        getRegisterPage().enterFullname(fullname);

        logAndReport("Step 3: Enter Password");
        getRegisterPage().enterPassword(password);

        logAndReport("Step 4: Enter Email");
        getRegisterPage().enterEmail(createRandomEmail(account));

        logAndReport("Step 5: Blank on the Phone field");

        logAndReport("Step 6: Select Group ID");
        getRegisterPage().selectGroup(groupId);

        logAndReport("Step 7: Click Register button");
        getRegisterPage().clickRegister();

        logAndReport("VP: Verify the Phone required error message displays");
        String actualMessage = getRegisterPage().getPhoneRequiredErrorMessage();

        Assert.assertFalse(
                actualMessage.isEmpty(),
                "Phone required error message was not displayed"
        );
    }
}

