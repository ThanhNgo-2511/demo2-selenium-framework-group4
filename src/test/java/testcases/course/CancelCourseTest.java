package testcases.course;

import apiHelper.APIAction;
import base.BaseTest;
import models.UserModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProfilePage;
import pages.components.RegisteredCourseCard;

import java.util.ArrayList;
import java.util.List;

import static apiHelper.APIConfig.*;
import static helper.TestCaseHelper.*;

public class CancelCourseTest extends BaseTest {
    private static final String VALID_COURSE_ID = "111111111111";

    @Test(priority = 1,
            description = "TC_CAN_001: Verify registered course card contains complete information")
    public void TC_CAN_001_Verify_Registered_Course_Card_Contains_Complete_Information() {
        String username ="test.course.1";
        String password = "Asd123!@#";
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = VALID_COURSE_ID;

        logAndReport("Pre-condition: User logged in account: "+username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has enrolled");
        api.enrollCourse(user, courseId);
        int indexCard = 1;

        logAndReport("Step 1: Open the Profile Page");
        ProfilePage profilePage = navigateToProfilePage();

        logAndReport("Step 2: Click on the \"Khóa học\" tab");
        profilePage.clickCoursesTab();

        RegisteredCourseCard card = profilePage.getCourseCardByIndex(indexCard);

        logAndReport("Check the card at position (" + indexCard + ")");

        // Verify Point
        logAndReport("VP1:The Course Image displays and doesn't break");
        boolean imgDisplayed = card.isCourseImageDisplayed();
        logAndReport("Course Image displayed = " + imgDisplayed);
        Assert.assertTrue(
                imgDisplayed,
                "[TC_CAN_001][FAIL] Course Image has displayed"
        );
        boolean imgLoaded = card.isCourseImageLoaded();
        logAndReport("Course Image loaded = " + imgLoaded);
        Assert.assertTrue(
                imgLoaded,
                "[TC_CAN_001][FAIL] Course Image has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP2: The Course Name displays");
        String courseName = card.getCourseName();
        logAndReport("Course Name: " + courseName);
        Assert.assertFalse(
                courseName.isEmpty(),
                "[TC_CAN_001][FAIL] Course Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP3: The Course Short Description displays");
        String shortDesc = card.getCourseShortDescription();
        logAndReport("Short Description: " + shortDesc);
        Assert.assertFalse(
                shortDesc.isEmpty(),
                "[TC_CAN_001][FAIL] Course Short Description is empty"
        );

        //-------------------------------------------
        logAndReport("VP4: The Specifications displays");
        boolean specifications = card.isRegisteredCourseCardSpecificationsDisplay();
        logAndReport("Specifications displayed = " + specifications);
        Assert.assertTrue(
                specifications,
                "[TC_CD_013][FAIL] Specifications doesn't display"
        );

        //-------------------------------------------
        logAndReport("VP5: The Star Icons displays");
        boolean starIcon = card.isRegisteredCourseCardStarIconDisplay();
        logAndReport("Star icons displayed = " + starIcon);
        Assert.assertTrue(
                starIcon,
                "[TC_CD_013][FAIL] Star Icons doesn't display"
        );

        //-------------------------------------------
        logAndReport("VP6: The Instructor Avatar displays and doesn't break");
        boolean avatarDisplay = card.isAvatarImageDisplayed();
        logAndReport(" Instructor Avatar display=" + avatarDisplay);
        Assert.assertTrue(
                avatarDisplay,
                "[TC_CAN_001][FAIL] Instructor Avatar displayed"
        );
        boolean avatarLoaded = card.isAvatarImageLoaded();
        logAndReport(" Instructor Avatar loaded=" + avatarLoaded);
        Assert.assertTrue(
                avatarLoaded,
                "[TC_CAN_001][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP7: The Instructor Name displays");
        String instructor = card.getCourseInstructorName();
        logAndReport("Instructor Name: " + instructor);
        Assert.assertFalse(
                instructor.isEmpty(),
                "[TC_CAN_001][FAIL] Instructor Name is empty"
        );

        //-------------------------------------------
        String nameButton = "HỦY KHÓA HỌC";
        logAndReport("VP8: The \""+nameButton +"\" button display and enable");
        boolean btnDisplayed = card.isCancelCourseButtonDisplayed();
        logAndReport("Enroll button displayed = " + btnDisplayed);
        Assert.assertTrue(btnDisplayed,
                "[TC_CAN_001][FAIL] The \""+nameButton +"\" button doesn't display");
        boolean btnEnabled = card.isCancelCourseButtonDisplayed();
        logAndReport("Enroll button enabled = " + btnEnabled);
        Assert.assertTrue(btnEnabled,
                "[TC_CAN_001][FAIL] The \""+nameButton +"\" button is disabled");
    }

    @Test(priority = 2,
            description = "TC_CAN_002: Verify cancel the only registered course")
    public void TC_CAN_002_Verify_Cancel_The_Only_Register_Course() {
        String username = "test.course.2";
        String password = "Asd123!@#";
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = VALID_COURSE_ID;
        int indexCard = 1;

        logAndReport("Pre-condition: User logged in account: "+username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has enrolled");
        api.enrollCourse(user, courseId);

        logAndReport("Step 1: Open the Profile Page");
        ProfilePage profilePage = navigateToProfilePage();

        logAndReport("Step 2: Click on the \"Khóa học\" tab");
        profilePage.clickCoursesTab();
        int beforeCount= profilePage.getCourseCardCount();
        RegisteredCourseCard card = profilePage.getCourseCardByIndex(indexCard);

        logAndReport("Step 3: Click on the HỦY KHÓA HỌC button on the card at position (" + indexCard + ")");
        String removedNameCourse = card.getCourseName();
        card.clickOnCancelCourseButton();
        profilePage.waitForCourseCardsCountToBe(beforeCount -1);
        int afterCount= profilePage.getCourseCardCount();

        try {
            // Verify Point
            logAndReport("VP1: The count of the course on My course decreases");
            Assert.assertEquals(
                    afterCount,
                    beforeCount - 1,
                    "[TC_CAN_002][FAIL]: The count of the course on My course doesn't decrease"
            );

            logAndReport("VP2: Verify enrolled course is removed to \"Khóa học\" (My Courses) list");
            Assert.assertFalse(
                    profilePage.isCourseNameInCourseList(removedNameCourse),
                    "[TC_CAN_002][FAIL]: " + removedNameCourse + " is in \"Khóa học\" (My Courses) list"
            );

            //-------------------------------------------
            logAndReport("VP3: Verify enrolled course is removed the chiTietKhoaHocGhiDanh list by API");
            Assert.assertFalse(api.isCourseEnrolledByName(user, removedNameCourse),
                    "[TC_CAN_002][FAIL]: " + removedNameCourse + " included in chiTietKhoaHocGhiDanh"
            );
        } finally {
            logAndReport("End Test: Remove courseId = \"" + courseId + "\" after test");
            api.leaveCourse(user, courseId);
        }
    }

    @Test(priority = 3,
            description = "TC_CAN_003: Verify cancel of the first course card in the list")
    public void TC_CAN_003_Verify_Cancel_The_First_Register_Course() {
        String username = "test.course.3";
        String password = "Asd123!@#";
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        List<String> courseIDs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            courseIDs.add(api.getRandomCourseId(COURSE_PAGE, COURSE_PAGESIZE, COURSE_GROUPID));
        }

        logAndReport("Pre-condition: User logged in account: "+username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: Add some course");
        for(String courseId: courseIDs)
        {
            api.enrollCourse(user, courseId);
        }

        logAndReport("Step 1: Open the Profile Page");
        ProfilePage profilePage = navigateToProfilePage();

        logAndReport("Step 2: Click on the \"Khóa học\" tab");
        profilePage.clickCoursesTab();
        int beforeCount= profilePage.getCourseCardCount();
        RegisteredCourseCard card = profilePage.getCourseCardByIndex(1);

        logAndReport("Step 3: Click on the HỦY KHÓA HỌC button on the first card");
        String removedNameCourse = card.getCourseName();
        card.clickOnCancelCourseButton();
        profilePage.waitForCourseCardsCountToBe(beforeCount -1);
        int afterCount= profilePage.getCourseCardCount();

        try {
            // Verify Assert
            logAndReport("VP1: The count of the course on My course decreases");
            Assert.assertEquals(
                    afterCount,
                    beforeCount - 1,
                    "[TC_CAN_003][FAIL]: The count of the course on My course doesn't decrease"
            );

            //-------------------------------------------
            logAndReport("VP2: Verify enrolled course is removed to \"Khóa học\" (My Courses) list");
            Assert.assertFalse(
                    profilePage.isCourseNameInCourseList(removedNameCourse),
                    "[TC_CAN_003][FAIL]: " + removedNameCourse + " is in \"Khóa học\" (My Courses) list"
            );

            //-------------------------------------------
            logAndReport("VP3: Verify enrolled course is removed the chiTietKhoaHocGhiDanh list by API");
            Assert.assertFalse(api.isCourseEnrolledByName(user, removedNameCourse),
                    "[TC_CAN_003][FAIL]: " + removedNameCourse + " included in chiTietKhoaHocGhiDanh"
            );
        } finally {
            logAndReport("End Test: Remove course after test");
            for(String courseId: courseIDs)
            {
                api.leaveCourse(user, courseId);
            }
        }
    }

    @Test(priority = 4,
            description = "TC_CAN_004: Verify search results are updated after course cancellation")
    public void TC_CAN_004_Verify_Search_Results_Updated_After_Cancel_Course() {
        String username = "test.course.4";
        String password = "Asd123!@#";
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        List<String> courseIDs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            courseIDs.add(api.getRandomCourseId(COURSE_PAGE, COURSE_PAGESIZE, COURSE_GROUPID));
        }

        logAndReport("Pre-condition: User logged in account: "+username);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: Add some courses");
        for(String courseId: courseIDs)
        {
            api.enrollCourse(user, courseId);
        }

        logAndReport("Step 1: Open the Profile Page");
        ProfilePage profilePage = navigateToProfilePage();

        logAndReport("Step 2: Click on the \"Khóa học\" tab");
        profilePage.clickCoursesTab();
        RegisteredCourseCard secondCard = profilePage.getCourseCardByIndex(2);
        String secondCardName = secondCard.getCourseName();

        logAndReport("Step 3: Input the \""+ secondCardName +"\" on the Search field");
        profilePage.inputTextSearchField(secondCardName);
        int beforeResultCount = profilePage.getCourseCardCount();

        logAndReport("Step 4: Click on the HỦY KHÓA HỌC button on the first card");
        RegisteredCourseCard firstCard = profilePage.getCourseCardByIndex(1);
        String removedNameCourse = firstCard.getCourseName();
        firstCard.clickOnCancelCourseButton();
        profilePage.waitForCourseCardsCountToBe(beforeResultCount -1);
        int afterResultCount = profilePage.getCourseCardCount();

        try {
            // Verify Assert
            logAndReport("VP1: The count of the result on decreases");
            Assert.assertEquals(
                    afterResultCount,
                    beforeResultCount - 1,
                    "[TC_CAN_004][FAIL]: The count of the course on My course doesn't decrease"
            );

            //-------------------------------------------
            logAndReport("VP2: Verify enrolled course is removed to \"Khóa học\" (My Courses) list");
            Assert.assertFalse(
                    profilePage.isCourseNameInCourseList(removedNameCourse),
                    "[TC_CAN_004][FAIL]: " + removedNameCourse + " is in \"Khóa học\" (My Courses) list"
            );

            //-------------------------------------------
            logAndReport("VP3: Verify enrolled course is removed the chiTietKhoaHocGhiDanh list by API");
            Assert.assertFalse(api.isCourseEnrolledByName(user, removedNameCourse),
                    "[TC_CAN_004][FAIL]: " + removedNameCourse + " included in chiTietKhoaHocGhiDanh"
            );
        } finally {
            logAndReport("End Test: Remove course after test");
            for(String courseId: courseIDs)
            {
                api.leaveCourse(user, courseId);
            }
        }
    }

}
