package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;

public class Level_05_Page_Generator_4 extends BaseTest {

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
        

        homePage = PageGenerator.getPageInstance(HomePageObject.class, driver);

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
        homePage = registerPage.clickToLogoutLink();
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

        customerPage = homePage.clickToMyAccountLink();

        Assert.assertEquals(customerPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerPage.getEmailAddressTextboxValue(),emailAddress);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
