package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePOJquery;
import pageObjects.jquery.PageGeneratorJQ;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.UserHomePageObject;
import pageObjects.nopCommerce.UserLoginPageObject;
import pageObjects.nopCommerce.UserRegisterPageObject;
import pageObjects.nopCommerce.sideBar.AddressPageObject;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import pageObjects.nopCommerce.sideBar.OrderPageObject;
import pageObjects.nopCommerce.sideBar.RewardPointPageObject;

import java.util.Random;

public class Level_11_DataTable extends BaseTest {

    WebDriver driver;
    HomePOJquery homePage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName);
        homePage = PageGeneratorJQ.getPageInstance(HomePOJquery.class, driver);

    }

    //@Test
    public void TC_01_Table01() {
        // Navigate to any page
        homePage.openPageByNumber("15");
        Assert.assertTrue(homePage.isPageNumberActived("15"));

        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageNumberActived("5"));

        homePage.openPageByNumber("12");
        Assert.assertTrue(homePage.isPageNumberActived("12"));

    }

    //@Test
    public void TC_02_Search_Table02() {
        // Enter value to header textbox
        homePage.enterToTextboxByHeaderName("Country", "Algeria");
        homePage.sleepInSecond(2000);

        // Check Row hiển thị đủ dữ liệu
        Assert.assertTrue(homePage.isRowDateValueDisplayed("283821", "Algeria", "295140", "578961"));
        homePage.refreshToPage(driver);

        homePage.enterToTextboxByHeaderName("Males", "407124");
        homePage.sleepInSecond(2000);
        homePage.refreshToPage(driver);

        homePage.enterToTextboxByHeaderName("Females", "777");
        homePage.sleepInSecond(2000);
        homePage.refreshToPage(driver);

        // Verify date in any row

    }

    //@Test
    public void TC_03_Delete_Edit() {

        // Tìm ra Key Duy Nhất trong table
        homePage.enterToTextboxByHeaderName("Country", "Algeria");
        homePage.sleepInSecond(2);

        // Click Delete Button
        homePage.deleteRowByCountryName("Algeria");

    }

    @Test
    public void TC_04_Action_By_Index() {

        // Có thể thao tác với bất kỳ 1 column/ row nào
        homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickToLoadDataButton();

        homePage.enterToTextboxByIndex("4", "Contact Person", "Paladin");
        homePage.sleepInSecond(3000);


        homePage.enterToTextboxByIndex("2", "Company", "MJ Kompany");
        homePage.sleepInSecond(3000);


        homePage.selectToDropdownByIndex("6", "Country", "Hong Kong");
        homePage.sleepInSecond(3000);


        homePage.checkToCheckBoxByIndex("6", "NPO?", true);


        homePage.checkToCheckBoxByIndex("5", "NPO?", false);


        homePage.clickToIconByIndex("8", "Move Up");


        homePage.clickToIconByIndex("6", "Remove");


        homePage.clickToIconByIndex("4", "Insert");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
