package pages.modals;

import base.BasePage;
import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonModal extends BasePage {

    private By byLblMessage;
    public CommonModal(WebDriver driver)
    {
        super(driver);
        this.byLblMessage = By.id("swal2-title");
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
        waitInVisibilityOfElementLocated(byLblMessage, TimeoutConstant.DEFAULT_TIMEOUT);
    }
}
