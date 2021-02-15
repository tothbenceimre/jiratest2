import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MainPage;
import util.UtilDriver;

class CreateIssueFormTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;

    @BeforeEach
    public void setUp(){
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.clickCreateButton();
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