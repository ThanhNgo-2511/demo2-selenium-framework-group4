package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BaseCourseCard extends BasePage {

    protected WebElement cardElement;

    public BaseCourseCard(WebDriver driver, WebElement cardElement ) {
        super(driver);
        this.cardElement = cardElement;
    }

    public void clickOnCard() {
        click(cardElement);
    }

    public void scrollToCard() {
        scrollToElement(cardElement);
    }

    /**
     * Get href of course card by index
     */
    public String getCourseCardHref() {
        return getAttribute(cardElement,"href");
    }

    public boolean isDisplayed(By locator) {
        return isDisplay(cardElement, locator);
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


}
