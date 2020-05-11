package Tests.EventPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UpcomingEventsDateValidation extends BaseTest {

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
    public void upcomingEventsDateValidation() throws ParseException {

        getDriver().get(getBaseUrl());

        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/events']")).click();

        List<WebElement> upcomingEventCardsDate = getDriver().findElements(By.cssSelector(".date"));

        ArrayList<String> upcomingEventsStringDates = new ArrayList<>();

        for (WebElement webElement : upcomingEventCardsDate) {
            String date = webElement.getAttribute("innerText");
            upcomingEventsStringDates.add(date);
        }
        ArrayList<Date> upcomingEventsDates = new ArrayList<>();

        for (String date : upcomingEventsStringDates) {
            DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            upcomingEventsDates.add(df.parse(date));
        }

        for (Date date : upcomingEventsDates) {
            if (currentDate.compareTo(date) > 0)
                Assert.fail();
        }

    }

}
