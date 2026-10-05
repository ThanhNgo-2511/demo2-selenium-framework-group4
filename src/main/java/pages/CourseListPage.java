package pages;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CourseListPage extends CommonPage {

    // --- Locators cho Course List & Cards ---
    private By byCourseCards;
    private By byCourseCardImages;

    // --- Locators cho Pagination ---
    private By byBtnPrevious;
    private By byBtnNext;
    private By byPaginationPages;
    private By byCurrentPage;
    private By byPagination;

    public CourseListPage(WebDriver driver) {
        super(driver);

        this.byCourseCards = By.xpath("//a[@class='cardGlobal']");
        this.byCourseCardImages = By.xpath("//a[@class='cardGlobal']/img");

        this.byBtnPrevious = By.xpath("//a[@aria-label='Previous page']");
        this.byBtnNext = By.xpath("//a[@aria-label='Next page']");
        this.byPaginationPages = By.xpath("//a[starts-with(@aria-label, 'Page')]");
        this.byCurrentPage = By.xpath("//ul[@class='paginationPages']//a[@aria-current='page']");
        this.byPagination = By.xpath("//ul[@class='paginationPages']");
    }

    public int getCourseCardCount() {
        return findElements(byCourseCards).size();
    }

    public String getCurrentPage() {
        return getText(byCurrentPage);
    }

    public void clickNextPage() {
        scrollToElement(byBtnNext);
        click(byBtnNext);
    }

    public void clickPageNumber(int pageNumber) {
        List<WebElement> pageNumbers = findElements(byPaginationPages);
        for (WebElement page : pageNumbers) {
            if (page.getAttribute("aria-label")
                    .equals("Page " + pageNumber)) {

                scrollToElement(page);
                click(page);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Page " + pageNumber + " is not currently available in pagination"
        );
    }

    public void scrollToCurrentPage() {
        scrollToElement(byCurrentPage);
    }

    public void clickPreviousPage() {
        scrollToElement(byBtnPrevious);
        click(byBtnPrevious);
    }

    public String getLastPageNumber() {
        List<WebElement> pageNumbers = findElements(byPaginationPages);

        WebElement lastPage = pageNumbers.get(pageNumbers.size() - 1);

        return lastPage.getAttribute("aria-label")
                .replace("Page ", "");
    }

    public void clickCourseCard (int index)
    {
        List<WebElement> courseCards = findElements(byCourseCards);
        if (courseCards.isEmpty()) {
            throw new IllegalArgumentException(
                    "No course card is available"
            );
        }

        scrollToElement(courseCards.get(index-1));
        click(courseCards.get(index-1));
    }
}
