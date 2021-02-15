package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    WebDriver driver;
    @FindBy(id = "up-d-username")
    WebElement username;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean verifySuccessfulLogin (String username) {
        return this.username.getText().equals(username);
    }
}
