import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.*;
import util.UtilDriver;

public class GlassComponentTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    ComponentPage componentPage;
    GlassComponentDocumentationBoxPage componentBox;
    GlassComponentPage componentTable;
    CreateIssueFromProject createIssueFromProject;
    CreateIssueForm createIssueForm;


    @BeforeEach
    public void setUp(){
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.loadpage();
        componentBox = new GlassComponentDocumentationBoxPage(utilDriver.getDriver());
        componentPage = new ComponentPage(utilDriver.getDriver());
        componentTable = new GlassComponentPage(utilDriver.getDriver());
    }

    @AfterEach
    public void tearDown(){
        utilDriver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component.csv", numLinesToSkip = 1)
    public void componentBox(String project, String name, String lead, String description, String assignee){
        mainPage.navigateToGlass(project);
        String before = componentBox.getComponentCount();
        mainPage.navigateToComponents(project);
        componentPage.fillMustHave(name, assignee);
        componentPage.clickAddButton();
        mainPage.navigateToGlass(project);
        String after = componentBox.getComponentCount();
        mainPage.navigateToComponents(project);
        componentPage.delete(componentPage.getId());
        Assertions.assertNotEquals(before, after);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component.csv", numLinesToSkip = 1)
    public void noComponentTableBefore(String project, String name, String lead, String description, String assignee){
        mainPage.navigateToGlass(project);
        boolean hasNoCompInTheBeginning = componentTable.hasNoComponentsContainer();
        mainPage.navigateToComponents(project);
        componentPage.fillAll(name, lead, description, assignee);
        componentPage.clickAddButton();
        String id = componentPage.getId();
        mainPage.navigateToGlass(project);
        componentTable = new GlassComponentPage(utilDriver.getDriver(), id);
        boolean hasNewComp = componentTable.hasComponentRow();
        mainPage.navigateToComponents(project);
        componentPage.delete(id);
        Assertions.assertTrue(hasNoCompInTheBeginning);
        Assertions.assertTrue(hasNewComp);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component.csv", numLinesToSkip = 1)
    public void hasComponentInTableBefore(String project, String name, String lead, String description, String assignee){
        String beforeId = addComp(project, "before"+name, lead, description, assignee);
        mainPage.navigateToGlass(project);
        int before = componentTable.getRowNum();
        String id = addComp(project, name, lead, description, assignee);
        mainPage.navigateToGlass(project);
        int after = componentTable.getRowNum();
        mainPage.navigateToComponents(project);
        componentPage.delete(beforeId);
        componentPage.delete(id);
        Assertions.assertEquals(before+1, after);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component_all.csv", numLinesToSkip = 1)
    public void allComponentCorrectInTable(String project, String name, String lead, String description, String assignee){
        String id = addComp(project, name, lead, description, assignee);
        mainPage.navigateToGlass(project);
        componentTable = new GlassComponentPage(utilDriver.getDriver(), id);
        boolean isAll = componentTable.allCorrect(name, lead, description, assignee, "0");
        mainPage.navigateToComponents(project);
        componentPage.delete(id);
        Assertions.assertTrue(isAll);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component_all.csv", numLinesToSkip = 1)
    public void editComponentCorrectInTable(String project, String name, String lead, String description, String assignee){
        String id = addComp(project, name, lead, description, assignee);
        mainPage.navigateToGlass(project);
        componentTable = new GlassComponentPage(utilDriver.getDriver(), id);
        String oldName = componentTable.getComponentName();
        mainPage.navigateToComponents(project);
        componentPage.edit(id);
        mainPage.navigateToGlass(project);
        mainPage.acceptAlert();
        componentTable = new GlassComponentPage(utilDriver.getDriver(), id);
        String newName = componentTable.getComponentName();
        mainPage.navigateToComponents(project);
        componentPage.delete(id);
        Assertions.assertNotEquals(oldName, newName);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component_all.csv", numLinesToSkip = 1)
    public void addIssueToComponentCorrectInTable(String project, String name, String lead, String description, String assignee){
        String id = addComp(project, name, lead, description, assignee);
        mainPage.navigateToGlass(project);
        componentTable = new GlassComponentPage(utilDriver.getDriver(), id);
        String oldIssueNum = componentTable.getIssueNum();
        mainPage.navigateToIssue(project, "75");
        createIssueFromProject = new CreateIssueFromProject(utilDriver.getDriver(), project);
        createIssueFromProject.addComponent(name);
        mainPage.navigateToGlass(project);
        componentTable = new GlassComponentPage(utilDriver.getDriver(), id);
        String newIssueNum = componentTable.getIssueNum();
        mainPage.navigateToComponents(project);
        componentPage.delete(id);

        Assertions.assertEquals(Integer.parseInt(oldIssueNum)+1, Integer.parseInt(newIssueNum));
    }

    private String addComp(String project, String name,String lead, String description, String assignee ){
        mainPage.navigateToComponents(project);
        componentPage.fillAll(name, lead, description, assignee);
        componentPage.clickAddButton();
        return componentPage.getId();
    }

}
