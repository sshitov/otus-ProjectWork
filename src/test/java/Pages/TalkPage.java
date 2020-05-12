package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TalkPage {

    public TalkPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class*='location']")
    protected WebElement location;

    @FindBy(css = "[class*='language'] > span")
    protected WebElement language;

    @FindBy(css = ".evnt-topics-wrapper .evnt-topic label")
    protected List<WebElement> categoriesWebElement;

    public String locationText() {
        return location.getAttribute("innerText");
    }

    public String languageText() {
        return language.getAttribute("innerText");
    }

    public ArrayList<String> categoriesNamesList() {
        ArrayList<String> categoriesName = new ArrayList<>();
        for (WebElement category : categoriesWebElement) {
            categoriesName.add(category.getText());
        }
        return categoriesName;
    }

    public boolean categoriesIsContains(String categoryName) {
        boolean categoryIsPresent = false;
        for (String category : categoriesNamesList()) {
            if (category.contains(categoryName)) {
                categoryIsPresent = true;
                break;
            }
        }
        return categoryIsPresent;
    }

}
