package testcases.personalProfile;

import base.BaseTest;
import drivers.DriverManager;
import helper.PropertyHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

import static helper.TestCaseHelper.navigateToLoginPage;
import static helper.TestCaseHelper.logAndReport;

public class ViewPersonalProfileTest extends BaseTest {
    String username = PropertyHelper.get("profile.username");
    String password = PropertyHelper.get("profile.password");

    // Dùng ThreadLocal để cô lập ProfilePage cho từng luồng riêng biệt
    private final ThreadLocal<ProfilePage> profilePage = new ThreadLocal<>();

    @BeforeMethod
    public void loginAndOpenProfile() {
        LoginPage loginPage = navigateToLoginPage();

        loginPage.login(username, password);
        loginPage.getTopNavigation().waitAvatarAppear();
        loginPage.getTopNavigation().navigateToProfilePage();

        // Khởi tạo và gán ProfilePage cho luồng hiện tại
        ProfilePage page = new ProfilePage(DriverManager.getDriver());
        page.waitForProfileLoaded();

        profilePage.set(page);
        logAndReport("Opened View Personal Profile page");
    }

    // Hàm tiện ích lấy ProfilePage của đúng luồng đang chạy
    public ProfilePage getProfilePage() {
        return profilePage.get();
    }

    @Test
    public void TC_VP_001_AccessProfilePageSuccessfully() {
        logAndReport("Verify the Personal Profile page is displayed");
        Assert.assertTrue(getProfilePage().getProfileTitle().toLowerCase().contains("thông tin cá nhân"),
                "Personal Profile page title should be displayed");
    }

    @Test
    public void TC_VP_002_VerifyNavigationTabsDisplay() {
        logAndReport("Verify both Profile navigation tabs are displayed");
        Assert.assertEquals(getProfilePage().getTabCount(), 2, "Profile should display two navigation tabs");
        Assert.assertTrue(getProfilePage().getTabText(0).toLowerCase().contains("thông tin cá nhân"));
        Assert.assertTrue(getProfilePage().getTabText(1).toLowerCase().contains("khóa học"));
    }

    @Test
    public void TC_VP_003_SwitchBetweenTabs() {
        logAndReport("Switch to Courses tab");
        getProfilePage().clickCoursesTab();
        Assert.assertTrue(getProfilePage().isCoursesSectionVisible(), "Courses section should be displayed");
        logAndReport("Switch back to Personal Information tab");
        getProfilePage().clickPersonalInformationTab();
        Assert.assertTrue(getProfilePage().isPersonalInformationVisible(),
                "Personal Information section should be displayed after switching back");
    }

    @Test
    public void TC_VP_004_VerifyDefaultPersonalInformationTab() {
        logAndReport("Verify Personal Information tab is selected by default");
        Assert.assertTrue(getProfilePage().isTabActive(0), "Personal Information tab should be active by default");
        Assert.assertFalse(getProfilePage().isTabActive(1), "Courses tab should not be active by default");
    }

    @Test
    public void TC_VP_005_VerifyPersonalInformationDisplay() {
        logAndReport("Verify Personal Information section and displayed fields");
        Assert.assertTrue(getProfilePage().arePersonalInformationFieldsVisible(),
                "Personal information fields should be displayed");
        Assert.assertTrue(getProfilePage().isUpdateButtonVisible(), "Update button should be displayed");
    }

    @Test
    public void TC_VP_007_VerifyProfileSummarySection() {
        logAndReport("Verify Profile Summary elements");
        Assert.assertTrue(getProfilePage().isProfileSummaryVisible(),
                "Profile summary avatar, full name and position should be displayed");
    }

    @Test
    public void TC_VP_010_VerifySkillProgressSection() {
        logAndReport("Verify skill progress bars are displayed");
        Assert.assertEquals(getProfilePage().getSkillBarCount(), 4,
                "HTML, CSS, JavaScript and React skill progress bars should be displayed");
    }

    @Test
    public void TC_VP_011_VerifySkillProgressBars() {
        logAndReport("Verify each skill has a progress bar");
        Assert.assertEquals(getProfilePage().getSkillBarCount(), 4,
                "Each of the four skills should have a progress bar");
    }

    @Test
    public void TC_VP_013_VerifyLearningStatisticsSection() {
        logAndReport("Verify learning statistics are displayed");
        Assert.assertEquals(getProfilePage().getLearningStatisticCount(), 6,
                "Six learning statistics should be displayed");
    }

    @Test
    public void TC_VP_022_VerifyEmptyCourseState() {
        logAndReport("Open Courses tab and verify the course section is shown");
        getProfilePage().clickCoursesTab();
        Assert.assertTrue(getProfilePage().isCoursesSectionVisible(),
                "Courses section should be displayed");
        Assert.assertTrue(getProfilePage().getCoursesSectionTitle().toLowerCase().contains("khóa học"),
                "Courses section title should be displayed");
    }
}
