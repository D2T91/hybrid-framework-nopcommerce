package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.AddressPageUI;

public class AddressPageObject extends BasePage {
    WebDriver driver;

    public AddressPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
