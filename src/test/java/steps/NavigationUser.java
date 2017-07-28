package steps;

import appmanager.BrowserBase;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.concurrent.TimeUnit;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class NavigationUser{

    LumensHomePage lumensHomePage;
    CategoryNavigationBar categoryNavigationBar;
    BrowserBase browser;
    LogIn logIn;
    ProductListPage PLP;

    @Step
    public void isOnTheHomePage() {
        lumensHomePage.open();
        lumensHomePage.closeUiDialogWidget();
        assertThat(lumensHomePage.getTitle()).containsIgnoringCase("Lumens");
        assertThat(lumensHomePage.getTitle()).isNotEmpty();
    }
    @Step
    public void shouldSeePageTitleContaining(String expectedTitle) {
        assertThat(lumensHomePage.getTitle()).containsIgnoringCase(expectedTitle);

    }
    @Step
    public void navigatesToCategory(Category category) {

        categoryNavigationBar.selectCategory(category);

    }
    @Step
    public void shouldSeeEmptyShoppingCart() {
        lumensHomePage.viewEmptyCart.isDisplayed();
    }

    @Step
    public void clickLogInButtonInNavigationMenu() {
        lumensHomePage.myAccount.click();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(browser.isElementPresent(lumensHomePage.logIn)){
            lumensHomePage.logIn.click();
            Assert.assertTrue(logIn.logInDialogWindowIsPresent());
        }
    }

    @Step
    public void clickCreateAccountButton() {
        if (logIn.logInDialogWindowIsPresent()) {
            Assert.assertEquals("CREATE ACCOUNT", logIn.createAccountButton.getText());
            logIn.createAccountButton.click();
        }
    }

    @Step ("Navigates to main category: {1}")
    public void navigatesToMainCategory(Category category, String href) {
        categoryNavigationBar.selectCategory(category);
    }

    @Step
    public void shouldSeeURLPageContaining(String label) {
        String currentURL = getDriver().getCurrentUrl();
        Assert.assertTrue(currentURL.contains(label));
    }

    @Step ("Navigates to primary category: {1}")
    public void navigatesToPrimaryCategory(PrimaryCategory category, String href) {
        String url = category.label();
        categoryNavigationBar.selectPrimaryCategory(category);
        browser.waitForPageLoad(getDriver());
    }


    @Step public void should_use_https_for_page() {
        String pageUrl = getDriver().getCurrentUrl();
        String protocol = pageUrl.substring(0, pageUrl.indexOf(":"));
        Assert.assertEquals("https", protocol);
    }

    @Step
    public void clickOnTheCurrentPageInTopPagination(int page) {
        if(browser.isElementPresent(PLP.paginationPage)) {
            getDriver().findElement(By.cssSelector(".pagination .page-"+String.valueOf(page))).click();
            WebDriverWait wait = new WebDriverWait(getDriver(), 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(PLP.paginationPage));
        }
    }

    @Step
    public void openPageByUrl(String url) {
        getDriver().navigate().to(getDriver().getCurrentUrl() + url);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        lumensHomePage.closeUiDialogWidget();
    }

}
