import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;
    CreateIssueForm createIssueForm;
    @FindBy(id = "create_link")
    WebElement createButton;


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
}
