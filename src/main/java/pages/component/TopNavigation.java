package pages.component;

import base.BasePage;
import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TopNavigation extends BasePage {

    private By byLnkLogin;
    private By byLnkRegister;

    private By byImgAvatar;
    private By byBtnLogout;

    public TopNavigation(WebDriver driver)
    {
        super(driver);
        this.byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
        this.byLnkRegister = By.xpath("//a[h3[text()='Đăng Ký']]");
        this.byImgAvatar = By.xpath("//img[@alt='Avatar']");
        this.byBtnLogout = By.xpath("//h3[text()='Đăng xuất']");
    }

    public void navigateToLoginPage ()
    {
        click(byLnkLogin);
    }

    public void navigateToRegisterPage ()
    {
        click(byLnkRegister);
    }


    public void waitAvatarAppear(long timeOutInSec)
    {
        waitVisibilityOfElementLocated(byImgAvatar,timeOutInSec);
    }

    public void waitAvatarAppear()
    {
        waitAvatarAppear(TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void assertAvatarAppear(long timeOutInSec)
    {
        Assert.assertTrue(isDisplay(byImgAvatar));
    }

    public void assertAvatarAppear()
    {
        assertAvatarAppear(TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void waitLogoutButtonAppear(long timeOutInSec)
    {
        waitVisibilityOfElementLocated(byBtnLogout,timeOutInSec);
    }

    public void waitLogoutButtonAppear()
    {
        waitLogoutButtonAppear(TimeoutConstant.DEFAULT_TIMEOUT);
    }

    public void assertLogoutButtonAppear(long timeOutInSec)
    {
        Assert.assertTrue(isDisplay(byBtnLogout));
    }

    public void assertLogoutButtonAppear()
    {
        assertAvatarAppear(TimeoutConstant.DEFAULT_TIMEOUT);
    }

}
