package steps;

import appmanager.BrowserBase;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProductSetsPage;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class productSetsPageSteps {
    BrowserBase browser;
    ProductSetsPage PSP;

    @Step public void shouldContainsCollectionWordInTheProductName() {
        browser.waitForPageLoad(getDriver());
        assertThat(PSP.mainProductName.getText()).containsIgnoringCase("Collection");
    }

    @Step public void shouldSeeSelectAddToCartMessage() {
        browser.waitForPageLoad(getDriver());
        assertEquals("Select options and quantities below to build your set.", PSP.addToCartMsgs.get(0).getText());
        assertEquals("Select options and quantities above to build your set.", PSP.addToCartMsgs.get(1).getText());
    }

    @Step public void shouldSeeSubPDP() {
        browser.waitForPageLoad(getDriver());
        assertTrue(PSP.subPDPs.size()>0);
    }

    @Step public void EnterTheGlobalQuantityNumber(int qty) {
        browser.type(PSP.globalQTY, String.valueOf(qty));

    }

    @Step public void clickUpdateQTYForAllProducts() {
        PSP.updateQtyButton.click();
    }

    @Step public void clickAddToCartButton() {
        PSP.addToCartButton.click();
    }

    @Step public void shouldSeeAddedToYourCartDialog() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PSP.addedToCartDialog));
        assertThat(getDriver().findElement(PSP.addedToCartDialog).isDisplayed());
        assertThat("Added to your cart");
    }

    @Step public void shouldSeeQTYOfAllAddedProductsInTheCartLinkOnTheTopOfPage(String expectedQTY) {
        String cart = PSP.cartLinkOnTopOfPage.getText();
        String actualQTY = cart.substring(0, cart.indexOf("Items")-1);
        Assert.assertEquals(expectedQTY, actualQTY);
    }

    @Step public String enterQTYForEachProductsOfSetOf() {
        int totalQuantity=0;
        int items = PSP.subPDPs.size();
        for(int i=0; i< items; i++){
            browser.type(PSP.quantityOfRelatedProducts.get(i), String.valueOf(i+1));
            totalQuantity += i+1;
        }
        return String.valueOf(totalQuantity);
    }

    @Step public void enterQTYForFirstProductOfSetOf(int globalQTY) {
        browser.type(PSP.quantityOfRelatedProducts.get(0), String.valueOf(globalQTY));
    }

    @Step public void clickFirstValueOfAdditionalOptionForProductSet() {
        for(WebElement productSetDetail:PSP.additionalOptionOfProductSet){
            productSetDetail.findElement(By.className("swatchanchor")).click();
        }
    }
}

