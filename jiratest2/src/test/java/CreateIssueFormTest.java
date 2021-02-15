import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.UtilDriver;

import static org.junit.jupiter.api.Assertions.*;

class CreateIssueFormTest {
    UtilDriver utilDriver;
    MainPage mainPage;

    @BeforeEach
    public void setUp(){
        utilDriver = new UtilDriver();
        login(utilDriver.getDriver());
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.clickCreateButton();
    }

    public void login(WebDriver driver){
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        driver.findElement(By.id("login-form-username")).sendKeys(System.getenv("jirausername"));
        driver.findElement(By.id("login-form-password")).sendKeys(System.getenv("jirapassword"));
        driver.findElement(By.id("login-form-submit")).click();
    }

    @AfterEach
    public void tearDown(){
        utilDriver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/fill_create_issue_form.csv", numLinesToSkip = 1)
    public void fillCreateIssueForm(String project, String issue, String summary, boolean isCreate){
        mainPage.fillCreateIssueForm(project, issue, summary, isCreate);
        boolean isCreatedCorrectly = mainPage.isIssueCreated(project, issue, summary);
        mainPage.deleteIssue();
        Assertions.assertTrue(isCreatedCorrectly);
    }
}