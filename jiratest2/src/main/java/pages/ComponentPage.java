package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ComponentPage {
    WebDriver driver;
    @FindBy(xpath = "//form[@id='components-add__component']//input[@name= 'name']") WebElement componentName;
    @FindBy(xpath = "//*[@id='leadUserName-single-select']/input") WebElement lead;
    @FindBy(xpath = "//select[@name='leadUserName']") WebElement leadSelect;
    @FindBy(xpath = "//input[@name='description']") WebElement description;
    @FindBy(xpath = "//*[@id='assigneeType-single-select']/input") WebElement assignee;
    @FindBy(xpath = "//form[@id='components-add__component']//button") WebElement addButton;
    @FindBy(xpath = "//*[@id='components-table']/tbody[@class='items']/tr") WebElement tr;
    @FindBy(id = "component-name") WebElement newNameInput;

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

    public void fillAll(String name, String lead, String description, String assignee){
        fillName(name);
        fillLead(lead);
        fillDescription(description);
        fillAssignee(assignee);
    }

    public void fillMustHave(String name, String assignee){
        fillName(name);
        fillAssignee(assignee);
    }

    public void fillLead(String leadName){
        try {
            lead.sendKeys(leadName);
            Select select = new Select(leadSelect);
            select.selectByVisibleText(leadName);
        } catch (NoSuchElementException e){
            lead.sendKeys(Keys.TAB);
        }
    }

    public void fillDescription(String desc){
        fill(description, desc);
    }

    public void fillAssignee(String asg){
        fill(assignee, asg);
    }

    public void clickAddButton(){
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    public String getId(){
        waitForStale(tr);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(tr));
        return tr.getAttribute("data-component-id");
    }

    public void delete(String componentId){
        driver.navigate().refresh();
        WebElement elem = driver.findElement(By.xpath("//tr[@data-component-id='"+componentId+"']"));
        elem.findElement(By.xpath("//td[7]//a")).click();
        driver.findElement(By.id("deletecomponent_"+componentId)).click();
        driver.findElement(By.id("submit")).click();
    }
    public void edit(String componentId){
        driver.navigate().refresh();
        WebElement elem = driver.findElement(By.xpath("//tr[@data-component-id='"+componentId+"']"));
        elem.findElement(By.xpath("//td[7]//a")).click();
        driver.findElement(By.id("editcomponent_"+componentId)).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(newNameInput));
        newNameInput.sendKeys("new_name");
        driver.findElement(By.id("component-save-submit")).click();
    }

    public void waitForStale(WebElement element){
        try {
            driver.navigate().refresh();
            new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e){
            new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
        }
    }
}
