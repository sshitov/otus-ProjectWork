package tests;

import helpers.pages.EventsPage;
import helpers.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class UpcomingEventsCountTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) throws MalformedURLException {
        driverCreate(browserName);
        mainPage = new MainPage(getDriver());
        eventsPage = new EventsPage(getDriver());
    }

    @AfterMethod
    public void tearDown() {
        driverQuit();
    }

    @Test(description = "Просмотр предстоящих мероприятий")
    public void upcomingEventsCountTest() {

        mainPage.open();

        mainPage.openEventsPage();

        // Verifying that value in upcoming events counter is equals actual event count in the list
        Assert.assertEquals(eventsPage.getEventsListSize(), eventsPage.getEventsCounterValue(),
                "Number in counter and event list size is not the same");

    }
}
