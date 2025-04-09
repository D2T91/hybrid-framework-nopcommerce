package pageObjects.saucelabs;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelabs.InventoryPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPageObject extends BasePage {
    private WebDriver driver;

    public InventoryPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortDropdown(String sortItem) {
        waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
        selectItemDropdown(driver, InventoryPageUI.SORT_DROPDOWN, sortItem);
        sleepInSecond(3000);
    }

    public boolean isNameSortAscending() {
        List<WebElement> productNameElement = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME);

        // Khai báo 1 List String để lưu trữ List productName
        List<String> productNameText = new ArrayList<String>();

        // Dùng vòng lặp lấy hết productName và thêm vào productNameText
        for (WebElement productName : productNameElement) {
            String text = productName.getText();
            productNameText.add(text);
        }
        // Clone toàn bộ text từ productNameText qua 1 list mới
        List<String> productNameTextClone = new ArrayList<>(productNameText);
        // Sort list clone
        Collections.sort(productNameTextClone);

        return productNameTextClone.equals(productNameText);
    }

    public boolean isNameSortDescending() {
        List<WebElement> productNameElement = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME);

        // Khai báo 1 List String để lưu trữ List productName
        List<String> productNameText = new ArrayList<String>();

        // Dùng vòng lặp lấy hết productName và thêm vào productNameText
        for (WebElement productName : productNameElement) {
            String text = productName.getText();
            productNameText.add(text);
        }
        // Clone toàn bộ text từ productNameText qua 1 list mới
        List<String> productNameTextClone = new ArrayList<>(productNameText);

        // Sort list clone vì trên UI đang là Descending
        Collections.sort(productNameTextClone);
        // Chuyển sang DESC
        Collections.reverse(productNameTextClone);

        return productNameTextClone.equals(productNameText);
    }
}
