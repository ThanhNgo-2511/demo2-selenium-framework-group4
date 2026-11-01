package testcases.course;

import apiHelper.APIAction;
import base.BaseTest;
import drivers.DriverManager;
import models.UserModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CourseDetailPage;
import pages.LoginPage;
import pages.components.Banner;
import pages.components.FeaturedCourseCard;
import pages.modals.CommonModal;

import static helper.TestCaseHelper.*;

public class CourseDetailTest extends BaseTest {
    private static final int COURSE_PAGE = 1;
    private static final int COURSE_PAGESIZE = 12;
    private static final String COURSE_GROUPID = "GP01";
    private static final String VALID_COURSE_ID = ".100";

    @Test(priority = 1,
            description = "TC_CD_001: Verify course detail page loads successfully")
    public void TC_CD_001_Verify_Page_Course_Loads_Successfully() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        //-------------------------------------------
        logAndReport("VP1: Verify URL");
        String currentUrl = courseDetailPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/chitiet/"),
                "[TC_CD_001][FAIL] URL must contains '/chitiet/'. Actual: " + currentUrl
        );

        //-------------------------------------------
        logAndReport("VP2: Verify page title is not empty");
        String title = courseDetailPage.getPageTitle();
        Assert.assertNotNull(title,
                "[TC_CD_001][FAIL] Page title is null"
        );
        Assert.assertFalse(title.trim().isEmpty(),
                "[TC_CD_001][FAIL] Page title is empty"
        );
    }
    @Test(priority = 2,
            description = "TC_CD_002: Verify the banner of the Course Detail page")
    public void TC_CD_002_Verify_The_Banner_Course_Displayed() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        navigateToCourseDetailPage(VALID_COURSE_ID);
        Banner banner = new Banner(DriverManager.getDriver());

        String expectedTitle ="THÔNG TIN KHÓA HỌC";
        logAndReport("VP1: Verify the Banner Title \""+ expectedTitle +"\" displays on the Course List");
        boolean bannerTitleDisplayed = banner.bannerTitleDisplayed();
        logAndReport("Banner Title displayed =" + bannerTitleDisplayed);
        Assert.assertTrue(bannerTitleDisplayed,
                "[TC_CD_002][FAIL]  The banner title doesn't display"
        );

        String bannerTitleText = banner.getBannerTitle();
        logAndReport("Banner Title: " + bannerTitleText);
        Assert.assertEquals(bannerTitleText,
                expectedTitle,
                "[TC_CD_002][FAIL] The title text isn't \""+ expectedTitle+"\""
        );

        String expectedSubTitle ="TIẾN LÊN VÀ KHÔNG CHẦN CHỪ !!!";
        logAndReport("VP2: Verify the Banner Sub-title \""+ expectedSubTitle +"\" displays on the Course List");
        boolean bannerSubTitleDisplayed = banner.bannerSubTitleDisplayed();
        logAndReport("Banner Sub-Title displayed =" + bannerSubTitleDisplayed);
        Assert.assertTrue(
                bannerSubTitleDisplayed,
                "[TC_CD_002][FAIL]  The banner title doesn't display"
        );

        String bannerSubTitleText = banner.getBannerSubTitle();
        logAndReport("Banner Sub-Title: " + bannerSubTitleText);
        Assert.assertEquals(bannerSubTitleText,
                expectedSubTitle,
                "[TC_CD_002][FAIL] The sub-title text isn't \""+ expectedSubTitle+"\""
        );
    }

    @Test(priority = 3,
            description = "TC_CD_003: Verify the hero information section displays")
    public void TC_CD_003_Verify_Hero_Information_Section_Displays() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        //-------------------------------------------
        logAndReport("VP1: The Course Name displays");
        String courseName = courseDetailPage.getCourseName();
        logAndReport("Course Name: " + courseName);
        Assert.assertFalse(courseName.isEmpty(),
                "[TC-CD-002][FAIL] Course Name is empty");

        //-------------------------------------------
        logAndReport("VP2: The Instructor Section displays");
        boolean instructorAvatar = courseDetailPage.isInstructorImageLoaded();
        logAndReport(" Instructor Avatar loaded=" + instructorAvatar);
        Assert.assertTrue(
                instructorAvatar,
                "[TC_CD_003][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        boolean instructorTitle = courseDetailPage.isInstructorTitleDisplay();
        logAndReport("Instructor Title displayed= " + instructorTitle);
        Assert.assertTrue(instructorTitle,
                "[TC_CD_003][FAIL] Instructor Title doesn't display");

        String instructorName = courseDetailPage.getInstructorName();
        logAndReport("Instructor Name: " + instructorName);
        Assert.assertFalse(instructorName.isEmpty(),
                "[TC_CD_003][FAIL] Instructor Name is empty");

        //-------------------------------------------
        logAndReport("VP3: The Course Category section displays");
        boolean graduationCapIcon = courseDetailPage.isCategoryIconDisplay();
        logAndReport("Graduation Cap Icon displayed=" + graduationCapIcon);
        Assert.assertTrue(
                graduationCapIcon,
                "[TC_CD_003][FAIL] Graduation Cap Icon doesn't display"
        );

        boolean categoryTitle = courseDetailPage.isCategoryTitleDisplay();
        logAndReport("Category Title displayed= " + categoryTitle);
        Assert.assertTrue(categoryTitle,
                "[TC_CD_003][FAIL] Category Title doesn't display");

        String category = courseDetailPage.getCourseCategory();
        logAndReport("Course Category: " + category);
        Assert.assertFalse(category.isEmpty(),
                "[TC_CD_003][FAIL] Course Category is empty");

        //-------------------------------------------
        logAndReport("VP4: The Course Rating displays");
        String ratingText = courseDetailPage.getCourseRating();
        logAndReport("Rating Text: " + ratingText);
        if (!ratingText.isEmpty()) {
            try {
                double rating = Double.parseDouble(ratingText.replaceAll("[^0-9.]", "").trim());
                Assert.assertTrue(rating >= 0.0 && rating <= 5.0,
                        "[TC_CD_003][FAIL] Rating is outside the 0–5 range: " + rating);
            } catch (NumberFormatException e) {
                logAndReport("[TC_CD_003][Rating] WARNING: Unable to parse the rating value");
            }
        }

        //-------------------------------------------
        logAndReport("VP5: The Review Count displays");
        String reviewCount = courseDetailPage.getReviewCount();
        logAndReport("Review Count: " + reviewCount);
        Assert.assertFalse(reviewCount.isEmpty(),
                "[TC_CD_003][FAIL] Review Count is empty");

        //-------------------------------------------
        logAndReport("VP6: The Course Description displays");
        String courseDescription = courseDetailPage.getCourseDescription();
        logAndReport("Course Description: " + courseDescription);
        Assert.assertFalse(courseDescription.isEmpty(),
                "[TC_CD_003][FAIL] Course Description is empty");

        //-------------------------------------------
        logAndReport("[TC_CD_003] PASS - All information on the Hero Section is valid");
    }

    @Test(priority = 4,
            description = "TC_CD_004: Verify \"Những gì bạn sẽ học\" section is displayed")
    public void TC_CD_004_Verify_What_You_Will_Learn_Section_Displays() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        courseDetailPage.scrollToWhatYouWillLearnSection();

        //-------------------------------------------
        logAndReport("VP1: The title:\"Những gì bạn sẽ học\" displays");
        boolean sectionTitleDisplayed = courseDetailPage.isLearnTitleDisplayed();
        logAndReport("Section Title displayed=: " + sectionTitleDisplayed);
        Assert.assertTrue(sectionTitleDisplayed,
                "[TC_CD_004][FAIL] The 'Những gì bạn sẽ học' section doesn't display");

        //-------------------------------------------
        logAndReport("VP2: The section has at least 1 item");
        int itemsCount = courseDetailPage.getLearnItemCount();
        logAndReport("Item count: " + itemsCount);
        Assert.assertTrue(itemsCount > 0,
                "[TC_CD_004][FAIL] Section with no items");

        //-------------------------------------------
        logAndReport("VP3: The first item is not empty");
        String firstItemText = courseDetailPage.getTextLearnItem(1);
        logAndReport("First item text: " + firstItemText);
        Assert.assertFalse(firstItemText.isEmpty(),
                "[TC_CD_004][FAIL] The text on the first item is empty");

        logAndReport("[TC_CD_004] PASS - " + itemsCount + " learning items vaild");
    }

    @Test(priority = 5,
            description = "TC_CD_005: Verify \"Nội dung khóa học\" section is displayed")
    public void TC_CD_005_Verify_Curriculum_Section_Displays() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);
        courseDetailPage.scrollToCurriculumSection();

        //-------------------------------------------
        logAndReport("VP1: The title:\"Nội dung khóa học\" displays");
        boolean curriculumTitleDisplayed = courseDetailPage.isCurriculumTitleDisplayed();
        logAndReport("Section Title displayed=: " + curriculumTitleDisplayed);
        Assert.assertTrue(curriculumTitleDisplayed,
                "[TC_CD_005][FAIL] The 'Nội dung khóa học' section doesn't display");

        //-------------------------------------------
        logAndReport("VP2: The section has at least 1 chapter");
        int chapterCount = courseDetailPage.getChapterCount();
        logAndReport("Chapters Count: " + chapterCount);
        Assert.assertTrue(chapterCount > 0,
                "[TC_CD_005][FAIL] There are no chapters in the curriculum.");

        //-------------------------------------------
        logAndReport("VP3: The section has at least 1 lesson");
        int lessonCount = courseDetailPage.getChapterCount();
        logAndReport("Lessons Count: " + lessonCount);
        Assert.assertTrue(lessonCount > 0,
                "[TC_CD_005][FAIL] There are no lessons in the curriculum.");

        //-------------------------------------------
        logAndReport("VP4: All durations must be in the correct MM:SS format.");
        boolean durationsValid = courseDetailPage.areLessonDurationsValid();
        logAndReport("  [durations valid]    : " + durationsValid);
        Assert.assertTrue(durationsValid,
                "[TC_CD_005][FAIL] Một số lesson có thời lượng sai định dạng (phải là MM:SS)");

        logAndReport("[TC_CD_005] PASS - " + chapterCount
                + " chapters, " + lessonCount + " lessons");
    }

    @Test(priority = 6,
            description = "TC_CD_006: Verify Course Image is displayed and loaded")
    public void TC_CD_006_Verify_Course_Image_Display() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        //-------------------------------------------
        logAndReport("VP1: Verify the course image is display");
        boolean imgDisplayed = courseDetailPage.isCourseImageDisplayed();
        logAndReport("Image displayed= " + imgDisplayed);
        Assert.assertTrue(imgDisplayed,
                "[TC_CD_006][FAIL] Ảnh khóa học không hiển thị trên trang chi tiết"
        );

        //-------------------------------------------
        logAndReport("VP2: Verify the course image is loaded (no broken)");
        boolean imgLoaded = courseDetailPage.isCourseImageLoaded();
        logAndReport("Image loaded=" + imgLoaded);
        Assert.assertTrue(imgLoaded,
                "[TC_CD_006][FAIL] Ảnh khóa học bị broken (naturalWidth = 0)");
    }

    @Test(priority = 7,
            description = "TC_CD_007: Verify Price and Enroll Button vaild")
    public void TC_CD_007_Verify_Price_And_Enroll_Button_Valid() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        //-------------------------------------------
        logAndReport("VP1: The price is displayed");
        String price = courseDetailPage.getPriceText();
        logAndReport("Price: " + price);
        Assert.assertFalse(price.isEmpty(),
                "[TC_CD_007][FAIL] Price is empty");
        Assert.assertTrue(
                price.matches(".*\\d.*"),
                "[TC_CD_007][FAIL] Price is in an incorrect format (must contain numbers): " + price);

        //-------------------------------------------
        logAndReport("VP2: The \"Đăng ký\" button is displayed");
        boolean btnDisplayed = courseDetailPage.isEnrollButtonDisplayed();
        logAndReport("Enroll button displayed=" + btnDisplayed);
        Assert.assertTrue(btnDisplayed,
                "[TC_CD_007][FAIL] The \"Đăng ký\" button doesn't display");

        //-------------------------------------------
        logAndReport("VP3: The \"Đăng ký\" button must be enable");
        boolean btnEnabled = courseDetailPage.isEnrollButtonEnable();
        logAndReport("Enroll button enabled=" + btnEnabled);
        Assert.assertTrue(btnEnabled,
                "[TC_CD_007][FAIL] The \"Đăng ký\" button is disabled");

        //-------------------------------------------
        logAndReport("[TC_CD_007] PASS - Valid price and button");
    }

    @Test(priority = 8,
            description = "TC_CD_008: Verify Enroll Course When Has Not Logged In")
    public void TC_CD_008_Verify_Enroll_Course_When_Has_Not_Logged_In() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        logAndReport("Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        //-------------------------------------------
        logAndReport("VP1: Verify URL");
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "[TC_CD_008][FAIL] URL must contains '/login'. Actual: " + currentUrl
        );

        logAndReport("VP2: The Name Form is Đăng nhập");
        Assert.assertEquals(loginPage.getNameForm(),
                "Đăng nhập",
                "[TC_CD_008][FAIL] Does not redircted to Login page "
        );
    }

    @DataProvider(name = "accountsForTC008")
    public Object[][] getAccountsForTC008() {
        return new Object[][]{
                {"test.course.1", "Asd123!@#"},
                {"test.course.2", "Asd123!@#"}
        };
    }
    @Test(priority = 9, dataProvider = "accountsForTC008",
            description = "TC_CD_009: Verify Enroll Course When Has Logged In")
    public void TC_CD_009_Verify_Enroll_Course_When_Has_Logged_In(String username, String password) {
        logAndReport("Pre-condition: User logged in account: " + username);
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = api.getRandomCourseId(COURSE_PAGE, COURSE_PAGESIZE, COURSE_GROUPID);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has not enrolled");
        api.leaveCourse(user, courseId);

        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + courseId);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(courseId);

        logAndReport("Step 2: Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();

        logAndReport("VP1: Message \"Đăng ký thành công\" displays");
        CommonModal commonModal = new CommonModal(DriverManager.getDriver());
        String recordedText = commonModal.getMessageText();
        Assert.assertEquals(recordedText,
                "Đăng kí thành công",
                "[TC_CD_009][FAIL]: Enroll Course message is incorrect"
        );

        logAndReport("VP2: Verify enrolled course is added the chiTietKhoaHocGhiDanh list by API");
        Assert.assertTrue(api.isCourseEnrolled(user, courseId),
                "[TC_CD_009][FAIL]: "+courseId + "not included in chiTietKhoaHocGhiDanh"
        );

        //logAndReport("VP3: Verify enrolled course is added to "Khóa học" (My Courses) list");

        logAndReport("End Test: Remove courseId = \"" + courseId + "\" after test");
        api.leaveCourse(user, courseId);
    }

    @DataProvider(name = "accountsForTC009")
    public Object[][] getAccountsForTC009() {
        return new Object[][]{
                {"test.course.3", "Asd123!@#"},
                {"test.course.4", "Asd123!@#"}
        };
    }
    @Test(priority = 10, dataProvider = "accountsForTC009",
            description = "TC_CD_010: Verify re-enrollment in the enrolled course")
    public void TC_CD_010_Verify_Enroll_In_The_Enrolled_Course(String username, String password) {
        logAndReport("Pre-condition: User logged in account: " + username);
        APIAction api = new APIAction();
        UserModel user = api.loginViaAPI(username, password);
        String courseId = api.getRandomCourseId(COURSE_PAGE, COURSE_PAGESIZE, COURSE_GROUPID);
        loginViaAPIAndSyncUI(user);

        logAndReport("Pre-condition: The course with id= " + courseId + " has enrolled");
        api.enrollCourse(user, courseId);

        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + courseId);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(courseId);

        logAndReport("Step 2: Click on \"Đăng Ký\" button");
        courseDetailPage.clickEnrollButton();

        logAndReport("VP1: Message \"Đã đăng ký khóa học này rồi!\" displays ");
        CommonModal commonModal = new CommonModal(DriverManager.getDriver());
        String recordedText = commonModal.getMessageText();
        Assert.assertEquals( recordedText,
                "Đã đăng ký khóa học này rồi!",
                "[TC_CD_010][FAIL]: Enroll Course message is incorrect");

        logAndReport("VP2: Verify the enrolled course is still in the chiTietKhoaHocGhiDanh list by API ");
        Assert.assertTrue(
                api.isCourseEnrolled(user, courseId),
                "[TC_CD_010][FAIL]: "+ courseId + "not included in chiTietKhoaHocGhiDanh"
        );

        //logAndReport("VP3: Verify the enrolled course is still in the "Khóa học" (My Courses) list");

        logAndReport("End Test: Leave courseId = \"" + courseId + "\" after test");
        api.leaveCourse(user, courseId);
    }

    @Test(priority = 11,
            description = "TC_CD_011: Verify the stats panel display")
    public void TC_CD_011_Verify_The_Stats_Panel_Display() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        logAndReport("VP1: Verify the number of enrolled students is display");
        String students = courseDetailPage.getStatStudents();
        logAndReport("The number of students: \"" + students +"\"");
        Assert.assertFalse(students.isEmpty(),
                "[TC_CD_011][FAIL] The 'Ghi danh' information is empty");

        logAndReport("VP2: Verify the study duration is display");
        String duration = courseDetailPage.getStatDuration();
        logAndReport("The study duration: \"" + duration+"\"");
        Assert.assertFalse(duration.isEmpty(),
                "[TC_CD_011][FAIL] The 'Thời gian' information is empty");

        logAndReport("VP3: Verify the number of lessons is display");
        String lessons = courseDetailPage.getStatLessons();
        logAndReport("The number of lessons: \"" + lessons+"\"");
        Assert.assertFalse(lessons.isEmpty(),
                "[TC_CD_011][FAIL] The 'Bài học' information is empty");

        logAndReport("VP4: Verify the number of videos is display");
        String videos = courseDetailPage.getStatVideos();
        logAndReport("The number of videos: \"" + videos+"\"");
        Assert.assertFalse(videos.isEmpty(),
                "[TC_CD_011][FAIL] The 'Video' information is empty");

        logAndReport("VP5: Verify the course level is display");
        String level = courseDetailPage.getStatLevel();
        logAndReport("Course Level: \"" + level+"\"");
        Assert.assertFalse(level.isEmpty(),
                "[TC_CD_011][FAIL] The 'Trình độ' information is empty");

        logAndReport("[TC_CD_011] PASS - Stats panel is valid");

    }

    @Test(priority = 12,
            description = "TC_CD_012: Verify Related Course section is displayed")
    public void TC_CD_012_Verify_Related_Course_Section_Is_Displayed() {
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);

        logAndReport("Step 2: Scroll to \"Khóa học tham khảo\" section");
        courseDetailPage.scrollToRelatedCourseSection();

        //-------------------------------------------
        logAndReport("VP1: The title:\"Khóa học tham khảo\" displays");
        boolean sectionTitleDisplayed = courseDetailPage.isRelatedCourseTitleDisplayed();
        logAndReport("Section Title displayed=: " + sectionTitleDisplayed);
        Assert.assertTrue(sectionTitleDisplayed,
                "[TC_CD_012][FAIL] The 'Khóa học tham khảo' title doesn't display");

        //-------------------------------------------
        logAndReport("VP2: Verify the page has course cards");
        int cardCount = courseDetailPage.getRelatedCourseCardCount();
        logAndReport("The card count: " + cardCount);
        Assert.assertTrue(
                cardCount > 0,
                "[TC_CD_012][FAIL] No course cards are displayed on the section"
        );
    }

    @Test(priority = 13,
            description = "TC_CD_013: Verify related course card contains complete information")
    public void TC_CL_012_Verify_Related_Course_Card_Contains_Complete_Information() {
        int indexCard = 1;
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);
        FeaturedCourseCard card = courseDetailPage.getRelatedCourseCard(indexCard);

        logAndReport("Step 2: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();

        logAndReport("Check the card at position (" + indexCard + ")");

        //-------------------------------------------
        logAndReport("VP1: The like tag displays");
        boolean liketagDisplay = card.isLikeTagDisplay();
        logAndReport("Like tag display=: " + liketagDisplay);
        Assert.assertTrue(
                liketagDisplay,
                "[TC_CD_013][FAIL]The like tag doesn't display"
        );

        //-------------------------------------------
        logAndReport("VP2: The Course Image displays and doesn't break");
        boolean imgDisplay = card.isCardRelatedCourseImageDisplayed();
        logAndReport(" Course Image displayed=" + imgDisplay);
        Assert.assertTrue(
                imgDisplay,
                "[TC_CD_013][FAIL] Instructor Avatar displayed"
        );
        boolean imgLoaded = card.isCardRelatedCourseImageLoaded();
        logAndReport(" Course Image loaded=" + imgLoaded);
        Assert.assertTrue(
                imgLoaded,
                "[TC_CD_013][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP3: The Course Name displays");
        String courseName = card.getCardRelatedCourseName();
        logAndReport("Course Name: " + courseName);
        Assert.assertFalse(
                courseName.isEmpty(),
                "[TC_CD_013][FAIL] Course Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP4: The Course Short Description displays");
        String shortDesc = card.getCardRelatedCourseShortDescription();
        logAndReport("Short Description: " + shortDesc);
        Assert.assertFalse(
                shortDesc.isEmpty(),
                "[TC_CD_013][FAIL] Course Short Description is empty"
        );

        //-------------------------------------------
        logAndReport("VP5: The Specifications displays");
        boolean specifications = card.isRelateCourseCardSpecificationsDisplay();
        logAndReport("Specifications display = " + specifications);
        Assert.assertTrue(
                specifications,
                "[TC_CD_013][FAIL] Specifications doesn't display"
        );

        //-------------------------------------------
        logAndReport("VP6: The Instructor Avatar displays and doesn't break");
        boolean avatarDisplay = card.isAvatarOfRelatedCourseDisplayed();
        logAndReport(" Instructor Avatar displayed=" + avatarDisplay);
        Assert.assertTrue(
                avatarDisplay,
                "[TC_CD_013][FAIL] Instructor Avatar displayed"
        );
        boolean avatarLoaded = card.isAvatarOfRelatedCourseLoaded();
        logAndReport(" Instructor Avatar loaded=" + avatarLoaded);
        Assert.assertTrue(
                avatarLoaded,
                "[TC_CD_013][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP7: The Instructor Name displays");
        String instructor = card.getCardRelatedCourseInstructorName();
        logAndReport("Instructor Name: " + instructor);
        Assert.assertFalse(
                instructor.isEmpty(),
                "[TC_CD_013][FAIL] Instructor Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP8: The Price Sale displays");
        String priceSale = card.getCardRelatedCoursePriceSale();
        logAndReport("Price Sale: " + priceSale);
        Assert.assertFalse(
                priceSale.isEmpty(),
                "[TC_CD_013][FAIL] Price Sale is empty"
        );
        Assert.assertTrue(
                priceSale.matches(".*\\d.*"),
                "[TC_CD_013][FAIL] Price Sale isn't correct format: " + priceSale
        );

        //-------------------------------------------
        logAndReport("VP9: The Price Original displays");
        String priceOriginal = card.getCardRelatedCoursePriceOriginal();
        logAndReport("Price Original: " + priceOriginal);
        Assert.assertFalse(
                priceOriginal.isEmpty(),
                "[TC_CD_013][FAIL] Price Original is empty"
        );
        Assert.assertTrue(
                priceOriginal.matches(".*\\d.*"),
                "[TC_CD_013][FAIL] Price Original isn't correct format: " + priceOriginal
        );

        //-------------------------------------------
        logAndReport("VP10: The Related Course Card Href Link is valid");
        String href = card.getCourseCardHref();
        logAndReport("Related Course Card Href Link: " + href);
        Assert.assertNotNull(href, "[TC_CD_013][FAIL] The href of the card is null.");
        Assert.assertFalse(href.trim().isEmpty(), "[TC_CD_013][FAIL] The href of the card is empty.");
        Assert.assertFalse(href.equals("#"), "[TC_CD_013][FAIL] The card's href is '#' (not yet assigned).");
        Assert.assertTrue(href.contains("/chitiet/"), "[TC_CD_013][FAIL] href doesn't match the pattern '/chitiet/{id}': " + href);

        //-------------------------------------------
        logAndReport("[TC_CD_013] PASS - All information on the card is valid");
    }

    @Test(priority = 14,
            description = "TC_CD_014: Verify navigation to the details page when clicking on the related card")
    public void TC_CD_014_Verify_Click_On_The_Related_Course_Card() {
        int indexCard = 1;
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);
        FeaturedCourseCard card = courseDetailPage.getRelatedCourseCard(indexCard);

        logAndReport("Step 2: Click on the related card at position (" + indexCard + ")");
        card.clickOnCard();
        String urlCard = card.getCourseCardHref();

        logAndReport("VP1: The current URL page is the Detail Page");
        String currentUrl = courseDetailPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/chitiet/"),
                "[TC_CD_014][FAIL] URL must contains '/chitiet/'. Actual: " + currentUrl
        );

        logAndReport("VP2: The current URL page matches the link card");
        Assert.assertEquals(
                courseDetailPage.getCurrentUrl(),
                urlCard,
                "[TC_CD_014][FAIL] The URL Detail doesn't match"
        );

        logAndReport("VP2: The title: \"THÔNG TIN KHÓA HỌC\" displays");
        Banner banner = new Banner(DriverManager.getDriver());
        Assert.assertEquals(
                banner.getBannerSubTitle(),
                "THÔNG TIN KHÓA HỌC",
                "[TC_CD_014][FAIL] The title: \"THÔNG TIN KHÓA HỌC\" doesn't displays");
    }

    @Test(priority = 15,
            description = "TC_CD_015: Verify hover card is displayed when hovering over a related course card")
    public void TC_CD_015_Verify_Hover_Card_Displays_When_Hover_Related_Course_Card() {
        int indexCard = 1;
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);
        FeaturedCourseCard card = courseDetailPage.getRelatedCourseCard(indexCard);

        logAndReport("Step 2: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();

        logAndReport("Step 3: Hover the related card at position (" + indexCard + ")");
        card.hoverOnCard();

        logAndReport("VP: The hover card displays");
        boolean hoverCardDisplayed = card.isHoverCardDisplay();
        Assert.assertTrue(
                hoverCardDisplayed,
                "[TC_CD_015][FAIL]The hover card doesn't displays");
    }

    @Test(priority = 16,
            //dependsOnMethods = {"TC_CD_015_Verify_Hover_Card_Displays_When_Hover_Related_Course_Card()" },
            description = "TC_CD_016: Verify hover course card contains complete information")
    public void TC_CD_016_Verify_Hover_Course_Card_Contains_Complete_Information() {
        int indexCard = 1;
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);
        FeaturedCourseCard card = courseDetailPage.getRelatedCourseCard(indexCard);

        logAndReport("Step 2: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();

        logAndReport("Step 3: Hover the related card at position (" + indexCard + ")");
        card.hoverOnCard();

        //-------------------------------------------
        logAndReport("VP1: The Instructor Avatar displays and doesn't break");
        boolean avatarDisplay = card.isHoverCardCourseAvatarImageDisplayed();
        logAndReport(" Instructor Avatar displayed=" + avatarDisplay);
        Assert.assertTrue(
                avatarDisplay,
                "[TC_CD_016][FAIL] Instructor Avatar displayed"
        );
        boolean avatarLoaded = card.isHoverCardCourseAvatarImageLoaded();
        logAndReport(" Instructor Avatar loaded=" + avatarLoaded);
        Assert.assertTrue(
                avatarLoaded,
                "[TC_CD_016][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP2: The Course Name displays");
        String courseName = card.getHoverCardCourseCourseName();
        logAndReport("Course Name: " + courseName);
        Assert.assertFalse(
                courseName.isEmpty(),
                "[TC_CD_016][FAIL] Course Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP3: The Course Short Description displays");
        String shortDesc = card.getCardHoverCourseShortDescription();
        logAndReport("Short Description: " + shortDesc);
        Assert.assertFalse(
                shortDesc.isEmpty(),
                "[TC_CD_016][FAIL] Course Short Description is empty"
        );

        //-------------------------------------------
        logAndReport("VP4: The Specifications displays");
        boolean specifications = card.isHoverCourseCardSpecificationsDisplay();
        logAndReport("Specifications display = " + specifications);
        Assert.assertTrue(
                specifications,
                "[TC_CD_016][FAIL] Specifications doesn't display"
        );

        //-------------------------------------------
        logAndReport("VP5: The Instructor Name displays");
        String instructor = card.getCardRelatedCourseInstructorName();
        logAndReport("Instructor Name: " + instructor);
        Assert.assertFalse(
                instructor.isEmpty(),
                "[TC_CD_016][FAIL] Instructor Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP5: The \"XEM CHI TIẾT\" button display and enable");
        boolean btnDisplayed = card.isViewDetailButtonDisplayed();
        logAndReport("Enroll button displayed=" + btnDisplayed);
        Assert.assertTrue(btnDisplayed,
                "[TC_CD_016][FAIL] The \"XEM CHI TIẾT\" button doesn't display");
        boolean btnEnabled = card.isViewDetailButtonEnable();
        logAndReport("Enroll button enabled=" + btnEnabled);
        Assert.assertTrue(btnEnabled,
                "[TC_CD_016][FAIL] The \"XEM CHI TIẾT\" button is disabled");

        //-------------------------------------------
        logAndReport("VP6: Href Link in the \"XEM CHI TIẾT\" button is valid");
        String href = card.getViewDetailButtonHref();
        logAndReport("View Detail Button Href Link: " + href);
        Assert.assertNotNull(href, "[TC_CD_016][FAIL] The href of the button is null.");
        Assert.assertFalse(href.trim().isEmpty(), "[TC_CD_016][FAIL] The href of the button is empty.");
        Assert.assertFalse(href.equals("#"), "[TC_CD_016][FAIL] The button's href is '#' (not yet assigned).");
        Assert.assertTrue(href.contains("/chitiet/"), "[TC_CD_016][FAIL] href doesn't match the pattern '/chitiet/{id}': " + href);

        //-------------------------------------------
        logAndReport("[TC_CD_016] PASS - All information on the hover card is valid");
    }

    @Test(priority = 17,
            description = "TC_CD_017: Verify course listing page loads successfully")
    public void TC_CD_017_Verify_Click_On_Detail_Button() {
        int indexCard = 1;
        logAndReport("Step 1: Navigate to Course Detail page with courseId= " + VALID_COURSE_ID);
        CourseDetailPage courseDetailPage = navigateToCourseDetailPage(VALID_COURSE_ID);
        FeaturedCourseCard card = courseDetailPage.getRelatedCourseCard(indexCard);

        logAndReport("Step 2: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();

        logAndReport("Step 3: Hover the related card at position (" + indexCard + ")");
        card.hoverOnCard();

        logAndReport("Step 4: Click on the \"XEM CHI TIẾT\" button");
        card.clickOnViewDetailButton();

        logAndReport("VP1: The current URL page is the Detail Page");
        String currentUrl = courseDetailPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/chitiet/"),
                "[TC_CD_017][FAIL] URL must contains '/chitiet/'. Actual: " + currentUrl
        );

        logAndReport("VP2: The current URL page matches the link card");
        String urlCard = card.getCourseCardHref();
        Assert.assertEquals(
                courseDetailPage.getCurrentUrl(),
                urlCard,
                "[TC_CD_017][FAIL] The URL Detail doesn't match"
        );

        logAndReport("VP3: The title: \"THÔNG TIN KHÓA HỌC\" displays");
        Banner banner = new Banner(DriverManager.getDriver());
        Assert.assertEquals(
                banner.getBannerTitle(),
                "THÔNG TIN KHÓA HỌC",
                "[TC_CD_017][FAIL] The title: \"THÔNG TIN KHÓA HỌC\" doesn't displays");
    }

