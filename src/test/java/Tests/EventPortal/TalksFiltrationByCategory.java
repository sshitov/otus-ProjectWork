package Tests.EventPortal;

import Pages.MainPage;
import Pages.TalkPage;
import Pages.TalksLibraryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class TalksFiltrationByCategory extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;
    private TalkPage talkPage;

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
        talkPage = new TalkPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void eventFiltrationByCategory() {

        mainPage.open();

        mainPage.openTalksLibraryPage();

        talksLibraryPage.openMoreFilters();

        // Select filters
        talksLibraryPage.openCategoryFilter().selectCategoryFilterValueDesign();

        talksLibraryPage.openLocationFilter().selectLocationFilterValueBelarus();

        talksLibraryPage.openLanguageFilter().selectLanguageFilterValueEnglish();

        // Page loading wait
        WebElement loader = getDriver().findElement(By.cssSelector(".evnt-global-loader"));
        getWait().until(ExpectedConditions.stalenessOf(loader));

        for (String talkUrl : talksLibraryPage.getTalksListUrls()) {
            talksLibraryPage.openTalkPage(talkUrl);

            // Check category: Design
            Assert.assertTrue(talkPage.categoriesIsContains("Design"));

            // Check search location: Belarus
            Assert.assertTrue(talkPage.locationText().contains("Belarus"));

            // Check search language: English
            Assert.assertEquals(talkPage.languageText(), "ENGLISH");
        }

    }
}



