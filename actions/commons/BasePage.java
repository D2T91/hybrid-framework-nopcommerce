package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.AdminLoginPageObject;
import pageObjects.nopCommerce.AdminProductPageObject;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.UserHomePageObject;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.orangeHRM.BasePageHRMUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    // Hàm static gọi trực tiếp từ Class không cần thông qua đối tượng
    public static BasePage getBasePage() {
        return new BasePage();
    }

    private long LONG_TIMEOUT = 20;

    // Chứa các common function chung cho các class bên Page Object
    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // Chờ Alert xuất hiện và switch qua
    private Alert waitToAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    //Alert
    public void acceptAlert(WebDriver driver) {
        waitToAlertPresence(driver).accept();
        //driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitToAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String valueToSend) {
        waitToAlertPresence(driver).sendKeys(valueToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitToAlertPresence(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String Title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            driver.getTitle();
            if (driver.getTitle().equals(Title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private String castParameter(String locator, String... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }

    public By getByLocator(String locator) {
        if (locator.isEmpty() || locator == null) {
            throw new RuntimeException("Locator type cannot be null or empty");
        }
        switch (locator.split("=")[0].toLowerCase()) {
            case "xpath" :
                return By.xpath(locator.substring(6));
            case "css" :
                return By.cssSelector(locator.substring(4));
            case "id" :
                return By.id(locator.substring(3));
            case "class" :
                return By.className(locator.substring(6));
            case "name" :
                return By.name(locator.substring(5));
            default:
                throw new InvalidArgumentException("Locator type is not support");
        }
    }

    protected List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListWebElement(WebDriver driver, String locator, String... restParameter) {
        return driver.findElements(getByLocator(castParameter(locator, restParameter)));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter) {
        getWebElement(driver, castParameter(locator, restParameter)).click();
        // return driver.findElement(getByLocator(locator));
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(valueToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend, String... restParameter) {
        getWebElement(driver, castParameter(locator, restParameter)).clear();
        getWebElement(driver, castParameter(locator, restParameter)).sendKeys(valueToSend);
    }

    public void sendkeyToElementWithKey(WebDriver driver, String locator, String valueToSend) {
        Keys key = null;
        if (GlobalConstants.OS_NAME.startsWith("Windows")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }
        // Clear dữ liệu cũ = Key
        getWebElement(driver, locator).sendKeys(Keys.chord(key,"a",Keys.BACK_SPACE));
        sleepInSecond(1000);
        getWebElement(driver, locator).sendKeys(valueToSend);
    }


    public void selectItemDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemDropdown(WebDriver driver, String locatorSelect, String textItem, String... restParameter) {
        new Select(getWebElement(driver, castParameter(locatorSelect, restParameter))).selectByVisibleText(textItem);
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String textItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentXpath))).click();
        sleepInSecond(2);

        List<WebElement> allItems = waitForListElementPresence(driver, childXpath);

        for (WebElement item : allItems) {
            if (item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public static void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Dimension getElementSize(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getSize();
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).getText();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomProperty(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).getDomProperty(attributeName);
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByRGB(String rgbValue) {
        return Color.fromString(rgbValue).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    public void checkToCheckBoxradio(WebDriver driver, String locator) {
        if (!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckBoxradio(WebDriver driver, String locator, String... restParameter) {
        if (!getWebElement(driver, castParameter(locator, restParameter)).isSelected()) {
            getWebElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    public void uncheckToCheckBox(WebDriver driver, String locator) {
        if (getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckBox(WebDriver driver, String locator, String... restParameter) {
        if (getWebElement(driver, castParameter(locator, restParameter)).isSelected()) {
            getWebElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    // CÁCH 1

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return getWebElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            // Nếu vào trường hợp 3 : element ko có UI và HTML > trả về false
            return false;
        }
    }

    // CÁCH 2 Dùng findElements và kiếm tra Size = 0

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListWebElement(driver, locator);
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0 ) {
            return true;
        } else  if (elements.size() > 0 && !elements.get(0).isDisplayed() ) {
            return true;
        } else {
            return false;
        }

    }

    public void overideGlobalTimeout(WebDriver driver, long timeInsecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInsecond));
    }


    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParameter) {
        return getWebElement(driver, castParameter(locator, restParameter)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public WebDriver switchToIframe(WebDriver driver, String locator) {
        return driver.switchTo().frame(getWebElement(driver, locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver, String locator) {
        return driver.switchTo().defaultContent();
    }

    // User Actions

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void doubleToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    // Enter vào phần tử lOCATOR
    public void pressKeyToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    // Enter vào phần tử lOCATOR
    public void pressKeyToElement(WebDriver driver, String locator, Keys keys, String... restParameter) {
        new Actions(driver).sendKeys(getWebElement(driver, castParameter(locator, restParameter)), keys).perform();
    }

    // JavascriptExecutor in BasePage

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getDomAttribute("style"); //
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... restParameter) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, castParameter(locator, restParameter)));
        sleepInSecond(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }

    public String getDomain(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.domain;");
    }

    // WAIT
    public Boolean waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(locator), attributeName, attributeValue));
    }

    public Boolean waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(castParameter(locator, restParameter)), attributeName, attributeValue));
    }

    public WebElement waitForElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitForElementVisible(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public List<WebElement> waitForListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restParameter))));
    }

    public WebElement waitForElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public List<WebElement> waitForListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitForElementSelected(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restParameter))));
    }

    public CustomerInforPageObject clickToMyAccountLinkUserSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_LINK);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_LINK);
        return PageGenerator.getPageInstance(CustomerInforPageObject.class, driver);
    }

    public UserHomePageObject clickToLogoutLinkUserSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
        return PageGenerator.getPageInstance(UserHomePageObject.class, driver);
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public AdminLoginPageObject clickToLogoutLinkAdminSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK);
        return PageGenerator.getPageInstance(AdminLoginPageObject.class, driver);
    }

    public UserHomePageObject openUserSite(WebDriver driver, String userUrl) {
        openPageUrl(driver, userUrl);
        return PageGenerator.getPageInstance(UserHomePageObject.class, driver);
    }

    public AdminLoginPageObject openAdminSite(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);
        return PageGenerator.getPageInstance(AdminLoginPageObject.class, driver);
    }

    public AdminProductPageObject openAdminProductPage(WebDriver driver) {
        String attributeValue = getElementAttribute(driver, BasePageUI.ADMIN_PRODUCT_MENU, "class");
        if (!attributeValue.endsWith("menu-open")) {
            waitForElementClickable(driver, BasePageUI.ADMIN_PRODUCT_MENU);
            clickToElement(driver, BasePageUI.ADMIN_PRODUCT_MENU);
        }

        // Sub-Menu
        waitForElementClickable(driver, BasePageUI.ADMIN_PRODUCT_SUBMENU);
        clickToElement(driver, BasePageUI.ADMIN_PRODUCT_SUBMENU);

        return PageGenerator.getPageInstance(AdminProductPageObject.class, driver);

    }

    public void upLoadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
    }

    // LV 19
    public void enterToTextboxByID(WebDriver driver, String textboxID, String valueToSend) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, valueToSend, textboxID);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        checkToCheckBoxradio(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public void clickToCheckboxByID(WebDriver driver, String checkboxID) {
        waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_ID, checkboxID);
        checkToCheckBoxradio(driver, BasePageUI.CHECKBOX_BY_ID, checkboxID);
    }

    // OrangeHRM Project
    public boolean waitAllLoadingIconInvisible(WebDriver driver) {
        return waitForListElementInvisible(driver, BasePageHRMUI.LOADING_ICON);
    }

    public boolean isSuccessMessageIsDisplayed(WebDriver driver) {
        waitForElementVisible(driver, BasePageHRMUI.SUCCESS_MESSAGE);
        return isElementDisplayed(driver, BasePageHRMUI.SUCCESS_MESSAGE);
    }
}
