package steps;

import appmanager.BrowserBase;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProductListPage;
import pages.SearchResultsPage;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ProductListPageSteps {
    SearchResultsPage searchPage;
    BrowserBase browser;
    ProductListPage PLP;


    @Step
    public String clickOnFirstAttributeValueForGroupInTheLeftNavMenu(String nameGroup) {
        searchPage.filterByNavigationGroup(nameGroup);
        return getDriver().findElement(By.cssSelector(".navgroup .selected")).getText();
    }

    @Step
    public void shouldBeAbleToRedirectedBackToTheSameProductResultsPage(int pageOfPagination, String  attributeValueInLeftNavMenu) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PLP.paginationPage));
        String curentPageOfPagination = getDriver().findElement(By.className("currentpage")).getText();
        Assert.assertEquals(String.valueOf(pageOfPagination), curentPageOfPagination);
        String selectedNavGroup = getDriver().findElement(By.cssSelector(".navgroup .selected")).getText();
        Assert.assertEquals(attributeValueInLeftNavMenu, selectedNavGroup);
    }
}
