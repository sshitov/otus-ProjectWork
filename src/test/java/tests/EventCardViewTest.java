package tests;

import helpers.pages.EventsPage;
import helpers.pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class EventCardViewTest extends BaseTest {

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

    @Test(description = "Просмотр карточек мероприятий")
    public void eventCardViewTest() {

        mainPage.open();

        mainPage.openEventsPage();

        // Verification that card contains info about location, language, title, date, register info, speakers
        softAssert.assertTrue(eventsPage.eventCardLocation().isDisplayed(), "The event location is not displayed");
        softAssert.assertTrue(eventsPage.eventCardLanguage().isDisplayed(), "The event language is not displayed");
        softAssert.assertTrue(eventsPage.eventCardName().isDisplayed(),"The event title is not displayed");
        softAssert.assertTrue(eventsPage.eventCardDate().isDisplayed(),"The event date is not displayed");
        softAssert.assertTrue(eventsPage.eventCardRegisterInfo().isDisplayed(), "Information about registration is not displayed");
        softAssert.assertTrue(eventsPage.eventCardSpeakers().isDisplayed(), "The speakers are not displayed");
        softAssert.assertAll();

    }
}
