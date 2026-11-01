package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.StandardCourseCard;

import java.util.*;

public class CourseListPage extends CommonPage {

    // --- Locators for Insight Information section  ---
    private By byTbInsightInformation;

    // --- Locators for Course List & Cards ---
    private By byLblTitleSection;
    private By byIcoLoading;
    private By byCourseCards;

    // --- Locators for Pagination ---
    private By byBtnPrevious;
    private By byBtnNext;
    private By byPaginationPages;
    private By byCurrentPage;


    public CourseListPage(WebDriver driver) {
        super(driver);
        this.byTbInsightInformation = By.xpath("//div[contains(@class,'coursesBoxItem')]");

        this.byLblTitleSection = By.xpath("//h6[text()='Danh sách khóa học']");
        this.byIcoLoading = By.xpath("//div[@id='loader']");
        this.byCourseCards = By.xpath("//a[@class='cardGlobal']");
        this.byBtnPrevious = By.xpath("//a[@aria-label='Previous page']");
        this.byBtnNext = By.xpath("//a[@aria-label='Next page']");
        this.byPaginationPages = By.xpath("//a[starts-with(@aria-label, 'Page')]");
        this.byCurrentPage = By.xpath("//ul[@class='paginationPages']//a[@aria-current='page']");

    }

    // ====================== Insight Information section Methods ======================
    public boolean inSightInformationBoxDisplayed()
    {
        return isDisplay(byTbInsightInformation);
    }

    public Map<String, String> getInsightInformation()
    {
        List<WebElement> rawElements = findElements(byTbInsightInformation);
        Map<String, String> inforTable = new LinkedHashMap<>();

        for (WebElement element:rawElements) {
            String rawText = getText(element);
            String[] lines = rawText.split("\\r?\\n");

            if (lines.length >= 2) {
                String key = lines[0].trim();
                String value = lines[1].trim();
                inforTable.put(key, value);
            }
        }
        return inforTable;
    }

    // ====================== Course Card Methods ======================
    public boolean isCourseListTitleDisplayed() {
        return isDisplay(byLblTitleSection);
    }

    public void waitCourseListLoadSuccess()
    {
        waitInVisibilityOfElementLocated(byIcoLoading);
    }

    /**
     * Get all course cards
     */
    public List<StandardCourseCard> getAllCourseCards() {
        waitCourseListLoadSuccess();
        List<WebElement> rawElements = findElements(byCourseCards);
        List<StandardCourseCard> cards = new ArrayList<>();

        for (WebElement element : rawElements) {
            cards.add(new StandardCourseCard(getDriver(), element));
        }
        return cards;
    }

    /**
     * Return the count of course cards
     */
    public int getCourseCardCount() {
        return getAllCourseCards().size();
    }

    /**
     * Get course card by index (1-based)
     */
    public StandardCourseCard getCourseCardByIndex(int index) {
        List<StandardCourseCard> courseCards = getAllCourseCards();
        if (index < 1 || index > courseCards.size()) {
            throw new IndexOutOfBoundsException(
                String.format(
                    "Index: %d, Size: %d. The card position you are trying to access does not exist on the current page."
                    , index, courseCards.size())
            );
        }
        return courseCards.get(index - 1);
    }

    // ====================== Pagination Methods ======================

    /**
     * Return the order of the current page on the pagination
     */
    public int getCurrentPage() {
        return Integer.parseInt(getText(byCurrentPage));
    }

    /**
     * Return the count of page on the pagination
     */
    public int getTotalPages() {
        List<WebElement> pageNumbers = findElements(byPaginationPages);
        if (pageNumbers.isEmpty()) return 1;

         WebElement lastPageEl = pageNumbers.get(pageNumbers.size() - 1);
        String ariaLabel = lastPageEl.getAttribute("aria-label");
        try {
            String pageNumStr = ariaLabel.replaceAll("[^0-9]", "");
            return Integer.parseInt(pageNumStr);
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * Checks whether the Next button is displayed and enabled.
     */
    public boolean hasNextPage() {
        List<WebElement> nextButtons = findElements(byBtnNext);
        if (nextButtons.isEmpty()) return false;

        WebElement nextButton = nextButtons.get(0);
        if (!nextButton.isDisplayed())  return false;

        String ariaDisabled = nextButton.getAttribute("aria-disabled");
        if ("true".equals(ariaDisabled)) return false;

        return true;
    }

    /**
     * Checks whether the Previous button is displayed and enabled.
     */
    public boolean hasPreviousPage() {
        List<WebElement> prevButtons = findElements(byBtnPrevious);
        if (prevButtons.isEmpty()) return false;

        WebElement prevButton = prevButtons.get(0);
        if (!prevButton.isDisplayed())  return false;

        String ariaDisabled = prevButton.getAttribute("aria-disabled");
        if ("true".equals(ariaDisabled)) return false;

        return true;
    }

    /**
     * Click on the Next button on the pagination.
     */
    public void clickNextPage() {
        if(hasNextPage())
        {
            waitElementToBeClickable(byBtnNext);
            scrollToElement(byBtnNext);
            click(byBtnNext);
        }
    }

    /**
     * Click on the Previous button on the pagination.
     */
    public void clickPreviousPage() {
        if (hasPreviousPage())
        {
            waitElementToBeClickable(byBtnPrevious);
            scrollToElement(byBtnPrevious);
            click(byBtnPrevious);
        }
    }

    /**
     * Click on the Specific Page on the pagination.
     */
    public void clickPageNumber(int targetPage) {
        if (targetPage < 1) {
            throw new IllegalArgumentException(
                    "Target page must be greater than 0"
            );
        }

        for (int attempt = 0; attempt < getTotalPages(); attempt++) {
            List<WebElement> pages = findElements(byPaginationPages);
            WebElement closestPage = null;
            int minDistance = Integer.MAX_VALUE;

            for (WebElement page : pages) {
                String label = page.getAttribute("aria-label");

                if (label == null || !label.startsWith("Page ")) {
                    continue;
                }

                int pageNumber = Integer.parseInt(
                        label.split(" ")[1]
                );

                // Found the target page
                if (pageNumber == targetPage) {
                    scrollToElement(page);
                    click(page);
                    return;
                }

                // Find the closest page to the target
                int distance = Math.abs(pageNumber - targetPage);

                if (distance < minDistance) {
                    minDistance = distance;
                    closestPage = page;
                }
            }

            if (closestPage == null) {
                break;
            }

            click(closestPage);
            waitCourseListLoadSuccess();
        }

        throw new IllegalStateException(
                "Cannot navigate to Page " + targetPage
        );
    }


}
