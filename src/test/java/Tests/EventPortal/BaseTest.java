package Tests.EventPortal;

import Helpers.Drivers.DriverManager;
import Helpers.Drivers.DriverManagerFactory;
import Helpers.Enums.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.Date;

public class BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class.getName());
    protected DriverManager driverManager;
    public SoftAssert softAssert = new SoftAssert();

    Date currentDate = new Date();

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void driverCreate(String browserName) {
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browserName));
        driver = driverManager.getDriver();
        logger.debug("Driver is created");
    }

    public void driverQuit() {
        driverManager.quitDriver();
        logger.debug("Driver is closed");
    }

}