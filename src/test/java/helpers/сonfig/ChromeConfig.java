package helpers.сonfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ChromeConfig.properties")
public interface ChromeConfig extends Config{

    @Key("implicitlyWaitValue")
    int implicitlyWaitValue();

    @Key("selenoidBrowserName")
    String selenoidBrowserName();

    @Key("selenoidBrowserVersion")
    String selenoidBrowserVersion();

    @Key("selenoidEnableVNC")
    Boolean selenoidEnableVNC();

    @Key("selenoidEnableVideo")
    Boolean selenoidEnableVideo();

    @Key("selenoidUrl")
    String selenoidUrl();

}
