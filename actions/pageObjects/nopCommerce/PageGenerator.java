package pageObjects.nopCommerce;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import java.lang.reflect.Constructor;

public class PageGenerator {

    // Viết các đoạn khởi tạo các PageObject thành các hàm khác nhau

    public static UserHomePageObject getHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserLoginPageObject getLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static CustomerInforPageObject getCustomerInforPage(WebDriver driver) {
        return new CustomerInforPageObject(driver);
    }

    // Tất cả Class kế thừa BasePage sẽ dùng được hàm này (các PageObject)
    // Sử dụng hàm này sẽ không cần tạo từng hàm như trên
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
