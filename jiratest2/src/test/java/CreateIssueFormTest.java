import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.CreateIssueForm;
import pages.DashboardPage;
import pages.MainPage;
import util.UtilDriver;

class CreateIssueFormTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    CreateIssueForm form;

    @BeforeEach
    public void setUp(){
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        form = new CreateIssueForm(utilDriver.getDriver());
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
        boolean isCreatedCorrectly = mainPage.isIssueCreatedCorrectly(project, issue, summary);
        mainPage.deleteIssue();
        Assertions.assertTrue(isCreatedCorrectly);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/cancel_in_issue_form.csv", numLinesToSkip = 1)
    public void cancelInIssueForm(String project, String issue, String summary, boolean isCreate){
        mainPage.fillCreateIssueForm(project, issue, summary, isCreate);
        boolean isCreated = mainPage.isIssueCreatedCorrectly(project, issue, summary);
        Assertions.assertFalse(isCreated);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/invalid_in_issue_form.csv", numLinesToSkip = 1)
    public void invalidDataInIssueForm(String project, String issue, String summary, boolean isCreate){
        mainPage.fillCreateIssueForm(project, issue, summary, isCreate);
        boolean isIncorrect = form.isNoMatchPresent();
        Assertions.assertTrue(isIncorrect);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/empty_summary_in_issue_form.csv", numLinesToSkip = 1)
    public void emptySummaryIssueForm(String project, String issue, String summary, boolean isCreate){
        mainPage.fillCreateIssueForm(project, issue, summary, isCreate);
        boolean isInCorrect = form.isErrorPresent();
        Assertions.assertTrue(isInCorrect);
    }
}