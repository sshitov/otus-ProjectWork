package Helpers.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EventsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @FindBy(css = "[class*='active'] [class*='evnt-tab-counter']")
    protected WebElement eventsCounterValue;

    @FindBy(css = "[class*='evnt-event-card']")
    protected List<WebElement> eventsList;

    @FindBy(css = ".tab-content > div > div:nth-child(1) > div > div:nth-child(1)")
    protected WebElement firstEventCard;

    @FindBy(css = ".date")
    protected List<WebElement> eventsCardsDate;

    @FindBy(css = ".evnt-tabs-list li:nth-child(2)")
    protected WebElement pastEventsSection;

    @FindBy(css = "[id='filter_location']")
    protected WebElement locationFilter;

    @FindBy(css = "[data-value='China']")
    protected WebElement locationValueChina;

    public WebElement getLocationFilter() {
        return locationFilter;
    }

    public WebElement getLocationValueChina() {
        return locationValueChina;
    }

    public WebElement getPastEventsSection() {
        return pastEventsSection;
    }

    public WebElement getFirstEventCard() {
        return firstEventCard;
    }

    public WebElement eventCardLocation() {
        return firstEventCard.findElement(By.cssSelector("div:nth-child(1) > div > :nth-child(1) > p"));
    }

    public WebElement eventCardLanguage() {
        return firstEventCard.findElement(By.cssSelector("div:nth-child(1) > div > :nth-child(2) > p"));
    }

    public WebElement eventCardName() {
        return firstEventCard.findElement(By.cssSelector("a > div > div:nth-child(2) > div > :nth-child(1)"));
    }

    public WebElement eventCardDate() {
        return firstEventCard.findElement(By.cssSelector("div:nth-child(2) > div > :nth-child(2) > div > div > p > span:nth-child(1)"));
    }

    public WebElement eventCardRegisterInfo() {
        return firstEventCard.findElement(By.cssSelector("div:nth-child(2) > div > :nth-child(2) > div > div > p > span:nth-child(2)"));
    }

    public WebElement eventCardSpeakers() {
        return firstEventCard.findElement(By.cssSelector("a > div > div:nth-child(3) > div > div"));
    }

    public int getEventsCounterValue() {
        return Integer.parseInt(eventsCounterValue.getText());
    }

    public int getEventsListSize() {
        return eventsList.size();
    }


    public ArrayList<Date> getEventsDates() throws ParseException {

        ArrayList<String> stringDates = new ArrayList<>();

        for (WebElement webElement : eventsCardsDate) {
            String date = webElement.getAttribute("innerText");
            stringDates.add(date);
        }

        ArrayList<Date> eventsDates = new ArrayList<>();

        for (String date : stringDates) {
            DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            eventsDates.add(df.parse(date));
        }
        return eventsDates;
    }

    public void selectPastEventsSection() {
        getPastEventsSection().click();
    }

    public EventsPage openFilterByLocation() {
        getLocationFilter().click();
        return this;
    }

    public void selectLocationValueChina() {
        getLocationValueChina().click();
    }

    public void openFirstEventCard() {
        getFirstEventCard().click();
    }

    public void eventListUpdateWait() {
        WebElement loader = driver.findElement(By.cssSelector(".evnt-global-loader"));
        wait.until(ExpectedConditions.stalenessOf(loader));
    }

}
