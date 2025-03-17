package com.orangehrm.employee;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.LoginPageObject;

import java.util.concurrent.TimeUnit;

public class Level_05_Test extends BaseTest {

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;


    @BeforeClass
    public void beforeClass() {

        getBrowserDriverTest("https://opensource-demo.orangehrmlive.com/", "firefox");
        username = "Admin";
        password = "admin123";

        loginPage = new LoginPageObject(driver);
    }

    @Test
    public void TC_01_Login() {

        loginPage.enterToUserNametextbox(username);
        loginPage.enterToPasswordtextbox(password);
        loginPage.clickToLoginButton();

        dashboardPage = new DashboardPageObject(driver);

    }

    @Test
    public void TC_02_New_Employee() {


    }

    @Test
    public void TC_03_Personal_Detail() {


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private String username, password;

    protected WebDriver getBrowserDriverTest(String urlValue, String browserName) {
        switch (browserName) {
            case "edge" :
                driver = new EdgeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "safari" :
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser Name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(urlValue);
        return driver;
    }
}
