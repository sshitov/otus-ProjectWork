package Tests.EventPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

public class EventCardViewTest extends BaseTest {

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
    public void eventCardViewTest() {

        getDriver().get(getBaseUrl());

        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/events']")).click();

        // локатор на первую карточку списка
        WebElement firstEventCard = getDriver().findElement(By.cssSelector(".tab-content > div > div:nth-child(1) > div > div:nth-child(1)"));

        // локатор "место проведения"
        WebElement eventCardLocation = firstEventCard.findElement(By.cssSelector("div:nth-child(1) > div > :nth-child(1) > p"));

        // локатор "язык"
        WebElement eventCardLanguage = firstEventCard.findElement(By.cssSelector("div:nth-child(1) > div > :nth-child(2) > p"));

        // локатор "название мероприятия"
        WebElement eventCardName = firstEventCard.findElement(By.cssSelector("a > div > div:nth-child(2) > div > :nth-child(1)"));

        // локатор "дата мероприятия"
        WebElement eventCardDate = firstEventCard.findElement(By.cssSelector("div:nth-child(2) > div > :nth-child(2) > div > div > p > span:nth-child(1)"));

        // локатор "информация о регистрации"
        WebElement eventCardRegisterInfo = firstEventCard.findElement(By.cssSelector("div:nth-child(2) > div > :nth-child(2) > div > div > p > span:nth-child(2)"));

        // локатор "список спикеров"
        WebElement eventCardSpeakers = firstEventCard.findElement(By.cssSelector("a > div > div:nth-child(3) > div > div"));

        softAssertion.assertTrue(eventCardLocation.isDisplayed());
        softAssertion.assertTrue(eventCardLanguage.isDisplayed());
        softAssertion.assertTrue(eventCardName.isDisplayed());
        softAssertion.assertTrue(eventCardDate.isDisplayed());
        softAssertion.assertTrue(eventCardRegisterInfo.isDisplayed());
        softAssertion.assertTrue(eventCardSpeakers.isDisplayed());

    }
}
