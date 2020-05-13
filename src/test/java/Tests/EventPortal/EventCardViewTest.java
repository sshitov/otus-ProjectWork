package Tests.EventPortal;

import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCardViewTest extends BaseTest {

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
