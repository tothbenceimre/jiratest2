import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.BrowseIssuePage;
import pages.DashboardPage;
import pages.MainPage;
import util.UtilDriver;

public class BrowseIssuesTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    BrowseIssuePage browseIssuePage;

    @BeforeEach
    public void setup() {
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.loadpage();
        browseIssuePage = new BrowseIssuePage(utilDriver.getDriver());
    }

        @AfterEach
    public void teardown() {
        utilDriver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseissue/browse_issue_in_project.csv", numLinesToSkip = 1)
    public void browseIssueInProject(String project,String fullProjectName, String id){
        mainPage.navigateToIssue(project, id);
        boolean actual = browseIssuePage.isIssueInProject(fullProjectName, id);

        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource("MTP,Main Testing Project, MTP-2")
    public void nonexistentIssue(String project,String fullProjectName, String id){
        mainPage.navigateToIssue(project, id);
        boolean actual = browseIssuePage.isIssueInProject(fullProjectName, id);

        Assertions.assertFalse(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseissue/browse_issue.csv", numLinesToSkip = 1)
    public void browseIssueShowProjectCorrectly(String project,String fullProjectName, String id){
        mainPage.navigateToIssue(project, id);
        browseIssuePage.load();
        String actual = browseIssuePage.getProjectNameVal();

        Assertions.assertEquals(fullProjectName, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseissue/browse_issue.csv", numLinesToSkip = 1)
    public void browseIssueShowSummaryCorrectly(String project,String fullProjectName, String id, String issuetype, String summary){
        mainPage.navigateToIssue(project, id);
        browseIssuePage.load();
        String actual = browseIssuePage.getSummaryVal();

        Assertions.assertEquals(summary, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseissue/browse_issue.csv", numLinesToSkip = 1)
    public void browseIssueShowIssueCorrectly(String project,String fullProjectName, String id, String issuetype){
        mainPage.navigateToIssue(project, id);
        browseIssuePage.load();
        String actual = browseIssuePage.getIssueTypeVal();

        Assertions.assertEquals(issuetype, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseissue/browse_issue.csv", numLinesToSkip = 1)
    public void browseIssueShowProjectidCorrectly(String project,String fullProjectName, String id){
        mainPage.navigateToIssue(project, id);
        browseIssuePage.load();
        String actual = browseIssuePage.getId();

        Assertions.assertEquals(id, actual);
    }
}
