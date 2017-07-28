package pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class CategoryNavigationBar extends PageObject {

    public void selectCategory(Category category) {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        find(new ByJQuerySelector("a[href*='"+category.label()+"'] span")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.urlContains(category.label()));
    }

    public void selectPrimaryCategory(PrimaryCategory category) {
        getDriver().navigate().to("https://www.lumens.com"+category.label());
    }
}
