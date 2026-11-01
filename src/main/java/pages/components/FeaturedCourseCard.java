package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeaturedCourseCard extends BaseCourseCard {

    private By byLblLikeTag;
    private By byImgCourse;
    private By byLblCourseName;
    private By byTxtShortDescription;
    private By byImgInstructorAvatar;
    private By byLblInstructorName;
    private By bySpecifications;
    private By byLblPriceSale;
    private By byLblPriceOriginal;

    private By byHoverCard;
    private By byImgInstructorAvatarOnHover;
    private By byLblInstructorNameOnHover;
    private By byLblCourseNameOnHover;
    private By byTxtShortDescriptionOnHover;
    private By bySpecificationsOnHover;
    private By byBtnViewDetailOnHover;

    public FeaturedCourseCard(WebDriver driver, WebElement cardElement) {
        super(driver, cardElement);
        this.byLblLikeTag = By.xpath(".//span[text()='Yêu thích']");
        this.byImgCourse = By.xpath("./img");
        this.byLblCourseName = By.xpath("./span[@class='stikerCard']");
        this.byTxtShortDescription = By.xpath(".//div[@class='cardBodyGlobal']/h6");
        this.byImgInstructorAvatar = By.xpath(".//div[@class='imgCardFooter']/img");
        this.byLblInstructorName = By.xpath(".//div[@class='titleMaker']/span");
        this.bySpecifications = By.xpath(".//div[@class='cardBodyGlobal']//div[@class='cardIcon']");
        this.byLblPriceSale = By.xpath(".//div[@class='cardFooter']//p[2]");
        this.byLblPriceOriginal = By.xpath(".//div[@class='cardFooter']//p[1]");

        this.byHoverCard = By.xpath("./div[@class='subCard']");
        this.byImgInstructorAvatarOnHover = By.xpath(".//div[@class='subCardHead']/img");
        this.byLblInstructorNameOnHover  = By.xpath(".//div[@class='subCardHead']/span");        this.byLblCourseNameOnHover = By.xpath("./div[@class='subCardHead']/span");
        this.byLblCourseNameOnHover = By.xpath("./h6");
        this.byTxtShortDescriptionOnHover = By.xpath("./p[@class='colorCardTitle']");
        this.bySpecificationsOnHover = By.xpath(".//div[@class='cardIcon']");
        this.byBtnViewDetailOnHover = By.xpath(".//a[text()='Xem chi tiết']");

    }


    /**
     * Check the like tag display on card
     */
    public boolean isLikeTagDisplay()
    {
        return isDisplay(byLblLikeTag);
    }

    /**
     * Check that the images in the card are displayed
     */
    public boolean isCardRelatedCourseImageDisplayed()
    {
        return isDisplay(byImgCourse);
    }

    /**
     * Check that the images in the card are not broken
     */
    public boolean isCardRelatedCourseImageLoaded() {
        return isImageLoaded(byImgCourse);
    }

    /**
     * Get the image's src of the course card by index
     */
    public String getImageCourseLink() {
        return getImageSrc(byImgCourse);
    }

    /**
     * Get name of course card by index
     */
    public String getCardRelatedCourseName() {
        return getText(byLblCourseName);
    }

    /**
     * Get short description of course card by index
     */
    public String getCardRelatedCourseShortDescription()
    {
        return getText(byTxtShortDescription);
    }

    /**
     * Check the Specifications display on card
     */
    public boolean isRelateCourseCardSpecificationsDisplay()
    {
        return isDisplay(bySpecifications);
    }

    /**
     * Check that the avatar in the related card are not broken.
     */
    public boolean isAvatarOfRelatedCourseDisplayed() {
        return isImageLoaded(byImgInstructorAvatar);
    }

    /**
     * Check that the avatar in the related card are not broken.
     */
    public boolean isAvatarOfRelatedCourseLoaded() {
        return isImageLoaded(byImgInstructorAvatar);
    }

    /**
     * Get Instructor Name on the card by index
     */
    public String getCardRelatedCourseInstructorName() {
        return getText(byLblInstructorName);
    }

    /**
     * Get Price Sale on the card by index
     */
    public String getCardRelatedCoursePriceSale() {
        return getText(byLblPriceSale);
    }

    /**
     * Get Price Original on the card by index
     */
    public String getCardRelatedCoursePriceOriginal() {
        return getText(byLblPriceOriginal);
    }


    /**
     * Hover on the Related Card
     */
    public void hoverOnCard()
    {
        hover(cardElement);
    }

    //-------------- Related Course Card Hover-------------

    /**
     * Check that the hover displayed.
     */
    public boolean isHoverCardDisplay()
    {
        WebElement hoverCard = getHoverCardElement();
        return isDisplay(hoverCard);
    }

    /**
     * Get hover card.
     */
    private WebElement getHoverCardElement() {
        //hoverOnCard();
        return findChildElement(cardElement, byHoverCard);
    }

    /**
     * Check that the avatar in the hover card are not broken.
     */
    public boolean isHoverCardCourseAvatarImageDisplayed() {
        WebElement hoverCard = getHoverCardElement();
        return isDisplay(hoverCard,byImgInstructorAvatarOnHover);
    }

    /**
     * Check that the avatar in the hover card are not broken.
     */
    public boolean isHoverCardCourseAvatarImageLoaded() {
        WebElement hoverCard = getHoverCardElement();
        return isImageLoaded(hoverCard, byImgInstructorAvatarOnHover);
    }

    /**
     * Get Instructor Name on hover course card
     */
    public String getHoverCardCourseInstructorName() {
        WebElement hoverCard = getHoverCardElement();
        return getText(hoverCard, byLblInstructorNameOnHover);
    }

    /**
     * Get Course Name on hover course card
     */
    public String getHoverCardCourseCourseName() {
        WebElement hoverCard = getHoverCardElement();
        return getText(hoverCard, byLblCourseNameOnHover);
    }

    /**
     * Get short description on hover course card
     */
    public String getCardHoverCourseShortDescription()
    {
        WebElement hoverCard = getHoverCardElement();
        return getText(hoverCard, byTxtShortDescriptionOnHover);
    }

    /**
     * Check the Specifications display on hover card
     */
    public boolean isHoverCourseCardSpecificationsDisplay()
    {
        WebElement hoverCard = getHoverCardElement();
        return isDisplay(hoverCard, bySpecificationsOnHover);
    }

    /**
     * Click on View Detail button on hover card
     */
    public void clickOnViewDetailButton() {
        WebElement hoverCard = getHoverCardElement();
        click(hoverCard, byBtnViewDetailOnHover);
    }

    /**
     * Click on View Detail button on hover card
     */
    public String getViewDetailButtonHref() {
        WebElement hoverCard = getHoverCardElement();
        return  getAttribute(hoverCard, byBtnViewDetailOnHover,"href");
    }

    /**
     * Check the View Detail button is display
     */
    public boolean isViewDetailButtonDisplayed() {
        WebElement hoverCard = getHoverCardElement();
        return isDisplay(hoverCard, byBtnViewDetailOnHover);
    }

    /**
     * Check the View Detail button is enable
     */
    public boolean isViewDetailButtonEnable() {
        WebElement hoverCard = getHoverCardElement();
        return isEnabled(hoverCard, byBtnViewDetailOnHover);
    }
}
