package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GlassComponentPage {
    WebDriver driver;
    @FindBy(id = "glass-general-components-panel") WebElement componentsContainer;
    @FindBy(xpath = "//table[@id ='components-table']/tbody/tr")
    List<WebElement> rowsInTable;
    @FindBy(id = "no-components-container") WebElement noComponentsContainer;
    WebElement componentRow;


    public GlassComponentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean hasNoComponentsContainer() {
        return noComponentsContainer.isDisplayed();
    }

    public int getRowNum(){
        return rowsInTable.size();
    }

    public GlassComponentPage(WebDriver driver, String id) {
        this.driver = driver;
        this.componentRow = driver.findElement(By.xpath("//tr[@data-component-id='" + id +"']"));
    }

    public boolean allCorrect(String name, String lead, String desc, String assignee, String issueNum){
        return getComponentName().equals(name) && getIssueNum().equals(issueNum) &&
                lead.contains(getLead()) && desc.contains(getDescription()) &&
                getAssignee().toLowerCase().contains(assignee.toLowerCase());
    }

    public boolean hasComponentRow(){
        return componentRow.isDisplayed();
    }

    public String getComponentName(){
//        return componentRow.findElement(By.xpath("//*[@class='components-table__name']//a")).getText();
        return componentRow.findElement(By.xpath("//td[1]//a")).getText();
    }

    public String getIssueNum(){
        return componentRow.findElement(By.xpath("//td[@class='components-table__issues-count']//a")).getText();
    }

    public String getLead(){
//        return componentRow.findElement(By.xpath("//*[@class='components-table__lead']//a")).getText();
        return componentRow.findElement(By.xpath("//td[3]")).getText();
    }

    public String getAssignee(){
//        return componentRow.findElement(By.xpath("//*[@class='components-table__assignee']")).getText();
        return componentRow.findElement(By.xpath("//td[4]")).getText();
    }

    public String getDescription(){
//        return componentRow.findElement(By.xpath("//*[@class='glass-components-table__description']/div")).getText();
        return componentRow.findElement(By.xpath("//td[5]//div")).getText();
    }

}
