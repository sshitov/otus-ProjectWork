package Helpers.Drivers;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void createDriver() throws MalformedURLException;

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() throws MalformedURLException {
        if (null == driver) {
            createDriver();
        }
        return driver;
    }
}
