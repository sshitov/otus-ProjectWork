package Tests.EventPortal;

import Pages.EventsPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class EventCardViewTest extends BaseTest {

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
    public void eventCardViewTest() {

        mainPage.open();

        mainPage.openEventsPage();

        // Verification that card contains info about location, language, title, date, register info, speakers
        Assert.assertTrue(eventsPage.eventCardLocation().isDisplayed());
        Assert.assertTrue(eventsPage.eventCardLanguage().isDisplayed());
        Assert.assertTrue(eventsPage.eventCardName().isDisplayed());
        Assert.assertTrue(eventsPage.eventCardDate().isDisplayed());
        Assert.assertTrue(eventsPage.eventCardRegisterInfo().isDisplayed());
        Assert.assertTrue(eventsPage.eventCardSpeakers().isDisplayed());

    }
}
