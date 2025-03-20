package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;

public class Level_04_Factory_Pattern extends BaseTest {

    WebDriver driver;
    String firstName, emailAddress, lastName, companyName, password;
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    CustomerInforPageObject customerPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName);

        // Áp dụng Factory Pattern để quản lý việc khởi tạo Page Object
        homePage = (UserHomePageObject) PageManager.getPage(driver, "HomePage");

        firstName = "Morten";
        lastName = "Murkit";
        companyName = "Fivebright";
        password = "Tung8545059@";
        emailAddress = "afc" + generateFakeNumber() + "@kype.com";

    }

    @Test
    public void TC_01_Register() {
        homePage.clickToRegisterLink();
 
        registerPage = (UserRegisterPageObject) PageManager.getPage(driver, "RegisterPage");

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLinkUserSite(driver);
        // Về lại trang HOME
        homePage = (UserHomePageObject) PageManager.getPage(driver, "HomePage");
        // Điểm kết thúc của testcase trên là bắt đầu của testcase dưới
    }

    @Test
    public void TC_02_Login() {
        homePage.clickToLoginLink();

        // Từ Home qua Login
        loginPage = (UserLoginPageObject) PageManager.getPage(driver, "LoginPage");

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);

        loginPage.clickToLoginButton();

        homePage = (UserHomePageObject) PageManager.getPage(driver, "HomePage");

    }

    @Test
    public void TC_03_MyAccount() {
        homePage.clickToMyAccountLinkUserSite(driver);

        customerPage = (CustomerInforPageObject) PageManager.getPage(driver, "CustomerInfor");

        Assert.assertEquals(customerPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerPage.getEmailAddressTextboxValue(),emailAddress);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
