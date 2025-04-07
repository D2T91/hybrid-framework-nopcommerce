package pageObjects.orangeHRM.pim.employee;

import org.openqa.selenium.WebDriver;

public class EmergencyContactsPageObject extends EmployeeTabs {
    private WebDriver driver;

    public EmergencyContactsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
