package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import pages.component.TopNavigation;

public class CommonPage extends BasePage {

    private TopNavigation topNavigation;

    public CommonPage(WebDriver driver) {
        super(driver);
        this.topNavigation = new TopNavigation(driver);
    }

    public TopNavigation getTopNavigation() {
        return this.topNavigation;
    }
}
