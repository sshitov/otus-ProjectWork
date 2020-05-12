package Pages;

import Config.CommonConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public static CommonConfig commonConfig = ConfigFactory.create(CommonConfig.class, System.getProperties());

    private WebDriver driver;
    protected String baseUrl = commonConfig.baseUrl();

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = ".evnt-platform-header [href='/events']")
    protected WebElement eventsSection;

    @FindBy(css = ".evnt-platform-header [href='/talks']")
    protected WebElement talksLibrarySection;

    public WebElement getEventsSection() {
        return eventsSection;
    }

    public WebElement getTalksLibrarySection() {
        return talksLibrarySection;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void open() {
        driver.get(getBaseUrl());
    }

    public void openEventsPage() {
        getEventsSection().click();
    }

    public void openTalksLibraryPage() {
        getTalksLibrarySection().click();
    }
}
