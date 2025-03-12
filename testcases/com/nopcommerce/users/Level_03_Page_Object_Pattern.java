package com.nopcommerce.users;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_Page_Object_Pattern extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(Level_03_Page_Object_Pattern.class);
    String projectPath = System.getProperty("user.dir");

    WebDriver driver;
    String firstName, emailAddress, lastName, companyName, password;
    HomePageObject homePage;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    CustomerInforPageObject customerPage;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        firstName = "Morten";
        lastName = "Murkit";
        companyName = "Fivebright";
        password = "Tung8545059@";
        emailAddress = "afc" + generateFakeNumber() + "@kype.com";

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

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
