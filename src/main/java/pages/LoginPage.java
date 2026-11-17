
package pages;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends CommonPage {

    // LOGIN LOCATORS
    private By byTxtAccountLogin;
    private By byTxtPasswordLogin;
    private By byBtnLogin;
    private By byTxtNameForm;
    private By byTxtLoginError;

    // AVATAR AND LOGOUT LOCATORS
    private By byImgAvatar;
    private By byBtnLogout;
    private By byLinkLogin;

    public LoginPage(WebDriver driver) {
        super(driver);

        // Login
        this.byTxtAccountLogin = By.xpath(
                "//form[@class='formLoginUser']/input[@name='taiKhoan']"
        );

        this.byTxtPasswordLogin = By.xpath(
                "//form[@class='formLoginUser']/input[@name='matKhau']"
        );

        this.byBtnLogin = By.xpath(
                "//form/button[text()='Đăng nhập']"
        );

        this.byTxtNameForm = By.xpath(
                "//h1[text()='Đăng nhập']"
        );

        // Login error message
        this.byTxtLoginError = By.xpath(
                "//*[normalize-space()='Tài khoản hoặc mật khẩu không đúng!']"
        );

        // Avatar
        this.byImgAvatar = By.cssSelector("img.avatar");

        // Logout button
        this.byBtnLogout = By.cssSelector(
                "span.logout a[href='/trangchu']"
        );

        // Login link after logout
        this.byLinkLogin = By.xpath(
                "//a[text()='Đăng nhập']"
        );
    }

    // LOGIN METHODS

    public void enterAccount(String account) {
        sendKeys(
                byTxtAccountLogin,
                account,
                TimeoutConstant.LONG_TIMEOUT
        );
    }

    public void enterPassword(String password) {
        sendKeys(
                byTxtPasswordLogin,
                password,
                TimeoutConstant.LONG_TIMEOUT
        );
    }

    public void clickLogin() {
        click(byBtnLogin);
    }

    public void login(String account, String password) {
        enterAccount(account);
        enterPassword(password);
        clickLogin();
    }

    public String getNameForm() {
        return getText(byTxtNameForm);
    }

    public boolean isLoginSuccessful() {
        return isDisplayed(
                byImgAvatar,
                TimeoutConstant.LONG_TIMEOUT
        );
    }

    public String getLoginErrorMessage() {
        return getText(byTxtLoginError);
    }

    // LOGOUT METHODS

    public void hoverAvatar() {
        hover(byImgAvatar);
    }

    public void clickLogout() {

        WebElement logoutButton = waitVisibilityOfElementLocated(
                byBtnLogout,
                TimeoutConstant.LONG_TIMEOUT
        );

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript(
                "arguments[0].click();",
                logoutButton
        );
    }

    public void logout() {
        hoverAvatar();
        clickLogout();
    }

    public boolean isLoginLinkDisplayed() {
        return isDisplayed(
                byLinkLogin,
                TimeoutConstant.LONG_TIMEOUT
        );
    }
    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(
                byBtnLogout,
                TimeoutConstant.LONG_TIMEOUT
        );
    }
}
