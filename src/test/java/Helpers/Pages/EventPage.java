package Helpers.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventPage {

    public EventPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".evnt-hero-header")
    protected WebElement eventHeader;

    @FindBy(css = ".evnt-reg-wrapper button")
    protected WebElement registerButton;

    @FindBy(css = "[id='agenda']")
    protected WebElement eventProgram;

    @FindBy(css = "[class*='evnt-day']")
    protected WebElement date;

    @FindBy(css = ".evnt-timeline-table > div > span")
    protected WebElement time;

    public WebElement getEventHeader() {
        return eventHeader;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public WebElement getEventProgram() {
        return eventProgram;
    }

    public WebElement getDate() {
        return date;
    }

    public WebElement getTime() {
        return time;
    }
}
