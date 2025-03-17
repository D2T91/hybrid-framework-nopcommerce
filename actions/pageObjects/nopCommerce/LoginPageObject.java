package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {

    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String valueToSend) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, valueToSend);
    }

    public void enterToPasswordTextbox(String valuePass) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, valuePass);
    }

    public HomePageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPageInstance(HomePageObject.class, driver);
    }

    public LoginPageObject login(String emailAddress, String passWord) {
        enterToEmailTextbox(emailAddress);               // Nhập email
        enterToPasswordTextbox(passWord);                // Nhập mật khẩu
        clickToLoginButton();                            // Nhấn nút đăng nhập
        return new LoginPageObject(driver);              // Trả về đối tượng LoginPageObject
    }
}
