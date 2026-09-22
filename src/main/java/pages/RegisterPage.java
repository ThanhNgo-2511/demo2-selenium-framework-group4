package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends CommonPage {
    private By byTxtAccount;
    private By byTxtPassword;
    private By byTxtConfirmPassword;
    private By byTxtFullname;
    private By byTxtEmail;
    private By byBtnRegister;

    public RegisterPage(WebDriver driver)
    {
        super(driver);
        this.byTxtAccount = By.id("taiKhoan");
        this.byTxtPassword = By.name("matKhau");
        this.byTxtConfirmPassword = By.xpath("//*[@id=\"confirmPassWord\"]");
        this.byTxtFullname = By.id("hoTen");
        this.byTxtEmail = By.id("email");
        this.byBtnRegister = By.xpath("//button[.='Đăng ký']");
    }
    public void enterAccount (String account)
    {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword (String password)
    {
        sendKeys(byTxtPassword, password);
    }

    public void confirmPassword (String password)
    {
        sendKeys(byTxtConfirmPassword,password);
    }

    public void enterFullname (String fullname)
    {
        sendKeys(byTxtFullname, fullname);
    }

    public void enterEmail (String email)
    {
        sendKeys(byTxtEmail, email);
    }

    public void clickRegister ()
    {
        click(byBtnRegister);
    }

    public void register (String account, String password, String confirmPW, String fullname, String email)
    {
        enterAccount(account);
        enterPassword(password);
        confirmPassword(confirmPW);
        enterFullname(fullname);
        enterEmail(email);
        clickRegister();
    }

}
