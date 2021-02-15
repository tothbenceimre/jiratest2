package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"user-options\"]/a")
    WebElement loginPageButton;
    @FindBy(xpath = "//*[contains(text(), 'You are now logged out. Any automatic login has also been stopped.')]")
    WebElement logoutMessage;


    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean verifySuccessLogOut () {
        return loginPageButton.isDisplayed() && logoutMessage.isDisplayed();
    }
}
