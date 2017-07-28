package steps;

import appmanager.BrowserBase;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LumensHomePage;
import pages.SearchResultsPage;

import java.util.List;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tanzilya on 10/3/2016.
 */

public class SearchingItems {
    LumensHomePage lumensHomePage;
    SearchResultsPage searchResultsPage;
    BrowserBase browserBase;
    private By readMoreLink = By.cssSelector(".read-more");
    private By showLessLink = By.cssSelector(".show-less");
    private By relatedSearchesLists = By.cssSelector(".br-related-query-link");
    private By navigationGroup = new ByJQuerySelector(".navgroup.refinement h3");


    @Step
    public void searchProductByKeyword(final String keywords) {
   //     lumensHomePage.closeUiDialogWidget();
        searchResultsPage.searchField.click();
        searchResultsPage.searchInput.sendKeys(keywords);
        searchResultsPage.searchButton.click();
    }

    @Step
    public void shouldSeeItemsRelatedTo(final String keyword) {
        lumensHomePage.closeUiDialogWidget();
        List<String> resultTitles = searchResultsPage.getResultTitles();
        resultTitles.stream().forEach(title -> Assertions.assertThat(title.contains(keyword)));
        assertThat(searchResultsPage.containsText("Search results for " + keyword));
        assertThat(searchResultsPage.containsText("Your Search results for: " + keyword));
        filteringSearchResultsByGroup("Category");
        filteringSearchResultsByGroup("By Price");
 //       filteringSearchResultsByGroup("By Customer Rating");
    }

    @Step
    public void shouldSeeBrandPage(final String nameBrand) {
        lumensHomePage.closeUiDialogWidget();
        shouldSeeTitleImageStory(nameBrand);
        shouldSeeRelatedProductsList();
        shouldSeeRelatedSearchesLinks();

    }

    @Step
    public void shouldSeeTitleImageStory(String nameBrand) {
        Assert.assertEquals(nameBrand, getDriver().findElement(By.cssSelector("h1")).getText());
        assertThat(searchResultsPage.containsElements(By.cssSelector(".logoCont")));
        assertThat(searchResultsPage.containsText("The " + nameBrand + " Story"));
        Assert.assertTrue(getDriver().findElement(readMoreLink).isDisplayed());
        getDriver().findElement(readMoreLink).click();
        Assert.assertFalse(getDriver().findElement(readMoreLink).isDisplayed());
        Assert.assertTrue(getDriver().findElement(showLessLink).isDisplayed());
        //     shouldSeeRelatedSearchesLinks();


    }

    @Step
    public void shouldSeeRelatedProductsList() {
        assertThat(searchResultsPage.containsText("Related Products"));
    }

    @Step
    public void shouldSeeRelatedSearchesLinks() {
        //    String winHandleBefore = getDriver().getWindowHandle();
        assertThat(searchResultsPage.containsText("Related Searches"));
 /*       List<WebElement> searchesLists = getDriver().findElements(relatedSearchesLists);
        for (int i = 0; i < searchesLists.size(); i++) {
            String nameRelatedLink = searchesLists.get(i).getText();
            String urlRelatedLink = searchesLists.get(i).getAttribute("href");

 //           String selectLinkOpeningNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
            getDriver().findElement(By.linkText("steel-fruit-bowl")).sendKeys(Keys.CONTROL +"t");

            // Switch to new window opened
            for(String winHandle : getDriver().getWindowHandles()){
                getDriver().switchTo().window(winHandle);
            }

            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, urlRelatedLink);
            assertThat(getDriver().getPageSource().compareToIgnoreCase(nameRelatedLink));
            getDriver().close();
            // Switch back to original browser (first window)
            getDriver().switchTo().window(winHandleBefore);

        }*/
    }

    @Step
    public void shouldBeMinimizeMaximizeNavigationGroup() {
        List<WebElement> navigationGroupList = getDriver().findElements(navigationGroup);
        for (int i = 0; i < navigationGroupList.size(); i++) {
            navigationGroupList.get(i).click();
            navigationGroupList = getDriver().findElements(navigationGroup);
            //          Assert.assertFalse(getDriver().findElement(By.cssSelector(".navgroup.refinement h3 .collapsed")).isDisplayed());
            //         Assert.assertTrue(navigationGroupList.get(i).findElement(By.className("collapsed")).isDisplayed());
            navigationGroupList.get(i).click();
            navigationGroupList = getDriver().findElements(navigationGroup);

        }
    }

    @Step("Filtering by {1} navigation group")
    public void filteringSearchResultsByGroup(String byNameGroup) {
        WebElement firstFilterValue = getDriver()
                .findElement(new ByJQuerySelector(".navgroup.refinement h3:contains(" + byNameGroup + ")"))
                .findElement(By.xpath(".."))
                .findElement(By.cssSelector("li"));
        String countFilteredProducts = firstFilterValue.findElement(By.cssSelector(".refineLink span")).getText().replaceAll("\\D", "");
        firstFilterValue.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new ByJQuerySelector(".searchinformation b")));
        String search = getDriver().findElement(new ByJQuerySelector(".searchinformation b")).getText().replaceAll("\\D", "");
        Assert.assertThat(countFilteredProducts, containsString(search));
        getDriver().findElement(new ByJQuerySelector(".navgroup.refinement li.selected")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(new ByJQuerySelector(".searchinformation b")));
    }

}



