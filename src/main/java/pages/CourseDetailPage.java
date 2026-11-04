package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.components.FeaturedCourseCard;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailPage extends CommonPage {

    // --- Locators for Hero Section ---
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


    public CourseDetailPage(WebDriver driver) {
        super(driver);

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

        // --- Locators for Những gì bạn sẽ học Section ---
        this.byCourseLearnTitle = By.xpath("//h6[text()='Những gì bạn sẽ học']");
        this.byCourseLearnItems = By.xpath("//div[@class='boxCourseLearn']//a");

        // --- Locators for Nội dung khóa học Section ---
        this.byCurriculumTitle = By.xpath("//h6[text()='Nội dung khóa học']");
        this.byChapterItems = By.xpath("//div[@class='sectionCourse']");
        this.byPreviewButtons = By.xpath("//button[text()='Xem trước']");
        this.byLessonItems = By.xpath("//div[@class='lessonContent mt-1']");
        this.byLessonDuration = By.xpath("//i[@class='fas fa-clock']/parent::span");

        // --- Locators for Side Bar Detail Content Section ---
        this.byCourseImage = By.xpath("//div[@class='sideBarCourseDetail']/img");
        this.byCoursePrice = By.xpath("//div[@class='coursePrice']//p");
        this.byBtnEnroll = By.xpath("//button[text()='Đăng ký']");
        this.byNumberOfStudents = By.xpath("//p[text()='Ghi danh:']");
        this.byStudyDuration = By.xpath("//p[text()='Thời gian: ']");
        this.byNumberOfLessons = By.xpath("//p[text()='Bài học:']");
        this.byNumberOfVideos = By.xpath("//p[text()='Video:']");
        this.byCourseLevel = By.xpath("//p[text()='Trình độ:']");
        this.byCouponInput = By.xpath("//input[@placeholder='Nhập mã']");

        // --- Locators for Related Course Cards ---
        this.byRelatedCourseTitle = By.xpath("//h6[a[text()='Khóa học tham khảo']]");
        this.byRelatedCourseCards = By.xpath("//a[starts-with(@class,'cardGlobal')]");
    }

    // ====================== Hero Section Methods ======================
    //------------------ Course Name --------------------
    /**
     * Get Course Name
     */
    public String getCourseName() {
        waitVisibilityOfElementLocated(byCourseName);
        return getText(byCourseName);
    }

    /**
     * Check Course Name matches with expected name
     */
    public boolean isCourseNameMatches(String expectedName)
    {
        return waitForTextDisplay(byCourseName, expectedName);
    }

    //------------------ Instructor Section --------------------
    /**
     * Check the Instructor Image is displayed
     */
    public boolean isInstructorImageDisplayed() {
        waitVisibilityOfElementLocated(byInstructorImg);
        return isDisplayed(byInstructorImg);
    }

    /**
     * Check the Instructor Image is loaded
     */
    public boolean isInstructorImageLoaded() {
        waitVisibilityOfElementLocated(byInstructorImg);
        return isImageLoaded(byInstructorImg);
    }

    /**
     * Get src of the avatar image
     */
    public String getAvatarImageLink() {
        return getAttribute(byInstructorImg, "src");
    }

    /**
     * Check the Instructor Title is displayed
     */
    public boolean isInstructorTitleDisplayed() {
        waitVisibilityOfElementLocated(byInstructorTitle);
        return isDisplayed(byInstructorTitle);
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
        return isDisplayed(byCategoryIcon);
    }

    /**
     * Check the Category Title is displayed
     */
    public boolean isCategoryTitleDisplay() {
        waitVisibilityOfElementLocated(byCategoryTitle);
        return isDisplayed(byCategoryTitle);
    }

    /**
     * Get Course Category
     */
    public String getCourseCategory() {
        waitVisibilityOfElementLocated(byCourseCategory);
        return getText(byCourseCategory);
    }

    //------------------ Rating & Review Section --------------------

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
    public String getReviewCountText() {
        waitVisibilityOfElementLocated(byReviewsCount);
        return getText(byReviewsCount);
    }

    //------------------ Description Section --------------------

    /**
     * Get Course Description
     */
    public String getCourseDescription() {
        waitVisibilityOfElementLocated(byCourseDescription);
        return getText(byCourseDescription);
    }

    // ====================== Những gì bạn sẽ học Section ======================

    /**
     * Scroll to What You Will Learn Section
     */
    public void scrollToWhatYouWillLearnSection() {
        scrollToElement(byCourseLearnTitle);
    }

    /**
     * Check the What You Will Learn Title is displayed
     */
    public boolean isLearnTitleDisplayed() {
        waitVisibilityOfElementLocated(byCourseLearnTitle);
        return isDisplayed(byCourseLearnTitle);
    }

    /**
     * Get all learn items
     */
    public List<WebElement> getAllLearnItems() {
        waitVisibilityOfElementsLocated(byCourseLearnItems);
        return findElements(byCourseLearnItems);
    }

    /**
     * Return the count of learn items
     */
    public int getLearnItemCount() {
        return getAllLearnItems().size();
    }

    /**
     * Get learn items by index (1-based)
     */
    public WebElement getLearnItem(int index) {
        List<WebElement> learnItems = getAllLearnItems();
        return getItemByIndex(learnItems,index);
    }

    /**
     * Get text of learn items by index (1-based)
     */
    public String getTextLearnItem(int index) {
        WebElement learnItem = getLearnItem(index);
        return getText(learnItem);
    }

    // ====================== Nội dung khóa học Section ======================

    /**
     * Scroll to Curriculum Section
     */
    public void scrollToCurriculumSection() {
        scrollToElement(byCurriculumTitle);
    }

    /**
     * Check the Curriculum title is displayed
     */
    public boolean isCurriculumTitleDisplayed() {
        waitVisibilityOfElementLocated(byCurriculumTitle);
        return isDisplayed(byCurriculumTitle);
    }

    //------------------ Chapter --------------------

    /**
     * Get all Chapter items
     */
    public List<WebElement> getAllChapterItems() {
        waitVisibilityOfElementsLocated(byChapterItems);
        return findElements(byChapterItems);
    }

    /**
     * Return the count of chapter items
     */
    public int getChapterCount() {
        return getAllChapterItems().size();
    }

    /**
     * Get chapter items by index (1-based)
     */
    public WebElement getChapterItem(int index) {
        List<WebElement> chapterItems = getAllChapterItems();
        return getItemByIndex(chapterItems,index);
    }

    /**
     * Get text of chapter items by index (1-based)
     */
    public String getTextChapterItem(int index) {
        WebElement chapterItem = getChapterItem(index);
        return getText(chapterItem);
    }

    //------------------ Preview buttons --------------------

    /**
     * Get all Preview buttons
     */
    public List<WebElement> getAllPreviewButtons() {
        waitVisibilityOfElementsLocated(byPreviewButtons);
        return findElements(byPreviewButtons);
    }

    /**
     * Return the count of preview button
     */
    public int getPreviewButtonCount() {
        return getAllPreviewButtons().size();
    }

    /**
     * Get preview button by index (1-based)
     */
    public WebElement getPreviewButton(int index) {
        List<WebElement> previewButton = getAllPreviewButtons();
        return getItemByIndex(previewButton,index);
    }

    /**
     * Get text of preview button by index (1-based)
     */
    public void clickPreviewButton(int index) {
        WebElement previewButton = getPreviewButton(index);
        click(previewButton);
    }

    //------------------ Lesson items --------------------

    /**
     * Get all lesson items
     */
    public List<WebElement> getAllLessonItems() {
        waitVisibilityOfElementsLocated(byLessonItems);
        return findElements(byLessonItems);
    }

    /**
     * Return the count of lesson items
     */
    public int getLessonItemCount() {
        return getAllLessonItems().size();
    }

    /**
     * Get lesson items by index (1-based)
     */
    public WebElement getLessonItem(int index) {
        List<WebElement> lessonItems = getAllLessonItems();
        return getItemByIndex(lessonItems,index);
    }

    /**
     * Get text of lesson items by index (1-based)
     */
    public String getTextLessonItem(int index) {
        WebElement lessonItem = getLessonItem(index);
        return getText(lessonItem);
    }

    /**
     * Check the MM:SS time format
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
     * Check the Course Image is displayed
     */
    public boolean isCourseImageDisplayed() {
        waitVisibilityOfElementLocated(byCourseImage);
        return isDisplayed(byCourseImage);
    }

    /**
     * Check the Course Image is loaded
     */
    public boolean isCourseImageLoaded() {
        waitVisibilityOfElementLocated(byCourseImage);
        return isImageLoaded(byCourseImage);
    }

    /**
     * Get src of course image
     */
    public String getCourseImageLink() {
        waitVisibilityOfElementLocated(byCourseImage);
        return findElement(byCourseImage).getAttribute("src");
    }

    //-------------- Price -------------
    /**
     * Get text of course price
     */
    public String getPriceText() {
        waitVisibilityOfElementLocated(byCoursePrice);
        return getText(byCoursePrice);
    }

    //-------------- Enroll Button -------------

    /**
     * Check the Enroll button is displayed
     */
    public boolean isEnrollButtonDisplayed() {
        return isDisplayed(byBtnEnroll);
    }

    /**
     * Check the Enroll button is enable
     */
    public boolean isEnrollButtonEnable() {
        return isEnabled(byBtnEnroll);
    }

    /**
     * Click on Enroll button
     */
    public void clickEnrollButton() {
        click(byBtnEnroll);
    }

    //-------------- Stats Panel -------------
    /**
     * Get Stat Students
     */
    public String getStatStudents() {
        scrollToElement(byNumberOfStudents);
        return getText(byNumberOfStudents);
    }

    /**
     * Get Stat Duration
     */
    public String getStatDuration() {
        scrollToElement(byStudyDuration);
        return getText(byStudyDuration);
    }

    /**
     * Get Stat Lessons
     */
    public String getStatLessons() {
        scrollToElement(byNumberOfLessons);
        return getText(byNumberOfLessons);
    }

    /**
     * Get Stat Videos
     */
    public String getStatVideos() {
        scrollToElement(byNumberOfVideos);
        return getText(byNumberOfVideos);
    }

    /**
     * Get Stat Level
     */
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
        return isDisplayed(byRelatedCourseTitle);
    }

    //-------------- Related Course Card -------------

    /**
     * Get all related course cards
     */
    public List<FeaturedCourseCard> getAllRelatedCCourseCards() {
        List<WebElement> rawElements = findElements(byRelatedCourseCards);
        List<FeaturedCourseCard> cards = new ArrayList<>();

        for (WebElement element : rawElements) {
            cards.add(new FeaturedCourseCard(getDriver(), element));
        }
        return cards;
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
    public FeaturedCourseCard getRelatedCourseCard(int index) {
        List<FeaturedCourseCard> courseCards = getAllRelatedCCourseCards();
        return getItemByIndex(courseCards,index);
    }
}
