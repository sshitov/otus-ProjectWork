package Tests.EventPortal;

import Helpers.Pages.EventPage;
import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class EventDetailInfoTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;
    private EventPage eventPage;

    @BeforeMethod
    public void createWebDriver() {
        driverCreate();
        mainPage = new MainPage(getDriver());
        eventsPage = new EventsPage(getDriver());
        eventPage = new EventPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        driverQuit();
    }

    @Test
    public void eventDetailInfoTest() {

        mainPage.open();

        mainPage.openEventsPage();

        eventsPage.openFirstEventCard();

        // Verification that event contains header witch register button, event program, date, time
        Assert.assertTrue(eventPage.getEventHeader().isDisplayed());
        Assert.assertTrue(eventPage.getRegisterButton().isDisplayed());
        Assert.assertTrue(eventPage.getEventProgram().isDisplayed());
        Assert.assertTrue(eventPage.getDate().isDisplayed());
        Assert.assertTrue(eventPage.getTime().isDisplayed());

    }

}
