package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.CustomerInforPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

import java.util.concurrent.TimeUnit;

public class Level_05_Page_Generator extends BaseTest {

    WebDriver driver;
    String firstName, emailAddress, lastName, companyName, password;
    HomePageObject homePage;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    CustomerInforPageObject customerPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName);

        firstName = "Morten";
        lastName = "Murkit";
        companyName = "Fivebright";
        password = "Tung8545059@";
        emailAddress = "afc" + generateFakeNumber() + "@kype.com";


        homePage = new HomePageObject(driver);

    }

    @Test
    public void TC_01_Register() {
        homePage.clickToRegisterLink();
 
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLink();
        // Về lại trang HOME
        homePage = new HomePageObject(driver);
        // Điểm kết thúc của testcase trên là bắt đầu của testcase dưới
    }

    @Test
    public void TC_02_Login() {
        homePage.clickToLoginLink();

        // Từ Home qua Login
        loginPage = new LoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);

        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);

    }

    @Test
    public void TC_03_MyAccount() {
        homePage.clickToMyAccountLink();

        customerPage = new CustomerInforPageObject(driver);

        Assert.assertEquals(customerPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerPage.getEmailAddressTextboxValue(),emailAddress);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
