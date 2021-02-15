import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import pages.DashboardPage;
import org.junit.jupiter.params.ParameterizedTest;
import util.UtilDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    UtilDriver driver;
    DashboardPage dashboardPage;


    @BeforeEach
    public void setup() {
        driver = new UtilDriver();
        dashboardPage = new DashboardPage(driver.getDriver());
    }

    //    @AfterEach
//    public void teardown() {
//        driver.close();
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/successfullogin.csv")
    public void loginTest_successfulLogInFromDashBoard_isWorking (String username, String password) {

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/validusernameinvalidpassword.csv")
    public void loginTest_unSuccessfulLogInWithValidUsernameAndInvalidPassword_isWorking (String username, String password) {
        dashboardPage.login(username, password);

        assertTrue(dashboardPage.verifyUnsuccessfulLogin());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/invalidusername.csv")
    public void loginTest_unSuccessfulLogInWithInvalidUsername_isWorking (String username, String password) {
        dashboardPage.login(username, password);

        assertTrue(dashboardPage.verifyUnsuccessfulLogin());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/emptycredentials.csv")
    public void loginTest_unSuccessfulLogInWithEmptyCredentials_isWorking (String username, String password) {
        dashboardPage.login(username, password);

        assertTrue(dashboardPage.verifyUnsuccessfulLogin());
    }
}

