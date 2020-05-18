package Tests.EventPortal;

import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

public class UpcomingEventsDateValidation extends BaseTest {

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

    @Test(description = "Валидация дат предстоящих мероприятий")
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
