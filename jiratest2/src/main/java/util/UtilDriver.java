package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UtilDriver {
        private WebDriver driver;
        private String baseUrl;

        public UtilDriver() {
            driver = new ChromeDriver();
            this.baseUrl = "";
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

}
