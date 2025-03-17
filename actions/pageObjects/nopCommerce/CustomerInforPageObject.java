package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.CustomerPageUI;

public class CustomerInforPageObject extends BasePage {

    public CustomerInforPageObject(WebDriver driver) {
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
