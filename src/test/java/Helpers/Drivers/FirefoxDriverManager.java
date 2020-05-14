package Helpers.Drivers;

import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    public static final Logger logger = LogManager.getLogger(FirefoxDriverManager.class.getName());
/*    public static FirefoxConfig firefoxConfig = ConfigFactory.create(FirefoxConfig.class, System.getProperties());*/
    Config config = com.typesafe.config.ConfigFactory.load("healenium.properties");

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

        // Part for Selenoid
/*        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(selenoidBrowserName);
        capabilities.setVersion(selenoidBrowserVersion);
        capabilities.setCapability("enableVNC", selenoidEnableVNC);
        capabilities.setCapability("enableVideo", selenoidEnableVideo);
        driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);*/

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("D:/Программы/Mozilla/firefox.exe");
        WebDriver delegate = new FirefoxDriver(options);

        // Сreate self-healing driver
        driver = SelfHealingDriver.create(delegate, config);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
}
