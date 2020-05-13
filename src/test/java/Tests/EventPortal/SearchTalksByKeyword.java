package Tests.EventPortal;

import Helpers.Pages.MainPage;
import Helpers.Pages.TalksLibraryPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchTalksByKeyword extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;

    @BeforeMethod
    public void createWebDriver() {
        driverCreate();
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        driverQuit();
    }

    @Test
    public void searchEventByKeyword() throws InterruptedException {

        mainPage.open();

        mainPage.openTalksLibraryPage();

        talksLibraryPage.inputValueInSearchField("Azure");

        // Verification that all 'talks' in the list in title contains search value
        for (String name : talksLibraryPage.getTalksListTitles()) {
            Assert.assertTrue(name.contains("Azure") || name.contains("azure"));
        }
    }
}


