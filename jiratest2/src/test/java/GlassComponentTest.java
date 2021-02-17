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

    @BeforeEach
    public void setUp(){
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.loadpage();
        componentBox = new GlassComponentDocumentationBoxPage(utilDriver.getDriver());
        componentPage = new ComponentPage(utilDriver.getDriver());
    }

    @AfterEach
    public void tearDown(){
        utilDriver.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/glasscomponent/glass_component_box.csv", numLinesToSkip = 1)
    public void componentBox(String project, String name, String lead, String description, String assignee){
        mainPage.navigateToGlass(project);
        String before = componentBox.getComponentCount();
        mainPage.navigateToComponents(project);
        componentPage.fillName(name);
        componentPage.fillAssignee(assignee);
        componentPage.clickAddButton();
        mainPage.navigateToGlass(project);
        String after = componentBox.getComponentCount();
        mainPage.navigateToComponents(project);
        componentPage.delete(componentPage.getId());
        Assertions.assertNotEquals(before, after);
    }
}
