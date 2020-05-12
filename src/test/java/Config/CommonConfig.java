package Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:CommonConfig.properties")
public interface CommonConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

}
