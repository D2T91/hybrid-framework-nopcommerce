package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageManager {

    // Áp dụng Factory Pattern để quản lý việc khởi tạo Page Object

    public static Object getPage(WebDriver driver, String pageName) {
        switch (pageName) {
            case "HomePage" :
                return new HomePageObject(driver);
            case "LoginPage" :
                return new LoginPageObject(driver);
            case "RegisterPage" :
                return new RegisterPageObject(driver);
            case "CustomerInfor" :
                return new CustomerInforPageObject(driver);
            default :
                throw new IllegalArgumentException("pageName is not valid");
        }
    }

}
