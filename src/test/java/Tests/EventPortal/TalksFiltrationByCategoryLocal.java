package Tests.EventPortal;

import Helpers.Pages.MainPage;
import Helpers.Pages.TalkPage;
import Helpers.Pages.TalksLibraryPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TalksFiltrationByCategoryLocal {

    private MainPage mainPage;
    private TalksLibraryPage talksLibraryPage;
    private TalkPage talkPage;
    private WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void loadDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        talksLibraryPage = new TalksLibraryPage(driver);
        talkPage = new TalkPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

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
            softAssert.assertTrue(talkPage.categoriesIsContains("Design"));

            // Verifying that location contains the 'Belarus' value
            softAssert.assertTrue(talkPage.locationText().contains("Belarus"));

            // Verifying that language contains the 'English' value
            softAssert.assertEquals(talkPage.languageText(), "ENGLISH");

        }
        softAssert.assertAll();
    }
}
