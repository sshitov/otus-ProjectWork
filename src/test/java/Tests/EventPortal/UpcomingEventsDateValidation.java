package Tests.EventPortal;

import Pages.EventsPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

public class UpcomingEventsDateValidation extends BaseTest {

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
    public void upcomingEventsDateValidation() throws ParseException {

        mainPage.open();

        mainPage.openEventsPage();

        // Verification that events date more or equals than the current date
        for (Date date : eventsPage.getEventsDates()) {
            if (currentDate.compareTo(date) > 0)
                Assert.fail();
        }

    }

}
