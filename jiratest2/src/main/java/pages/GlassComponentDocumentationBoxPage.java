package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlassComponentDocumentationBoxPage {
    WebDriver driver;
    @FindBy(xpath = "//*[@id='glass-general-panel']//b[contains(., 'component(s)')]")
    WebElement componentCount;

    public GlassComponentDocumentationBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getComponentCount() {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(componentCount));
        return componentCount.getText();
    }
}
