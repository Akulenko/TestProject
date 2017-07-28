package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends PageObject {

    @FindBy(css = "#nav-icon-search-ttl")
    public
    WebElement searchField;

    @FindBy(css = "#simplesearchbtn > span")
    public
    WebElement searchButton;

    @FindBy(css = "#searchinput")
    public
    WebElement searchInput;

    @FindBy(css = ".product .general-cell .name h3")
    public List<WebElement> listingSearch;

    @FindBy(css = ".navgroup.refinement")
    public
    WebElement navigationGroup;


    public List<String> getResultTitles() {
        return listingSearch.stream().map(element -> element.getText()).collect(Collectors.toList());
    }

    public String filterByNavigationGroup(String byNameGroup){
        WebElement firstFilterValue = getDriver()
                .findElement(new ByJQuerySelector(".navgroup.refinement h3:contains(" + byNameGroup + ")"))
                .findElement(By.xpath(".."))
                .findElement(By.cssSelector("li"));
        String countGroupItems = firstFilterValue.findElement(By.cssSelector(".refineLink span")).getText().replaceAll("\\D", "");
        firstFilterValue.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 90);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        return countGroupItems;
    }
}
