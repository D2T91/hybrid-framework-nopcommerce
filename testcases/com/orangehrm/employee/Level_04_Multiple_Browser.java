package com.orangehrm.employee;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.LoginPageObject;

public class Level_04_Multiple_Browser extends BaseTest {

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private String username, password;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName); // driver của ClassTest cần hứng dữ liệu driver từ hàm getBrowserDriver

//        switch (browserName) {
//            case "edge" :
//                driver = new EdgeDriver();
//                break;
//            case "firefox" :
//                driver = new FirefoxDriver();
//                break;
//            case "chrome" :
//                driver = new ChromeDriver();
//                break;
//            case "safari" :
//                driver = new SafariDriver();
//                break;
//            default:
//                throw new IllegalArgumentException("Browser Name is not valid");
//        }
//
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.get(urlValue);

        // Đưa tất cả đoạn code trên vào Class BaseTest
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
        quitBrowserDriver();
    }

}
