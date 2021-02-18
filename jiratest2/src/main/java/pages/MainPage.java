package pages;

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
    @FindBy(id = "browse_link")
    WebElement browseButton;
    @FindBy(id = "project_view_all_link_lnk")
    WebElement viewAllProjectsTab;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void loadpage(){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(avatar));
    }

    public void clickCreateButton(){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
    }

    public void fillCreateIssueForm(String project, String issue, String summary, boolean isCreate){
        createIssueForm = new CreateIssueForm(driver);
        createIssueForm.fillProjectField(project);
        createIssueForm.fillIssueType(issue);
        createIssueForm.fillSummary(summary);
        createIssueForm.finishCreate(isCreate);
    }

    public boolean isIssueCreatedCorrectly(String project, String issue, String summary){
        if (createIssueForm.isErrorPresent()){
            return false;
        }
        createIssueForm.navigateToLastCreatedIssue();
        return createIssueForm.isIssueCreated(project, issue, summary);
    }

    public void deleteIssue(){
        createIssueForm.deleteCreatedIssue();
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

    public void clickOnViewAllProjects () {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(browseButton));
        browseButton.click();
        viewAllProjectsTab.click();
    }

    public void navigateToIssue(String project, String id) {
        driver.navigate().to("https://jira-auto.codecool.metastage.net/projects/"+project+"/issues/"+id);
    }

    public void navigateToGlass(String project){
        driver.navigate().to("https://jira-auto.codecool.metastage.net/projects/"+project+"?selectedItem=com.codecanvas.glass:glass");
    }

    public void navigateToComponents(String project){
        driver.navigate().to("https://jira-auto.codecool.metastage.net/projects/"+project+"?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
}
