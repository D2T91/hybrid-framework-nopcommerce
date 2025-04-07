package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginFBPageIU;

public class LoginFBPageObject extends BasePage {
    WebDriver driver;

    public LoginFBPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToNewAccountButton() {
        waitForElementClickable(driver, LoginFBPageIU.CREAT_NEW_ACCOUNT_BUTTON);
        clickToElement(driver, LoginFBPageIU.CREAT_NEW_ACCOUNT_BUTTON);

    }

    public void enterToEmailAddressTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginFBPageIU.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, LoginFBPageIU.EMAIL_ADDRESS_TEXTBOX, emailAddress);

    }

    public boolean isConfirmEmailTextboxDisplayed() {
        //waitForElementVisible(driver, LoginFBPageIU.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginFBPageIU.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public boolean isConfirmEmailTextboxUndisplayed() {
        //waitForElementVisible(driver, LoginFBPageIU.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementUndisplayed(driver, LoginFBPageIU.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public void clickToCloseIcon() {
        waitForElementClickable(driver, LoginFBPageIU.CLOSE_ICON);
        clickToElement(driver, LoginFBPageIU.CLOSE_ICON);
    }

    // Tạo 1 hàm dùng cho tất cả trường hợp
    // Có hiển thị
    // Ko hiển thị/ ko trên UI
    // ko hiển thị/ ko có trong HTML
    // Test chạy nhanh > isConfirmEmailTextboxUndisplayed
}
