package pageObjects.orangeHRM.pim.employee;

import org.openqa.selenium.WebDriver;

public class ContactDetailsPageObject extends EmployeeTabs {
    private WebDriver driver;

    public ContactDetailsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
