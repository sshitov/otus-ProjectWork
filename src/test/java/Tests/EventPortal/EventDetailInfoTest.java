package Tests.EventPortal;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;


public class EventDetailInfoTest extends BaseTest {


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
    public void pastEventsInChinaTests() {

        getDriver().get(getBaseUrl());
        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/events']")).click();
        getDriver().findElement(By.cssSelector(".tab-content > div > div:nth-child(1) > div > div:nth-child(1) > div")).click();

        //шапка
        softAssertion.assertTrue(getDriver().findElement(By.cssSelector(".evnt-hero-header")).isDisplayed());
        //кнопка
        softAssertion.assertTrue(getDriver().findElement(By.cssSelector(".evnt-reg-wrapper button")).isDisplayed());
        //тело
        softAssertion.assertTrue(getDriver().findElement(By.cssSelector("[id='agenda']")).isDisplayed());
        //день
        softAssertion.assertTrue(getDriver().findElement(By.cssSelector("[class*='evnt-day']")).isDisplayed());
        //время
        softAssertion.assertTrue(getDriver().findElement(By.cssSelector(".evnt-timeline-table > div > span")).isDisplayed());

        //место - необязательный параметр в рамках события, может не быть места проведения соответственно ждать от любой карточки его наличия не корректно

    }
}
