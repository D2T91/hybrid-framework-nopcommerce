package pageObjects.orangeHRM.pim.employee;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.PageGenerator;
import pageUIs.orangeHRM.pim.employee.EmployeeTabsPageUI;

public class EmployeeTabs extends BasePage {
    private WebDriver driver;

    public EmployeeTabs(WebDriver driver) {
        this.driver = driver;
    }

    public PersonalDetailsPageObject openPersonalDetailsPage() {
        waitForElementClickable(driver, EmployeeTabsPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, EmployeeTabsPageUI.PERSONAL_DETAIL_LINK);
        return PageGenerator.getPageInstance(PersonalDetailsPageObject.class, driver);
    }

    public ContactDetailsPageObject openContactDetailsPage() {
        waitForElementClickable(driver, EmployeeTabsPageUI.CONTACTS_DETAIL_LINK);
        clickToElement(driver, EmployeeTabsPageUI.CONTACTS_DETAIL_LINK);
        return PageGenerator.getPageInstance(ContactDetailsPageObject.class, driver);
    }

    public EmergencyContactsPageObject openEmergencyContactsPage() {
        waitForElementClickable(driver, EmployeeTabsPageUI.EMERGENCY_CONTACTS_LINK);
        clickToElement(driver, EmployeeTabsPageUI.EMERGENCY_CONTACTS_LINK);
        return PageGenerator.getPageInstance(EmergencyContactsPageObject.class, driver);
    }

}
