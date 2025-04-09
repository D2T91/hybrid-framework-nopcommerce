package com.nopcommerce.users;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Level_22_Multiple_Environment extends BaseTest {
    WebDriver driver;

    @Parameters({"environment", "browser"})
    @BeforeClass
    public void beforeClass(String environment, String browserName) {

        driver = getBrowserDriver(environment, browserName);

    }
    
    @Test
    public void TC_01_Register() {
        BasePage.sleepInSecond(5000);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

}
