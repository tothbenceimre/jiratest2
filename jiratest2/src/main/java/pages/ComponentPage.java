package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComponentPage {
    WebDriver driver;
    @FindBy(xpath = "//form[@id='components-add__component']//input[@name= 'name']") WebElement componentName;
    @FindBy(xpath = "//*[@id='leadUserName-single-select']/input") WebElement lead;
    @FindBy(xpath = "//input[@name='description']") WebElement description;
    @FindBy(xpath = "//*[@id='assigneeType-single-select']/input") WebElement assignee;
    @FindBy(xpath = "//form[@id='components-add__component']//button") WebElement addButton;
    @FindBy(xpath = "//*[@id='content-container']") WebElement container;
    @FindBy(xpath = "//*[@id='components-table']/tbody[@class='items']/tr[1]") WebElement tr;

    public ComponentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fill(WebElement element, String msg){
        element.sendKeys(Keys.CONTROL+"a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(msg);
        element.sendKeys(Keys.TAB);
    }

    public void fillName(String name){
        fill(componentName, name);
    }

    public void fillLead(String leadName){
        fill(lead, leadName);
    }

    public void fillDescription(String desc){
        fill(description, desc);
    }

    public void fillAssignee(String asg){
        fill(assignee, asg);
    }

    public void clickAddButton(){
//        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(addButton));
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    public String getId(){
//        waitForStale(tr);
        return tr.getAttribute("data-component-id");
    }

    public void delete(String componentId){
        tr.findElement(By.xpath("//td[7]//a")).click();
        driver.findElement(By.id("deletecomponent_"+componentId)).click();
        driver.findElement(By.id("submit")).click();
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public void waitForStale(WebElement element){
        try {
            element.click();
        } catch (StaleElementReferenceException e){
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
        }
    }
}
