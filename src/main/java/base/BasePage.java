package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static constants.TimeoutConstant.*;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver)
    {
        this.driver= driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    //---------------------------------------------------------------------------
    /**
     * Initialize WebDriverWait
     */
    public WebDriverWait getWebDriverWait()
    {
        return getWebDriverWait(DEFAULT_TIMEOUT);
    }

    public WebDriverWait getWebDriverWait(long timeOutInSec)
    {
        return new WebDriverWait(this.driver, Duration.ofSeconds(timeOutInSec));
    }

    // ======================  Wait Helpers ======================
    // Wait for element to meet condition - presence, visibility, clickability, etc.

    //---------------------------------------------------------------------------
    /**
     * Wait Visibility Of Element Located
     */
    public WebElement waitVisibilityOfElementLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitVisibilityOfElementLocated(By locator)
    {
        return waitVisibilityOfElementLocated(locator,DEFAULT_TIMEOUT);
    }

    public WebElement waitVisibilityOf(WebElement element, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitVisibilityOf(WebElement element)
    {
        return waitVisibilityOf(element,DEFAULT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Wait Visibility Of Elements Located
     */

    public List<WebElement> waitVisibilityOfElementsLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitVisibilityOfElementsLocated(By locator)
    {
        return waitVisibilityOfElementsLocated(locator, DEFAULT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Wait Element To Be Clickable
     */

    public WebElement waitElementToBeClickable(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitElementToBeClickable(By locator)
    {
        return waitElementToBeClickable(locator, DEFAULT_TIMEOUT);
    }

    public WebElement waitElementToBeClickable(WebElement element, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementToBeClickable(WebElement element)
    {
        return waitElementToBeClickable(element, DEFAULT_TIMEOUT);
    }


    //---------------------------------------------------------------------------
    /**
     * Wait InVisibility Of Element Located
     */

    public boolean waitInVisibilityOfElementLocated(By locator, long timeOutInSec)
    {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitInVisibilityOfElementLocated(By locator)
    {
        return waitInVisibilityOfElementLocated(locator,DEFAULT_TIMEOUT);
    }

    // ======================  Common Actions ======================
    // Send Keys, Get Text, Click Element, etc.

    //---------------------------------------------------------------------------
    /**
     * Send Keys
     */

    public void sendKeys (By locator, String value,long timeOutInSec)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        element.sendKeys(value);
    }

    public void sendKeys (By locator, String value)
    {
       sendKeys(locator,value, DEFAULT_TIMEOUT);
    }

    public void sendKeys (WebElement element, String value,long timeOutInSec)
    {
        waitVisibilityOf(element,timeOutInSec);
        element.sendKeys(value);
    }

    public void sendKeys (WebElement element, String value)
    {
        sendKeys(element,value, DEFAULT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Click Element
     */

    public void click (By locator, long timeOutInSec)
    {
        WebElement element = waitElementToBeClickable(locator,timeOutInSec);
        element.click();
    }

    public void click (By locator)
    {
        click(locator, DEFAULT_TIMEOUT);
    }

    public void click (WebElement element, long timeOutInSec)
    {
        waitElementToBeClickable(element);
        element.click();
    }

    public void click (WebElement element)
    {
        click(element, DEFAULT_TIMEOUT);
    }

    public void click (WebElement parent, By locator, long timeOutInSec)
    {
        WebElement element = findChildElement(parent,locator,timeOutInSec);
        waitElementToBeClickable(element);
        element.click();
    }

    public void click (WebElement parent, By locator)
    {
        click(parent, locator, DEFAULT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Hover Element
     */

    public void hover (By locator, long timeOutInSec)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void hover (By locator)
    {
        hover(locator, DEFAULT_TIMEOUT);
    }

    public void hover (WebElement element, long timeOutInSec)
    {
        waitVisibilityOf(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void hover (WebElement element) {
        hover(element, DEFAULT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Get text of element
     */

    public String getText (By locator, long timeOutInSec)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        return element.getText();
    }

    public String getText (By locator)
    {
        return getText(locator, DEFAULT_TIMEOUT);
    }

    public String getText (WebElement element, long timeOutInSec)
    {
        waitVisibilityOf(element,timeOutInSec);
        return element.getText();
    }

    public String getText (WebElement element) {
        return getText(element, DEFAULT_TIMEOUT);
    }

    public String getText (WebElement parent, By locator, long timeOutInSec)
    {
        WebElement element = findChildElement(parent,locator,timeOutInSec);
        return element.getText();
    }

    public String getText (WebElement parent,By locator)
    {
        return getText(parent, locator, DEFAULT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Get attribute of element
     */

    public String getAttribute (By locator, String name, long timeOutInSec)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        return element.getAttribute(name);
    }

    public String getAttribute (By locator, String name)
    {
        return getAttribute(locator, name, DEFAULT_TIMEOUT);
    }

    public String getAttribute (WebElement element,String name, long timeOutInSec)
    {
        waitVisibilityOf(element,timeOutInSec);
        return element.getAttribute(name);
    }

    public String getAttribute (WebElement element,String name) {
        return getAttribute(element,name, DEFAULT_TIMEOUT);
    }

    public String getAttribute (WebElement parent, By locator,String name, long timeOutInSec)
    {
        WebElement element = findChildElement(parent,locator,timeOutInSec);
        return element.getAttribute(name);
    }

    public String getAttribute (WebElement parent, By locator,String name)
    {
        return getAttribute(parent,locator,name, DEFAULT_TIMEOUT);
    }


    //---------------------------------------------------------------------------
    /**
     * Check element is display
     */

    public boolean isDisplay (By locator, long timeOutInSec)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        return element.isDisplayed();
    }

    public boolean isDisplay (By locator)
    {
        return isDisplay(locator,DEFAULT_TIMEOUT );
    }

    public boolean isDisplay (WebElement element, long timeOutInSec)
    {
        waitVisibilityOf(element,timeOutInSec);
        return element.isDisplayed();
    }

    public boolean isDisplay (WebElement element)
    {
        return isDisplay(element,DEFAULT_TIMEOUT );
    }

    public boolean isDisplay (WebElement element, By locator, long timeOutInSec)
    {
        WebElement child = findChildElement(element,locator,timeOutInSec);
        return child.isDisplayed();
    }

    public boolean isDisplay (WebElement element, By locator)
    {
        return isDisplay(element,locator,DEFAULT_TIMEOUT );
    }

    //---------------------------------------------------------------------------
    /**
     * Check element is enabled
     */

    public boolean isEnabled(By locator, long timeOutInSec)
    {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        return element.isEnabled();
    }

    public boolean isEnabled(By locator)
    {
        return isEnabled(locator,DEFAULT_TIMEOUT );
    }

    public boolean isEnabled(WebElement element, long timeOutInSec) {
        waitVisibilityOf(element, timeOutInSec);
        return element.isEnabled();
    }

    public boolean isEnabled(WebElement element)
    {
        return isEnabled(element,DEFAULT_TIMEOUT );
    }

    public boolean isEnabled(WebElement parent, By locator, long timeOutInSec)
    {
        WebElement element = findChildElement(parent,locator,timeOutInSec);
        return element.isEnabled();
    }

    public boolean isEnabled(WebElement parent, By locator)
    {
        return isEnabled(parent, locator,DEFAULT_TIMEOUT );
    }

    //---------------------------------------------------------------------------
    /**
     * Find Elements
     */
    public List<WebElement> findElements(By locator,long timeoutInSeconds) {
        waitVisibilityOfElementsLocated(locator,timeoutInSeconds);
        return driver.findElements(locator);
    }

    public List<WebElement> findElements(By locator) {
        return findElements(locator,SHORT_TIMEOUT);
    }

    /**
     * Find Element
     */
    public WebElement findElement(By locator,long timeoutInSeconds) {
        waitVisibilityOfElementsLocated(locator,timeoutInSeconds);
        return driver.findElement(locator);
    }

    public WebElement findElement(By locator) {
        return findElement(locator,SHORT_TIMEOUT);
    }

    /**
     * Find Child Element of the element
     */
    public WebElement findChildElement(WebElement parent, By childLocator, long timeoutInSeconds)
    {
        WebDriverWait wait = getWebDriverWait(timeoutInSeconds);
        return wait.until(driverInstance -> {
            try {
                WebElement element = parent.findElement(childLocator);
                return element.isDisplayed() ? element : null;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement findChildElement(WebElement parent, By childLocator)
    {
        return findChildElement(parent,childLocator, SHORT_TIMEOUT);
    }


    //---------------------------------------------------------------------------
    /**
     * Highlight of element
     */

    public void highlight(By locator,long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='2px solid red';" +
                        "arguments[0].style.backgroundColor='yellow';",
                element
        );
    }

    public void highlight(By locator) {
        highlight(locator, SHORT_TIMEOUT);
    }

    public void highlight(WebElement element,long timeOutInSec) {
        waitVisibilityOf(element,timeOutInSec);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='2px solid red';" +
                        "arguments[0].style.backgroundColor='yellow';",
                element
        );
    }

    public void highlight(WebElement element) {
       highlight(element,SHORT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Unhighlight of element
     */

    public void unhighlight(By locator,long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='';" +
                        "arguments[0].style.backgroundColor='';",
                element
        );
    }

    public void unhighlight(By locator) {
        unhighlight(locator, SHORT_TIMEOUT);
    }
    public void unhighlight(WebElement element,long timeOutInSec) {
        waitVisibilityOf(element,timeOutInSec);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='';" +
                        "arguments[0].style.backgroundColor='';",
                element
        );
    }
    public void unhighlight(WebElement element) {
        unhighlight(element,SHORT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Scroll To Element
     */

    public void scrollToElement(By locator,long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator,timeOutInSec);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                element
        );
    }

    public void scrollToElement(By locator)
    {
        scrollToElement(locator, SHORT_TIMEOUT);
    }

    public void scrollToElement(WebElement element, long timeOutInSec) {
        waitVisibilityOf(element, timeOutInSec);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
    }

    public void scrollToElement(WebElement element) {
        scrollToElement(element,SHORT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Check that images are not broken
     */

    public boolean isImageLoaded(By locator,long timeOutInSec) {
        try {
            return getWebDriverWait(timeOutInSec).until(driverInstance -> {
                try {
                    WebElement image = driverInstance.findElement(locator);

                    JavascriptExecutor js = (JavascriptExecutor) driverInstance;

                    Long naturalWidth = (Long) js.executeScript(
                            "return arguments[0].naturalWidth;", image);

                    return naturalWidth != null && naturalWidth > 0;

                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            });

        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isImageLoaded(By locator) {
        return isImageLoaded(locator, SHORT_TIMEOUT);
    }

    public boolean isImageLoaded(WebElement image,long timeOutInSec) {
        if (image == null) {
            return false;
        }

        try {
            return getWebDriverWait(timeOutInSec).until(driverInstance -> {
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driverInstance;

                    Long naturalWidth = (Long) js.executeScript(
                            "return arguments[0].naturalWidth;", image);

                    return naturalWidth != null && naturalWidth > 0;

                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });

        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isImageLoaded(WebElement image) {
        return isImageLoaded(image,SHORT_TIMEOUT);
    }

    public boolean isImageLoaded(WebElement parent, By locator, long timeOutInSec) {
        if (parent == null) return false;

        try {
            return getWebDriverWait(timeOutInSec).until(driverInstance -> {
                try {
                    WebElement image = parent.findElement(locator);

                    Long naturalWidth = (Long) ((JavascriptExecutor) driverInstance)
                            .executeScript("return arguments[0].naturalWidth;", image);

                    return naturalWidth != null && naturalWidth > 0;

                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isImageLoaded(WebElement parent,By locator) {
        return isImageLoaded(parent,locator,SHORT_TIMEOUT);
    }

    //---------------------------------------------------------------------------
    /**
     * Get URL of the page
     */
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    /**
     * Get Title of the page
     */
    public String getPageTitle()
    {
        return driver.getTitle();
    }

    public boolean waitForTextDisplay(By locator, String expectedName, long timeOutInSec) {
        WebDriverWait wait = getWebDriverWait(timeOutInSec);
        return  wait.until(ExpectedConditions.textToBe(locator, expectedName));
    }

    public boolean waitForTextDisplay(By locator, String expectedName) {
        WebDriverWait wait = getWebDriverWait(SHORT_TIMEOUT);
        return  wait.until(ExpectedConditions.textToBe(locator, expectedName));
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
}
