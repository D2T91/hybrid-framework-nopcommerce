package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jQuery.HomePageUI;

public class HomePOJquery extends BasePage {

    private WebDriver driver;

    public HomePOJquery(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        sleepInSecond(2);
    }

    public boolean isPageNumberActived(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        return getElementAttribute(driver, HomePageUI.DYNAMIC_PAGE_LINK, "class", pageNumber).endsWith("active");
    }

    public void enterToTextboxByHeaderName(String headerName, String valueToSend) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADERNAME, headerName);
        sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADERNAME, valueToSend, headerName);
        pressKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADERNAME, Keys.ENTER, headerName);
    }


    public boolean isRowDateValueDisplayed(String female, String country, String male, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_ROW, female, country, male, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_DATA_ROW, female, country, male, total);
    }

    public void deleteRowByCountryName(String countryName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRYNAME, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRYNAME, countryName);
        sleepInSecond(2);
    }

    public void clickToLoadDataButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }


    public void enterToTextboxByIndex(String rowIndex, String columnName, String valueTosend) {

        // Từ ColumnName làm sao để lấy ra ColumnIndex ?
        // Lấy ra ListWebElement, sau đó .size để lấy số lượng, rồi +1
        int columnIndexNumber = getListWebElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Sau đó Convert qua dạng Text (String) ?
        String columnIndex = String.valueOf(columnIndexNumber);

        // Truyền 2 giá trị rowIndex và columnIndex vào Locator để tương tác và sendkey
        sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, valueTosend , rowIndex, columnIndex);

    }

    public void selectToDropdownByIndex(String rowIndex, String columnName, String valueToSelect) {

        // Lấy ra ListWebElement, sau đó .size để lấy số lượng, rồi +1
        int columnIndexNumber = getListWebElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Sau đó Convert qua dạng Text (String) ?
        String columnIndex = String.valueOf(columnIndexNumber);

        // Truyền 2 giá trị rowIndex và columnIndex vào Locator để tương tác và select dropdown
        selectItemDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, valueToSelect, rowIndex, columnIndex);

    }

    public void checkToCheckBoxByIndex(String rowIndex, String columnName, boolean checkOrUncheck) {
        // Lấy ra ListWebElement, sau đó .size để lấy số lượng, rồi +1
        int columnIndexNumber = getListWebElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Sau đó Convert qua dạng Text (String) ?
        String columnIndex = String.valueOf(columnIndexNumber);

        if (checkOrUncheck == true) {
            checkToCheckBoxradio(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        } else {
            uncheckToCheckBox(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        }
    }

    public void clickToIconByIndex(String rowIndex, String iconName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);
        clickToElement(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);
    }
}
