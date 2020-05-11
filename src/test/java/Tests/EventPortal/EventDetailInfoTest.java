package Tests.EventPortal;

import Pages.EventPage;
import Pages.EventsPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class EventDetailInfoTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;
    private EventPage eventPage;

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
        mainPage = new MainPage(getDriver());
        eventsPage = new EventsPage(getDriver());
        eventPage = new EventPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void eventDetailInfoTest() {

        mainPage.open();

        mainPage.openEventsPage();

        eventsPage.openFirstEventCard();

        Assert.assertTrue(eventPage.getEventHeader().isDisplayed());
        Assert.assertTrue(eventPage.getRegisterButton().isDisplayed());
        Assert.assertTrue(eventPage.getEventProgram().isDisplayed());
        Assert.assertTrue(eventPage.getDate().isDisplayed());
        Assert.assertTrue(eventPage.getTime().isDisplayed());

    }

}
