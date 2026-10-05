package pages.modals;

import base.BasePage;
import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonModal extends BasePage {

    private By byLayout;
    private By byLblMessage;
    public CommonModal(WebDriver driver)
    {
        super(driver);
        this.byLayout = By.cssSelector(".swal-overlay");
        this.byLblMessage = By.xpath("//div[@class=\"swal-title\"]");
    }
    public String getMessageText()
    {
        return getText(byLblMessage);
    }

    public void waitModalDisappear( long timeOutInSec)
    {
        waitInVisibilityOfElementLocated(byLblMessage,timeOutInSec);
    }

    public void waitModalDisappear()
    {
        waitInVisibilityOfElementLocated(byLblMessage);
    }

    public void waitModalAppear(long timeOutInSec)
    {
        waitVisibilityOfElementLocated(byLblMessage,timeOutInSec);
    }

    public void waitModalAppear()
    {
        waitVisibilityOfElementLocated(byLblMessage);
    }
}
