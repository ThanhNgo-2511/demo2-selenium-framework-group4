package testcases.personalProfile;

import base.BaseTest;
import drivers.DriverManager;
import helper.PropertyHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import static helper.TestCaseHelper.*;

public class EditPersonalProfileTest extends BaseTest {
    String username = PropertyHelper.get("profile.username");
    String password = PropertyHelper.get("profile.password");

    // Sử dụng ThreadLocal để cô lập ProfilePage cho từng luồng riêng biệt
    private final ThreadLocal<ProfilePage> profilePage = new ThreadLocal<>();

    @BeforeMethod
    public void loginAndOpenEditProfile() {
        LoginPage loginPage = navigateToLoginPage();

        loginPage.login(username, password);
        loginPage.getTopNavigation().waitAvatarAppear();
        loginPage.getTopNavigation().navigateToProfilePage();

        // Khởi tạo và gán ProfilePage cho luồng hiện tại
        ProfilePage page = new ProfilePage(DriverManager.getDriver());
        page.waitForProfileLoaded();
        page.openEditPopup();

        profilePage.set(page);
        logAndReport("Opened Edit Personal Profile popup");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUpProfilePage() {
        profilePage.remove();
    }

    // Hàm tiện ích lấy ProfilePage của đúng luồng đang chạy
    public ProfilePage getProfilePage() {
        return profilePage.get();
    }

    @Test
    public void TC_EP_001_OpenEditProfilePopupSuccessfully() {
        logAndReport("Verify Edit Profile popup is displayed");
        Assert.assertTrue(getProfilePage().isEditPopupVisible(), "Edit Profile popup should be displayed");
        Assert.assertTrue(getProfilePage().getEditPopupTitle().toLowerCase().contains("chỉnh sửa"),
                "Edit Profile popup title should be displayed");
    }

    @Test
    public void TC_EP_002_VerifyCurrentInformationDisplayed() {
        logAndReport("Verify all profile fields are displayed in the popup");
        Assert.assertTrue(getProfilePage().areEditFieldsVisible(),
                "Full Name, Password, Email and Phone Number fields should be displayed");
    }

    @Test
    public void TC_EP_004_ValidateRequiredFields() {
        logAndReport("Clear all fields and submit the edit form");
        getProfilePage().setFieldValue("hoTen", "");
        getProfilePage().setFieldValue("matKhau", "");
        getProfilePage().setFieldValue("email", "");
        getProfilePage().setFieldValue("soDT", "");
        getProfilePage().clickSubmit();
        Assert.assertTrue(getProfilePage().isEditPopupVisible(),
                "Edit Profile popup should remain open when required fields are empty");
        Assert.assertEquals(getProfilePage().getValidationMessageContainerCount(), 4,
                "A validation message container should exist for each required field");
    }

    @Test
    public void TC_EP_006_ValidateEmptyNameFormat() {
        logAndReport("Clear Full Name and trigger field validation");
        getProfilePage().setFieldValue("hoTen", "");
        getProfilePage().blurField("hoTen");
        Assert.assertTrue(getProfilePage().getValidationMessage(0).contains("Tên không được để trống"),
                "Empty name validation message should match the manual test case");
    }

    @Test
    public void TC_EP_007_ValidateEmptyPasswordFormat() {
        logAndReport("Clear Password and trigger field validation");
        getProfilePage().setFieldValue("matKhau", "");
        getProfilePage().blurField("matKhau");
        Assert.assertTrue(getProfilePage().getValidationMessage(1).contains("Mật khẩu không được để trống"),
                "Empty password validation message should match the manual test case");
    }

    @Test
    public void TC_EP_008_ValidateEmptyEmailFormat() {
        logAndReport("Clear Email and trigger field validation");
        getProfilePage().setFieldValue("email", "");
        getProfilePage().blurField("email");
        Assert.assertTrue(getProfilePage().getValidationMessage(2).contains("Email không được để trống"),
                "Empty email validation message should match the manual test case");
    }

    @Test
    public void TC_EP_009_ValidateEmptyPhoneNumberFormat() {
        logAndReport("Clear Phone Number and trigger field validation");
        getProfilePage().setFieldValue("soDT", "");
        getProfilePage().blurField("soDT");
        Assert.assertTrue(getProfilePage().getValidationMessage(3).contains("Số điện thoại không được để trống"),
                "Empty phone validation message should match the manual test case");
    }

    @Test
    public void TC_EP_010_ValidateInvalidNameFormat() {
        logAndReport("Enter an invalid name containing digits and trigger validation");
        getProfilePage().setFieldValue("hoTen", "12345");
        getProfilePage().blurField("hoTen");
        Assert.assertTrue(getProfilePage().getValidationMessage(0).contains("Chỉ nhập kí tự chữ"),
                "Invalid name validation message should match the manual test case");
    }

    @Test
    public void TC_EP_012_ValidateInvalidEmailFormat() {
        logAndReport("Enter an invalid email and trigger validation");
        getProfilePage().setFieldValue("email", "invalid-email");
        getProfilePage().blurField("email");
        Assert.assertTrue(getProfilePage().getValidationMessage(2).contains("Email không hợp lệ"),
                "Invalid email validation message should match the manual test case");
    }

    @Test
    public void TC_EP_014_ClosePopupSuccessfully() {
        logAndReport("Close Edit Profile popup");
        getProfilePage().closeEditPopup();
        Assert.assertTrue(getProfilePage().isEditPopupClosed(), "Edit Profile popup should be closed");
    }
}
