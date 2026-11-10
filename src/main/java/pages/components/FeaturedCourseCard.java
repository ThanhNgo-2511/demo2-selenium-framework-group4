package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeaturedCourseCard extends BaseCourseCard {

    private By byLblLikeTag;
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
        setByImgCourse(By.xpath("./img"));
        setByLblCourseName(By.xpath("./span[@class='stikerCard']"));
        setByTxtShortDescription(By.xpath(".//div[@class='cardBodyGlobal']/h6"));
        setByImgInstructorAvatar(By.xpath(".//div[@class='imgCardFooter']/img"));
        setByLblInstructorName(By.xpath(".//div[@class='titleMaker']/span"));
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
        return isDisplayed(byLblLikeTag);
    }

    /**
     * Check the Specifications display on card
     */
    public boolean isRelatedCourseCardSpecificationsDisplay()
    {
        return isDisplayed(bySpecifications);
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
        return isDisplayed(hoverCard);
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
        return isDisplayed(hoverCard,byImgInstructorAvatarOnHover);
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
        return isDisplayed(hoverCard, bySpecificationsOnHover);
    }

    /**
     * Click on View Detail button on hover card
     */
    public void clickOnViewDetailButton() {
        WebElement hoverCard = getHoverCardElement();
        click(hoverCard, byBtnViewDetailOnHover);
    }

    /**
     * Get Href of the View Detail button on hover card
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
        return isDisplayed(hoverCard, byBtnViewDetailOnHover);
    }

    /**
     * Check the View Detail button is enable
     */
    public boolean isViewDetailButtonEnable() {
        WebElement hoverCard = getHoverCardElement();
        return isEnabled(hoverCard, byBtnViewDetailOnHover);
    }
}
