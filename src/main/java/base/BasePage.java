package base;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver)
    {
        this.driver= driver;
    }

    //
    public WebDriverWait getWebDriverWait()
    {
        return getWebDriverWait(TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public WebDriverWait getWebDriverWait(long timeOutInSec)
    {
        return new WebDriverWait(this.driver, Duration.ofSeconds(timeOutInSec));
    }

    // ---- Wait Helpers --- //
    // Wait for element to meet condition - presence, visibility, clickability, etc.

    public WebElement waitVisibilityOfElementLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitVisibilityOfElementLocated(By locator)
    {
        return waitVisibilityOfElementLocated(locator,TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public WebElement waitVisibilityOf(WebElement element, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitVisibilityOf(WebElement element)
    {
        return waitVisibilityOf(element,TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public List<WebElement> waitVisibilityOfElementsLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitVisibilityOfElementsLocated(By locator)
    {
        return waitVisibilityOfElementsLocated(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    //=========================
    public WebElement waitElementToBeClickable(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitElementToBeClickable(By locator)
    {
        return waitElementToBeClickable(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public WebElement waitElementToBeClickable(WebElement element, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementToBeClickable(WebElement element)
    {
        return waitElementToBeClickable(element, TimeoutConstant.DEFAULT_TIMEOUT);
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

    //=========================
    /**
     * Chờ và trả về phần tử con hiển thị bên trong phần tử cha
     */
    public WebElement waitForChildElement(WebElement parent, By childLocator, long timeOutInSec) {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(driver -> {
            WebElement element = parent.findElement(childLocator);
            return (element.isDisplayed()) ? element : null;
        });
    }

    /**
     * Overload với timeout mặc định
     */
    public WebElement waitForChildElement(WebElement parent, By childLocator) {
        return waitForChildElement(parent, childLocator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    // ---- Common Actions ---- //
    //Send Keys
    public void sendKeys (By locator, String value,long timeOut)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        element.sendKeys(value);
    }

    public void sendKeys (By locator, String value)
    {
       sendKeys(locator,value, TimeoutConstant.DEFAULT_TIMEOUT);
    }


    //Click element
    public void click (By locator, long timeOut)
    {
        WebElement element = waitElementToBeClickable(locator,timeOut);
        element.click();
    }

    public void click (By locator)
    {
        click(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void click (WebElement element, long timeOut)
    {
        waitElementToBeClickable(element);
        element.click();
    }

    public void click (WebElement element)
    {
        click(element, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    //Hover element
    public void hover (By locator, long timeOut)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void hover (By locator)
    {
        hover(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void hover (WebElement element, long timeOut)
    {
        waitVisibilityOf(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void hover (WebElement element) {
        hover(element, TimeoutConstant.DEFAULT_TIMEOUT);
    }
    //=========================
    // Get text of element
    public String getText (By locator, long timeOut)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        return element.getText();
    }

    public String getText (By locator)
    {
        return getText(locator, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public String getText (WebElement element, long timeOut)
    {
        waitVisibilityOf(element,timeOut);
        return element.getText();
    }

    public String getText (WebElement element) {
        return getText(element, TimeoutConstant.DEFAULT_TIMEOUT);
    }

    //=========================
    // Check element is display
    public boolean isDisplay (By locator, long timeOut)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        return element.isDisplayed();
    }

    public boolean isDisplay (By locator)
    {
        return isDisplay(locator,TimeoutConstant.DEFAULT_TIMEOUT );
    }

    public boolean isDisplay (WebElement element, long timeOut)
    {
        waitVisibilityOf(element,timeOut);
        return element.isDisplayed();
    }

    public boolean isDisplay (WebElement element)
    {
        return isDisplay(element,TimeoutConstant.DEFAULT_TIMEOUT );
    }

    //=========================
    // Check element is enable

    public boolean isEnable (By locator, long timeOut)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        return element.isEnabled();
    }

    public boolean isEnable (By locator)
    {
        return isEnable(locator,TimeoutConstant.DEFAULT_TIMEOUT );
    }

    public boolean isEnable (WebElement element, long timeOut) {
        waitVisibilityOf(element, timeOut);
        return element.isEnabled();
    }

    public boolean isEnable (WebElement element)
    {
        return isEnable(element,TimeoutConstant.DEFAULT_TIMEOUT );
    }

    //=========================
    public List<WebElement> findElements(By locator) {

        waitVisibilityOfElementsLocated(locator);
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator) {

        waitVisibilityOfElementsLocated(locator);
        return driver.findElement(locator);
    }

    public WebElement findChildElement(WebElement parent, By childLocator)
    {
        return waitForChildElement(parent, childLocator);
    }

    //=========================
    public void highlight(By locator,long timeOut) {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='2px solid red';" +
                        "arguments[0].style.backgroundColor='yellow';",
                element
        );
    }

    public void highlight(By locator) {
        highlight(locator,TimeoutConstant.SHORT_TIMEOUT);
    }

    //=========================
    public void unhighlight(By locator,long timeOut) {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='';" +
                        "arguments[0].style.backgroundColor='';",
                element
        );
    }

    public void unhighlight(By locator) {
        unhighlight(locator,TimeoutConstant.SHORT_TIMEOUT);
    }

    //=========================
    public void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='2px solid red';" +
                        "arguments[0].style.backgroundColor='yellow';",
                element
        );
    }

    public void unhighlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='';" +
                        "arguments[0].style.backgroundColor='';",
                element
        );
    }

    //=========================
    public void scrollToElement(By locator,long timeOut) {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOut);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
    }

    public void scrollToElement(By locator)
    {
        scrollToElement(locator,TimeoutConstant.SHORT_TIMEOUT);
    }

    //=========================
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
    }

    //===========================
    /**
     * Kiểm tra ảnh trong card không bị broken
     */
    public boolean isImageLoaded(WebElement image) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long naturalWidth = (Long) js.executeScript(
                "return arguments[0].naturalWidth;",
                image
        );
        if (naturalWidth == null || naturalWidth <= 0) {
            return false;
        }
        return true;
    }

    public boolean isImageLoaded(By locator,long timeOut) {
        WebElement image = waitVisibilityOfElementLocated(locator,timeOut);
        return isImageLoaded(image);
    }

    public boolean isImageLoaded(By locator) {
       return isImageLoaded(locator,TimeoutConstant.SHORT_TIMEOUT);
    }



    //===========================
    /**
     * Get URL of the page
     */
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public String getPageTitle()
    {
        return driver.getTitle();
    }



}
