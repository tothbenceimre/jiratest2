package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class ViewAllProjectsPage {
    WebDriver driver;

    public ViewAllProjectsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement findCurrentProject (String project) {
        return driver.findElement(By.xpath("/html//div[@id='projects']//table[@class='aui']//a[contains(text(),'" + project + "')]"));
    }

    public void clickOnSearchedProject (String project) {
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        findCurrentProject(project).click();
    }
}
