package testcases.PersonalProfile;

import base.BaseTest;
import drivers.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

import static helper.TestCaseHelper.navigateToLoginPage;
import static helper.TestCaseHelper.logAndReport;

public class ViewPersonalProfileTest extends BaseTest {
    private ProfilePage profilePage;

    @BeforeMethod
    public void loginAndOpenProfile() {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.login("thua358", "@123456Aa");
        loginPage.getTopNavigation().waitAvatarAppear();
        loginPage.getTopNavigation().navigateToProfilePage();
        profilePage = new ProfilePage(DriverManager.getDriver());
        profilePage.waitForProfileLoaded();
        logAndReport("Opened View Personal Profile page");
    }

    @Test
    public void TC_VP_001_AccessProfilePageSuccessfully() {
        logAndReport("Verify the Personal Profile page is displayed");
        Assert.assertTrue(profilePage.getProfileTitle().toLowerCase().contains("thông tin cá nhân"),
                "Personal Profile page title should be displayed");
    }

    @Test
    public void TC_VP_002_VerifyNavigationTabsDisplay() {
        logAndReport("Verify both Profile navigation tabs are displayed");
        Assert.assertEquals(profilePage.getTabCount(), 2, "Profile should display two navigation tabs");
        Assert.assertTrue(profilePage.getTabText(0).toLowerCase().contains("thông tin cá nhân"));
        Assert.assertTrue(profilePage.getTabText(1).toLowerCase().contains("khóa học"));
    }

    @Test
    public void TC_VP_003_SwitchBetweenTabs() {
        logAndReport("Switch to Courses tab");
        profilePage.clickCoursesTab();
        Assert.assertTrue(profilePage.isCoursesSectionVisible(), "Courses section should be displayed");
        logAndReport("Switch back to Personal Information tab");
        profilePage.clickPersonalInformationTab();
        Assert.assertTrue(profilePage.isPersonalInformationVisible(),
                "Personal Information section should be displayed after switching back");
    }

    @Test
    public void TC_VP_004_VerifyDefaultPersonalInformationTab() {
        logAndReport("Verify Personal Information tab is selected by default");
        Assert.assertTrue(profilePage.isTabActive(0), "Personal Information tab should be active by default");
        Assert.assertFalse(profilePage.isTabActive(1), "Courses tab should not be active by default");
    }

    @Test
    public void TC_VP_005_VerifyPersonalInformationDisplay() {
        logAndReport("Verify Personal Information section and displayed fields");
        Assert.assertTrue(profilePage.arePersonalInformationFieldsVisible(),
                "Personal information fields should be displayed");
        Assert.assertTrue(profilePage.isUpdateButtonVisible(), "Update button should be displayed");
    }

    @Test
    public void TC_VP_007_VerifyProfileSummarySection() {
        logAndReport("Verify Profile Summary elements");
        Assert.assertTrue(profilePage.isProfileSummaryVisible(),
                "Profile summary avatar, full name and position should be displayed");
    }

    @Test
    public void TC_VP_010_VerifySkillProgressSection() {
        logAndReport("Verify skill progress bars are displayed");
        Assert.assertEquals(profilePage.getSkillBarCount(), 4,
                "HTML, CSS, JavaScript and React skill progress bars should be displayed");
    }

    @Test
    public void TC_VP_011_VerifySkillProgressBars() {
        logAndReport("Verify each skill has a progress bar");
        Assert.assertEquals(profilePage.getSkillBarCount(), 4,
                "Each of the four skills should have a progress bar");
    }

    @Test
    public void TC_VP_013_VerifyLearningStatisticsSection() {
        logAndReport("Verify learning statistics are displayed");
        Assert.assertEquals(profilePage.getLearningStatisticCount(), 6,
                "Six learning statistics should be displayed");
    }

    @Test
    public void TC_VP_022_VerifyEmptyCourseState() {
        logAndReport("Open Courses tab and verify the course section is shown");
        profilePage.clickCoursesTab();
        Assert.assertTrue(profilePage.isCoursesSectionVisible(),
                "Courses section should be displayed");
        Assert.assertTrue(profilePage.getCoursesSectionTitle().toLowerCase().contains("khóa học"),
                "Courses section title should be displayed");
    }
}
