package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {

    private WebDriver driver;
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstNameValue) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.FIRST_NAME_TEXTBOX, firstNameValue);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String emailValue) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX, emailValue);
    }

    public void enterToPasswordTextbox(String passWord) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public void enterToConfirmTextbox(String confirmPass) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPass);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public HomePageObject clickToLogoutLink() {
        waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
        return PageGenerator.getPageInstance(HomePageObject.class, driver);
    }
}
