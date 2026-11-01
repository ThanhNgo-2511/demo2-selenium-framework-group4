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
import pages.components.Banner;
import pages.components.StandardCourseCard;

import java.util.*;

import static helper.TestCaseHelper.*;

public class CourseListTest extends BaseTest {

    @Test(priority = 1,
            description = "TC_CL_001: Verify course listing page loads successfully")
    public void TC_CL_001_Verify_Page_Course_Loads_Successfully() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("VP1: Verify URL");
        String currentUrl = courseListPage.getCurrentUrl();
        logAndReport("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("/khoahoc"),
                "[TC_CL_001][FAIL] URL must contains '/khoahoc'"
        );

        logAndReport("VP2: Verify page title is not empty");
        String title = courseListPage.getPageTitle();
        logAndReport("Page Title: " + title);
        Assert.assertNotNull(title,
                "[TC_CL_001][FAIL] Page title is null"
        );
        Assert.assertFalse(title.trim().isEmpty(),
                "[TC_CL_001][FAIL] Page title is empty"
        );

        logAndReport("VP3: The title: \"Danh sách khóa học\" displays");
        boolean sectionTitleDisplayed = courseListPage.isCourseListTitleDisplayed();
        logAndReport("Section Title displayed= " + sectionTitleDisplayed);
        Assert.assertTrue(sectionTitleDisplayed,
                "[TC_CL_001][FAIL] The 'Danh sách khóa học' title doesn't display");

