package Tests.EventPortal;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.List;

public class UpcomingEventsCountTest extends BaseTest {

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
    public void upcomingEventsCountTest() {

        getDriver().get(getBaseUrl());

        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/events']")).click();

        String upcomingEventsCounterValue = getDriver().findElement(By.cssSelector("[class*='active'] [class*='evnt-tab-counter']")).getText();

        List<WebElement> upcomingEventCardsActualCount = getDriver().findElements(By.cssSelector("[class*='evnt-event-card']"));

        Assert.assertEquals(upcomingEventCardsActualCount.size(), Integer.parseInt(upcomingEventsCounterValue));

    }

}
