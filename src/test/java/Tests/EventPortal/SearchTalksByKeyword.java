package Tests.EventPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SearchTalksByKeyword extends BaseTest {

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void searchEventByKeyword() throws InterruptedException {

        getDriver().get(getBaseUrl());
        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/talks']")).click();


        getDriver().findElement(By.cssSelector(".evnt-search-filter > input")).clear();
        getDriver().findElement(By.cssSelector(".evnt-search-filter > input")).sendKeys("Azure");

        ArrayList<String> talkNames = new ArrayList<>();

        while (true) {

            List<WebElement> firstTalksTitleWebElements = getDriver().findElements(By.cssSelector(".evnt-card-body span"));

            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, 10000)");

            Thread.sleep(1000);

            List<WebElement> secondTalksTitleWebElements = getDriver().findElements(By.cssSelector(".evnt-card-body span"));

            if (firstTalksTitleWebElements.size() == secondTalksTitleWebElements.size()) {
                logger.info(secondTalksTitleWebElements.size());
                for (WebElement talk : secondTalksTitleWebElements) {
                    talkNames.add(talk.getText());
                }
                break;
            }

        }

        for (String name : talkNames) {
            Assert.assertTrue(name.contains("Azure") || name.contains("azure"));
        }
    }
}


