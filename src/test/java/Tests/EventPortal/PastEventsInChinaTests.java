package Tests.EventPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PastEventsInChinaTests extends BaseTest {

    SoftAssert softAssertion = new SoftAssert();

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void pastEventsInChinaTests() throws ParseException {

        getDriver().get(getBaseUrl());
        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/events']")).click();
        getDriver().findElement(By.cssSelector(".evnt-tabs-list li:nth-child(2)")).click();
        getDriver().findElement(By.cssSelector("[id='filter_location']")).click();
        getDriver().findElement(By.cssSelector("[data-value='China']")).click();

        WebElement loader = getDriver().findElement(By.cssSelector(".evnt-global-loader"));

        //тут нужно подождать
        getWait().until(ExpectedConditions.stalenessOf(loader));

        String pastEventsCounterValue = getDriver().findElement(By.cssSelector("[class*='active'] [class*='evnt-tab-counter']")).getText();
        List<WebElement> upcomingEventCardsActualCount = getDriver().findElements(By.cssSelector("[class*='evnt-event-card']"));
        logger.debug(pastEventsCounterValue);

        // проверка что колличество карточек на странице соответсвует счетчику past
        softAssertion.assertEquals(upcomingEventCardsActualCount.size(), Integer.parseInt(pastEventsCounterValue));


        List<WebElement> pastEventCardsDate = getDriver().findElements(By.cssSelector(".date"));

        ArrayList<String> pastEventsStringDates = new ArrayList<>();

        for (WebElement webElement : pastEventCardsDate) {
            String date = webElement.getAttribute("innerText");
            pastEventsStringDates.add(date);
        }
        ArrayList<Date> pastEventsDates = new ArrayList<>();

        for (String date : pastEventsStringDates) {
            DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            pastEventsDates.add(df.parse(date));
        }

        logger.debug(pastEventsDates);

        // проверка что даты проведенных мероприятий меньше текущей
        for (Date date : pastEventsDates) {
            if (currentDate.compareTo(date) < 0)
                Assert.fail();
        }
    }

}
