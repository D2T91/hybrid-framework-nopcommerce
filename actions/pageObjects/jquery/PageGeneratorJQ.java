package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGeneratorJQ {

    // Tất cả Class kế thừa BasePage sẽ dùng được hàm này (các PageObject)
    // Sử dụng hàm này sẽ không cần tạo từng hàm như trên
    public static <T extends BasePage> T getPageInstance(Class<T>pageClass, WebDriver driver) {
        try {
            // Lấy constructor nhận WebDriver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            // Tạo new Instance của page class
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Can not init Page Object Class" + pageClass.getSimpleName(),e);
        }
    }

}
