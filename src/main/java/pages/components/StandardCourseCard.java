package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandardCourseCard extends BaseCourseCard {
    private By byImgCourse;
    private By byLblCourseName;
    private By byTxtShortDescription;
    private By byImgInstructorAvatar;
    private By byLblInstructorName;
    private By byLblPriceSale;
    private By byLblPriceOriginal;
    private By byLblRating;
    private By byLblReviewNumber;

    public StandardCourseCard(WebDriver driver, WebElement cardElement) {
        super(driver, cardElement);
        this.byImgCourse = By.xpath(".//img");
        this.byLblCourseName = By.xpath(".//span[@class='stikerCard']");
        this.byTxtShortDescription = By.xpath(".//div[@class='cardBodyGlobal']/h6");
        this.byImgInstructorAvatar = By.xpath(".//div[@class='imgCardFooter']/img");
        this.byLblInstructorName = By.xpath(".//div[@class='titleMaker']/span");
        this.byLblPriceSale = By.xpath(".//div[@class='cardFooter']//p[2]");
        this.byLblPriceOriginal = By.xpath(".//div[@class='cardFooter']//p[1]");
        this.byLblRating = By.xpath(".//span[@class='textStar']");
        this.byLblReviewNumber = By.xpath(".//span[@class='colorCardTitle']");
    }

    /**
     * Check that the images in the card are displayed
     */
    public boolean isCardCourseImageDisplayed() {
        return isDisplayed(byImgCourse);
    }

    /**
     * Check that the images in the card are not broken
     */
    public boolean isCardCourseImageLoaded() {
        return isImageLoaded(byImgCourse);
    }

    /**
     * Get the image's src of the course card by index
     */
    public String getImageCourseLink() {
        return getImageSrc(byImgCourse);
    }

    /**
     * Get name of course card
     */
    public String getCardCourseName() {
        return getText(byLblCourseName);
    }

    /**
     * Get short description of course card
     */
    public String getCardCourseShortDescription()
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
     * Get Instructor Name on the card by index
     */
    public String getCardCourseInstructorName() {
        return getText(byLblInstructorName);
    }

    /**
     * Get Price Sale on the card by index
     */
    public String getCardCoursePriceSale() {
        return getText(byLblPriceSale);
    }

    /**
     * Get Price Original on the card by index
     */
    public String getCardCoursePriceOriginal() {
        return getText(byLblPriceOriginal);
    }

    /**
     * Get Rating Value on the card by index
     */
    public String getCardRating() {
        return getText(byLblRating);
    }

    /**
     * Get the Review Number on the card by index
     */
    public String getCardReviewNumber() {
        return getText(byLblReviewNumber);
    }
}
