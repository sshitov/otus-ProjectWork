package tests;

import com.epam.healenium.annotation.DisableHealing;
import helpers.pages.MainPage;
import helpers.pages.TalkPage;
import helpers.pages.TalksLibraryPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TalksFiltrationByCategory extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;
    private TalkPage talkPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) throws MalformedURLException {
        driverCreate(browserName);
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
        talkPage = new TalkPage(getDriver());
    }

    @AfterMethod
    public void tearDown() {
        driverQuit();
    }

    @DisableHealing
    @Test(description = "Фильтрация докладов по категориям")
    public void talksFiltrationByCategory() {

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
            softAssert.assertTrue(talkPage.categoriesIsContains("Design"),
                    "The category is not contains search value");

            // Verifying that location contains the 'Belarus' value
            softAssert.assertTrue(talkPage.locationText().contains("Belarus"),
                    "The location is not contains search value");

            // Verifying that language contains the 'English' value
            softAssert.assertEquals(talkPage.languageText(), "ENGLISH",
                    "The language is not equal the search value");

        }
        softAssert.assertAll();
    }
}



