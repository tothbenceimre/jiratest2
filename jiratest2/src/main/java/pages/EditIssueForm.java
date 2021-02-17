package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssueForm {
    WebDriver driver;
    @FindBy(id = "summary") WebElement summaryField;
    @FindBy(id = "edit-issue-submit") WebElement updateButton;
    @FindBy(xpath = "//*[@id='edit-issue-dialog']/footer/div/div/button") WebElement cancelButton;
    @FindBy(xpath = "//*[@id='summary']/following-sibling::div") WebElement errorMessage;


    public EditIssueForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillSummaryField (String summary) {
        summaryField.sendKeys(summary);
    }

    public void editSummaryField(String summary) {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.clear();
        fillSummaryField(summary);
        updateButton.click();
    }

    public void clickOnUpdateButton() {
        updateButton.click();
    }

    public void acceptAlert () {
        driver.switchTo().alert().accept();
    }

    public void clickOnCancelButton () {
        cancelButton.click();
        acceptAlert();
    }

    public boolean isErrorMessageDisplayed () {
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(errorMessage));
            return true;
        } catch (TimeoutException e){ return false;}
    }
}
