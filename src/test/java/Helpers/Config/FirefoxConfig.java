package Helpers.Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:FirefoxConfig.properties")
public interface FirefoxConfig extends Config{

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
