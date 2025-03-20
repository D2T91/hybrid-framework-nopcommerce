package pageObjects.nopCommerce.sideBar;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.sideBar.SideBarPageUI;

public class SidebarPageObject extends BasePage {
    private WebDriver driver;

    public SidebarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Open page tại Sidebar Page

    public AddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, SideBarPageUI.ADDRESS_LINK);
        clickToElement(driver, SideBarPageUI.ADDRESS_LINK);
        return PageGenerator.getPageInstance(AddressPageObject.class, driver);
    }

    public RewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, SideBarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, SideBarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getPageInstance(RewardPointPageObject.class, driver);
    }

    public OrderPageObject openOrderPage(WebDriver driver) {
        waitForElementClickable(driver, SideBarPageUI.ORDER_LINK);
        clickToElement(driver, SideBarPageUI.ORDER_LINK);
        return PageGenerator.getPageInstance(OrderPageObject.class, driver);
    }

    public CustomerInforPageObject openCustomerPage(WebDriver driver) {
        waitForElementClickable(driver, SideBarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, SideBarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getPageInstance(CustomerInforPageObject.class, driver);
    }
}
