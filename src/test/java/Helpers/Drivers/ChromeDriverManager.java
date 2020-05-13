package Helpers.Drivers;

import Helpers.Config.ChromeConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager{

    public static final Logger logger = LogManager.getLogger(ChromeDriverManager.class.getName());
    public static ChromeConfig chromeConfig = ConfigFactory.create(ChromeConfig.class, System.getProperties());

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
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(implicitlyWaitValue, TimeUnit.SECONDS);
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
