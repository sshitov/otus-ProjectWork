package Helpers.Drivers;

import Helpers.Config.ChromeConfig;
import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager{

    public static final Logger logger = LogManager.getLogger(ChromeDriverManager.class.getName());
    public static ChromeConfig chromeConfig = ConfigFactory.create(ChromeConfig.class, System.getProperties());
    Config config = com.typesafe.config.ConfigFactory.load("healenium.properties");

    int implicitlyWaitValue = chromeConfig.implicitlyWaitValue();
    /*    String selenoidBrowserName = chromeConfig.selenoidBrowserName();
    String selenoidBrowserVersion = chromeConfig.selenoidBrowserVersion();
    Boolean selenoidEnableVNC = chromeConfig.selenoidEnableVNC();
    Boolean selenoidEnableVideo = chromeConfig.selenoidEnableVideo();
    String selenoidUrl = chromeConfig.selenoidUrl();*/

    @Override
    protected void loadDriver() {
        WebDriverManager.chromedriver().setup();
        logger.debug("Driver is load");
    }

    @Override
    protected void createDriver() {

        // Part for Selenoid
/*
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(selenoidBrowserName);
        capabilities.setVersion(selenoidBrowserVersion);
        capabilities.setCapability("enableVNC", selenoidEnableVNC);
        capabilities.setCapability("enableVideo", selenoidEnableVideo);

        WebDriver delegate = new RemoteWebDriver(new URL(selenoidUrl), capabilities);
        driver = SelfHealingDriver.create(delegate, config);
        driver.manage().timeouts().implicitlyWait(implicitlyWaitValue, TimeUnit.SECONDS);
        driver.manage().window().maximize();
*/

        WebDriver delegate = new ChromeDriver();

        // Сreate self-healing driver
        driver = SelfHealingDriver.create(delegate, config);
        driver.manage().timeouts().implicitlyWait(implicitlyWaitValue, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
