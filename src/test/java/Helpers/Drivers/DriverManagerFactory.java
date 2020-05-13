package Helpers.Drivers;

import Helpers.Enums.DriverType;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        if (type == DriverType.firefox) {
            driverManager = new FirefoxDriverManager();
        } else {
            driverManager = new ChromeDriverManager();
        }
        return driverManager;

    }

}
