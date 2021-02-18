import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.CreateIssueForm;
import pages.CreateIssueFromProject;
import pages.DashboardPage;
import pages.MainPage;
import util.UtilDriver;

class CreateIssueFromProjectTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    CreateIssueFromProject createIssuePage;
    CreateIssueForm page;
    MainPage mainPage;

    @BeforeEach
    public void setUp(){
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.loadpage();
    }

    @AfterEach
    public void tearDown(){
        utilDriver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/create_issue_from_project.csv", numLinesToSkip = 1)
    public void createIssueFromProject(String project, String issue, String summary, String longProjectName){
        createIssuePage = new CreateIssueFromProject(utilDriver.getDriver(), project );
        createIssuePage.createIssue(issue, summary);
        page = new CreateIssueForm(utilDriver.getDriver());
        page.navigateToLastCreatedIssue();
        boolean created = page.isIssueCreated(longProjectName, issue, summary);
        page.deleteCreatedIssue();
        Assertions.assertTrue(created);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/create_issue_from_project.csv", numLinesToSkip = 1)
    public void cancelCreateIssueFromProject(String project, String issue, String summary, String longProjectName){
        createIssuePage = new CreateIssueFromProject(utilDriver.getDriver(), project );
        createIssuePage.cancelIssue(issue, summary);
        page = new CreateIssueForm(utilDriver.getDriver());
        page.navigateToLastCreatedIssue();
        boolean created = page.isIssueCreated(longProjectName, issue, summary);
        Assertions.assertFalse(created);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/empty_summary_create_issue_from_project.csv")
    public void emptySummaryCreateIssueFromProject(String project) {
        createIssuePage = new CreateIssueFromProject(utilDriver.getDriver(), project );
        createIssuePage.clickCreateIssue();
        Assertions.assertTrue(createIssuePage.isError());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue/invalid_issuetype_create_issue_from_project.csv")
    public void invalidIssueCreateIssueFromProject(String project, String issue){
        createIssuePage = new CreateIssueFromProject(utilDriver.getDriver(), project );
        createIssuePage.clickCreateIssue();
        boolean hasError = createIssuePage.isValidIssue(issue);
        Assertions.assertFalse(hasError);
    }



}