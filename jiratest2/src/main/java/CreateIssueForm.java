import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueForm {
    WebDriver driver;
    @FindBy(id = "project-field")
    WebElement projectField;
    @FindBy(id = "issuetype-single-select")
    WebElement issueTypeField;
    @FindBy(xpath = "//*[@id='issuetype-single-select']/input")
    WebElement issueTypeInput;
//    @FindBy(xpath = "//input[@id='issuetype']")
//    WebElement issueTypeInput;
    @FindBy(id = "summary")
    WebElement summaryField;
    @FindBy(id = "create-issue-submit")
    WebElement createButton;
    @FindBy(xpath = "//*[@id='aui-flag-container']//a")
    WebElement issueCreatedLink;
    @FindBy(xpath = "//*[@id='create-issue-submit']/following-sibling::button")
    WebElement cancelButton;
    @FindBy(id = "type-val")
    WebElement issueTypeVal;
    @FindBy(id = "project-name-val")
    WebElement projectNameVal;
    @FindBy(id = "summary-val")
    WebElement summaryVal;

    public CreateIssueForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public void fillProjectField(String projectName){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(projectField));
        projectField.clear();
        projectField.sendKeys(projectName);
        projectField.sendKeys(Keys.ENTER);
    }

    public void fillIssueType(String issue){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(issueTypeField));
        issueTypeField.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(issueTypeInput));
        issueTypeInput.sendKeys(issue);
    }

    public void fillSummary(String summary){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.click();
        summaryField.sendKeys(summary);
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
        } else {clickCancel();}
    }

    public void navigateToCreatedIssue(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(issueCreatedLink));
        issueCreatedLink.click();
    }
    public boolean isIssueCreated(String projectName, String issueType, String summary){
        if (!projectNameVal.getText().equals(projectName)){
            return false;
        }
        if (!issueTypeVal.getText().equals(issueType)){
            return false;
        }
        if (!summaryVal.getText().equals(summary)){
            return false;
        }
        return true;

    }
}
