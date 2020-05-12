package Tests.EventPortal;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class.getName());

    Date currentDate = new Date();

    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void driverLoad() {
        WebDriverManager.chromedriver().setup();
        logger.debug("driver is load");
    }

    public void create() throws MalformedURLException {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        logger.debug("driver is created");

        // Part for Selenoid
/*        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("78.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), capabilities);*/

    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
        logger.debug("driver is closed");
    }

}