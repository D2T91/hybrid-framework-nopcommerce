package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;

    // Set protected chỉ cần lớp con kế thừa mới có thể GỌI
    protected WebDriver getBrowserDriver(String urlValue, String browserName) {

        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());
        switch (browserType) {
            case EDGE:
                driver = new EdgeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser Name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(urlValue);
        return driver;
    }

    protected int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected void quitBrowserDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
