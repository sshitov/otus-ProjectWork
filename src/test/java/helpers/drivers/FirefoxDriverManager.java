package helpers.drivers;

import helpers.сonfig.FirefoxConfig;
import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    public static FirefoxConfig firefoxConfig = ConfigFactory.create(FirefoxConfig.class, System.getProperties());
    Config config = com.typesafe.config.ConfigFactory.load("healenium.properties");

    String selenoidBrowserName = firefoxConfig.selenoidBrowserName();
    String selenoidBrowserVersion = firefoxConfig.selenoidBrowserVersion();
    Boolean selenoidEnableVNC = firefoxConfig.selenoidEnableVNC();
    Boolean selenoidEnableVideo = firefoxConfig.selenoidEnableVideo();
    String selenoidUrl = firefoxConfig.selenoidUrl();
    int implicitlyWaitValue = firefoxConfig.implicitlyWaitValue();

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
