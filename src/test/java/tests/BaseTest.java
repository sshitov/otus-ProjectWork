package tests;

import helpers.drivers.DriverManager;
import helpers.drivers.DriverManagerFactory;
import helpers.enums.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.Date;

public class BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseTest.class.getName());
    protected DriverManager driverManager;
    private WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();
    Date currentDate = new Date();

    public WebDriver getDriver() {
        return driver;
    }

    public void driverCreate(String browserName) throws MalformedURLException {
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browserName));
        driver = driverManager.getDriver();
        logger.debug("Driver is created");
    }

    public void driverQuit() {
        driverManager.quitDriver();
        logger.debug("Driver is closed");
    }

}