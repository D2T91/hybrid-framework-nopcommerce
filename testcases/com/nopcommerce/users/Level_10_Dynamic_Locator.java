package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.AddressPageObject;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import pageObjects.nopCommerce.sideBar.OrderPageObject;
import pageObjects.nopCommerce.sideBar.RewardPointPageObject;

public class Level_10_Dynamic_Locator extends BaseTest {

    WebDriver driver;
    String firstName, emailAddress, lastName, companyName, password;
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    CustomerInforPageObject customerPage;
    AddressPageObject addressPage;
    RewardPointPageObject rewardPointPage;
    OrderPageObject orderPage;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName);

        firstName = "Morten";
        lastName = "Murkit";
        companyName = "Fivebright";
        password = "Tung8545059@";
        emailAddress = "afc" + generateFakeNumber() + "@kype.com";

        homePage = PageGenerator.getPageInstance(UserHomePageObject.class, driver);

    }

    @Test
    public void TC_01_Register() {

        registerPage = homePage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        // Click logout về lại trang HOME
        homePage = registerPage.clickToLogoutLinkUserSite(driver);
        // Điểm kết thúc của testcase trên là bắt đầu của testcase dưới
    }

    @Test
    public void TC_02_Login() {

        // Từ Home qua Login
        loginPage = homePage.clickToLoginLink();

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);

        homePage = loginPage.clickToLoginButton();

    }

    @Test
    public void TC_03_MyAccount() {

        customerPage = homePage.clickToMyAccountLinkUserSite(driver);

        Assert.assertEquals(customerPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerPage.getEmailAddressTextboxValue(),emailAddress);

    }

    @Test
    public void TC_04_Dynamic_Page() {

        // Customer > Address
        addressPage = (AddressPageObject) customerPage.openSidebarLinkByPageName("Addresses"); // Ép kiểu

        // Address > Reward Point
        rewardPointPage = (RewardPointPageObject) addressPage.openSidebarLinkByPageName("Reward points"); // Ép kiểu

        // Reward Point > Order
        orderPage = (OrderPageObject) rewardPointPage.openSidebarLinkByPageName("Orders");

        // Order > Customer
        customerPage = (CustomerInforPageObject) orderPage.openSidebarLinkByPageName("Customer info");

        // Customer > Reward Point
        rewardPointPage = (RewardPointPageObject) customerPage.openSidebarLinkByPageName("Reward points");

        addressPage = (AddressPageObject) rewardPointPage.openSidebarLinkByPageName("Addresses");

    }

    @Test
    public void TC_05_Dynamic_Page() {

        // Dùng Cách 2
        // Address > Reward Point
        addressPage.openSidebarLinkByPageNames("Reward points");
        rewardPointPage = PageGenerator.getPageInstance(RewardPointPageObject.class, driver);

        // Reward Point > Order
        rewardPointPage.openSidebarLinkByPageNames("Orders");
        orderPage = PageGenerator.getPageInstance(OrderPageObject.class, driver);

        // Order > Customer
        orderPage.openSidebarLinkByPageNames("Customer info");
        customerPage = PageGenerator.getPageInstance(CustomerInforPageObject.class, driver);

        // Customer > Reward Point
        rewardPointPage = (RewardPointPageObject) customerPage.openSidebarLinkByPageName("Reward points");

        addressPage = (AddressPageObject) rewardPointPage.openSidebarLinkByPageName("Addresses");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
