package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandardCourseCard extends BaseCourseCard {
    private By byLblPriceSale;
    private By byLblPriceOriginal;
    private By byLblRating;
    private By byLblReviewNumber;

    public StandardCourseCard(WebDriver driver, WebElement cardElement) {
        super(driver, cardElement);
        setByImgCourse(By.xpath(".//img"));
        setByLblCourseName(By.xpath(".//span[@class='stikerCard']"));
        setByTxtShortDescription(By.xpath(".//div[@class='cardBodyGlobal']/h6"));
        setByImgInstructorAvatar(By.xpath(".//div[@class='imgCardFooter']/img"));
        setByLblInstructorName(By.xpath(".//div[@class='titleMaker']/span"));
        this.byLblPriceSale = By.xpath(".//div[@class='cardFooter']//p[2]");
        this.byLblPriceOriginal = By.xpath(".//div[@class='cardFooter']//p[1]");
        this.byLblRating = By.xpath(".//span[@class='textStar']");
        this.byLblReviewNumber = By.xpath(".//span[@class='colorCardTitle']");
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
