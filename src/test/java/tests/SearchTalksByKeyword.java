package tests;

import helpers.pages.MainPage;
import helpers.pages.TalksLibraryPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SearchTalksByKeyword extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) throws MalformedURLException {
        driverCreate(browserName);
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
    }

    @AfterMethod
    public void tearDown() {
        driverQuit();
    }

    @Test(description = "Поиск докладов по ключевому слову")
    public void searchTalksByKeyword() throws InterruptedException {

        mainPage.open();

        mainPage.openTalksLibraryPage();

        talksLibraryPage.inputValueInSearchField("Azure");

        // Verification that all 'talks' in the list in title contains search value
        for (String name : talksLibraryPage.getTalksListTitles()) {
            softAssert.assertTrue(name.contains("Azure") || name.contains("azure"),
                    "Talks list contains talk without search value");
        }
        softAssert.assertAll();
    }
}


