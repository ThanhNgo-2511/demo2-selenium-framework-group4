package pages;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.util.List;

import static config.urlConfig.BASE_URL_PATTERN;

public class ProfilePage extends CommonPage {

    private final WebDriver driver;

    private final By byProfileTitle = By.xpath("//h3[text()='Thông tin cá nhân']");
    private final By byTabs = By.xpath("//button[contains(@class,'tabLink')]");
    private final By byPersonalInfoTab = By.id("infoPersonal");
    private final By byCoursesTab = By.id("infoCourse");
    private final By byUpdateButton = By.xpath("//button[text()='Cập nhật']");
    private final By byModal = By.id("myModal");
    private final By byModalTitle = By.xpath("//h5[text()='Chỉnh sửa thông tin cá nhân']");
    private final By byFullName = By.xpath("//input[@name='hoTen']");
    private final By byPassword = By.xpath("//input[@name='matKhau']");
    private final By byEmail = By.xpath("//input[@name='email']");
    private final By byPhone = By.xpath("//input[@name='soDT']");
    private final By byMessages = By.xpath("//div[contains(@class,'errorMessage') or contains(@class,'message')]");
    private final By bySubmit = By.xpath("//button[text()='Hoàn thành']");
    private final By byClose = By.xpath("//button[text()='Đóng']");
    private final By bySkillBars = By.xpath("//div[@class='progress-bar progress-bar-striped progress-bar-animated']");
    private final By byLearningStatistics = By.xpath("//div[@class='timeStudyItem']");
    private final By byCourseSectionTitle = By.xpath("//h6[text()='Khóa học của tôi']");

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /** Opens the profile page and waits for its identifying heading. */
    public void waitForProfileLoaded() {
        waitVisibilityOfElementLocated(
                byProfileTitle,
                TimeoutConstant.LONG_TIMEOUT
        );
    }

    public String getProfileTitle() {
        return getText(byProfileTitle);
    }

    public int getTabCount() {
        return driver.findElements(byTabs).size();
    }

    public String getTabText(int zeroBasedIndex) {
        List<WebElement> tabs = driver.findElements(byTabs);
        return tabs.get(zeroBasedIndex).getText().trim();
    }

    public boolean isTabActive(int zeroBasedIndex) {
        List<WebElement> tabs = driver.findElements(byTabs);
        return tabs.get(zeroBasedIndex).getAttribute("class").contains("active");
    }

    public void clickPersonalInformationTab() {
        click(tabAt(0));
        waitVisibilityOfElementLocated(byPersonalInfoTab);
    }

    public void clickCoursesTab() {
        click(tabAt(1));
        waitVisibilityOfElementLocated(byCoursesTab);
    }

    private By tabAt(int index) {
        return By.xpath("//*[contains(concat(' ', normalize-space(@class), ' '), ' infoRight ')]//*[contains(concat(' ', normalize-space(@class), ' '), ' tab ')]/button[position()=" + (index + 1) + " and contains(concat(' ', normalize-space(@class), ' '), ' tabLink ')]");
    }

    public boolean isPersonalInformationVisible() {
        return isDisplay(byPersonalInfoTab);
    }

    public boolean isCoursesSectionVisible() {
        return isDisplay(byCoursesTab);
    }

    public String getCoursesSectionTitle() {
        return getText(byCourseSectionTitle);
    }

    public boolean isProfileSummaryVisible() {
        return isDisplay(By.xpath("//div[@class='infoLeft']//h6"))
                && isDisplay(By.xpath("//div[@class='infoLeft']//p"))
                && isDisplay(By.xpath("//div[@class='infoLeft']//img"));
    }

    public int getSkillBarCount() {
        return driver.findElements(bySkillBars).size();
    }

    public int getLearningStatisticCount() {
        return driver.findElements(byLearningStatistics).size();
    }

    public boolean arePersonalInformationFieldsVisible() {
        return isDisplay(By.xpath("//div[@class='userInfoTop']"))
                && isDisplay(By.xpath("//div[@class='userInfoTop']//p"));
    }

    public boolean isUpdateButtonVisible() {
        return isDisplay(byUpdateButton);
    }

    public void openEditPopup() {
        click(byUpdateButton);
        waitVisibilityOfElementLocated(byModal);
    }

    public boolean isEditPopupVisible() {
        return isDisplay(byModal);
    }

    public String getEditPopupTitle() {
        return getText(byModalTitle);
    }

    public boolean areEditFieldsVisible() {
        return isDisplay(byFullName) && isDisplay(byPassword)
                && isDisplay(byEmail) && isDisplay(byPhone);
    }

    public String getFieldValue(String fieldName) {
        return waitVisibilityOfElementLocated(locatorForField(fieldName)).getAttribute("value");
    }

    public void setFieldValue(String fieldName, String value) {
        WebElement field = waitVisibilityOfElementLocated(locatorForField(fieldName));
        field.clear();
        if (value != null && !value.isEmpty()) {
            field.sendKeys(value);
        }
    }

    public void blurField(String fieldName) {
        waitVisibilityOfElementLocated(locatorForField(fieldName)).sendKeys(Keys.TAB);
    }

    public void clickSubmit() {
        click(bySubmit);
    }

    public void closeEditPopup() {
        click(byClose);
        getWebDriverWait().until(driver -> driver.findElements(byModal).isEmpty()
                || !driver.findElement(byModal).isDisplayed());
    }

    public boolean isEditPopupClosed() {
        return getWebDriverWait().until(driver -> driver.findElements(byModal).isEmpty()
                || !driver.findElement(byModal).isDisplayed());
    }

    public String getValidationMessage(int fieldIndex) {
        By fieldLocator = fieldLocatorAt(fieldIndex);
        return getWebDriverWait().until(driver -> {
            List<WebElement> fields = driver.findElements(fieldLocator);
            if (fields.isEmpty()) {
                return null;
            }

            List<WebElement> messages = fields.get(0).findElements(By.xpath("following-sibling::div[contains(concat(' ', normalize-space(@class), ' '), ' errorMessage ') or contains(concat(' ', normalize-space(@class), ' '), ' message ')][1]"));
            if (messages.isEmpty()) {
                return null;
            }

            WebElement message = messages.get(0);
            String text = message.getText().trim();
            return message.isDisplayed() && !text.isEmpty() ? text : null;
        });
    }

    public int getValidationMessageContainerCount() {
        return driver.findElements(byMessages).size();
    }

    private By fieldLocatorAt(int index) {
        switch (index) {
            case 0: return byFullName;
            case 1: return byPassword;
            case 2: return byEmail;
            case 3: return byPhone;
            default: throw new IllegalArgumentException("Unsupported validation field index: " + index);
        }
    }

    private By locatorForField(String fieldName) {
        switch (fieldName) {
            case "hoTen": return byFullName;
            case "matKhau": return byPassword;
            case "email": return byEmail;
            case "soDT": return byPhone;
            default: throw new IllegalArgumentException("Unsupported profile field: " + fieldName);
        }
    }
}