//    @Test(priority = 1,
//            description = "TC_CD_001: Verify_Course_Detail_Matches_Response_From_API")
//    public void Verify_Course_Detail_Matches_Response_From_API() {
//        CourseListPage courseListPage = navigateToCourseListPage();
//        APIAction apiAction = new APIAction();
//        String courseId = apiAction.getRandomCourseId(1, 12, "GP01");
//        logAndReport("/---------------------------/");
//        logAndReport("courseId:" + courseId);
//        CourseModel course = apiAction.getCourseDetail(courseId);
//
//        logAndReport("Click on the course card");
//        courseListPage.clickCourseCardByCourseId(courseId);
//
//        logAndReport("The detail page opens");
//        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
//
//        Assert.assertEquals(
//                courseDetailPage.getCourseImageLink(),
//                course.getHinhAnh(),
//                "Course Image does not match with API"
//        );
//
//        Assert.assertEquals(
//                courseDetailPage.getCourseName(),
//                course.getTenKhoaHoc(),
//                "Course Name does not match with API"
//        );
//
//        String expectedDesc = course.getMoTa();
//        String actualDesc = courseDetailPage.getCourseDescription();
//        Assert.assertTrue(
//                expectedDesc.contains(actualDesc.replace("...", "").trim()),
//                "Course Describe does not match with API"
//        );
//
//        Assert.assertEquals(
//                courseDetailPage.getInstructorName(),
//                course.getNguoiTao().getHoTen(),
//                "Course Author does not match with API"
//        );
//    }
//

}
