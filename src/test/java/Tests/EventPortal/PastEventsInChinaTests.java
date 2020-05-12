package Tests.EventPortal;

import Pages.EventsPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

public class PastEventsInChinaTests extends BaseTest {

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
    public void pastEventsInChinaTests() throws ParseException {

        mainPage.open();

        mainPage.openEventsPage();

        eventsPage.selectPastEventsSection();

        eventsPage.openFilterByLocation().selectLocationValueChina();

        //  Waiting for event list update
        eventsPage.eventListUpdateWait();

        // Verification that card count in the counter is right
        Assert.assertEquals(eventsPage.getEventsListSize(), eventsPage.getEventsCounterValue());

        // Verification that events dates less than current date
        for (Date date : eventsPage.getEventsDates()) {
            if (currentDate.compareTo(date) < 0)
                Assert.fail();
        }
    }

}
