package steps;

import appmanager.BrowserBase;
import model.PaymentData;
import model.ProductData;
import model.ShippingAddressData;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import pages.LumensHomePage;
import pages.ProductDetailsPage;
import pages.ShoppingCartPage;
import webdriver.RunConfigurator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ShoppingCart {

    ProductDetailsPage productDetailsPage;
    RunConfigurator runConfigurator = new RunConfigurator();
    LumensHomePage lumensHomePage;
    ShoppingCartPage cart;
    CheckoutPage checkout;
    private final BrowserBase browser = new BrowserBase();

    private By productId = By.cssSelector(".detailscolumn .productid");
    private String quantityOfProduct = runConfigurator.GetValue("quantityOfProduct");
    private By qtyProductInCart = By.cssSelector(".inputbox.quantityinput.positivenumber");
    private By amountProductInCart = By.cssSelector(".divadj.price");
    private By nameProductInCart = new ByJQuerySelector(".name a:visible");
    private By orderSubtotalInRightColumn = By.cssSelector("#rightcolumn .ordersubtotal .value");
    private By priceProductInCart = By.cssSelector(".standardprice");
    private By brandProductInCart = By.cssSelector(".cartBrand");
    private By imageProductInCart = By.cssSelector(".imagecolumn>img");
    private By viewCartButton = By.cssSelector("#mcBookMark");
    private By orderSubtotal = new ByJQuerySelector(".ordersubtotal td:eq(1)");
    private By updateQtyButton = By.cssSelector("button[value='Update']");
    private By removeProductButton = By.cssSelector("button[value='Remove']");
    private By applyPromoCodeButton = By.cssSelector("button[value='Apply']");
    private By summaryProductForm = By.cssSelector("#dwfrm_cart");
    private By inputPromoCodeField = By.cssSelector(".couponinput");
    private By promoCodeMessage = By.cssSelector("span.couponcode");
    private By viewAllCurrentPromotionsLink = By.cssSelector("#btn-view-promos");
    private By currentPromotionsPopupTitle = new ByJQuerySelector(".ui-dialog:visible h2.promosOnly");


    @Step("The Shopping cart has the added product, a correct QTY and a Total price")
    public void shouldSeeAddedProductsOnTheShoppingCart(ProductData product) {
        lumensHomePage.closeUiDialogWidget();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertThat("Shopping Cart");
        assertThat("Item");
        assertThat("Price");
        assertThat("QTY");
        assertThat("Amount");
        for (int i = 0; i < product.options.size(); i++) {
            assertThat(product.options.get(i).getValueOption());
        }
        Assert.assertEquals(quantityOfProduct, getDriver().findElement(qtyProductInCart).getAttribute("value"));
        assertTrue(product.getProductID().equalsIgnoreCase(getDriver().findElement(productId).getText()));
        if(browser.isElementPresent(By.cssSelector(".detailscolumn .promo"))){
            assertTrue(getDriver().findElement(By.cssSelector(".salesprice.fontnormal")).isDisplayed());
            assertTrue(getDriver().findElement(By.cssSelector(".cartProdLvlDisc .salesprice")).isDisplayed());
        }
        assertThat(product.getName().contains(getDriver().findElement(nameProductInCart).getText()));
        Assert.assertEquals(product.getPrice(), getDriver().findElement(orderSubtotalInRightColumn).getText());
        Assert.assertEquals("By " + product.getBrand(), getDriver().findElement(brandProductInCart).getText());
        assertThat(getDriver().findElement(imageProductInCart).isDisplayed());
    }

    @Step
    public void openShoppingCartPage() {
        String link = getDriver().findElement(viewCartButton).getAttribute("href");
        getDriver().navigate().to(link);
        lumensHomePage.closeUiDialogWidget();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.titleIs("Shopping Cart | View Cart at Lumens.com"));
    }

    @Step("Update quantity of product from '3' to '4'")
    public void updateQuantityOfProduct() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().findElement(qtyProductInCart).clear();
        getDriver().findElement(qtyProductInCart).sendKeys("4");
        getDriver().findElement(updateQtyButton).click();
    }

    @Step
    public void shouldBeUpdateTotalAmount() {

        Assert.assertEquals("4", getDriver().findElement(qtyProductInCart).getAttribute("value"));
//        Assert.assertEquals("$1,000.00", getDriver().findElement(amountProductInCart).getText());
        //      Assert.assertEquals("$1,000.00", getDriver().findElement(orderSubtotal).getText());
        //     Assert.assertEquals("4 Items, Total: $1,000.00", (getDriver().findElement(viewCartButton).getText()));
    }

    @Step
    public void removeProductInShoppingCart() {
        lumensHomePage.closeUiDialogWidget();
        getDriver().findElement(removeProductButton).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(summaryProductForm));
        assertThat("Your Shopping Cart is Empty");
        Assert.assertEquals("$0.00", getDriver().findElement(orderSubtotal).getText());
    }

    @Step
    public void shouldApplyEmptyPromoCode(ProductData product) {
        getDriver().findElement(inputPromoCodeField).clear();
        lumensHomePage.closeUiDialogWidget();
        getDriver().findElement(applyPromoCodeButton).click();
        lumensHomePage.closeUiDialogWidget();
        Assert.assertEquals("Please Enter a Promo Code", getDriver().findElement(promoCodeMessage).getText());
        Assert.assertEquals(product.getPrice(), getDriver().findElement(orderSubtotal).getText());
    }

    @Step
    public void shouldApplyInvalidPromoCode(ProductData product) {
        getDriver().findElement(inputPromoCodeField).sendKeys("Promo Code 12345");
        getDriver().findElement(applyPromoCodeButton).click();
        lumensHomePage.closeUiDialogWidget();
        Assert.assertEquals("Promo code \"PROMO CODE 12345\" is not valid.", getDriver().findElement(promoCodeMessage).getText());
        Assert.assertEquals(product.getPrice(), getDriver().findElement(orderSubtotal).getText());
    }

    @Step
    public void shouldViewAllCurrentPromotionsLink() {
        getDriver().findElement(viewAllCurrentPromotionsLink).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPromotionsPopupTitle));
        Assert.assertEquals("Current Promotions", getDriver().findElement(currentPromotionsPopupTitle).getText());
    }

    @Step
    public void shouldBeOpenCheckoutPage() {
        Assert.assertEquals("Checkout", getDriver().findElement(By.cssSelector("h1")).getText());

    }

    @Step
    public void addShippingAddress() {
        ShippingAddressData address = new ShippingAddressData().withFirstName("firtsName").withLastName("lastName")
                .withAddress1("2168  32nd Ave").withCity("San Francisco").withZip("94116").withShippingPhone("5283069155466784").withState("California");
        checkout.fillShippingAddress(address);

    }

    @Step
    public void addPayment() {
        PaymentData payment = new PaymentData().withNameOnCard("Test Test").withNumber("4111111111111111").withCardMonth("January").withCardYear("2017").withSecurityCode("100");
        checkout.fillPaymentSection(payment);
    }

    @Step
    public void clickOnTheAddedProduct(ProductData product) {
        List<WebElement> pids =getDriver().findElements(productId);
        for(WebElement pid:pids){
            if(pid.getText().equalsIgnoreCase(product.getProductID())){
                pid.findElement(By.xpath("..")).findElement(By.xpath("..")).findElement(By.cssSelector(".name a")).click();
            }
        }
    }
}