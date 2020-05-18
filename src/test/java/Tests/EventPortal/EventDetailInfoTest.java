package Tests.EventPortal;

import Helpers.Pages.EventPage;
import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class EventDetailInfoTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;
    private EventPage eventPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) throws MalformedURLException {
        driverCreate(browserName);
        mainPage = new MainPage(getDriver());
        eventsPage = new EventsPage(getDriver());
        eventPage = new EventPage(getDriver());
    }

    @AfterMethod
    public void tearDown() {
        driverQuit();
    }

    @Test(description = "Просмотр детальной информации о мероприятии")
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
