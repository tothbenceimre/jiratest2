package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueForm {
    WebDriver driver;
    @FindBy(id = "project-field") WebElement projectField;
    @FindBy(xpath = "//*[@id='issuetype-single-select']/input") WebElement issueTypeInput;
    @FindBy(id = "summary") WebElement summaryField;
    @FindBy(id = "create-issue-submit") WebElement createButton;
    @FindBy(xpath = "//*[@id='aui-flag-container']//a") WebElement issueCreatedLink;
    @FindBy(xpath = "//*[@id='create-issue-submit']/following-sibling::button") WebElement cancelButton;
    @FindBy(id = "type-val") WebElement issueTypeVal;
    @FindBy(id = "project-name-val") WebElement projectNameVal;
    @FindBy(id = "summary-val") WebElement summaryVal;
    @FindBy(id = "opsbar-operations_more") WebElement moreButton;
    @FindBy(id = "delete-issue") WebElement deleteButton;
    @FindBy(id = "delete-issue-submit") WebElement deleteConfirm;
    @FindBy(id = "find_link") WebElement issuesButton;
    @FindBy(xpath = "//*[@id='issues_history_main']//li[1]") WebElement recentIssue;
    @FindBy(xpath = "//*[@id='summary']/following-sibling::div[@class='error']") WebElement errorSummary;
    boolean isNoMatch;

    public CreateIssueForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillProjectField(String projectName){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(projectField));
        projectField.click();
        fill(projectField, projectName);
    }

    public void fillIssueType(String issue){
        waitForStale(issueTypeInput);
        fill(issueTypeInput, issue);
    }

    public void fill(WebElement webElement, String str){
        webElement.sendKeys(Keys.CONTROL+"a");
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(str);
        isNoMatch = isNoMatchPresent();
        webElement.sendKeys(Keys.TAB);
    }

    public void waitForStale(WebElement element){
        try {
            element.click();
        } catch (StaleElementReferenceException e){
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
        }
    }

    public void waitForStaleSummary(WebElement element){
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (StaleElementReferenceException e){
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
        }
    }

    public void fillSummary(String summary){
        waitForStaleSummary(summaryField);
        summaryField.sendKeys(summary);
    }

    public boolean isErrorPresent(){
        try {
            return errorSummary.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isNoMatchPresent(){
        return driver.getPageSource().contains("No Matches");
    }

    public void clickCreate(){
        createButton.click();
    }

    public void clickCancel(){
        cancelButton.click();
    }

    public void finishCreate(boolean isCreate) {
        if (isCreate){
            clickCreate();
        } else {
            clickCancel();
            acceptAlert();
        }
    }

    public void navigateToLastCreatedIssue(){
        acceptAlert();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(issuesButton));
        issuesButton.click();
        waitForStale(recentIssue);
    }

    public void acceptAlert(){
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ignored){ }
    }


    public boolean isIssueCreated(String projectName, String issueType, String summary){
        if (!projectName.contains(projectNameVal.getText())){
            return false;
        }
        if (!issueTypeVal.getText().contains(issueType)|| !issueType.contains(issueTypeVal.getText())){
            return false;
        }
        if (!summaryVal.getText().equals(summary)){
            return false;
        }
        return true;
    }

    public void deleteCreatedIssue(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(moreButton));
        moreButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(deleteConfirm));
        deleteConfirm.click();
    }

}
