package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CourseListPage extends CommonPage {

    private By byLoadIcon;

    // --- Locators for Course List & Cards ---
    private By byCourseCards;
    private By byCourseCardImage;
    private By byCourseCardName;
    private By byCourseCardShortDescription;
    private By byCourseCardInstructorAvatar;
    private By byCourseCardInstructorName;
    private By byCourseCardPriceSale;
    private By byCourseCardPriceOriginal;
    private By byCourseCardRating;
    private By byCourseCardReviewNumber;

    // --- Locators for Pagination ---
    private By byBtnPrevious;
    private By byBtnNext;
    private By byPaginationPages;
    private By byCurrentPage;


    public CourseListPage(WebDriver driver) {
        super(driver);
        this.byLoadIcon = By.xpath("//div[@id='loader']");

        this.byCourseCards = By.xpath("//a[@class='cardGlobal']");
        this.byCourseCardImage = By.xpath(".//img");
        this.byCourseCardName = By.xpath(".//span[@class='stikerCard']");
        this.byCourseCardShortDescription = By.xpath(".//div[@class='cardBodyGlobal']/h6");
        this.byCourseCardInstructorAvatar = By.xpath(".//div[@class='imgCardFooter']/img");
        this.byCourseCardInstructorName = By.xpath(".//div[@class='titleMaker']/span");
        this.byCourseCardPriceSale = By.xpath(".//div[@class='cardFooter']//p[2]");
        this.byCourseCardPriceOriginal = By.xpath(".//div[@class='cardFooter']//p[1]");
        this.byCourseCardRating = By.xpath(".//span[@class='textStar']");
        this.byCourseCardReviewNumber = By.xpath(".//span[@class='colorCardTitle']");

        this.byBtnPrevious = By.xpath("//a[@aria-label='Previous page']");
        this.byBtnNext = By.xpath("//a[@aria-label='Next page']");
        this.byPaginationPages = By.xpath("//a[starts-with(@aria-label, 'Page')]");
        this.byCurrentPage = By.xpath("//ul[@class='paginationPages']//a[@aria-current='page']");

    }

    ////
    public void waitCourseListLoadSuccess()
    {
        waitInVisibilityOfElementLocated(byLoadIcon);
    }

    // ====================== Course Card Methods ======================

    /**
     * Get all course cards
     */
    public List<WebElement> getAllCourseCards() {
        return findElements(byCourseCards);
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
    public WebElement getCourseCard(int index) {
        List<WebElement> courseCards = getAllCourseCards();
        if (index < 1 || index > courseCards.size()) {
            throw new IndexOutOfBoundsException(
                String.format(
                    "Index: %d, Size: %d. The card position you are trying to access does not exist on the current page."
                    , index, courseCards.size())
            );
        }
        return courseCards.get(index - 1);
    }

    /**
     * Get href of course card by index
     */
    public String getCourseCardHref(int index) {
        WebElement card = getCourseCard(index);
        String href = card.getAttribute("href");
        return href != null ? href : "";
    }

    /**
     * Get name of course card by index
     */
    public String getCardCourseName(int index) {
        WebElement card = getCourseCard(index);
        WebElement nameElement = findChildElement(card, byCourseCardName);
        String name = getText(nameElement).trim();
        return name != null ? name : "";
    }

    /**
     * Get short description of course card by index
     */
    public String getCardCourseShortDescription(int index)
    {
        WebElement card = getCourseCard(index);
        WebElement shortDescElement = findChildElement(card, byCourseCardShortDescription);
        String shortDesc = getText(shortDescElement).trim();
        return shortDesc != null ? shortDesc : "";
    }

    /**
     * Check that the images in the card are not broken
     */
    public boolean isCardCourseImageDisplayed(int index) {
        WebElement card = getCourseCard(index);
        WebElement imageCourseElement = findChildElement(card, byCourseCardImage);
        return isDisplay(imageCourseElement);
    }

    /**
     * Check that the images in the card are not broken
     */
    public boolean isCardCourseImageLoaded(int index) {
        WebElement card = getCourseCard(index);
        WebElement imageCourseElement = findChildElement(card, byCourseCardImage);
        return isImageLoaded(imageCourseElement);
    }

    /**
     * Check that the avatar in the card are display.
     */
    public boolean isAvatarImageDisplayed(int index) {
        WebElement card = getCourseCard(index);
        WebElement avatarElement = findChildElement(card,byCourseCardInstructorAvatar);
        return isDisplay(avatarElement);
    }

    /**
     * Check that the avatar in the card are not broken.
     */
    public boolean isAvatarImageLoaded(int index) {
        WebElement card = getCourseCard(index);
        WebElement avatarElement = findChildElement(card,byCourseCardInstructorAvatar);
        return isImageLoaded(avatarElement);
    }

    /**
     * Get Instructor Name on the card by index
     */
    public String getCardCourseInstructorName(int index) {
        WebElement card = getCourseCard(index);
        WebElement instructorNameElement = findChildElement(card, byCourseCardInstructorName);
        String name = getText(instructorNameElement).trim();
        return name != null ? name : "";
    }

    /**
     * Get Price Sale on the card by index
     */
    public String getCardCoursePriceSale(int index) {
        WebElement card = getCourseCard(index);
        WebElement priceSaleElement = findChildElement(card, byCourseCardPriceSale);
        String priceSale = getText(priceSaleElement).trim();
        return priceSale != null ? priceSale : "";
    }

    /**
     * Get Price Original on the card by index
     */
    public String getCardCoursePriceOriginal(int index) {
        WebElement card = getCourseCard(index);
        WebElement priceOrigElement = findChildElement(card, byCourseCardPriceOriginal);
        String priceOrig = getText(priceOrigElement).trim();
        return priceOrig != null ? priceOrig : "";
    }

    /**
     * Get Rating Value on the card by index
     */
    public String getCardRating(int index) {
        WebElement card = getCourseCard(index);
        WebElement ratingElement = findChildElement(card, byCourseCardRating);
        String number = getText(ratingElement).trim();
        return number != null ? number : "";
    }

    /**
     * Get the Review Number on the card by index
     */
    public String getCardReviewNumber(int index) {
        WebElement card = getCourseCard(index);
        WebElement reviewElement = findChildElement(card, byCourseCardReviewNumber);
        String number = getText(reviewElement).trim();
        return number != null ? number : "";
    }

    /**
     * Get the image's src of the course card by index
     */
    public String getImageCourseLink(int index) {
        WebElement card = getCourseCard(index);
        WebElement image = findChildElement(card, byCourseCardImage);
        String src = image.getAttribute("src");
        return src != null ? src : "";
    }



    //-------------------------------------------
    public String getImageCourseLink(String courseId)
    {
        By byCourseCardImage = By.xpath(String.format("//a[contains(@href, '/chitiet/%s')]/img", courseId));
        waitVisibilityOfElementLocated(byCourseCardImage);
        return findElement(byCourseCardImage).getAttribute("src");
    }



    public String getNameCourseCard(String courseId)
    {
        By byNameCourseCard = By.xpath(String.format("//a[contains(@href, '/chitiet/%s')]/span[@class='stikerCard']", courseId));
        waitVisibilityOfElementLocated(byNameCourseCard);
        return getText(byNameCourseCard);
    }

    public String getCourseCardDescribe(String courseId)
    {
        By bygetCourseCardDescribe = By.xpath(String.format("//a[contains(@href, '/chitiet/%s')]/div[@class='cardBodyGlobal']/h6", courseId));
        waitVisibilityOfElementLocated (bygetCourseCardDescribe);
        return getText(bygetCourseCardDescribe);
    }

    //-------------------------------------------

    public String getCourseCardAuthor(String courseId)
    {
        By byCourseCardAuthor = By.xpath(String.format("//a[contains(@href, '/chitiet/%s')]//div[@class='titleMaker']/span", courseId));
        waitVisibilityOfElementLocated (byCourseCardAuthor);
        return getText(byCourseCardAuthor);
    }

    //-------------------------------------------

    /**
     * Click on the course card by index
     */
    public void clickCourseCard(int index)
    {
        WebElement coursecardEle = getCourseCard(index);
        scrollToElement(coursecardEle);
        click(coursecardEle);
    }

    /**
     * Click on the course card by Course ID
     */
    public void clickCourseCardByCourseId(String courseId)
    {
        By courseCard = By.xpath(String.format("//a[contains(@href, '/chitiet/%s')]", courseId));
        waitVisibilityOfElementLocated(courseCard);
        click(courseCard);
    }

    /**
     * Scroll tới course card theo index
     */
    public void scrollCourseCard(int index)
    {
       scrollToElement(getCourseCard(index));
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