        logAndReport("VP4: Verify the page has course cards");
        int cardCount = courseListPage.getCourseCardCount();
        logAndReport("Card count: " + cardCount);
        Assert.assertTrue(
                cardCount > 0,
                "[TC_CL_001][FAIL] No course cards are displayed on the page"
        );
    }

    @Test(priority = 2,
            description = "TC_CL_002: Verify the banner of the Course List page")
    public void TC_CL_002_Verify_The_Banner_Course_Displayed() {
        navigateToCourseListPage();
        Banner banner = new Banner(DriverManager.getDriver());

        String expectedTitle = "KHÓA HỌC";
        logAndReport("VP1: Verify the Banner Title \"" + expectedTitle + "\" displays on the Course List");
        boolean bannerTitleDisplayed = banner.bannerTitleDisplayed();
        logAndReport("Banner Title displayed =" + bannerTitleDisplayed);
        Assert.assertTrue(bannerTitleDisplayed,
                "[TC_CD_002][FAIL]  The banner title doesn't display"
        );

        String bannerTitleText = banner.getBannerTitle();
        logAndReport("Banner Title: " + bannerTitleText);
        Assert.assertEquals(bannerTitleText,
                expectedTitle,
                "[TC_CD_002][FAIL] The title text isn't \"" + expectedTitle + "\""
        );

        String expectedSubTitle = "BẮT ĐẦU HÀNH TRÌNH NÀO!!!";
        logAndReport("VP2: Verify the Banner Sub-title \"" + expectedSubTitle + "\" displays on the Course List");
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
                "[TC_CD_002][FAIL] The sub-title text isn't \"" + expectedSubTitle + "\""
        );
    }

    @Test(priority = 3,
            description = "TC_CL_003: Verify the Insight Information of the Course List page")
    public void TC_CL_003_Verify_The_Insight_Information_Section_Displayed() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("VP1: Verify the Insight Information section displays on the Course List");
        boolean infoBoxDisplayed = courseListPage.inSightInformationBoxDisplayed();
        logAndReport("Insight Information displayed =" + infoBoxDisplayed);
        Assert.assertTrue(infoBoxDisplayed,
                "[TC_CL_003][FAIL] The banner title doesn't display"
        );

        Map<String, String> actualInfo = courseListPage.getInsightInformation();
        Map<String, String> expectedInfo = new LinkedHashMap<>();
        expectedInfo.put("CHƯƠNG TRÌNH HỌC", "300");
        expectedInfo.put("NHÀ SÁNG TẠO", "10000");
        expectedInfo.put("NHÀ THIẾT KẾ", "400");
        expectedInfo.put("BÀI GIẢNG", "3000");
        expectedInfo.put("VIDEO", "40000");
        expectedInfo.put("LĨNH VỰC", "200");

        List<String> actualkeys = new ArrayList<>(actualInfo.keySet());
        List<String> expectedKeys = new ArrayList<>(expectedInfo.keySet());

        logAndReport("VP2: The number of insight items displayed matches expectations!");
        Assert.assertEquals(
                actualInfo.size(),
                expectedInfo.size(),
                "[TC_CL_003][FAIL] The number of insight items displayed does not match expectations!"
        );

        for (int i = 0; i < expectedKeys.size(); i++) {
            logAndReport("VP" + (i + 3) + ": Verify the context on the Insight Box (" + (i + 1) + ").");
            String lblInsightTitleActual = actualkeys.get(i);
            String lblInsightTitleExpected = expectedKeys.get(i);
            logAndReport("Insight Title: " + lblInsightTitleActual);
            Assert.assertEquals(
                    lblInsightTitleActual,
                    lblInsightTitleExpected,
                    "[TC_CL_003][FAIL] The \"" + lblInsightTitleExpected + "\" label doesn't match"
            );

            String lblInsightCountActual = actualInfo.get(lblInsightTitleActual);
            String lblInsightCountExpected = expectedInfo.get(lblInsightTitleExpected);
            logAndReport("Insight Value: " + lblInsightCountActual);
            Assert.assertEquals(
                    lblInsightCountActual,
                    lblInsightCountExpected,
                    "[TC_CL_003][FAIL] The \"" + lblInsightTitleExpected + "\" value doesn't match"
            );
        }
    }

    @Test(priority = 4,
            description = "TC_CL_004: Verify the number of course cards per page ")
    public void TC_CL_004_Verify_The_Number_Of_Course_Cards_Per_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("VP: The count of Course Cards are >0 and <=12");
        int cardCount = courseListPage.getCourseCardCount();
        logAndReport("Number of Course Cards displayed: " + cardCount);
        Assert.assertTrue(
                cardCount > 0 && cardCount <= 12,
                "[TC_CL_004][FAIL] Invalid number of Course Cards: " + cardCount
        );
    }

    @DataProvider(name = "validCardCourse")
    public Object[][] getIndexCard() {
        return new Object[][]{
                {3},
                {12}
        };
    }

    @Test(priority = 5, dataProvider = "validCardCourse",
            description = "TC_CL_005: Verify course card contains complete information")
    public void TC_CL_005_Verify_Course_Card_Contains_Complete_Information(int indexCard) {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Scroll to the card at position (" + indexCard + ")");
        StandardCourseCard card = courseListPage.getCourseCardByIndex(indexCard);
        card.scrollToCard();

        logAndReport("Check the card at position (" + indexCard + ")");

        //-------------------------------------------
        logAndReport("VP1:The Course Image displays and doesn't break");
        boolean imgDisplayed = card.isCardCourseImageDisplayed();
        logAndReport("Course Image displayed=" + imgDisplayed);
        Assert.assertTrue(
                imgDisplayed,
                "[TC_CL_005][FAIL] Course Image has displayed"
        );
        boolean imgLoaded = card.isCardCourseImageLoaded();
        logAndReport("Course Image loaded=" + imgLoaded);
        Assert.assertTrue(
                imgLoaded,
                "[TC_CL_005][FAIL] Course Image has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP2: The Course Name displays");
        String courseName = card.getCardCourseName();
        logAndReport("Course Name: " + courseName);
        Assert.assertFalse(
                courseName.isEmpty(),
                "[TC_CL_005][FAIL] Course Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP3: The Course Short Description displays");
        String shortDesc = card.getCardCourseShortDescription();
        logAndReport("Short Description: " + shortDesc);
        Assert.assertFalse(
                shortDesc.isEmpty(),
                "[TC_CL_005][FAIL] Course Short Description is empty"
        );

        //-------------------------------------------
        logAndReport("VP4: The Instructor Avatar displays and doesn't break");
        boolean avatarDisplay = card.isAvatarImageDisplayed();
        logAndReport(" Instructor Avatar display=" + avatarDisplay);
        Assert.assertTrue(
                avatarDisplay,
                "[TC_CL_005][FAIL] Instructor Avatar displayed"
        );
        boolean avatarLoaded = card.isAvatarImageLoaded();
        logAndReport(" Instructor Avatar loaded=" + avatarLoaded);
        Assert.assertTrue(
                avatarLoaded,
                "[TC_CL_005][FAIL] Instructor Avatar has broken (naturalWidth = 0)"
        );

        //-------------------------------------------
        logAndReport("VP5: The Instructor Name displays");
        String instructor = card.getCardCourseInstructorName();
        logAndReport("Instructor Name: " + instructor);
        Assert.assertFalse(
                instructor.isEmpty(),
                "[TC_CL_005][FAIL] Instructor Name is empty"
        );

        //-------------------------------------------
        logAndReport("VP6: The Price Sale displays");
        String priceSale = card.getCardCoursePriceSale();
        logAndReport("Price Sale: " + priceSale);
        Assert.assertFalse(
                priceSale.isEmpty(),
                "[TC_CL_005][FAIL] Price Sale is empty"
        );
        Assert.assertTrue(
                priceSale.matches(".*\\d.*"),
                "[TC_CL_005][FAIL] Price Sale isn't correct format: " + priceSale
        );

        //-------------------------------------------
        logAndReport("VP7: The Price Original displays");
        String priceOriginal = card.getCardCoursePriceOriginal();
        logAndReport("Price Original: " + priceOriginal);
        Assert.assertFalse(
                priceOriginal.isEmpty(),
                "[TC_CL_005][FAIL] Price Original is empty"
        );
        Assert.assertTrue(
                priceOriginal.matches(".*\\d.*"),
                "[TC_CL_005][FAIL] Price Original isn't correct format: " + priceOriginal
        );

        //-------------------------------------------
        logAndReport("VP8: The Rating displays");
        String ratingText = card.getCardRating();
        logAndReport("Rating: " + ratingText);
        Assert.assertFalse(
                ratingText.isEmpty(),
                "[TC_CL_005][FAIL] Rating is empty"
        );
        try {
            double rating = Double.parseDouble(
                    ratingText.replaceAll("[^0-9.]", "").trim());
            Assert.assertTrue(
                    rating >= 0.0 && rating <= 5.0,
                    "[TC_CL_005][FAIL] Rating is outside the 0–5 range: " + rating
            );
        } catch (NumberFormatException e) {
            logAndReport("[TC_CL_005][Rating] WARNING: Unable to parse the rating value");
        }

        //-------------------------------------------
        logAndReport("VP9: The Review Number displays");
        String reviewNumber = card.getCardReviewNumber();
        logAndReport("Review Number : " + reviewNumber);
        Assert.assertFalse(
                reviewNumber.isEmpty(),
                "[TC_CL_005][FAIL] Review Number is empty"
        );
        Assert.assertTrue(
                reviewNumber.matches(".*\\d.*"),
                "[TC_CL_005][FAIL] Review Number isn't correct format: " + reviewNumber
        );

        //-------------------------------------------
        logAndReport("VP10: The Course Card Href Link is valid");
        String href = card.getCourseCardHref();
        logAndReport("Course Card Href Link: " + href);
        Assert.assertNotNull(href, "[TC_CL_005][FAIL] The href of the card is null.");
        Assert.assertFalse(href.trim().isEmpty(), "[TC_CL_005][FAIL] The href of the card is empty.");
        Assert.assertFalse(href.equals("#"), "[TC_CL_005][FAIL] The card's href is '#' (not yet assigned).");
        Assert.assertTrue(href.contains("/chitiet/"), "[TC_CL_005][FAIL] href doesn't match the pattern '/chitiet/{id}': " + href);

        //-------------------------------------------
        logAndReport("[TC_CL_005] PASS - All information on the card is valid");
    }

    @Test(priority = 6, dataProvider = "validCardCourse",
            description = "TC_CL_006: Verify navigation to the details page when clicking on the card")
    public void TC_CL_006_Verify_Navigation_To_Course_Detail(int indexCard) {
        CourseListPage courseListPage = navigateToCourseListPage();
        StandardCourseCard card = courseListPage.getCourseCardByIndex(indexCard);

        logAndReport("Step 1: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();
        String urlCard = card.getCourseCardHref();

        logAndReport("Step 2: Click on the card at position (" + indexCard + ")");
        card.clickOnCard();
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());

        logAndReport("VP1: The current URL page is the Detail Page");
        String currentUrl = courseDetailPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/chitiet/"),
                "[TC_CL_006][FAIL] URL must contains '/chitiet/'. Actual: " + currentUrl
        );

        logAndReport("VP2: The current URL page matches the link card");
        Assert.assertEquals(
                courseDetailPage.getCurrentUrl(),
                urlCard,
                "[TC_CL_006][FAIL] The URL Detail doesn't match the link card"
        );

        logAndReport("VP3: The title: \"THÔNG TIN KHÓA HỌC\" displays");
        Banner banner = new Banner(DriverManager.getDriver());
        Assert.assertEquals(
                banner.getBannerTitle(),
                "THÔNG TIN KHÓA HỌC",
                "[TC_CL_006][FAIL] The title : \"THÔNG TIN KHÓA HỌC\" doesn't display"
        );
    }

    @Test(priority = 7,
            description = "TC_CL_007: Verify the navigation to next page when click Next button")
    public void TC_CL_007_Verify_Click_Next_Button_In_Pagination() {
        int indexCard = 1;
        CourseListPage courseListPage = navigateToCourseListPage();

        StandardCourseCard oldCard = courseListPage.getCourseCardByIndex(indexCard);
        String oldCourseName = oldCard.getCardCourseName();

        logAndReport("Step 1: Click on the Next button on the pagination");
        courseListPage.clickNextPage();
        courseListPage.waitCourseListLoadSuccess();

        logAndReport("VP1: Current page is page 2");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                2,
                "[TC_CL_007][FAIL] Next pagination did not navigate to Page 2"
        );

        logAndReport("VP2: Course list content is refreshed");
        StandardCourseCard newCard = courseListPage.getCourseCardByIndex(indexCard);
        String newCourseName = newCard.getCardCourseName();
        Assert.assertNotEquals(
                oldCourseName,
                newCourseName,
                "[TC_CL_007][FAIL] The first card on the page 2 is the same on the page 1"
        );
    }

    @Test(priority = 8,
            description = "TC_CL_008: Verify Next button when on the last page of pagination")
    public void TC_CL_008_Verify_Next_Button_When_On_Last_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Step 1: Click on the last page button on the pagination");
        courseListPage.clickPageNumber(courseListPage.getTotalPages());

        logAndReport("Step 2: Click on the Next button on the pagination");
        courseListPage.clickNextPage();

        logAndReport("Verify Point: Current page is still the last page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                courseListPage.getTotalPages(),
                "[TC_CL_008][FAIL] Next pagination changed the page from the last page"
        );
    }

    @Test(priority = 9,
            description = "TC_CL_09: Verify the navigation to new course page when select a specific page number")
    public void TC_CL_009_Verify_Specific_Page_On_Navigation() {
        int indexCard = 1;
        int pageNumber = 5;
        CourseListPage courseListPage = navigateToCourseListPage();

        StandardCourseCard oldCard = courseListPage.getCourseCardByIndex(indexCard);
        String oldCourseName = oldCard.getCardCourseName();

        logAndReport("Click on the page " + pageNumber);
        courseListPage.clickPageNumber(pageNumber);
        courseListPage.waitCourseListLoadSuccess();

        logAndReport("VP1: Current page is page " + pageNumber);
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                pageNumber,
                "[TC_CL_009][FAIL] Specific page navigation did not navigate to Page " + pageNumber
        );

        logAndReport("VP2: Course list content is refreshed");
        StandardCourseCard newCard = courseListPage.getCourseCardByIndex(indexCard);
        String newCourseName = newCard.getCardCourseName();
        Assert.assertNotEquals(
                oldCourseName,
                newCourseName,
                "[TC_CL_009][FAIL] The first card on the page " + pageNumber + " is the same on the page 1"
        );
    }

    @Test(priority = 10,
            description = "TC_CL_010: Verify the navigation to previous page when click Next button")
    public void TC_CL_010_Verify_Click_Previous_Button_On_Pagination() {
        int indexCard = 1;
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Step 1: Click on the Page 2 on pagination");
        courseListPage.clickPageNumber(2);
        courseListPage.waitCourseListLoadSuccess();
        StandardCourseCard oldCard = courseListPage.getCourseCardByIndex(indexCard);
        String oldCourseName = oldCard.getCardCourseName();

        logAndReport("Step 2: Click on the Previous button on the navigation");
        courseListPage.clickPreviousPage();
        courseListPage.waitCourseListLoadSuccess();
        StandardCourseCard newCard = courseListPage.getCourseCardByIndex(indexCard);
        String newCourseName = newCard.getCardCourseName();


        logAndReport("VP1: Current page is the page 1");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                1,
                "[TC_CL_010][FAIL] Previous pagination did not navigate to Page 1"
        );

        logAndReport("VP2: Course list content is refreshed");
        Assert.assertNotEquals(
                oldCourseName,
                newCourseName,
                "[TC_CL_010][FAIL] The first card on the page 2 is the same on the page 1"
        );
    }

    @Test(priority = 11,
            description = "TC_CL_011: Verify Previous button when on the first page of pagination")
    public void TC_CL_011_Verify_Previous_Button_When_On_First_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Step1: Click on the Previous button on the navigation");
        courseListPage.clickPreviousPage();

        logAndReport("VP: Current page is still the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                1,
                "[TC_CL_011][FAIL]Previous button changed the page from Page 1"
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

    @Test(enabled = false, priority = 12, dataProvider = "paginationTest",
            description = "TC_CL_012: Verify that the course data in the card matches the response from the API")
    public void TC_CL_012_Verify_Course_Card_Information_Matches_Response_From_API(int pageNum) {
        int Card = 2;
        APIAction api = new APIAction();
        CourseModel course = api.getCourseInforFromList(api.getCourseList(pageNum, 12, "GP01"), 1);

        CourseListPage courseListPage = navigateToCourseListPage();
        if (pageNum > 1) {
            logAndReport("Navigation to the (" + pageNum + ") page.");
            courseListPage.clickPageNumber(pageNum);
            courseListPage.waitCourseListLoadSuccess();
        }

        StandardCourseCard card = courseListPage.getCourseCardByIndex(Card);
        logAndReport("Scroll to the card at position (" + Card + ")");
        card.scrollToCard();

        logAndReport("Check the card at position (" + Card + ")");


        logAndReport("VP1: The link of the course details contains the \"MaKhoaHoc\" field from the API.");
        String actualURL = card.getCourseCardHref();
        String expectedURL = course.getMaKhoaHoc().replace(" ", "%20");
        Assert.assertTrue(
                actualURL.contains(expectedURL),
                "[TC_CL_012][FAIL] Course Href doesn't match with API"
        );

        logAndReport("VP2: The link of the course image matches the \"HinhAnh\" field from the API");
        Assert.assertEquals(
                card.getImageCourseLink(),
                course.getHinhAnh(),
                "[TC_CL_012][FAIL] Course Image doesn't match with API"
        );

        logAndReport("VP3: The course name matches the \"TenKhoaHoc\" field from the API");
        Assert.assertEquals(
                card.getCardCourseName(),
                course.getTenKhoaHoc(),
                "[TC_CL_012][FAIL] Course Name doesn't match with API"
        );

        logAndReport("VP4: The short description on the card contains a portion of the \"MoTa\" field from the API.");
        String expectedDesc = course.getMoTa();
        String actualDesc = card.getCardCourseShortDescription();
        Assert.assertTrue(
                expectedDesc.contains(actualDesc.replace("...", "").trim()),
                "[TC_CL_012][FAIL] Course Describe doesn't contain with API"
        );

        logAndReport("VP5: The instructor name matches the \"NguoiTao.HoTen\" field from the API");
        Assert.assertEquals(
                card.getCardCourseInstructorName(),
                course.getNguoiTao().getHoTen(),
                "[TC_CL_012][FAIL] Course Instructor Name doesn't match with API"
        );
    }

    @DataProvider(name = "validCardCourseMatchName")
    public Object[][] getIndexValidCard() {
        return new Object[][]{
                {3},
                {11}
        };
    }
    @Test(priority = 13,dataProvider = "validCardCourseMatchName",
            description = "TC_CL_013: Verify course name on the Course Detail page matches the selected course card.")
    public void TC_CD_006_Verify_Course_Name_Card_Matches_Course_Name_Detail(int indexCard) {
        CourseListPage courseListPage = navigateToCourseListPage();
        StandardCourseCard card = courseListPage.getCourseCardByIndex(indexCard);

        logAndReport("Step 1: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();
        String courseNameCard = card.getCardCourseName();

        logAndReport("Step 2: Click on the card at position (" + indexCard + ")");
        card.clickOnCard();
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
        courseDetailPage.scrollToTop();

        logAndReport("VP: Course Name on the detail page matches course name in card");
        Assert.assertTrue(
                courseDetailPage.isCourseNameMatches(courseNameCard),
                "[TC_CL_013][FAIL] Course name on Course Detail does not match Course Card"
        );
    }

    @Test(priority = 14,
            description = "TC_CD_007: Verify course image on the Course Detail page matches the selected course card.")
    public void TC_CD_014_Verify_Course_Image_Card_Matches_Course_Image_Detail() {
        int indexCard = 11;
        CourseListPage courseListPage = navigateToCourseListPage();
        StandardCourseCard card = courseListPage.getCourseCardByIndex(indexCard);

        logAndReport("Step 1: Scroll to the card at position (" + indexCard + ")");
        card.scrollToCard();
        String imageCardLink = card.getImageCourseLink();

        logAndReport("Step 2: Click on the card at position (" + indexCard + ")");
        card.clickOnCard();
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
        courseDetailPage.scrollToTop();

        logAndReport("VP: Course Image on the detail page matches course image in card");
        String imageCoursePageLink =  courseDetailPage.getCourseImageLink();
        logAndReport("The src of Image course on the card: "+ imageCardLink);
        logAndReport("The src of Image course on the detail page: "+imageCoursePageLink);
        Assert.assertEquals(
                imageCoursePageLink,
                imageCardLink,
                "[TC_CL_014][FAIL]The course image on the detail page does not match with data in course card"
        );
    }

//    @Test(enabled = false,
//            description = "TC_CD_008: Verify Instructor information matches the selected course card.")
//    public void TC_CD_008_Verify_Course_Instructor_Card_Matches_Course_Instructor_Detail() {
//        CourseListPage courseListPage = navigateToCourseListPage();
//
//        int indexCard = 2;
//        String courseAuthor = courseListPage.getCardCourseName();
//
//        logAndReport("Click on the card at position ("+indexCard+")");
//        courseListPage.clickCourseCard();
//
//        logAndReport("The detail page opens");
//        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
//
//        logAndReport("VP1: Course Author on the detail page matches course author in card");
//        Assert.assertEquals(
//                courseDetailPage.getInstructorName(),
//                courseAuthor,
//                "The course author on the detail page does not match with data in course card"
//        );
//
//        logAndReport("VP2: Course Author on the detail page matches course author in card");
//        Assert.assertEquals(
//                courseDetailPage.getInstructorName(),
//                courseAuthor,
//                "The course author on the detail page does not match with data in course card"
//        );
//    }
//
//    @Test(enabled = false,
//            description = "TC_CD_008: Verify course des on the Course Detail page matches the selected course card.")
//    public void TC_CD_009_Verify_Course_Description_Card_Matches_Course_Description_Detail() {
//        CourseListPage courseListPage = navigateToCourseListPage();
//
//        int indexCard = 2;
//        String courseDescription = courseListPage.getCardCourseShortDescription();
//
//        logAndReport("Click on the card at position ("+indexCard+")");
//        courseListPage.clickCourseCard();
//
//        logAndReport("The detail page opens");
//        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
//
//        logAndReport("VP1: Course Description on the detail page contains the course described in the card");
//        String expectedDesc =  courseDetailPage.getCourseDescription();
//        Assert.assertTrue(
//                expectedDesc.contains(courseDescription.replace("...", "").trim()),
//                "Course Description on the detail page doesn't contain the course described in the card"
//        );
//    }
//
//    @Test(enabled = false,
//            description = "TC_CD_008: Verify course des on the Course Detail page matches the selected course card.")
//    public void TC_CD_010_Verify_Course_Description_Card_Matches_Course_Description_Detail() {
//        CourseListPage courseListPage = navigateToCourseListPage();
//
//        int indexCard = 2;
//        String courseDescription = courseListPage.getCardCourseShortDescription();
//
//        logAndReport("Click on the card at position ("+indexCard+")");
//        courseListPage.clickCourseCard();
//
//        logAndReport("The detail page opens");
//        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
//
//        logAndReport("VP1: Course Description on the detail page contains the course described in the card");
//        String expectedDesc =  courseDetailPage.getCourseDescription();
//        Assert.assertTrue(
//                expectedDesc.contains(courseDescription.replace("...", "").trim()),
//                "Course Description on the detail page doesn't contain the course described in the card"
//        );
//    }

}
