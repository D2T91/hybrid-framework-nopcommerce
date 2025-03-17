package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;

public class PageGenerator {

    // Viết các đoạn khởi tạo các PageObject thành các hàm khác nhau

    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static CustomerInforPageObject getCustomerInforPage(WebDriver driver) {
        return new CustomerInforPageObject(driver);
    }

    // Tất cả Class kế thừa BasePage đều dùng được hàm này (các Page Object)
    // Sử dụng hàm này sẽ không cần tạo từng hàm như trên nữa
    public static <T extends BasePage> T getPageInstance(Class<T>pageClass, WebDriver driver) {
        try {
            // Lấy constructor nhận WebDriver
            Constructor<T>constructor = pageClass.getConstructor(WebDriver.class);
            // Tạo new Instance của page class
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Can not init Page Object Class" + pageClass.getSimpleName(),e);
        }
    }

}
