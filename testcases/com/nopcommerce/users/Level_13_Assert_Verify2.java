package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.UserHomePageObject;
import pageObjects.nopCommerce.UserLoginPageObject;
import pageObjects.nopCommerce.UserRegisterPageObject;
import pageObjects.nopCommerce.sideBar.AddressPageObject;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import pageObjects.nopCommerce.sideBar.OrderPageObject;
import pageObjects.nopCommerce.sideBar.RewardPointPageObject;

public class Level_13_Assert_Verify2 extends BaseTest {

    WebDriver driver;
    String firstName, emailAddress, lastName, companyName, password;
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    CustomerInforPageObject customerPage;
    AddressPageObject addressPage;
    RewardPointPageObject rewardPointPage;
    OrderPageObject orderPage;


    @Parameters({"urlUser", "browser"})
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

        // Use the verifyEquals function
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");

        homePage = registerPage.clickToLogoutLinkUserSite(driver);

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

        verifyEquals(customerPage.getFirstNameTextboxValue(),firstName);
        verifyEquals(customerPage.getLastNameTextboxValue(),lastName);
        verifyEquals(customerPage.getEmailAddressTextboxValue(),emailAddress);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
