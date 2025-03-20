package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;

public class PageManager {

    // Áp dụng Factory Pattern để quản lý việc khởi tạo Page Object

    public static Object getPage(WebDriver driver, String pageName) {
        switch (pageName) {
            case "HomePage" :
                return new UserHomePageObject(driver);
            case "LoginPage" :
                return new UserLoginPageObject(driver);
            case "RegisterPage" :
                return new UserRegisterPageObject(driver);
            case "CustomerInfor" :
                return new CustomerInforPageObject(driver);
            default :
                throw new IllegalArgumentException("pageName is not valid");
        }
    }

}
