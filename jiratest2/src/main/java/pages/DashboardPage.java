package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    WebDriver driver;

    @FindBy(id = "login-form-username")
    WebElement usernameForm;

    @FindBy(id = "login-form-password")
    WebElement passwordForm;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"user-options\"]/a")
    WebElement loginPageButton;

    @FindBy(xpath = "//div/p[contains(text(),'Sorry, your username and password are incorrect - please try again.')]")
    WebElement loginError;

    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void fillUserForm (String username) {
        usernameForm.sendKeys(username);
    }

    public void fillPasswordForm (String password) {
        passwordForm.sendKeys(password);
    }

    public void login (String username, String password) {
        fillUserForm(username);
        fillPasswordForm(password);
        loginButton.click();
    }

    public boolean verifyUnsuccessfulLogin () {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(loginError));
        return loginError.isDisplayed() && loginPageButton.isDisplayed();
    }

    public void navigateToLoginPage () {
        loginPageButton.click();
    }

}


