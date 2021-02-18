package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ReleasePage {
    WebDriver driver;
    @FindBy(id = "aui-uid-2") WebElement versionsTab;
    @FindBy(xpath = "//form[@id='releases-add__version']//input[@name='name']") WebElement nameInput;
    @FindBy(xpath = "//form[@id='releases-add__version']//input[@name='description']") WebElement descriptionInput;
    @FindBy(xpath = "//button[text()='Add']") WebElement addButton;
    @FindBy(xpath = "//table[@id='versions-table']//td[@class='dynamic-table__actions']//div/a") WebElement operations;
    @FindBy(xpath = "//table[@id='versions-table']//td[@class='dynamic-table__actions']//a[@class='version-edit-dialog']") WebElement edit;
    @FindBy(xpath = "//table[@id='versions-table']//td[@class='dynamic-table__actions']//a[@class='project-config-operations-release']") WebElement release;
    @FindBy(id = "version-name") WebElement nameEdit;
    @FindBy(id = "version-description") WebElement descriptionEdit;
    @FindBy(id = "version-save-submit") WebElement editSave;
    @FindBy(id = "project-config-version-release-form-submit") WebElement releaseSubmit;
    @FindBy(xpath = "//table[@id='versions-table']/tbody[@class='items ui-sortable']/tr[1]") WebElement currentRelease;



    public ReleasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillNameInputField (String releaseName) {
        nameInput.sendKeys(releaseName);
    }

    public void fillDescriptionField (String description) {
        descriptionInput.sendKeys(description);
    }

    public void clickOnAddButton () {
        addButton.click();
    }

    public void addNewRelease (String releaseName, String description) {
        fillNameInputField(releaseName);
        fillDescriptionField(description);
        clickOnAddButton();
    }

    public void clickOnOperations () {
        operations.click();
    }

    public void clickOnEdit () {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        edit.click();
    }

    public void fillNameInEditForm (String name) {
        nameEdit.sendKeys(name);
    }

    public void fillDescriptionInEditForm (String description) {
        descriptionEdit.sendKeys(description);
    }

    public void clickOnSaveButton () {
        editSave.click();
    }

    public void editRelease (String name, String description) {
        fillNameInEditForm(name);
        fillDescriptionInEditForm(description);
        clickOnSaveButton();
    }

    public void clickOnRelease() {
        release.click();
    }

    public void clickOnReleaseSubmit () {
        releaseSubmit.click();
    }

    public void release () {
        clickOnRelease();
        clickOnReleaseSubmit();
    }

    public String getId()  {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return currentRelease.getAttribute("data-component-id");
    }
}
