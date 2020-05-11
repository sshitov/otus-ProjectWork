package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EventsPage {

    private WebDriver driver;

    public EventsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "[class*='active'] [class*='evnt-tab-counter']")
    protected WebElement upcomingEventsCounterValue;

    @FindBy(css = "[class*='evnt-event-card']")
    protected List<WebElement> upcomingEventsList;

    public int getUpcomingEventsCounterValue() {
        return Integer.parseInt(upcomingEventsCounterValue.getText());
    }

    public List<WebElement> getUpcomingEventsList() {
        return upcomingEventsList;
    }

    public int getUpcomingEventsListSize() {
        return upcomingEventsList.size();
    }


}
