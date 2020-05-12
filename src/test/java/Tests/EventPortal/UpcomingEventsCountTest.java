package Tests.EventPortal;

import Pages.EventsPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class UpcomingEventsCountTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
        mainPage = new MainPage(getDriver());
        eventsPage = new EventsPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void upcomingEventsCountTest() {

        mainPage.open();

        mainPage.openEventsPage();

        // Verifying that value in upcoming events counter is equals actual event count in the list
        Assert.assertEquals(eventsPage.getEventsListSize(), eventsPage.getEventsCounterValue());

    }
}
