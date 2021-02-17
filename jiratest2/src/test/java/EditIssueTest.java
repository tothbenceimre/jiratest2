import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.BrowseIssuePage;
import pages.DashboardPage;
import pages.LogoutPage;
import pages.MainPage;
import util.UtilDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditIssueTest {
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

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssue/every_issue_of_certain_project_is_available.csv", numLinesToSkip = 1)
    public void editIssueTest_everyIssueOfCertainProject_isEditable (String project, String id) {
        mainPage.navigateToIssue(project, id);

        assertTrue(browseIssuePage.issueIsEditable());
    }
}
