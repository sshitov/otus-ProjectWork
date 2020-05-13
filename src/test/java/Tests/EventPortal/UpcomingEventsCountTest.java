package Tests.EventPortal;

import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class UpcomingEventsCountTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;

    @BeforeMethod
    public void createWebDriver() {
        driverCreate();
        mainPage = new MainPage(getDriver());
        eventsPage = new EventsPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        driverQuit();
    }

    @Test
    public void upcomingEventsCountTest() {

        mainPage.open();

        mainPage.openEventsPage();

        // Verifying that value in upcoming events counter is equals actual event count in the list
        Assert.assertEquals(eventsPage.getEventsListSize(), eventsPage.getEventsCounterValue());

    }
}
