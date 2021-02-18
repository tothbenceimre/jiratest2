import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.BeforeEach;
import pages.DashboardPage;
import org.junit.jupiter.params.ParameterizedTest;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import util.UtilDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    UtilDriver driver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    ProfilePage profilePage;
    LoginPage loginPage;


    @BeforeEach
    public void setup() {
        driver = new UtilDriver();
        dashboardPage = new DashboardPage(driver.getDriver());
        mainPage = new MainPage(driver.getDriver());
        profilePage = new ProfilePage(driver.getDriver());
        loginPage = new LoginPage(driver.getDriver());
    }

    //    @AfterEach
//    public void teardown() {
//        driver.close();
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/successful_login.csv", numLinesToSkip = 1)
    public void loginTest_successfulLogInFromDashBoard_isWorking (String username, String password) {
        dashboardPage.login(username, password);
        mainPage.navigateToProfile();

        assertTrue(profilePage.verifySuccessfulLogin(username));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/successful_login.csv", numLinesToSkip = 1)
    public void loginTest_successfulLogInFromLoginPage_isWorking (String username, String password) {
        dashboardPage.navigateToLoginPage();
        loginPage.login(username, password);
        mainPage.navigateToProfile();

        assertTrue(profilePage.verifySuccessfulLogin(username));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/valid_username_invalid_password.csv", numLinesToSkip = 1)
    public void loginTest_unSuccessfulLogInWithValidUsernameAndInvalidPassword_isWorking (String username, String password) {
        dashboardPage.login(username, password);

        assertTrue(dashboardPage.verifyUnsuccessfulLogin());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/invalid_username.csv", numLinesToSkip = 1)
    public void loginTest_unSuccessfulLogInWithInvalidUsername_isWorking (String username, String password) {
        dashboardPage.login(username, password);

        assertTrue(dashboardPage.verifyUnsuccessfulLogin());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login/empty_credentials.csv", numLinesToSkip = 1)
    public void loginTest_unSuccessfulLogInWithEmptyCredentials_isWorking (String username, String password) {
        dashboardPage.login(username, password);

        assertTrue(dashboardPage.verifyUnsuccessfulLogin());
    }
}

