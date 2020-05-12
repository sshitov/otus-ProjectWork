package Tests.EventPortal;

import Pages.MainPage;
import Pages.TalksLibraryPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class SearchTalksByKeyword extends BaseTest {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
        mainPage = new MainPage(getDriver());
        talksLibraryPage = new TalksLibraryPage(getDriver());
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void searchEventByKeyword() throws InterruptedException {

        mainPage.open();

        mainPage.openTalksLibraryPage();

        talksLibraryPage.inputValueInSearchField("Azure");

        for (String name : talksLibraryPage.getTalksListTitles()) {
            Assert.assertTrue(name.contains("Azure") || name.contains("azure"));
        }
    }
}


