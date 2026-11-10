package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisteredCourseCard extends BaseCourseCard {

    private By bySpecifications;
    private By byIconStars;
    private By byBtnCancelCourse;

    public RegisteredCourseCard(WebDriver driver, WebElement cardElement) {
        super(driver, cardElement);

        setByImgCourse(By.xpath(".//img[@class ='imgNet']"));
        setByLblCourseName(By.xpath(".//div[contains(@class, 'cardNetContent')]/h6"));
        setByTxtShortDescription(By.xpath(".//div[contains(@class, 'cardNetContent')]/p[@class ='colorCardTitle']"));
        this.bySpecifications = By.xpath(".//div[contains(@class, 'cardNetContent')]/div[@class ='iconNetCard']");
        this.byIconStars = By.xpath(".//div[contains(@class, 'cardNetContent')]/p[@class ='iconStarNet']");
        setByImgInstructorAvatar(By.xpath(".//img[@class='imgNetFooter']"));
        setByLblInstructorName(By.xpath(".//img[@class='imgNetFooter']/following-sibling::span"));
        this.byBtnCancelCourse = By.xpath(".//button[text()='Hủy khóa học']");
    }

    /**
     * Check the Specifications display on card
     */
    public boolean isRegisteredCourseCardSpecificationsDisplay()
    {
        return isDisplayed(bySpecifications);
    }

    /**
     * Check the Star Icon display on card
     */
    public boolean isRegisteredCourseCardStarIconDisplay()
    {
        return isDisplayed(byIconStars);
    }

    /**
     * Click on Cancel Course button
     */
    public void clickOnCancelCourseButton() {
        click(byBtnCancelCourse);
    }

    /**
     * Check the Cancel Course button is display
     */
    public boolean isCancelCourseButtonDisplayed() {
        return isDisplayed(byBtnCancelCourse);
    }

    /**
     * Check the Cancel Course button is enable
     */
    public boolean isCancelCourseButtonEnable() {
        return isEnabled(byBtnCancelCourse);
    }

}
