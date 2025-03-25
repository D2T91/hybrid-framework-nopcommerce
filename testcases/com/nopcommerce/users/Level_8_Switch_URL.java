package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.AdminProductPageObject;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.AddressPageObject;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import pageObjects.nopCommerce.sideBar.OrderPageObject;
import pageObjects.nopCommerce.sideBar.RewardPointPageObject;

public class Level_8_Switch_URL extends BaseTest {

    WebDriver driver;
    String firstName, emailAddress, lastName, companyName, password;
    UserHomePageObject userHomePage;
    UserLoginPageObject userLoginPage;
    UserRegisterPageObject userRegisterPage;
    CustomerInforPageObject userCustomerPage;
    AdminLoginPageObject adminLoginPage;
    AdminDashboardPageObject adminDashboardPage;
    AdminProductPageObject adminProductPage;
    AddressPageObject addressPage;
    RewardPointPageObject rewardPointPage;
    OrderPageObject orderPage;
    String userUrl, adminUrl;


    @Parameters({"urlUser", "urlAdmin", "browser"})
    @BeforeClass
    public void beforeClass(String userUrl, String adminUrl, String browserName) {
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        driver = getBrowserDriver(userUrl, browserName);

        firstName = "Morten";
        lastName = "Murkit";
        companyName = "Fivebright";
        password = "Tung8545059@";
        emailAddress = "afc" + generateFakeNumber() + "@kype.com";


        userHomePage = PageGenerator.getPageInstance(UserHomePageObject.class, driver);

        // Pre-Condition
        userRegisterPage = userHomePage.clickToRegisterLink();

        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.enterToEmailTextbox(emailAddress);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmTextbox(password);

        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        userCustomerPage = userRegisterPage.clickToMyAccountLinkUserSite(driver);
        // Logout
        userHomePage = userCustomerPage.clickToLogoutLinkUserSite(driver);

    }

    @Test(description = "Login")
    public void TC_01_Required_Login() {
        // User site > Admin site

        // Switch to admin site - Change order status to complete
        adminLoginPage = userHomePage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToEmailTextbox("admin@yourstore.com");
        adminLoginPage.enterToPasswordTextbox("admin");
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        // Wait cho Page đã Load xong
        Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));

        // Logout
        adminLoginPage = adminDashboardPage.clickToLogoutLinkAdminSite(driver);

        // Admin site > User site
        userHomePage = adminLoginPage.openUserSite(driver, userUrl);

    }

    @Test(description = "Dont Login")
    public void TC_02_DoNot_Required_Login() {
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.enterToEmailTextbox(emailAddress);
        userLoginPage.enterToPasswordTextbox(password);
        userHomePage = userLoginPage.clickToLoginButton();

        userCustomerPage = userHomePage.clickToMyAccountLinkUserSite(driver);

        // User site > Admin site
        adminLoginPage = userCustomerPage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToEmailTextbox("admin@yourstore.com");
        adminLoginPage.enterToPasswordTextbox("admin");
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        // Wait cho Page đã Load xong
        Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));

        adminProductPage = adminDashboardPage.openAdminProductPage(driver);

        // Admin site > User site
        userHomePage = adminProductPage.openUserSite(driver, userUrl);

        userHomePage.openAdminSite(driver, adminUrl);
        // Do tài khoản admin đã login rồi, nên lúc này userHomePage.openAdminSite(driver, adminUrl) sẽ mở luôn trang adminDashboardPage
        // phải tách thành 2 Step
        adminDashboardPage = PageGenerator.getPageInstance(AdminDashboardPageObject.class, driver);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
