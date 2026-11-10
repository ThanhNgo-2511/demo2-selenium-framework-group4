package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BaseCourseCard extends BasePage {

    protected WebElement cardElement;
    private By byImgCourse;
    private By byLblCourseName;
    private By byTxtShortDescription;
    private By byImgInstructorAvatar;
    private By byLblInstructorName;

    public BaseCourseCard(WebDriver driver, WebElement cardElement ) {
        super(driver);
        this.cardElement = cardElement;
    }

    public WebElement getCardElement() {
        return cardElement;
    }

    public void setCardElement(WebElement cardElement) {
        this.cardElement = cardElement;
    }

    public By getByImgCourse() {
        return byImgCourse;
    }

    public void setByImgCourse(By byImgCourse) {
        this.byImgCourse = byImgCourse;
    }

    public By getByLblCourseName() {
        return byLblCourseName;
    }

    public void setByLblCourseName(By byLblCourseName) {
        this.byLblCourseName = byLblCourseName;
    }

    public By getByTxtShortDescription() {
        return byTxtShortDescription;
    }

    public void setByTxtShortDescription(By byTxtShortDescription) {
        this.byTxtShortDescription = byTxtShortDescription;
    }

    public By getByImgInstructorAvatar() {
        return byImgInstructorAvatar;
    }

    public void setByImgInstructorAvatar(By byImgInstructorAvatar) {
        this.byImgInstructorAvatar = byImgInstructorAvatar;
    }

    public By getByLblInstructorName() {
        return byLblInstructorName;
    }

    public void setByLblInstructorName(By byLblInstructorName) {
        this.byLblInstructorName = byLblInstructorName;
    }

    public void clickOnCard() {
        click(cardElement);
    }

    public void scrollToCard() {
        scrollToElement(cardElement);
    }

    public String getCourseCardHref() {
        return getAttribute(cardElement,"href");
    }

    public boolean isDisplayed(By locator) {
        return isDisplayed(cardElement, locator);
    }

    public boolean isImageLoaded(By locator) {
        return isImageLoaded(cardElement, locator);
    }

    public String getImageSrc(By locator) {
        return getAttribute(cardElement, locator,"src");
    }

    public String getText(By locator) {
        return getText(cardElement, locator);
    }

    /**
     * Check that the image in the card are displayed
     */
    public boolean isCourseImageDisplayed()
    {
        return isDisplayed(byImgCourse);
    }

    /**
     * Check that the images in the card are not broken
     */
    public boolean isCourseImageLoaded() {
        return isImageLoaded(byImgCourse);
    }

    /**
     * Get the image's src of the course card by index
     */
    public String getCourseImageLink() {
        return getImageSrc(byImgCourse);
    }

    /**
     * Get name of course card
     */
    public String getCourseName() {
        return getText(byLblCourseName);
    }

    /**
     * Get short description of course card
     */
    public String getCourseShortDescription()
    {
        return getText(byTxtShortDescription);
    }

    /**
     * Check that the avatar in the card are display.
     */
    public boolean isAvatarImageDisplayed() {
        return isDisplayed(byImgInstructorAvatar);
    }

    /**
     * Check that the avatar in the card are not broken.
     */
    public boolean isAvatarImageLoaded() {
        return isImageLoaded(byImgInstructorAvatar);
    }

    /**
     * Get src of the avatar image
     */
    public String getAvatarImageLink() {
        return getImageSrc(byImgInstructorAvatar);
    }

    /**
     * Get Instructor Name on the card by index
     */
    public String getCourseInstructorName() {
        return getText(byLblInstructorName);
    }


}
