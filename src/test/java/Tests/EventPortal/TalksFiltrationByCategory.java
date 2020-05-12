package Tests.EventPortal;

import Pages.MainPage;
import Pages.TalkPage;
import Pages.TalksLibraryPage;
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

        // Select filters
        talksLibraryPage.openMoreFilters();
        talksLibraryPage.openCategoryFilter().selectCategoryFilterValueDesign();
        talksLibraryPage.openLocationFilter().selectLocationFilterValueBelarus();
        talksLibraryPage.openLanguageFilter().selectLanguageFilterValueEnglish();

        // Waiting for talks list update
        talksLibraryPage.talkListUpdateWait();

        // Verifying that all talks in the list contain filtration values
        for (String talkUrl : talksLibraryPage.getTalksListUrls()) {
            talksLibraryPage.openTalkPage(talkUrl);

            // Verifying that categories contain the 'Design' value
            Assert.assertTrue(talkPage.categoriesIsContains("Design"));

            // Verifying that location contains the 'Belarus' value
            Assert.assertTrue(talkPage.locationText().contains("Belarus"));

            // Verifying that language contains the 'English' value
            Assert.assertEquals(talkPage.languageText(), "ENGLISH");
        }

    }
}



