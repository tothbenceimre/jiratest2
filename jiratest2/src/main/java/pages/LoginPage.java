package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "login-form-username")
    WebElement usernameForm;

    @FindBy(id = "login-form-password")
    WebElement passwordForm;

    @FindBy(id = "login-form-submit")
    WebElement loginButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillUsernameForm (String username) {
        usernameForm.sendKeys(username);
    }

    public void fillPasswordForm (String password) {
        passwordForm.sendKeys(password);
    }

    public void login (String username, String password) {
        fillUsernameForm(username);
        fillPasswordForm(password);
        loginButton.click();
    }
}
