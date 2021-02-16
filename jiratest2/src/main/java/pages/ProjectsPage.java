package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsPage {
    WebDriver driver;
    @FindBy(xpath = "//*[text()=\"You can't view this project\"]")
    WebElement errorMessage;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebElement findProjectsKey (String project) {
        return driver.findElement(By.xpath("//*[text()='" + project + "']"));
    }

    public boolean verifyProjectIsAvailable (String project) {
        return findProjectsKey(project).getText().equals(project);
    }

    public boolean verifyProjectIsNotAvailable () {
        return errorMessage.getText().equals("You can't view this project");
    }
}
