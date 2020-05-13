package Helpers.Drivers;

import Helpers.Config.FirefoxConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    public static final Logger logger = LogManager.getLogger(FirefoxDriverManager.class.getName());
    public static FirefoxConfig firefoxConfig = ConfigFactory.create(FirefoxConfig.class, System.getProperties());

/*    String selenoidBrowserName = firefoxConfig.selenoidBrowserName();
    String selenoidBrowserVersion = firefoxConfig.selenoidBrowserVersion();
    Boolean selenoidEnableVNC = firefoxConfig.selenoidEnableVNC();
    Boolean selenoidEnableVideo = firefoxConfig.selenoidEnableVideo();
    String selenoidUrl = firefoxConfig.selenoidUrl();*/

    @Override
    protected void loadDriver() {
        WebDriverManager.firefoxdriver().setup();
        logger.debug("Driver is load");
    }

    @Override
    protected void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("D:/Программы/Mozilla/firefox.exe");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Part for Selenoid
/*        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(selenoidBrowserName);
        capabilities.setVersion(selenoidBrowserVersion);
        capabilities.setCapability("enableVNC", selenoidEnableVNC);
        capabilities.setCapability("enableVideo", selenoidEnableVideo);
        driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);*/

    }
}
