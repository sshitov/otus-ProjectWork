package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TalksLibraryPage {

    public static final Logger logger = LogManager.getLogger(TalksLibraryPage.class.getName());
    private WebDriver driver;

    public TalksLibraryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = ".show-more")
    protected WebElement moreFilters;

    @FindBy(css = "[id='filter_category']")
    protected WebElement categoryFilter;

    @FindBy(css = "[data-value='Design']")
    protected WebElement categoryFilterValueDesign;

    @FindBy(css = "[id='filter_location']")
    protected WebElement locationFilter;

    @FindBy(css = "[data-value='Belarus']")
    protected WebElement locationFilterValueBelarus;

    @FindBy(css = "[id='filter_language']")
    protected WebElement languageFilter;

    @FindBy(css = "[data-value='ENGLISH']")
    protected WebElement languageFilterValueEnglish;

    @FindBy(css = ".evnt-talk-card")
    protected List<WebElement> talksList;

    @FindBy(css = ".evnt-talk-card > a")
    protected WebElement talksListUrls;

    @FindBy(css = ".evnt-search-filter > input")
    protected WebElement searchField;

    @FindBy(css = ".evnt-card-body span")
    protected List<WebElement> talksTitleWebElements;

    public List<WebElement> getTalksTitleWebElements() {
        return talksTitleWebElements;
    }

    public void openMoreFilters(){
        moreFilters.click();
    }

    public TalksLibraryPage openCategoryFilter(){
        categoryFilter.click();
        return this;
    }

    public void selectCategoryFilterValueDesign(){
        categoryFilterValueDesign.click();
    }

    public TalksLibraryPage openLocationFilter(){
        locationFilter.click();
        return this;
    }

    public void selectLocationFilterValueBelarus(){
        locationFilterValueBelarus.click();
    }

    public TalksLibraryPage openLanguageFilter(){
        languageFilter.click();
        return this;
    }

    public void selectLanguageFilterValueEnglish(){
        languageFilterValueEnglish.click();
    }

    public ArrayList<String> getTalksListUrls(){
        ArrayList<String> talksUrls = new ArrayList<>();
        for (WebElement ignored : talksList) {
            String talkUrl = talksListUrls.getAttribute("href");
            talksUrls.add(talkUrl);
        }
        return talksUrls;
    }

    public void openTalkPage(String url){
        driver.get(url);
    }

    public void inputValueInSearchField(String searchValue){
        searchField.clear();
        searchField.sendKeys(searchValue);
    }

    public ArrayList<String> getTalksListTitles() throws InterruptedException {

        ArrayList<String> talkNames = new ArrayList<>();

        while (true) {

            List<WebElement> firstTalksTitleWebElements = driver.findElements(By.cssSelector(".evnt-card-body span"));


            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 10000)");

            Thread.sleep(1000);

            List<WebElement> secondTalksTitleWebElements = getTalksTitleWebElements();

            if (firstTalksTitleWebElements.size() == secondTalksTitleWebElements.size()) {
                logger.info(secondTalksTitleWebElements.size());
                for (WebElement talk : secondTalksTitleWebElements) {
                    talkNames.add(talk.getText());
                }
                break;
            }

        }
        return talkNames;
    }
}
