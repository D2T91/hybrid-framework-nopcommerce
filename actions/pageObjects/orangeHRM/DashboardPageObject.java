package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.pim.employee.EmployeeListPageObject;
import pageUIs.orangeHRM.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public EmployeeListPageObject clickToPIMPage() {
        waitForElementClickable(driver, DashboardPageUI.PIM_LINK);
        clickToElement(driver, DashboardPageUI.PIM_LINK);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getPageInstance(EmployeeListPageObject.class, driver);
    }

}
