package testcases.course;

import apiHelper.APIAction;
import base.BaseTest;
import drivers.DriverManager;
import models.CourseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CourseDetailPage;
import pages.CourseListPage;

import static helper.TestCaseHelper.*;

public class CourseListTest extends BaseTest {

    @Test(priority = 1,
            description = "TC_CL_001: Verify course listing page loads successfully")
    public void TC_CL_001_Verify_Page_Course_Loads_Successfully() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("VP1: Verify URL");
        String currentUrl = courseListPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/khoahoc"),
                "[TC_CL_001][FAIL] URL must contains '/khoahoc'. Actual: " + currentUrl
        );

        logAndReport("VP2: Verify page title is not empty");
        String title = courseListPage.getPageTitle();
        Assert.assertNotNull(title,
                "[TC_CL_001][FAIL] Page title is null"
        );
        Assert.assertFalse(title.trim().isEmpty(),
                "[TC_CL_001][FAIL] Page title is empty"
        );

        logAndReport("VP3: Verify the page has course cards");
        int cardCount = courseListPage.getCourseCardCount();
        Assert.assertTrue(
                cardCount > 0,
                "[TC_CL_001][FAIL] No course cards are displayed on the page"
        );
    }

    @Test(priority = 2,
            description = "TC_CL_002: Verify the number of course cards per page ")
    public void TC_CL_002_Verify_The_Number_Of_Course_Cards_Per_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();
        int cardCount = courseListPage.getCourseCardCount();
        logAndReport("[TC_CL_002] Number of Course Cards displayed: " + cardCount);
        logAndReport("VP: The count of Course Cards are >0 and <=12");
        Assert.assertTrue(
                cardCount > 0 && cardCount <= 12,
                "[TC_CL_002][FAIL] Invalid number of Course Cards: " + cardCount
        );
    }

    @DataProvider(name = "validCardCourse")
    public Object[][] getIndexCard() {
        return new Object[][]{
                {3},
                {12}
        };
    }

    @Test(priority = 3, dataProvider = "validCardCourse",
            description = "TC_CL_003: Verify course card contains complete information")
    public void TC_CL_003_Verify_Course_Card_Contains_Complete_Information(int indexCard) {
        CourseListPage courseListPage = navigateToCourseListPage();
        logAndReport("Check the card at position (" + indexCard + ")");

        //-------------------------------------------
        logAndReport("VP1: The Course Name displays");
        String courseName = courseListPage.getCardCourseName(indexCard);
        logAndReport("Course Name: " + courseName);
        Assert.assertFalse(
                courseName.isEmpty(),
                "[TC_CL_003][FAIL] Course Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP2: The Course Short Description displays");
        String shortDesc = courseListPage.getCardCourseShortDescription(indexCard);
        logAndReport("Short Description: " + shortDesc);
        Assert.assertFalse(
                shortDesc.isEmpty(),
                "[TC_CL_003][FAIL] Course Short Description is empty"
        );

        //-------------------------------------------
        logAndReport("VP3:The Course Image displays and doesn't break");
        boolean imgDisplayed = courseListPage.isCardCourseImageDisplayed(indexCard);
        logAndReport(" Course Image loaded=" + imgDisplayed);
        Assert.assertTrue(
                imgDisplayed,
                "[TC_CL_003][FAIL] Course Image has broken (naturalWidth = 0)"
        );
        boolean imgLoaded = courseListPage.isCardCourseImageLoaded(indexCard);
        logAndReport(" Course Image loaded=" + imgLoaded);
        Assert.assertTrue(
                imgLoaded,
                "[TC_CL_003][FAIL] Course Image has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP4: The Instructor Avatar displays and doesn't break");
        boolean avatarDisplay = courseListPage.isAvatarImageDisplayed(indexCard);
        logAndReport(" Instructor Avatar display=" + avatarDisplay);
        Assert.assertTrue(
                avatarDisplay,
                "[TC_CL_003][FAIL] Instructor Avatar displayed"
        );
        boolean avatarLoaded = courseListPage.isAvatarImageLoaded(indexCard);
        logAndReport(" Instructor Avatar loaded=" + avatarLoaded);
        Assert.assertTrue(
                avatarLoaded,
                "[TC_CL_003][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP5: The Instructor Name displays");
        String instructor = courseListPage.getCardCourseInstructorName(indexCard);
        logAndReport("Instructor Name: " + instructor);
        Assert.assertFalse(
                instructor.isEmpty(),
                "[TC_CL_003][FAIL] Instructor Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP6: The Price Sale displays");
        String priceSale = courseListPage.getCardCoursePriceSale(indexCard);
        logAndReport("Price Sale: " + priceSale);
        Assert.assertFalse(
                priceSale.isEmpty(),
                "[TC_CL_003][FAIL] Price Sale is empty"
        );
        Assert.assertTrue(
                priceSale.matches(".*\\d.*"),
                "[TC_CL_003][FAIL] Price Sale isn't correct format: " + priceSale
        );

        //-------------------------------------------
        logAndReport("VP7: The Price Original displays");
        String priceOriginal = courseListPage.getCardCoursePriceOriginal(indexCard);
        logAndReport("Price Original: " + priceOriginal);
        Assert.assertFalse(
                priceOriginal.isEmpty(),
                "[TC_CL_003][FAIL] Price Original is empty"
        );
        Assert.assertTrue(
                priceOriginal.matches(".*\\d.*"),
                "[TC_CL_003][FAIL] Price Original isn't correct format: " + priceOriginal
        );

        //-------------------------------------------
        logAndReport("VP8: The Rating displays");
        String ratingText = courseListPage.getCardRating(indexCard);
        logAndReport("Rating: " + ratingText);
        Assert.assertFalse(
                ratingText.isEmpty(),
                "[TC_CL_003][FAIL] Rating is empty"
        );
        try {
            double rating = Double.parseDouble(
                    ratingText.replaceAll("[^0-9.]", "").trim());
            Assert.assertTrue(
                    rating >= 0.0 && rating <= 5.0,
                    "[TC_CL_003][FAIL] Rating is outside the 0–5 range: " + rating
            );
        } catch (NumberFormatException e) {
            logAndReport("[TC_CL_003][Rating] WARNING: Unable to parse the rating value");
        }

        //-------------------------------------------
        logAndReport("VP9: The Review Number displays");
        String reviewNumber = courseListPage.getCardReviewNumber(indexCard);
        logAndReport("Review Number : " + reviewNumber);
        Assert.assertFalse(
                reviewNumber.isEmpty(),
                "[TC_CL_003][FAIL] Review Number is empty"
        );
        Assert.assertTrue(
                reviewNumber.matches(".*\\d.*"),
                "[TC_CL_003][FAIL] Review Number isn't correct format: " + reviewNumber
        );

        //-------------------------------------------
        logAndReport("VP10: The Course Card Href Link is valid");
        String href = courseListPage.getCourseCardHref(indexCard);
        logAndReport("Course Card Href Link: " + href);
        Assert.assertNotNull(href, "[TC_CL_003][FAIL] The href of the card is null.");
        Assert.assertFalse(href.trim().isEmpty(), "[TC_CL_003][FAIL] The href of the card is empty.");
        Assert.assertFalse(href.equals("#"), "[TC_CL_003][FAIL] The card's href is '#' (not yet assigned).");
        Assert.assertTrue(href.contains("/chitiet/"), "[TC_CL_003][FAIL] href doesn't match the pattern '/chitiet/{id}': " + href);

        //-------------------------------------------
        logAndReport("[TC_CL_003] PASS - All information on the card is valid");
    }

    @Test(priority = 4, dataProvider = "validCardCourse",
            description = "TC_CL_004: Verify navigation to the details page when clicking on the card")
    public void TC_CL_004_Verify_Navigation_To_Course_Detail(int indexCard) {
        CourseListPage courseListPage = navigateToCourseListPage();

        String urlCard = courseListPage.getCourseCardHref(indexCard);
        logAndReport("Click on the card at position (" + indexCard + ")");
        courseListPage.clickCourseCard(indexCard);

        logAndReport("The detail page opens");
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());

        logAndReport("VP1: The current URL page is the Detail Page");
        Assert.assertEquals(
                courseDetailPage.getCurrentUrl(),
                urlCard,
                "[TC_CL_004][FAIL] The URL Detail doesn't match"
        );

        logAndReport("VP2: The title: \"THÔNG TIN KHÓA HỌC\" displays");
        Assert.assertEquals(
                courseDetailPage.getTitleDetailPage(),
                "THÔNG TIN KHÓA HỌC",
                "[TC_CL_004][FAIL] The title : \"THÔNG TIN KHÓA HỌC\" doesn't display"
        );
    }

    @DataProvider(name = "paginationTest")
    public Object[][] getNumberPage() {
        return new Object[][]{
                {1},
                {2},
                {20},
                {21}
        };
    }

    @Test(enabled = false, priority = 5, dataProvider = "paginationTest",
            description = "TC_CL_005: Verify that the course data in the card matches the response from the API")
    public void TC_CL_005_Verify_Course_Card_Information_Matches_Response_From_API(int pageNum) {
        CourseListPage courseListPage = navigateToCourseListPage();
        if (pageNum > 1) {
            logAndReport("Step 1: Click on the (" + pageNum + ") page.");
            courseListPage.clickPageNumber(pageNum);
            courseListPage.waitCourseListLoadSuccess();
        }

        int index = 2;
        courseListPage.scrollCourseCard(index);
        APIAction api = new APIAction();
        CourseModel course = api.getCourseInforFromList(api.getCourseList(pageNum, 12, "GP01"), index);

        logAndReport("VP1: The link of the course details contains the \"MaKhoaHoc\" field from the API.");
        String actualURL = courseListPage.getCourseCardHref(index);
        String expectedURL = course.getMaKhoaHoc().replace(" ", "%20");
        Assert.assertTrue(
                actualURL.contains(expectedURL),
                "[TC_CL_005][FAIL] Course Href doesn't match with API"
        );

        logAndReport("VP2: The link of the course image matches the \"HinhAnh\" field from the API");
        Assert.assertEquals(
                courseListPage.getImageCourseLink(index),
                course.getHinhAnh(),
                "[TC_CL_005][FAIL] Course Image doesn't match with API"
        );

        logAndReport("VP3: The course name matches the \"TenKhoaHoc\" field from the API");
        Assert.assertEquals(
                courseListPage.getCardCourseName(index),
                course.getTenKhoaHoc(),
                "[TC_CL_005][FAIL] Course Name doesn't match with API"
        );

        logAndReport("VP4: The short description on the card contains a portion of the \"MoTa\" field from the API.");
        String expectedDesc = course.getMoTa();
        String actualDesc = courseListPage.getCardCourseShortDescription(index);
        Assert.assertTrue(
                expectedDesc.contains(actualDesc.replace("...", "").trim()),
                "[TC_CL_005][FAIL] Course Describe doesn't contain with API"
        );

        logAndReport("VP5: The instructor name matches the \"NguoiTao.HoTen\" field from the API");
        Assert.assertEquals(
                courseListPage.getCardCourseInstructorName(index),
                course.getNguoiTao().getHoTen(),
                "[TC_CL_005][FAIL] Course Instructor Name doesn't match with API"
        );
    }

    @Test(priority = 6,
            description = "TC_CL_006: Verify the navigation to next page when click Next button")
    public void TC_CL_006_Verify_Click_Next_Button_In_Pagination() {
        CourseListPage courseListPage = navigateToCourseListPage();
        String oldCourseName = courseListPage.getCardCourseName(1);

        logAndReport("Step 1: Click on the Next button on the pagination");
        courseListPage.clickNextPage();
        courseListPage.waitCourseListLoadSuccess();

        String newCourseName = courseListPage.getCardCourseName(1);
        logAndReport("VP1: Current page is page 2");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                2,
                "[TC_CL_006][FAIL] Next pagination did not navigate to Page 2"
        );

        logAndReport("VP2: Course list content is refreshed");
        System.out.println("courseCard1: " + oldCourseName);
        System.out.println("courseCard2: " + newCourseName);
        Assert.assertNotEquals(
                oldCourseName,
                newCourseName,
                "[TC_CL_006][FAIL] The first card on the page 2 is the same on the page 1"
        );
    }

    @Test(priority = 7,
            description = "TC_CL_007: Verify Next button when on the last page of pagination")
    public void TC_CL_007_Verify_Next_Button_When_On_Last_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Step 1: Click on the last page button on the pagination");
        courseListPage.clickPageNumber(courseListPage.getTotalPages());

        logAndReport("Step 2: Click on the Next button on the pagination");
        courseListPage.clickNextPage();

        logAndReport("Verify Point: Current page is still the last page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                courseListPage.getTotalPages(),
                "[TC_CL_007][FAIL] Next pagination changed the page from the last page"
        );
    }

    @Test(priority = 8,
            description = "TC_CL_008: Verify the navigation to new course page when select a specific page number")
    public void TC_CL_008_Verify_Specific_Page_On_Navigation() {
        CourseListPage courseListPage = navigateToCourseListPage();
        String oldCourseName = courseListPage.getCardCourseName(1);

        int pageNumber = 5;
        logAndReport("Step 1: Click on the page " + pageNumber);
        courseListPage.clickPageNumber(pageNumber);
        courseListPage.waitCourseListLoadSuccess();
        String newCourseName = courseListPage.getCardCourseName(1);

        logAndReport("VP1: Current page is page " + pageNumber);
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                pageNumber,
                "[TC_CL_008][FAIL] Specific page navigation did not navigate to Page " + pageNumber
        );

        logAndReport("VP2: Course list content is refreshed");
        System.out.println("courseCard1: " + oldCourseName);
        System.out.println("courseCard2: " + newCourseName);
        Assert.assertNotEquals(
                oldCourseName,
                newCourseName,
                "[TC_CL_008][FAIL] The first card on the page " + pageNumber + " is the same on the page 1"
        );
    }

    @Test(priority = 9,
            description = "TC_CL_009: Verify the navigation to previous page when click Next button")
    public void TC_CL_009_Verify_Click_Previous_Button_On_Pagination() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Step 1: Click on the Page 2 on pagination");
        courseListPage.clickPageNumber(2);
        courseListPage.waitCourseListLoadSuccess();
        String oldCourseName = courseListPage.getCardCourseName(1);

        logAndReport("Step 2: Click on the Previous button on the navigation");
        courseListPage.clickPreviousPage();
        courseListPage.waitCourseListLoadSuccess();
        String newCourseName = courseListPage.getCardCourseName(1);

        logAndReport("VP1: Current page is the page 1");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                1,
                "[TC_CL_009][FAIL] Previous pagination did not navigate to Page 1"
        );

        logAndReport("VP2: Course list content is refreshed");
        System.out.println("courseCard1: " + oldCourseName);
        System.out.println("courseCard2: " + newCourseName);
        Assert.assertNotEquals(
                oldCourseName,
                newCourseName,
                "[TC_CL_009][FAIL] The first card on the page 1 is the same on the page 2"
        );
    }

    @Test(priority = 10,
            description = "TC_CL_010: Verify Previous button when on the first page of pagination")
    public void TC_CL_010_Verify_Previous_Button_When_On_First_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Step1: Click on the Previous button on the navigation");
        courseListPage.clickPreviousPage();

        logAndReport("VP: Current page is still the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                1,
                "Previous button changed the page from Page 1"
        );
    }

}
