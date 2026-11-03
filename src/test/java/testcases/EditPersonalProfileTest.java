package testcases;

import base.BaseTest;
import drivers.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

import static helper.TestHelper.navigateToLoginPage;
import static helper.TestHelper.logAndReport;

public class EditPersonalProfileTest extends BaseTest {
    private ProfilePage profilePage;

    @BeforeMethod
    public void loginAndOpenEditProfile() {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.login("thua358", "@123456Aa");
        loginPage.getTopNavigation().waitAvatarAppear();
        loginPage.getTopNavigation().navigateToProfilePage();
        profilePage = new ProfilePage(DriverManager.getDriver());
        profilePage.waitForProfileLoaded();
        profilePage.openEditPopup();
        logAndReport("Opened Edit Personal Profile popup");
    }

    @Test
    public void TC_EP_001_OpenEditProfilePopupSuccessfully() {
        logAndReport("Verify Edit Profile popup is displayed");
        Assert.assertTrue(profilePage.isEditPopupVisible(), "Edit Profile popup should be displayed");
        Assert.assertTrue(profilePage.getEditPopupTitle().toLowerCase().contains("chỉnh sửa"),
                "Edit Profile popup title should be displayed");
    }

    @Test
    public void TC_EP_002_VerifyCurrentInformationDisplayed() {
        logAndReport("Verify all profile fields are displayed in the popup");
        Assert.assertTrue(profilePage.areEditFieldsVisible(),
                "Full Name, Password, Email and Phone Number fields should be displayed");
    }

    @Test
    public void TC_EP_004_ValidateRequiredFields() {
        logAndReport("Clear all fields and submit the edit form");
        profilePage.setFieldValue("hoTen", "");
        profilePage.setFieldValue("matKhau", "");
        profilePage.setFieldValue("email", "");
        profilePage.setFieldValue("soDT", "");
        profilePage.clickSubmit();
        Assert.assertTrue(profilePage.isEditPopupVisible(),
                "Edit Profile popup should remain open when required fields are empty");
        Assert.assertEquals(profilePage.getValidationMessageContainerCount(), 4,
                "A validation message container should exist for each required field");
    }

    @Test
    public void TC_EP_006_ValidateEmptyNameFormat() {
        logAndReport("Clear Full Name and trigger field validation");
        profilePage.setFieldValue("hoTen", "");
        profilePage.blurField("hoTen");
        Assert.assertTrue(profilePage.getValidationMessage(0).contains("Tên không được để trống"),
                "Empty name validation message should match the manual test case");
    }

    @Test
    public void TC_EP_007_ValidateEmptyPasswordFormat() {
        logAndReport("Clear Password and trigger field validation");
        profilePage.setFieldValue("matKhau", "");
        profilePage.blurField("matKhau");
        Assert.assertTrue(profilePage.getValidationMessage(1).contains("Mật khẩu không được để trống"),
                "Empty password validation message should match the manual test case");
    }

    @Test
    public void TC_EP_008_ValidateEmptyEmailFormat() {
        logAndReport("Clear Email and trigger field validation");
        profilePage.setFieldValue("email", "");
        profilePage.blurField("email");
        Assert.assertTrue(profilePage.getValidationMessage(2).contains("Email không được để trống"),
                "Empty email validation message should match the manual test case");
    }

    @Test
    public void TC_EP_009_ValidateEmptyPhoneNumberFormat() {
        logAndReport("Clear Phone Number and trigger field validation");
        profilePage.setFieldValue("soDT", "");
        profilePage.blurField("soDT");
        Assert.assertTrue(profilePage.getValidationMessage(3).contains("Số điện thoại không được để trống"),
                "Empty phone validation message should match the manual test case");
    }

    @Test
    public void TC_EP_010_ValidateInvalidNameFormat() {
        logAndReport("Enter an invalid name containing digits and trigger validation");
        profilePage.setFieldValue("hoTen", "12345");
        profilePage.blurField("hoTen");
        Assert.assertTrue(profilePage.getValidationMessage(0).contains("Chỉ nhập kí tự chữ"),
                "Invalid name validation message should match the manual test case");
    }

    @Test
    public void TC_EP_012_ValidateInvalidEmailFormat() {
        logAndReport("Enter an invalid email and trigger validation");
        profilePage.setFieldValue("email", "invalid-email");
        profilePage.blurField("email");
        Assert.assertTrue(profilePage.getValidationMessage(2).contains("Email không hợp lệ"),
                "Invalid email validation message should match the manual test case");
    }

    @Test
    public void TC_EP_014_ClosePopupSuccessfully() {
        logAndReport("Close Edit Profile popup");
        profilePage.closeEditPopup();
        Assert.assertTrue(profilePage.isEditPopupClosed(), "Edit Profile popup should be closed");
    }
}
