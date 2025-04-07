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

public class Level_19_Pattern_Object extends BaseTest {
    String browserName;

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

        this.browserName = browserName;

    }
    
    @Test
    public void TC_01_Register() {

        registerPage = homePage.clickToRegisterLink();

        // Patter Object, đặt các hàm ở BasePage
        registerPage.enterToTextboxByID(driver,"FirstName", firstName);
        registerPage.enterToTextboxByID(driver,"LastName", lastName);
        registerPage.enterToTextboxByID(driver,"Email", emailAddress);

        registerPage.clickToCheckboxByID(driver, "Newsletter");

        registerPage.enterToTextboxByID(driver,"Password", password);
        registerPage.enterToTextboxByID(driver,"ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "Register");

        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickToLogoutLinkUserSite(driver);

    }

    @Test
    public void TC_02_Login() {

        // Từ Home qua Login
        loginPage = homePage.clickToLoginLink();

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);

        //homePage = loginPage.clickToLoginButton();
        loginPage.clickToButtonByText(driver, "Log in");
        homePage = PageGenerator.getPageInstance(UserHomePageObject.class, driver);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

}
