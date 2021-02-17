import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.*;
import util.UtilDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditIssueTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    BrowseIssuePage browseIssuePage;
    CreateIssueForm page;
    CreateIssueFromProject createIssuePage;
    EditIssueForm editIssueForm;


    @BeforeEach
    public void setup() {
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.loadpage();
        browseIssuePage = new BrowseIssuePage(utilDriver.getDriver());
        editIssueForm = new EditIssueForm(utilDriver.getDriver());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssue/every_issue_of_certain_project_is_available.csv", numLinesToSkip = 1)
    public void editIssueTest_everyIssueOfCertainProject_isEditable (String project, String id) {
        mainPage.navigateToIssue(project, id);

        assertTrue(browseIssuePage.issueIsEditable());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssue/editing_certain_issue.csv", numLinesToSkip = 1)
    public void editIssueTest_editingCertainIssue_isWorking (String project, String issue, String summary, String longProjectName, String newSummary) {
        createIssuePage = new CreateIssueFromProject(utilDriver.getDriver(), project );
        createIssuePage.createIssue(issue, summary);
        page = new CreateIssueForm(utilDriver.getDriver());
        page.navigateToLastCreatedIssue();
        browseIssuePage.clickOnEditIssue();
        editIssueForm.editSummaryField(newSummary);
        boolean edited = page.isIssueCreated(longProjectName, issue, newSummary);
        page.deleteCreatedIssue();

        Assertions.assertTrue(edited);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "editIssue/summary_left_empty.csv")
    public void editIssueTest_summaryLeftEmptyWhileEditing_isNotWorking (String project, String issue, String summary, String longProjectName, String newSummary) {
        createIssuePage = new CreateIssueFromProject(utilDriver.getDriver(), project );
        createIssuePage.createIssue(issue, summary);
        page = new CreateIssueForm(utilDriver.getDriver());
        page.navigateToLastCreatedIssue();
        browseIssuePage.clickOnEditIssue();
        editIssueForm.editSummaryField(newSummary);
        boolean edited = page.isIssueCreated(longProjectName, issue, newSummary);
        page.deleteCreatedIssue();

        Assertions.assertTrue(edited);
    }
}
