package pages;

import constants.TimeoutConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {
    private By byTxtAccountLogin;
    private By byTxtPasswordLogin;
    private By byBtnLogin;

    //Ham khoi tao (contructor)
    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.byTxtAccountLogin = By.id("taiKhoan");
        this.byTxtPasswordLogin = By.id("matKhau");
        this.byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");
    }

    public void enterAccount (String account)
    {
        sendKeys(byTxtAccountLogin,account, TimeoutConstant.LONG_TIMEOUT);
    }

    public void enterPassword (String password)
    {
        sendKeys(byTxtPasswordLogin,password, TimeoutConstant.LONG_TIMEOUT);
    }

    public void clickLogin ()
    {
       click(byBtnLogin);
    }

    //High level account (business action)
    public void login (String account, String password)
    {
        enterAccount(account);
        enterPassword(password);
        clickLogin();
    }
}
