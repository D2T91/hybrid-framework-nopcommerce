package pageObjects.nopCommerce;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.UserHomePageUI;

public class UserHomePageObject extends BasePage {

    private WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open Register Page")
    public UserRegisterPageObject clickToRegisterLink() {

        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getPageInstance(UserRegisterPageObject.class, driver);
    }

    @Step("Open Login Page")
    public UserLoginPageObject clickToLoginLink() {

        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGenerator.getPageInstance(UserLoginPageObject.class, driver);
    }
}
