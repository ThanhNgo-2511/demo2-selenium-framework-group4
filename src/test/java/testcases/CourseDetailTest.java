package testcases;

import base.BaseTest;
import constants.TimeoutConstant;
import drivers.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CourseDetailPage;
import pages.CourseListPage;
import pages.LoginPage;
import pages.modals.CommonModal;

import static helper.TestHelper.*;

public class CourseDetailTest extends BaseTest {
    @Test
    public void Verify_Register_Course_When_Has_Not_Logined() {
        CourseListPage courseListPage = navigateToCourseListPage();
        logAndReport("Click on the second card");
        courseListPage.clickCourseCard (2);
        logAndReport("The detail page opens");
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
        logAndReport("Click on \"Đăng Ký\" button");
        courseDetailPage.clickRegisterButton();
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        logAndReport("Verify Point: The Name Form is Đăng nhập");
        Assert.assertEquals(loginPage.getNameForm(),"Đăng nhập", "Does not redircted to Login page ");
    }

    @Test
    public void Verify_Register_Course_When_Has_Logined() {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.login("thanh.ngo", "Asd123!@#");
        CourseListPage courseListPage = navigateToCourseListPage();
        logAndReport("Click on the second card");
        courseListPage.clickCourseCard (2);
        logAndReport("The detail page opens");
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
        logAndReport("Click on \"Đăng Ký\" button");
        courseDetailPage.clickRegisterButton();
        CommonModal commonModal = new CommonModal(DriverManager.getDriver());
        String recordedText = commonModal.getMessageText();
        Assert.assertEquals(recordedText, "Đăng kí thành công", "Register Course message is incorrect");
        // Call API để xóa course đã đăng ký
    }

    @Test
    public void Verify_The_Course_That_Was_Registered() {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.login("thanh.ngo", "Asd123!@#");
        CourseListPage courseListPage = navigateToCourseListPage();
        logAndReport("Click on the second card");
        courseListPage.clickCourseCard (3);
        logAndReport("The detail page opens");
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
        logAndReport("Click on \"Đăng Ký\" button");

        courseDetailPage.clickRegisterButton();
        CommonModal commonModal = new CommonModal(DriverManager.getDriver());
        commonModal.waitModalAppear();
        commonModal.waitModalDisappear();

        logAndReport("Click on \"Đăng Ký\" button again");
        courseDetailPage.clickRegisterButton();
        String recordedText = commonModal.getMessageText();
        Assert.assertEquals(recordedText, "Đã đăng ký khóa học này rồi!", "Register Course message is incorrect");
        // Call API để xóa course đã đăng ký
    }

    //Đã đăng ký khóa học này rồi!
}
