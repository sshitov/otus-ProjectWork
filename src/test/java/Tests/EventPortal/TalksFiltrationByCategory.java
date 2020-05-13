package Tests.EventPortal;

import Helpers.Pages.MainPage;
import Helpers.Pages.TalkPage;
import Helpers.Pages.TalksLibraryPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class TalksFiltrationByCategory extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;
    private TalkPage talkPage;

    @BeforeMethod
    public void createWebDriver() {
        driverCreate();
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
        talkPage = new TalkPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        driverQuit();
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



