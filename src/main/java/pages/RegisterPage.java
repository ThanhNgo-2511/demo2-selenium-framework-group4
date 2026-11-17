
package pages;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends CommonPage {
    private By byTxtPhoneError ;
    private By byBtnOpenRegister;
    private By byTxtAccount;
    private By byTxtPassword;
    private By byTxtFullname;
    private By byTxtEmail;
    private By byTxtPhone;
    private By bySelectGroup;
    private By byBtnRegister;
    private By byTxtRegisterTitle;
    private By byTxtRegisterSuccess;
    private By byTxtAccountError;
    private By byTxtPasswordError;
    private By byTxtEmailError;
    private By byTxtPhoneRequiredError;

    public RegisterPage(WebDriver driver) {
        super(driver);

        byBtnOpenRegister = By.id("signUp");

        byTxtAccount = By.cssSelector(
                ".sign-up-container input[name='taiKhoan']"
        );

        byTxtFullname = By.cssSelector(
                ".sign-up-container input[name='hoTen']"
        );

        byTxtPassword = By.cssSelector(
                ".sign-up-container input[name='matKhau']"
        );

        byTxtEmail = By.cssSelector(
                ".sign-up-container input[name='email']"
        );

        byTxtPhone = By.cssSelector(
                ".sign-up-container input[name='soDT']"
        );

        bySelectGroup = By.cssSelector(
                ".sign-up-container select[name='maNhom']"
        );

        byBtnRegister = By.cssSelector(
                ".sign-up-container button[type='submit']"
        );

        byTxtRegisterTitle = By.xpath(
                "//div[contains(@class,'sign-up-container')]//h2[text()='ĐĂNG KÝ']"
        );

        byTxtRegisterSuccess = By.cssSelector(".swal-title");

        byTxtAccountError = By.cssSelector(
                ".sign-up-container .errorMessage"
        );

        byTxtPasswordError = By.cssSelector(
                ".sign-up-container input[name='matKhau'] + div.errorMessage"
        );

        byTxtEmailError = By.xpath(
                "//div[@class='errorMessage' and normalize-space()='Email không hợp lệ']"
        );
        byTxtPhoneError = By.xpath(
                "//div[@class='errorMessage' and normalize-space()='Số điện thoại chưa đúng định đạng']"
        );
        byTxtPhoneRequiredError = By.cssSelector(
                ".sign-up-container input[name='soDT'] + div.errorMessage"
        );
    }

    public void openRegisterForm() {
        click(byBtnOpenRegister);
    }

    public void enterAccount(String account) {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword(String password) {
        sendKeys(byTxtPassword, password);
    }

    public void enterFullname(String fullname) {
        sendKeys(byTxtFullname, fullname);
    }

    public void enterEmail(String email) {
        sendKeys(byTxtEmail, email);
    }

    public void enterPhone(String phone) {
        sendKeys(byTxtPhone, phone);
    }

    public void selectGroup(String group) {
        WebElement groupDropdown = waitVisibilityOfElementLocated(
                bySelectGroup,
                TimeoutConstant.LONG_TIMEOUT
        );

        Select select = new Select(groupDropdown);
        select.selectByValue(group);
    }

    public void clickRegister() {
        click(byBtnRegister);
    }

    public void register(
            String account,
            String password,
            String fullname,
            String email,
            String phone,
            String group
    ) {
        enterAccount(account);
        enterFullname(fullname);
        enterPassword(password);
        enterEmail(email);
        enterPhone(phone);
        selectGroup(group);
        clickRegister();
    }

    public void setEmailInputTypeText() {
        WebElement emailInput = waitVisibilityOfElementLocated(
                byTxtEmail,
                TimeoutConstant.LONG_TIMEOUT
        );

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].setAttribute('type', 'text');",
                emailInput
        );
    }

    public boolean isRegisterFormDisplayed() {
        return isDisplayed(
                byTxtRegisterTitle,
                TimeoutConstant.LONG_TIMEOUT
        );
    }

    public String getRegisterTitle() {
        return getText(byTxtRegisterTitle);
    }

    public String getRegisterSuccessMessage() {
        return getText(byTxtRegisterSuccess);
    }

    public String getAccountErrorMessage() {
        return getText(byTxtAccountError);
    }

    public String getPasswordErrorMessage() {
        return getText(byTxtPasswordError);
    }

    public String getEmailErrorMessage() {
        return getText(byTxtEmailError);
    }
    public String getPhoneErrorMessage() { return getText(byTxtPhoneError);}
    public String getPhoneRequiredErrorMessage() { return getText(byTxtPhoneRequiredError);}
}
