package pageObjects.nopCommerce.sideBar;

import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.sideBar.CustomerPageUI;

public class CustomerInforPageObject extends SidebarPageObject {

    public CustomerInforPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebDriver driver;

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailAddressTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.EMAIL_TEXTBOX, "value");
    }

}
