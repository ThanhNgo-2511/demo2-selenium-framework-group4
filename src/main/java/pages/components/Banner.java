package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CommonPage;

public class Banner extends CommonPage {
    private By byLblBannerTitle;
    private By byLblBannerSubTitle;

    public Banner(WebDriver driver) {
        super(driver);
        this.byLblBannerTitle = By.xpath("//div[@class='titleCourse']/h3");
        this.byLblBannerSubTitle = By.xpath("//div[@class='titleCourse']/p");
    }

    public boolean bannerTitleDisplayed()
    {
        return isDisplay(byLblBannerTitle);
    }

    public String getBannerTitle()
    {
        return getText(byLblBannerTitle);
    }

    public boolean bannerSubTitleDisplayed()
    {
        return isDisplay(byLblBannerSubTitle);
    }

    public String getBannerSubTitle()
    {
        return getText(byLblBannerSubTitle);
    }
}
