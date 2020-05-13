package Tests.EventPortal;

import Helpers.Pages.EventsPage;
import Helpers.Pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EventCardViewTest extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;

    @Parameters("browser")
    @BeforeMethod
    public void createWebDriver(String browserName) {
        driverCreate(browserName);
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
        softAssert.assertTrue(eventsPage.eventCardLocation().isDisplayed());
        softAssert.assertTrue(eventsPage.eventCardLanguage().isDisplayed());
        softAssert.assertTrue(eventsPage.eventCardName().isDisplayed());
        softAssert.assertTrue(eventsPage.eventCardDate().isDisplayed());
        softAssert.assertTrue(eventsPage.eventCardRegisterInfo().isDisplayed());
        softAssert.assertTrue(eventsPage.eventCardSpeakers().isDisplayed());
        softAssert.assertAll();

    }
}
