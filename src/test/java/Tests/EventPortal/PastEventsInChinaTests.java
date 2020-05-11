package Tests.EventPortal;

import Pages.EventsPage;
import Pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

public class PastEventsInChinaTests extends BaseTest {

    private MainPage mainPage;
    private EventsPage eventsPage;

    SoftAssert softAssertion = new SoftAssert();

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
    public void pastEventsInChinaTests() throws ParseException {

        mainPage.open();

        mainPage.openEventsPage();

        eventsPage.selectPastEventsSection();

        eventsPage.openFilterByLocation().selectLocationValueChina();

        //Уточнить как спрятать ожидание и нужно ли, т.к.
        WebElement loader = getDriver().findElement(By.cssSelector(".evnt-global-loader"));
        getWait().until(ExpectedConditions.stalenessOf(loader));

        // Verification that card count in the counter is right
        softAssertion.assertEquals(eventsPage.getEventsListSize(), eventsPage.getEventsCounterValue());

        // Verification that events dates less than current date
        for (Date date : eventsPage.getEventsDates()) {
            if (currentDate.compareTo(date) < 0)
                Assert.fail();
        }
    }

}
