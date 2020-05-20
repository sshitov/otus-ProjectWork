package helpers.drivers;

import helpers.сonfig.ChromeConfig;
import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {

    public static ChromeConfig chromeConfig = ConfigFactory.create(ChromeConfig.class, System.getProperties());
    Config config = com.typesafe.config.ConfigFactory.load("healenium.properties");

    int implicitlyWaitValue = chromeConfig.implicitlyWaitValue();
    String selenoidBrowserName = chromeConfig.selenoidBrowserName();
    String selenoidBrowserVersion = chromeConfig.selenoidBrowserVersion();
    Boolean selenoidEnableVNC = chromeConfig.selenoidEnableVNC();
    Boolean selenoidEnableVideo = chromeConfig.selenoidEnableVideo();
    String selenoidUrl = chromeConfig.selenoidUrl();

    @Override
    protected void createDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(selenoidBrowserName);
        capabilities.setVersion(selenoidBrowserVersion);
        capabilities.setCapability("enableVNC", selenoidEnableVNC);
        capabilities.setCapability("enableVideo", selenoidEnableVideo);

        // Сreate self-healing remote driver (selenoid)
        WebDriver delegate = new RemoteWebDriver(new URL(selenoidUrl), capabilities);
        driver = SelfHealingDriver.create(delegate, config);
        driver.manage().timeouts().implicitlyWait(implicitlyWaitValue, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
