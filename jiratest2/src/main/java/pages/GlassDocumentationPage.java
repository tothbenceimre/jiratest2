package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlassDocumentationPage {
    WebDriver driver;
    @FindBy(id = "aui-uid-2") WebElement versionsTab;


    public GlassDocumentationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnVersionsTab () {
        versionsTab.click();
    }

    public WebElement getCurrentReleaseRow (String id) {
        return driver.findElement(By.xpath("//a[@href='https://jira-auto.codecool.metastage.net/browse/PP/fixforversion/" + id +"']"));
    }

    public boolean isAdded (String name, String id) {
        if (getCurrentReleaseRow(id).getText().equals(name)) {
            return true;
        }
        else {
            return false;
        }
    }
}
