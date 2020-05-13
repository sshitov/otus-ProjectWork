package Tests.EventPortal;

import Helpers.Pages.MainPage;
import Helpers.Pages.TalksLibraryPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTalksByKeyword extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;

    @Parameters("browser")
    @BeforeMethod
    public void createWebDriver(String browserName) {
        driverCreate(browserName);
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
            softAssert.assertTrue(name.contains("Azure") || name.contains("azure"));
        }
        softAssert.assertAll();
    }
}


