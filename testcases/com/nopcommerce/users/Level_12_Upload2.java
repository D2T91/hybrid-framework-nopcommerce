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

public class Level_12_Upload2 extends BaseTest {

    WebDriver driver;
    HomePOJquery homePage;
    String danang, halong, thainguyen;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {

        driver = getBrowserDriver(urlValue, browserName);
        homePage = PageGeneratorJQ.getPageInstance(HomePOJquery.class, driver);
        danang = "Da Nang.jpg";
        halong = "Ha Long.jpg";
        thainguyen = "Thai Nguyen.jpg";

    }

    @Test
    public void TC_01_UpLoad() {
        // Lấy ra đúng đường dẫn File
        // Tất cả các Os : Window/ Mac đều chạy được

        // Upload 1 lần 1 file > Dùng 1 hàm
        homePage.upLoadMultipleFiles(driver, danang);
        homePage.sleepInSecond(3000);
        homePage.refreshToPage(driver);

        // Upload 1 lần nhiều file > Dùng 1 hàm
        homePage.upLoadMultipleFiles(driver, danang, halong, thainguyen);
        homePage.sleepInSecond(3000);

        // Verify Load File lên
        Assert.assertTrue(homePage.isFileLoadedByName(danang));
        Assert.assertTrue(homePage.isFileLoadedByName(halong));

        // Click to Upload All Button
        homePage.clickToUploadAllButton(driver);

    }

    @Test
    public void TC_02_Search_Table02() {


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
