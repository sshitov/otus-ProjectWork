package Tests.EventPortal;

import Helpers.Pages.MainPage;
import Helpers.Pages.TalkPage;
import Helpers.Pages.TalksLibraryPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TalksFiltrationByCategory extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;
    private TalkPage talkPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) {
        driverCreate(browserName);
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
        talkPage = new TalkPage(getDriver());
    }

    @AfterMethod
    public void tearDown() {
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
            softAssert.assertTrue(talkPage.categoriesIsContains("Design"));

            // Verifying that location contains the 'Belarus' value
            softAssert.assertTrue(talkPage.locationText().contains("Belarus"));

            // Verifying that language contains the 'English' value
            softAssert.assertEquals(talkPage.languageText(), "ENGLISH");

        }
        softAssert.assertAll();
    }
}



