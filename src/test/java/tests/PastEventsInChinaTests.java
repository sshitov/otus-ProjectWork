package tests;

import helpers.pages.EventsPage;
import helpers.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

public class PastEventsInChinaTests extends BaseTest {

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

    @Test(description = "Просмотр прошедших мероприятий в Китае")
    public void pastEventsInChinaTests() throws ParseException {

        mainPage.open();

        mainPage.openEventsPage();

        eventsPage.selectPastEventsSection();

        eventsPage.openFilterByLocation().selectLocationValueChina();

        //  Waiting for event list update
        eventsPage.eventListUpdateWait();

        // Verification that card count in the counter is right
        softAssert.assertEquals(eventsPage.getEventsListSize(), eventsPage.getEventsCounterValue(),
                "Number in counter and event list size is not the same");

        // Verification that events dates less than current date
        for (Date date : eventsPage.getEventsDates()) {
            if (currentDate.compareTo(date) < 0)
                Assert.fail();
        }
        softAssert.assertAll();
    }

}
