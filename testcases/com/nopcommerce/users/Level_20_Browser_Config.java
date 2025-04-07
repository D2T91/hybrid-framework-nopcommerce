package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Level_20_Browser_Config extends BaseTest {
    WebDriver driver;

    @Parameters({"urlUser", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        // Run headless mode
        driver = getBrowserDriver(urlValue, browserName);

    }
    
    @Test
    public void TC_01_Register() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

}
