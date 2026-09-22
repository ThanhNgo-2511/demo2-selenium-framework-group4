package base;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver)
    {
        this.driver= driver;
    }

    public WebDriverWait getWebDriverWait()
    {
        return getWebDriverWait(TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public WebDriverWait getWebDriverWait(long timeOutInSec)
    {
        return new WebDriverWait(this.driver, Duration.ofSeconds(timeOutInSec));
    }

    //=========================
    public boolean waitInVisibilityOfElementLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitInVisibilityOfElementLocated(By locator)
    {
        return waitInVisibilityOfElementLocated(locator,TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public WebElement waitVisibilityOfElementLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitVisibilityOfElementLocated(By locator)
    {
        return waitVisibilityOfElementLocated(locator,TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public WebElement waitElementToBeClickable(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitElementToBeClickable(By locator)
    {
        return waitElementToBeClickable(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void sendKeys (By locator, String value,long timeOut)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        element.sendKeys(value);
    }

    public void sendKeys (By locator, String value)
    {
       sendKeys(locator,value, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void click (By locator, long timeOut)
    {
        WebElement element = waitElementToBeClickable(locator,timeOut);
        element.click();
    }

    public void click (By locator)
    {
        click(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public String getText (By locator, long timeOut)
    {
        WebElement element = waitElementToBeClickable(locator,timeOut);
        return element.getText();
    }

    public String getText (By locator)
    {
        return getText(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public boolean isDisplay (By locator, long timeOut)
    {
        WebElement element = waitElementToBeClickable(locator,timeOut);
        return element.isDisplayed();
    }

    public boolean isDisplay (By locator)
    {
        return isDisplay(locator,TimeoutConstant.DEFAULT_TIMEOUT );
    }

}
