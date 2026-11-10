package testcases.course;

import apiHelper.APIAction;
import base.BaseTest;
import drivers.DriverManager;
import models.CourseModel;
import models.UserModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CourseDetailPage;
import pages.CourseListPage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.components.StandardCourseCard;
import pages.modals.CommonModal;

import static apiHelper.APIConfig.*;
import static helper.TestCaseHelper.*;

public class EnrollCourseTest extends BaseTest {
    private static final String VALID_COURSE_ID = "111111111111";

    String username = "thanh.ngo";
    String password = "Asd123!@#";

    @Test(priority = 1,
            description = "TC_ENR_001: Verify Enroll Course When Has Not Logged In")
    public void TC_ENR_001_Verify_Enroll_Course_When_Has_Not_Logged_In() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        logAndReport("Step 2: Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        // Verify Point
        logAndReport("VP1: Verify URL");
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "[TC_ENR_001][FAIL] URL must contains '/login'. Actual: " + currentUrl
        );

        //-------------------------------------------
        logAndReport("VP2: The Name Form is Đăng nhập");
        Assert.assertEquals(loginPage.getNameForm(),
                "Đăng nhập",
                "[TC_ENR_001][FAIL] Does not redircted to Login page "
        );
    }

    @Test(priority = 2,
            description = "TC_ENR_002: Verify Enroll Course When Has Logged In")
    public void TC_ENR_002_Verify_Enroll_Course_When_Has_Logged_In() {
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = api.getRandomCourseId(COURSE_PAGE, COURSE_PAGESIZE, COURSE_GROUPID);

        logAndReport("Pre-condition: User logged in account: " + username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has not enrolled");
        api.leaveCourse(user, courseId);

        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + courseId);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(courseId);

        logAndReport("Step 2: Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();

        try {
            // Verify Point
            logAndReport("VP1: Message \"Đăng ký thành công\" displays");
            CommonModal commonModal = new CommonModal(DriverManager.getDriver());
            String recordedText = commonModal.getMessageText();
            Assert.assertEquals(recordedText,
                    "Đăng kí thành công",
                    "[TC_ENR_002][FAIL]: Enroll Course message is incorrect"
            );

            //-------------------------------------------
            logAndReport("VP2: Verify the course that has courseId = \"" + courseId + "\" is added the chiTietKhoaHocGhiDanh list by API");
            Assert.assertTrue(api.isCourseEnrolledById(user, courseId),
                    "[TC_ENR_002][FAIL]: " + courseId + " not included in chiTietKhoaHocGhiDanh"
            );
        } finally {
            logAndReport("End Test: Remove courseId = \"" + courseId + "\" after test");
            api.leaveCourse(user, courseId);
        }
    }

    @Test(priority = 3,
            description = "TC_ENR_003: Verify re-enrollment in the enrolled course")
    public void TC_ENR_003_Verify_Enroll_In_The_Enrolled_Course() {
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = api.getRandomCourseId(COURSE_PAGE, COURSE_PAGESIZE, COURSE_GROUPID);

        logAndReport("Pre-condition: User logged in account: " + username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has enrolled");
        api.enrollCourse(user, courseId);

        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + courseId);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(courseId);

        logAndReport("Step 2: Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();

        try {
            // Verify Point
            logAndReport("VP1: Message \"Đã đăng ký khóa học này rồi!\" displays ");
            CommonModal commonModal = new CommonModal(DriverManager.getDriver());
            String recordedText = commonModal.getMessageText();
            Assert.assertEquals(recordedText,
                    "Đã đăng ký khóa học này rồi!",
                    "[TC_ENR_003][FAIL]: Enroll Course message is incorrect");

            //-------------------------------------------
            logAndReport("VP2: Verify the enrolled course is still in the chiTietKhoaHocGhiDanh list by API ");
            Assert.assertTrue(
                    api.isCourseEnrolledById(user, courseId),
                    "[TC_ENR_003][FAIL]: " + courseId + "not included in chiTietKhoaHocGhiDanh"
            );
        } finally {

            logAndReport("End Test: Leave courseId = \"" + courseId + "\" after test");
            api.leaveCourse(user, courseId);
        }
    }

    @Test(priority = 4,
            description = "TC_ENR_004: Verify enrolled course is added to \"Khóa học\" (My Courses) list")
    public void TC_ENR_004_Verify_Enroll_Course_Is_Add_My_Course() {
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = VALID_COURSE_ID;

        logAndReport("Pre-condition: User logged in account: " + username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has not enrolled");
        api.leaveCourse(user, courseId);

        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + courseId);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(courseId);
        String courseName = courseDetailPage.getCourseName();

        logAndReport("Step 2: Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();

        CommonModal commonModal = new CommonModal(DriverManager.getDriver());
        commonModal.waitModalAppear();
        commonModal.waitModalDisappear();

        try {
            logAndReport("VP: Verify enrolled course is added to \"Khóa học\" (My Courses) list");
            courseDetailPage.getTopNavigation().navigateToProfilePage();
            ProfilePage profilePage = new ProfilePage(DriverManager.getDriver());
            profilePage.clickCoursesTab();
            Assert.assertTrue(
                    profilePage.isCourseNameInCourseList(courseName),
                    "[TC_ENR_004][FAIL]: " + courseName + " isn't added to \"Khóa học\" (My Courses) list"
            );
        } finally {
            logAndReport("End Test: Remove courseId = \"" + courseId + "\" after test");
            api.leaveCourse(user, courseId);
        }
    }

}