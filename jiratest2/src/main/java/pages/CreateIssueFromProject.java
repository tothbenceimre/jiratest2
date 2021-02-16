package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueFromProject {
    WebDriver driver;
    String urlStartWith ="https://jira-auto.codecool.metastage.net/projects/";
    String urlEndWith = "/issues";
    @FindBy(xpath = "//*[@class='inline-issue-create-container']//button[contains(., 'Create issue')]")
    WebElement createButton;
    @FindBy(xpath = "//*[@class='iic-widget__issue-type-selector']//button") WebElement issueTypeField;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//textarea") WebElement summaryField;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']") WebElement form;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//button[contains(.,'Cancel')]") WebElement cancelButton;
    String errorXpath = "//form[@class='iic-widget__form aui']//textarea[contains(@class,'iic-error')]";
    String issuePathmiddle = "//a[text()= ";
    String issuePathStart = "//form[@class='iic-widget__form aui']";
    String issuePathEnd = "]";


    public CreateIssueFromProject(WebDriver driver, String projectUrl) {
        this.driver = driver;
        driver.navigate().to(urlStartWith + projectUrl + urlEndWith);
        PageFactory.initElements(this.driver, this);
    }

    public void clickCancel(){cancelButton.click();}

    public void clickcreateIssue(){ createButton.click(); }

    public void cancelIssue(String issue, String summary){
        clickcreateIssue();
        chooseIssue(issue);
        fillSummary(summary);
        clickCancel();
    }

    public void createIssue(String issue, String summary){
        clickcreateIssue();
        chooseIssue(issue);
        fillAndSubmitSummary(summary);
    }

    public boolean isErrorPresent(){
        try {
            driver.findElement(By.xpath(errorXpath));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isValidSummary(String sum){
        return sum != null;
    }

    public boolean isValidIssue(String issue){
        try {
            driver.findElement(By.xpath(setIssueXpath(issue))).click();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }
    public void fillSummary(String summary){
        summaryField.sendKeys(summary);
    }

    public void fillAndSubmitSummary(String summary) {
        summaryField.sendKeys(summary);
        summaryField.sendKeys(Keys.ENTER);
    }

    public boolean isError(){
        fillAndSubmitSummary("");
        return isErrorPresent();
    }

    public String setIssueXpath(String issue){
        this.issuePathmiddle +="'"+ issue +"'";
        return issuePathStart+issuePathmiddle+issuePathEnd;
    }

    public void chooseIssue(String issue) {
        issueTypeField.click();
        String xpath = setIssueXpath(issue);
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

}
