package Helpers.Drivers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void loadDriver();
    protected abstract void createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() {
        if (null == driver) {
            loadDriver();
            createDriver();
        }
        return driver;
    }
}
