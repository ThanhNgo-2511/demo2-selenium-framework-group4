package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CourseDetailPage extends CommonPage {

    // --- Locators for Hero Section ---
    private By byTitlePage;
    private By byCourseName;
    private By byInstructorImg;
    private By byInstructorTitle;
    private By byInstructorName;
    private By byCategoryIcon;
    private By byCategoryTitle;
    private By byCourseCategory;
    private By byRatingScore;
    private By byReviewsCount;
    private By byCourseDescription;

    // --- Locators for Những gì bạn sẽ học Section ---
    private By byCourseLearnTitle;
    private By byCourseLearnItems;

    // --- Locators for Nội dung khóa học Section ---
    private By byCurriculumTitle;
    private By byChapterItems;
    private By byPreviewButtons;
    private By byLessonItems;
    private By byLessonDuration;

    // --- Locators for Side Bar Detail Content Section ---
    private By byCourseImage;
    private By byCoursePrice;
    private By byNumberOfStudents;
    private By byStudyDuration;
    private By byNumberOfLessons;
    private By byNumberOfVideos;
    private By byCourseLevel;
    private By byBtnEnroll;
    private By byCouponInput;

    // --- Locators for Related Course Cards ---
    private By byRelatedCourseTitle;
    private By byRelatedCourseCards;
    private By byLikeTag;
    private By byRelatedCourseCardImage;
    private By byRelatedCourseCardName;
    private By byRelatedCourseCardShortDescription;
    private By byRelatedCourseCardInstructorAvatar;
    private By byRelatedCourseCardInstructorName;
    private By byRelatedCourseCardSpecifications;
    private By byRelatedCourseCardPriceSale;
    private By byRelatedCourseCardPriceOriginal;

    private By byHoverCourseCard;
    private By byHoverCourseCardInstructorAvatar;
    private By byHoverCourseCardInstructorName;
    private By byHoverCourseCardName;
    private By byHoverCourseCardShortDescription;
    private By byHoverCourseCardSpecifications;
    private By byBtnViewDetail;

    // --- Locators for Related Course Hover Cards ---

    public CourseDetailPage(WebDriver driver) {
        super(driver);
        this.byTitlePage = By.xpath("//div[@class='titleCourse']/h3");

        // --- Locators for Hero Section ---
        this.byCourseName = By.xpath("//h4[@class='titleDetailCourse']");
        this.byInstructorImg = By.xpath("//div[@class='detailCourseIntro']//img");
        this.byInstructorTitle = By.xpath("//p[text()='Giảng viên']");
        this.byInstructorName = By.xpath("//p[text()='Giảng viên']/following-sibling::p");
        this.byCategoryIcon = By.xpath("//i[@class='fas fa-graduation-cap']");
        this.byCategoryTitle = By.xpath("//p[text()='Lĩnh vực']");
        this.byCourseCategory = By.xpath("//p[text()='Lĩnh vực']/following-sibling::p");
        this.byRatingScore = By.xpath("//div[@class='reviewDetail']/span");
        this.byReviewsCount = By.xpath("//div[@class='reviewDetail']/p");
        this.byCourseDescription = By.xpath("//p[@class='textDiscripts']");

        this.byCourseLearnTitle = By.xpath("//h6[text()='Những gì bạn sẽ học']");
        this.byCourseLearnItems = By.xpath("//div[@class='boxCourseLearn']//a");

        this.byCurriculumTitle = By.xpath("//h6[text()='Nội dung khóa học']");
        this.byChapterItems = By.xpath("//div[@class='sectionCourse']");
        this.byPreviewButtons = By.xpath("//button[text()='Xem trước']");
        this.byLessonItems = By.xpath("//div[@class='lessonContent mt-1']");
        this.byLessonDuration = By.xpath("//i[@class='fas fa-clock']/parent::span");


        this.byCourseImage = By.xpath("//div[@class='sideBarCourseDetail']/img");
        this.byCoursePrice = By.xpath("//div[@class='coursePrice']//p");
        this.byBtnEnroll = By.xpath("//button[text()='Đăng ký']");

        this.byNumberOfStudents = By.xpath("//p[text()='Ghi danh:']");
        this.byStudyDuration = By.xpath("//p[text()='Thời gian: ']");
        this.byNumberOfLessons = By.xpath("//p[text()='Bài học:']");
        this.byNumberOfVideos = By.xpath("//p[text()='Video:']");
        this.byCourseLevel = By.xpath("//p[text()='Trình độ:']");
        this.byCouponInput = By.xpath("//input[@placeholder='Nhập mã']");

        this.byRelatedCourseTitle = By.xpath("//h6[a[text()='Khóa học tham khảo']]");
        this.byRelatedCourseCards = By.xpath("//a[starts-with(@class,'cardGlobal')]");
        this.byLikeTag = By.xpath(".//span[text()='Yêu thích']");
        this.byRelatedCourseCardImage = By.xpath("./img");
        this.byRelatedCourseCardName = By.xpath("./span[@class='stikerCard']");
        this.byRelatedCourseCardShortDescription = By.xpath(".//div[@class='cardBodyGlobal']/h6");
        this.byRelatedCourseCardInstructorAvatar = By.xpath(".//div[@class='imgCardFooter']/img");
        this.byRelatedCourseCardInstructorName = By.xpath(".//div[@class='titleMaker']/span");
        this.byRelatedCourseCardSpecifications = By.xpath(".//div[@class='cardBodyGlobal']//div[@class='cardIcon']");
        this.byRelatedCourseCardPriceSale = By.xpath(".//div[@class='cardFooter']//p[2]");
        this.byRelatedCourseCardPriceOriginal = By.xpath(".//div[@class='cardFooter']//p[1]");


        this.byHoverCourseCard = By.xpath("./div[@class='subCard']");
        this.byHoverCourseCardInstructorName  = By.xpath(".//img");
        this.byHoverCourseCardName = By.xpath("./div[@class='subCardHead']/span");
        this.byHoverCourseCardShortDescription = By.xpath(".//h6");
        this.byHoverCourseCardSpecifications = By.xpath(".//div[@class='cardIcon']");
        this.byBtnViewDetail = By.xpath("//a[text()='Xem chi tiết']");


    }

    public String getTitleDetailPage() {
        waitVisibilityOfElementLocated(byTitlePage);
        return getText(byTitlePage);
    }

    // ====================== Hero Info ======================

    /**
     * Get Course Name
     */
    public String getCourseName() {
        waitVisibilityOfElementLocated(byCourseName);
        return getText(byCourseName);
    }

    //------------------ Instructor Section --------------------

    /**
     * Check the Instructor Image is loaded
     */
    public boolean isInstructorImageLoaded() {
        waitVisibilityOfElementLocated(byInstructorImg);
        return isImageLoaded(byInstructorImg);
    }

    /**
     * Check the Instructor Title is displayed
     */
    public boolean isInstructorTitleDisplay() {
        waitVisibilityOfElementLocated(byInstructorTitle);
        return isDisplay(byInstructorTitle);
    }

    /**
     * Get Instructor Name
     */
    public String getInstructorName() {
        waitVisibilityOfElementLocated(byInstructorName);
        return getText(byInstructorName);
    }

    //------------------ Category Section --------------------

    /**
     * Check the graduation cap Icon is displayed
     */
    public boolean isCategoryIconDisplay() {
        waitVisibilityOfElementLocated(byCategoryIcon);
        return isDisplay(byCategoryIcon);
    }

    /**
     * Check the Category Title is displayed
     */
    public boolean isCategoryTitleDisplay() {
        waitVisibilityOfElementLocated(byCategoryTitle);
        return isDisplay(byCategoryTitle);
    }

    /**
     * Get Course Category
     */
    public String getCourseCategory() {
        waitVisibilityOfElementLocated(byCourseCategory);
        return getText(byCourseCategory);
    }

    /**
     * Get Course Rating
     */
    public String getCourseRating() {
        waitVisibilityOfElementLocated(byRatingScore);
        return getText(byRatingScore);
    }

    /**
     * Get Review Count
     */
    public String getReviewCount() {
        waitVisibilityOfElementLocated(byReviewsCount);
        return getText(byReviewsCount);
    }

    /**
     * Get Course Description
     */
    public String getCourseDescription() {
        waitVisibilityOfElementLocated(byCourseDescription);
        return getText(byCourseDescription);
    }

    // ====================== Những gì bạn sẽ học Section ======================\

    /**
     * Scroll to What You Will Learn Section
     */
    public void scrollToWhatYouWillLearnSection() {
        scrollToElement(byCourseLearnTitle);
    }

    /**
     * Get What You Will Learn Title
     */
    public boolean isLearnTitleDisplayed() {
        waitVisibilityOfElementLocated(byCourseLearnTitle);
        return isDisplay(byCourseLearnTitle);
    }

    /**
     * Get all learn items
     */
    public List<WebElement> getLearnItems() {
        waitVisibilityOfElementsLocated(byCourseLearnItems);
        return findElements(byCourseLearnItems);
    }

    /**
     * Return the count of learn items
     */
    public int getLearnItemCount() {
        return getLearnItems().size();
    }

    /**
     * Get learn items by index (1-based)
     */
    public WebElement getLearnItem(int index) {
        List<WebElement> learnItems = getLearnItems();
        if (index < 1 || index > learnItems.size()) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Index: %d, Size: %d. The learning item you are trying to access does not exist on the current page."
                            , index, learnItems.size())
            );
        }
        return learnItems.get(index - 1);
    }

    /**
     * Get text of learn items by index (1-based)
     */
    public String getTextLearnItem(int index) {
        WebElement learnItem = getLearnItem(index);
        String itemText = getText(learnItem).trim();
        return itemText != null ? itemText : "";
    }

    // ====================== Nội dung khóa học Section ======================

    /**
     * Scroll to Curriculum Section
     */
    public void scrollToCurriculumSection() {
        scrollToElement(byCurriculumTitle);
    }

    /**
     * Get Curriculum title
     */
    public boolean isCurriculumTitleDisplayed() {
        waitVisibilityOfElementLocated(byCurriculumTitle);
        return isDisplay(byCurriculumTitle);
    }

    //------------------ Chapter --------------------

    /**
     * Get all Chapter items
     */
    public List<WebElement> getChapterItems() {
        waitVisibilityOfElementsLocated(byChapterItems);
        return findElements(byChapterItems);
    }

    /**
     * Return the count of chapter items
     */
    public int getChapterCount() {
        return getChapterItems().size();
    }

    /**
     * Get chapter items by index (1-based)
     */
    public WebElement getChapterItem(int index) {
        List<WebElement> learnItems = getLearnItems();
        if (index < 1 || index > learnItems.size()) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Index: %d, Size: %d. The Chapter you are trying to access does not exist on the current page."
                            , index, learnItems.size())
            );
        }
        return learnItems.get(index - 1);
    }

    /**
     * Get text of chapter items by index (1-based)
     */
    public String getTextChapterItem(int index) {
        WebElement learnItem = getLearnItem(index);
        String itemText = getText(learnItem).trim();
        return itemText != null ? itemText : "";
    }

    //------------------ Preview buttons --------------------

    /**
     * Get all Preview buttons
     */
    public List<WebElement> getPreviewButtons() {
        waitVisibilityOfElementsLocated(byPreviewButtons);
        return findElements(byPreviewButtons);
    }

    /**
     * Return the count of preview button
     */
    public int getPreviewButtonCount() {
        return getPreviewButtons().size();
    }

    /**
     * Get preview button by index (1-based)
     */
    public WebElement getPreviewButton(int index) {
        List<WebElement> previewButton = getLearnItems();
        if (index < 1 || index > previewButton.size()) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Index: %d, Size: %d. The preview button you are trying to access does not exist on the current page."
                            , index, previewButton.size())
            );
        }
        return previewButton.get(index - 1);
    }

    /**
     * Get text of preview button by index (1-based)
     */
    public void clickPreviewButton(int index) {
        WebElement previewButton = getPreviewButton(index);
        click(previewButton);
    }

    public boolean isPreviewButtonAvailable() {
        return !getPreviewButtons().isEmpty();
    }

    //------------------ Lesson items --------------------

    /**
     * Get all lesson items
     */
    public List<WebElement> getLessonItems() {
        waitVisibilityOfElementsLocated(byCourseLearnItems);
        return findElements(byCourseLearnItems);
    }

    /**
     * Return the count of lesson items
     */
    public int getLessonItemCount() {
        return getLearnItems().size();
    }

    /**
     * Get lesson items by index (1-based)
     */
    public WebElement getLessonItem(int index) {
        List<WebElement> learnItems = getLearnItems();
        if (index < 1 || index > learnItems.size()) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Index: %d, Size: %d. The lesson you are trying to access does not exist on the current page."
                            , index, learnItems.size())
            );
        }
        return learnItems.get(index - 1);
    }

    /**
     * Get text of lesson items by index (1-based)
     */
    public String getTextLessonItem(int index) {
        WebElement learnItem = getLearnItem(index);
        String itemText = getText(learnItem).trim();
        return itemText != null ? itemText : "";
    }


    /**
     * Kiểm tra định dạng thời gian MM:SS
     */
    public boolean areLessonDurationsValid() {
        List<WebElement> durations = findElements(byLessonDuration);
        for (WebElement dur : durations) {
            String text = dur.getText().trim();
            if (!text.isEmpty() && !text.matches("\\d+:\\d{2}")) {
                return false;
            }
        }
        return true;
    }

    // ====================== Side Bar Detail Content Section ======================
    //-------------- Course Image -------------

    /**
     * Check the Course Image is loaded
     */
    public boolean isCourseImageDisplayed() {
        waitVisibilityOfElementLocated(byCourseImage);
        return isDisplay(byCourseImage);
    }

    /**
     * Check the Course Image is loaded
     */
    public boolean isCourseImageLoaded() {
        waitVisibilityOfElementLocated(byCourseImage);
        return isImageLoaded(byCourseImage);
    }

    /**
     * Get url of course image
     */
    public String getCourseImageURL() {
        waitVisibilityOfElementLocated(byCourseImage);
        return findElement(byCourseImage).getAttribute("src");
    }

    //-------------- Price -------------
    public String getPriceText() {
        waitVisibilityOfElementLocated(byCoursePrice);
        return getText(byCoursePrice);
    }

    //-------------- Enroll Button -------------
    /**
     * Click on Enroll button
     */
    public void clickEnrollButton() {
        click(byBtnEnroll);
    }

    /**
     * Check the Enroll button is display
     */
    public boolean isEnrollButtonDisplayed() {
        return isDisplay(byBtnEnroll);
    }

    /**
     * Check the Enroll button is enable
     */
    public boolean isEnrollButtonEnable() {
        return isEnable(byBtnEnroll);
    }

    //-------------- Stats Panel -------------
    public String getStatStudents() {
        scrollToElement(byNumberOfStudents);
        return getText(byNumberOfStudents);
    }

    public String getStatDuration() {
        scrollToElement(byStudyDuration);
        return getText(byStudyDuration);
    }

    public String getStatLessons() {
        scrollToElement(byNumberOfLessons);
        return getText(byNumberOfLessons);
    }

    public String getStatVideos() {
        scrollToElement(byNumberOfVideos);
        return getText(byNumberOfVideos);
    }

    public String getStatLevel() {
        scrollToElement(byCourseLevel);
        return getText(byCourseLevel);
    }

    // ====================== Related Course Section ======================

    //-------------- Title -------------
    public void scrollToRelatedCourseSection() {
        scrollToElement(byRelatedCourseTitle);
    }

    public boolean isRelatedCourseTitleDisplayed() {
        return isDisplay(byRelatedCourseTitle);
    }

    //-------------- Related Course Card -------------

    /**
     * Get all related course cards
     */
    public List<WebElement> getAllRelatedCCourseCards() {
        return findElements(byRelatedCourseCards);
    }

    /**
     * Return the count of related course cards
     */
    public int getRelatedCourseCardCount() {
        return getAllRelatedCCourseCards().size();
    }

    /**
     * Get related course card by index (1-based)
     */
    public WebElement getRelatedCourseCard(int index) {
        List<WebElement> courseCards = getAllRelatedCCourseCards();
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
    public String getRelatedCourseCardHref(int index) {
        WebElement card = getRelatedCourseCard(index);
        String href = card.getAttribute("href");
        return href != null ? href : "";
    }

    /**
     * Check the like tag display on card
     */
    public boolean isLikeTagDisplay(int index)
    {
        WebElement card = getRelatedCourseCard(index);
        WebElement likeTagEle = findChildElement(card, byRelatedCourseCardName);
        return isDisplay(likeTagEle);
    }

    /**
     * Check that the images in the card are displayed
     */
    public boolean isCardRelatedCourseImageDisplayed(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement imageCourseElement = findChildElement(card, byRelatedCourseCardImage);
        return isDisplay(imageCourseElement);
    }

    /**
     * Check that the images in the card are not broken
     */
    public boolean isCardRelatedCourseImageLoaded(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement imageCourseElement = findChildElement(card, byRelatedCourseCardImage);
        return isImageLoaded(imageCourseElement);
    }

    /**
     * Get name of course card by index
     */
    public String getCardRelatedCourseName(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement nameElement = findChildElement(card, byRelatedCourseCardName);
        String name = getText(nameElement).trim();
        return name != null ? name : "";
    }

    /**
     * Get short description of course card by index
     */
    public String getCardRelatedCourseShortDescription(int index)
    {
        WebElement card = getRelatedCourseCard(index);
        WebElement shortDescElement = findChildElement(card, byRelatedCourseCardShortDescription);
        String shortDesc = getText(shortDescElement).trim();
        return shortDesc != null ? shortDesc : "";
    }

    /**
     * Check the Specifications display on card
     */
    public boolean isRelateCourseCardSpecificationsDisplay(int index)
    {
        WebElement card = getRelatedCourseCard(index);
        WebElement specificationsEle = findChildElement(card, byRelatedCourseCardSpecifications);
        return isDisplay(specificationsEle);
    }

    /**
     * Check that the avatar in the related card are not broken.
     */
    public boolean isAvatarOfRelatedCourseDisplayed(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement avatarElement = findChildElement(card,byRelatedCourseCardInstructorAvatar);
        return isImageLoaded(avatarElement);
    }

    /**
     * Check that the avatar in the related card are not broken.
     */
    public boolean isAvatarOfRelatedCourseLoaded(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement avatarElement = findChildElement(card,byRelatedCourseCardInstructorAvatar);
        return isImageLoaded(avatarElement);
    }

    /**
     * Get Instructor Name on the card by index
     */
    public String getCardRelatedCourseInstructorName(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement instructorNameElement = findChildElement(card, byRelatedCourseCardInstructorName);
        String name = getText(instructorNameElement).trim();
        return name != null ? name : "";
    }

    /**
     * Get Price Sale on the card by index
     */
    public String getCardRelatedCoursePriceSale(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement priceSaleElement = findChildElement(card, byRelatedCourseCardPriceSale);
        String priceSale = getText(priceSaleElement).trim();
        return priceSale != null ? priceSale : "";
    }

    /**
     * Get Price Original on the card by index
     */
    public String getCardRelatedCoursePriceOriginal(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement priceOrigElement = findChildElement(card, byRelatedCourseCardPriceOriginal);
        String priceOrig = getText(priceOrigElement).trim();
        return priceOrig != null ? priceOrig : "";
    }

    /**
     * Get the image's src of the course card by index
     */
    public String getImageCourseLink(int index) {
        WebElement card = getRelatedCourseCard(index);
        WebElement image = findChildElement(card, byRelatedCourseCardImage);
        String src = image.getAttribute("src");
        return src != null ? src : "";
    }

    /**
     * Click on the course card by index
     */
    public void clickRelatedCourseCard(int index)
    {
        WebElement card = getRelatedCourseCard(index);
        scrollToElement(card);
        click(card);
    }

    /**
     * Hover on the Related Card
     */
    public void hoverOnRelatedCourseCard(int index)
    {
        WebElement card = getRelatedCourseCard(index);
        hover(card);
    }

    //-------------- Related Course Card Hover-------------
    public boolean ishoverCardDisplay(int index)
    {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        return isDisplay(hoverCard);
    }

    /**
     * Get hover card of related course card
     */
    public WebElement getCourseHoverCard(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        return findChildElement(hoverCard,byHoverCourseCard);
    }

    /**
     * Check that the avatar in the hover card are not broken.
     */
    public boolean isHoverCardCourseAvatarImageDisplayed(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement avatarElement = findChildElement(hoverCard,byHoverCourseCardInstructorAvatar);
        return isDisplay(avatarElement);
    }

    /**
     * Check that the avatar in the hover card are not broken.
     */
    public boolean isHoverCardCourseAvatarImageLoaded(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement avatarElement = findChildElement(hoverCard,byHoverCourseCardInstructorAvatar);
        return isImageLoaded(avatarElement);
    }

    /**
     * Get Instructor Name on hover course card
     */
    public String getHoverCardCourseInstructorName(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement instructorNameElement = findChildElement(hoverCard, byHoverCourseCardInstructorName);
        String name = getText(instructorNameElement).trim();
        return name != null ? name : "";
    }

    /**
     * Get Course Name on hover course card
     */
    public String getHoverCardCourseCourseName(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement courseNameElement = findChildElement(hoverCard, byHoverCourseCardName);
        String name = getText(courseNameElement).trim();
        return name != null ? name : "";
    }

    /**
     * Get short description on hover course card
     */
    public String getCardHoverCourseShortDescription(int index)
    {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement shortDescElement = findChildElement(hoverCard, byHoverCourseCardShortDescription);
        String shortDesc = getText(shortDescElement).trim();
        return shortDesc != null ? shortDesc : "";
    }

    /**
     * Check the Specifications display on hover card
     */
    public boolean isHoverCourseCardSpecificationsDisplay(int index)
    {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement specificationsEle = findChildElement(hoverCard, byHoverCourseCardShortDescription);
        return isDisplay(specificationsEle);
    }


    /**
     * Click on View Detail button on hover card
     */
    public void clickOnViewDetailButton(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement viewDetailButton = findChildElement(hoverCard, byBtnViewDetail);
        click(viewDetailButton);
    }

    /**
     * Click on View Detail button on hover card
     */
    public String getViewDetailButtonHref(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement viewDetailButton = findChildElement(hoverCard, byBtnViewDetail);
        String href = viewDetailButton.getAttribute("href");
        return href != null ? href : "";
    }

    /**
     * Check the View Detail button is display
     */
    public boolean isViewDetailButtonDisplayed(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement viewDetailButton = findChildElement(hoverCard, byBtnViewDetail);
        return isDisplay(viewDetailButton);
    }

    /**
     * Check the View Detail button is enable
     */
    public boolean isViewDetailButtonEnable(int index) {
        WebElement relatedCourse = getRelatedCourseCard(index);
        WebElement hoverCard = findChildElement(relatedCourse,byHoverCourseCard);
        WebElement viewDetailButton = findChildElement(hoverCard, byBtnViewDetail);
        return isEnable(viewDetailButton);
    }
}
