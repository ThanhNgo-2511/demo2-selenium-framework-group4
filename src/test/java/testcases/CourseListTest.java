package testcases;

import base.BaseTest;
import drivers.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CourseDetailPage;
import pages.CourseListPage;

import static helper.TestHelper.*;

public class CourseListTest extends BaseTest {

    @Test
    public void Verify_Course_Card_Count_Per_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();
        int courseCardCount = courseListPage.getCourseCardCount();
        logAndReport("Number of Course Cards displayed: " + courseCardCount);

        logAndReport("Verify Point: The count of Course Cards are >0 and <=12");
        Assert.assertTrue(
                courseCardCount > 0 && courseCardCount <= 12,
                "Invalid number of Course Cards: " + courseCardCount
        );
    }

    @Test
    public void Verify_Navigation_To_Course_Detail() {
        CourseListPage courseListPage = navigateToCourseListPage();
        logAndReport("Click on the second card");
        courseListPage.clickCourseCard (2);
        logAndReport("The detail page opens");
        CourseDetailPage courseDetailPage = new CourseDetailPage(DriverManager.getDriver());
        logAndReport("Verify Point: The title: \"THÔNG TIN KHÓA HỌC\" displays");
        Assert.assertEquals(courseDetailPage.getTitleDetailPage(),"THÔNG TIN KHÓA HỌC");
    }

    @Test
    public void Verify_Next_Pagination() {
        CourseListPage courseListPage = navigateToCourseListPage();
        logAndReport("Check the current page is the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "1",
                "Current page is not Page 1"
        );

        logAndReport("Click on the Next button on the pagination");
        courseListPage.clickNextPage();

        logAndReport("Verify Point: Current page is page 2");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "2",
                "Next pagination did not navigate to Page 2"
        );
    }

    @Test
    public void Verify_Next_Pagination_On_Last_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();
        String lastPage = courseListPage.getLastPageNumber();

        logAndReport("Click on the last page button on the pagination");
        courseListPage.clickPageNumber(Integer.parseInt(lastPage));

        logAndReport("Check the current page is the last page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                lastPage,
                "Failed to navigate to the last page"
        );

        logAndReport("Click on the Next button on the pagination");
        courseListPage.clickNextPage();

        logAndReport("Verify Point: Current page is still the last page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                lastPage,
                "Next pagination changed the page from the last page"
        );
    }

    @Test
    public void Verify_Specific_Page_Navigation() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Check the current page is the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "1",
                "Current page is not Page 1"
        );
        logAndReport("Scroll to pagination");
        courseListPage.scrollToCurrentPage();
        int pageNumber = 3;

        logAndReport("Click on the page " + pageNumber);
        courseListPage.clickPageNumber(pageNumber);

        logAndReport("Verify Point: Current page is page "+ pageNumber);
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                Integer.toString(pageNumber),
                "Specific page navigation did not navigate to Page "+pageNumber
        );
    }

    @Test
    public void Verify_Previous_Pagination() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Check the current page is the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "1",
                "Current page is not Page 1"
        );

        logAndReport("Click on the Page 2 on pagination");
        courseListPage.clickPageNumber(2);

        logAndReport("Check the current page is the page 2");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "2",
                "Failed to navigate to Page 2"
        );
        logAndReport("Scroll to pagination");
        courseListPage.scrollToCurrentPage();

        logAndReport("Click on the Previous button on the navigation");
        courseListPage.clickPreviousPage();

        logAndReport("Verify Point: Current page is the page 1");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "1",
                "Previous pagination did not navigate to Page 1"
        );
    }

    @Test
    public void Verify_Previous_Pagination_On_First_Page() {
        CourseListPage courseListPage = navigateToCourseListPage();

        logAndReport("Check the current page is the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "1",
                "Current page is not Page 1"
        );

        logAndReport("Click on the Previous button on the navigation");
        courseListPage.clickPreviousPage();

        logAndReport("Verify Point: Current page is still the first page");
        Assert.assertEquals(
                courseListPage.getCurrentPage(),
                "1",
                "Previous button changed the page from Page 1"
        );
    }
}
