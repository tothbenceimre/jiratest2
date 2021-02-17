import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.BeforeEach;
import pages.DashboardPage;
import org.junit.jupiter.params.ParameterizedTest;
import pages.LogoutPage;
import pages.MainPage;
import util.UtilDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {
    UtilDriver driver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    LogoutPage logoutPage;


    @BeforeEach
    public void setup() {
        driver = new UtilDriver();
        dashboardPage = new DashboardPage(driver.getDriver());
        mainPage = new MainPage(driver.getDriver());
        logoutPage = new LogoutPage(driver.getDriver());
    }

    //    @AfterEach
//    public void teardown() {
//        driver.close();
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/successful_login.csv", numLinesToSkip = 1)
    public void logoutTest_successfulLogOut_isWorking (String username, String password) {
        dashboardPage.login(username, password);
        mainPage.clickOnLogout();

        assertTrue(logoutPage.verifySuccessLogOut());
    }
}
