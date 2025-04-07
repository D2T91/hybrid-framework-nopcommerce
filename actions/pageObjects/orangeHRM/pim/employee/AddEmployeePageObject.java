package pageObjects.orangeHRM.pim.employee;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.PageGenerator;
import pageUIs.orangeHRM.pim.employee.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToFirstNameTextBox(String valueFirstName) {
        waitForElementClickable(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, valueFirstName);
    }

    public void enterToLastNameTextBox(String valueLastName) {
        waitForElementClickable(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, valueLastName);
    }

    public String getEmployeeID() {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementAttribute(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX, "_value");
    }

    public PersonalDetailsPageObject clickToSaveButtonAtEmployeeContainer() {
        waitForElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPageInstance(PersonalDetailsPageObject.class, driver);
    }
}
