import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    CreateIssueForm createIssueForm;
    @FindBy(id = "create_link")
    WebElement createButton;
    @FindBy(xpath = "//*[@id=\"header-details-user-fullname\"]/span/span/img")
    WebElement avatar;
    @FindBy(id = "view_profile")
    WebElement viewProfileTab;
    @FindBy(id = "log_out")
    WebElement logoutTab;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickCreateButton(){
        createButton.click();
    }

    public void fillCreateIssueForm(String project, String issue, String summary, boolean isCreate){
        createIssueForm = new CreateIssueForm(driver);
        createIssueForm.fillProjectField(project);
        createIssueForm.fillIssueType(issue);
        createIssueForm.fillSummary(summary);
        createIssueForm.finishCreate(isCreate);
    }

    public boolean isIssueCreated(String project, String issue, String summary){
        createIssueForm.navigateToCreatedIssue();
        return createIssueForm.isIssueCreated(project, issue, summary);
    }

    public void navigateToProfile () {
        avatar.click();
        viewProfileTab.click();
    }

    public void clickOnLogout() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(avatar));
        avatar.click();
        logoutTab.click();
    }

    public void deleteIssue(){
        createIssueForm.deleteCreatedIssue();
    }
}
