package com.facebook.cases;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.LoginFBPageObject;
import pageObjects.facebook.PageGenerator;

public class Level_18_Undisplayed extends BaseTest {

    WebDriver driver;
    LoginFBPageObject loginFBPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName);
        loginFBPage = PageGenerator.getPageInstance(LoginFBPageObject.class, driver);

        loginFBPage.clickToNewAccountButton();

    }

    @Test
    public void TC_01_Element_Undisplayed() {
        // Nhập dữ liệu vào email address textbox
        loginFBPage.enterToEmailAddressTextbox("dam@gmail.com");

        // Case 1 - Verify Confirm Email textbox is displayed
        Assert.assertTrue(loginFBPage.isConfirmEmailTextboxDisplayed());


        // Case 2 - Verify Confirm Email textbox is not displayed (present)
        // Kỳ vọng element is not displayed > Ko thể dùng waitForElementVisible
        loginFBPage.enterToEmailAddressTextbox("");
        Assert.assertFalse(loginFBPage.isConfirmEmailTextboxDisplayed());


        // Case 3 - Verify Confirm Email textbox is not displayed (non-present)
        loginFBPage.clickToCloseIcon();
        Assert.assertTrue(loginFBPage.isConfirmEmailTextboxUndisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
