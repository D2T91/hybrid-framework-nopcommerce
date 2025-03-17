package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.RewardPointPageUI;

public class RewardPointPageObject extends BasePage {
    WebDriver driver;

    public RewardPointPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
