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

public class Level_7_Page_Navigation extends BaseTest {

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

        // Về lại trang HOME
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
    public void TC_04_SwitchPage() {

        // Customer > Address
        addressPage = customerPage.openAddressPage(driver);

        // Address > Reward Point
        rewardPointPage = addressPage.openRewardPointPage(driver);

        // Reward Point > Order
        orderPage = rewardPointPage.openOrderPage(driver);

        // Order > Customer
        customerPage = orderPage.openCustomerPage(driver);

        // Customer > Reward Point
        rewardPointPage = customerPage.openRewardPointPage(driver);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
