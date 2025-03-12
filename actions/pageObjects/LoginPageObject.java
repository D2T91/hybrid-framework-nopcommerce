package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public void enterToEmailTextbox(String valueToSend) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, valueToSend);
    }

    public void enterToPasswordTextbox(String valuePass) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, valuePass);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
