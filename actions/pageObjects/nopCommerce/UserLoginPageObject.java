package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {

    private WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String valueToSend) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, valueToSend);
    }

    public void enterToPasswordTextbox(String valuePass) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, valuePass);
    }

    public UserHomePageObject clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPageInstance(UserHomePageObject.class, driver);
    }

    public UserLoginPageObject login(String emailAddress, String passWord) {
        enterToEmailTextbox(emailAddress);               // Nhập email
        enterToPasswordTextbox(passWord);                // Nhập mật khẩu
        clickToLoginButton();                            // Nhấn nút đăng nhập
        return new UserLoginPageObject(driver);              // Trả về đối tượng LoginPageObject
    }
}
