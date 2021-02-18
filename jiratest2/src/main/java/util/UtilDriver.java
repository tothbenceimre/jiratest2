package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UtilDriver {
        private WebDriver driver;
        private String baseUrl;

        public UtilDriver() {
            driver = new ChromeDriver();
            this.baseUrl = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
            driver.get(baseUrl);
            new WebDriverWait(driver, 40).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
        }

        public WebDriver getDriver() {
            return driver;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void close(){
            driver.close();
        }

        public void navigationToCertainProject (String name)  {
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 10000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='header-details-user-fullname']/span/span/img"))));
            driver.get("https://jira-auto.codecool.metastage.net/projects/" + name + "/summary");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        public void navigationToCertainProjectGlassProject (String key) {
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 10000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='header-details-user-fullname']/span/span/img"))));
            driver.get("https://jira-auto.codecool.metastage.net/projects/" + key + "?selectedItem=com.codecanvas.glass:glass");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

    public void navigationToCertainProjectReleasePage (String key) {
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='header-details-user-fullname']/span/span/img"))));
        driver.get("https://jira-auto.codecool.metastage.net/projects/" + key + "?selectedItem=com.atlassian.jira.jira-projects-plugin%3Arelease-page&status=no-filter");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
