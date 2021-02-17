package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseIssuePage {
    WebDriver driver;
    @FindBy(id = "type-val") WebElement issueTypeVal;
    @FindBy(id = "project-name-val") WebElement projectNameVal;
    @FindBy(id = "summary-val") WebElement summaryVal;
    @FindBy(id = "key-val") WebElement issueId;
    @FindBy(id = "edit-issue") WebElement editIssueButton;

    public BrowseIssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void load(){
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(issueId));
    }

    public boolean isIssueInProject(String projectName, String id){
        try {
            load();
            return (projectName.equals(getProjectNameVal()) && id.equals(getId()));
        } catch (TimeoutException e){ return false;}
    }

    public String getIssueTypeVal() {
        return issueTypeVal.getText();
    }

    public String getProjectNameVal() {
        return projectNameVal.getText();
    }

    public String getSummaryVal() {
        return summaryVal.getText();
    }

    public String getId() {
        return issueId.getText();
    }

    public boolean issueIsEditable () {
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(editIssueButton));
            return true;
        } catch (TimeoutException e){ return false;}
    }

    public void clickOnEditIssue () {
        editIssueButton.click();
    }
}
