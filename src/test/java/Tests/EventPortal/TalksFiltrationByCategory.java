package Tests.EventPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class TalksFiltrationByCategory extends BaseTest {

    @BeforeClass
    public void setupDriver() {
        driverLoad();
    }

    @BeforeMethod
    public void createWebDriver() throws MalformedURLException {
        create();
    }

    @AfterMethod
    public void closeWebDriver() {
        quit();
    }

    @Test
    public void eventFiltrationByCategory() {

        getDriver().get(getBaseUrl());
        getDriver().findElement(By.cssSelector(".evnt-platform-header [href='/talks']")).click();

        //открыть фильтры
        getDriver().findElement(By.cssSelector(".show-more")).click();

        //установка фильтров
        getDriver().findElement(By.cssSelector("[id='filter_category']")).click();
        getDriver().findElement(By.cssSelector("[data-value='Design']")).click();

        getDriver().findElement(By.cssSelector("[id='filter_location']")).click();
        getDriver().findElement(By.cssSelector("[data-value='Belarus']")).click();

        getDriver().findElement(By.cssSelector("[id='filter_language']")).click();
        getDriver().findElement(By.cssSelector("[data-value='ENGLISH']")).click();

        // ждем применения фильтра
        WebElement loader = getDriver().findElement(By.cssSelector(".evnt-global-loader"));
        getWait().until(ExpectedConditions.stalenessOf(loader));

        // сибираем все карточки в массив
        List<WebElement> talksList = getDriver().findElements(By.cssSelector(".evnt-talk-card"));

        // собираем из собранных карточек урлы
        ArrayList<String> talksUrls = new ArrayList<>();

        // Наполняем массив talksUrls ссылками
        for (WebElement ignored : talksList) {
            String talkUrl = getDriver().findElement(By.cssSelector(".evnt-talk-card > a")).getAttribute("href");
            talksUrls.add(talkUrl);
        }

        for (String talkUrl : talksUrls) {
            getDriver().get(talkUrl);
            //проверяем элементы на странице
            //Находим все категории карточки
            List<WebElement> categoriesWebElement = getDriver().findElements(By.cssSelector(".evnt-topics-wrapper .evnt-topic label"));

            ArrayList<String> categoriesName = new ArrayList<>();

            //Наполняем список категорий - категориями карточек
            for (WebElement category : categoriesWebElement) {
                categoriesName.add(category.getText());
            }
            //Проверяем, что в списке категорий карточки есть искомый: design
            boolean categoryIsPresent = false;
            for (String category : categoriesName) {
                if (category.contains("Design")) {
                    categoryIsPresent = true;
                    break;
                }
            }
            Assert.assertTrue(categoryIsPresent);

            //находим адрес
            String location = getDriver().findElement(By.cssSelector("[class*='location']")).getAttribute("innerText");

            Assert.assertTrue(location.contains("Belarus"));

            //находим язык
            String language = getDriver().findElement(By.cssSelector("[class*='language'] > span")).getAttribute("innerText");
            Assert.assertEquals(language, "ENGLISH");
        }

    }
}



