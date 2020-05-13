package Tests.EventPortal;

import Helpers.Pages.EventPage;
import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        softAssert.assertTrue(eventPage.getEventHeader().isDisplayed());
        softAssert.assertTrue(eventPage.getRegisterButton().isDisplayed());
        softAssert.assertTrue(eventPage.getEventProgram().isDisplayed());
        softAssert.assertTrue(eventPage.getDate().isDisplayed());
        softAssert.assertTrue(eventPage.getTime().isDisplayed());
        softAssert.assertAll();

    }

}
