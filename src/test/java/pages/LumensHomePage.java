package pages;


import appmanager.BrowserBase;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

@DefaultUrl("https://www.lumens.com/")

public class LumensHomePage extends PageObject{

    public By miniProductInfo = new ByJQuerySelector(".minicartcontent #mc_headerCont");
    public By valueOptionsProductInFlyover = By.cssSelector(".minicartcontent .attributes");
    public By qtyOfProductInFlyover = By.cssSelector(".minicartcontent .quickviewqty");
    public By nameProductInFlyover = new ByJQuerySelector(".minicartcontent .name a");
    public By brandOfProductInFlyover = new ByJQuerySelector(".minicartcontent .miniBrand");
    public By imageOfProductInFlyover = By.cssSelector(".minicartcontent .imageMain img");
    public By subtotalInFlyover = By.cssSelector(".minicartcontent .mini_value");
    public By viewCartButtonInFlyover = By.cssSelector("#minicart-ab .minicart-btn-viewcart");
    public By freeShippingLinkInFlyover = new ByJQuerySelector(".minicartslot a[href*='#']:contains('FREE SHIPPING')");
    public By lumensGuaranteeLinkInFlyover = new ByJQuerySelector(".minicartslot a[href*='#']:contains('Guarantee')");
    public By amountOfProductInFlyover = By.cssSelector(".minicartcontent .productprice");
    public By priceOfProductInFlyover = By.cssSelector(".minicartcontent .summaryproduct .mini_productprice");
    public By continueShoppingButtonInFlyover = By.cssSelector("#minicart-ab .minicartclose");
    public By saleProductPriceInFlyover = By.cssSelector(".minicartcontent .salesprice");
    public By viewCartFlyover = By.cssSelector(".minicartcontent");
    public By productIdInFlyover = By.cssSelector(".miniUPC");

    @FindBy(css = "#searchinput")
    WebElement inputSearchField;

    @FindBy(css = "#nav-icon-search-ttl")
    WebElement searchField;

    @FindBy(css = "#simplesearchbtn > span")
    WebElement searchButton;

    @FindBy(jquery =".ui-button:visible")
    WebElement closeButtonDialogWidget;

    @FindBy(jquery =".minicarttotal:eq(0)")
    public WebElement viewCartButton;

    @FindBy(css =".emptycart")
    public WebElement viewEmptyCart;

//    @FindBy(jquery =".minicartcontent")
//    public WebElement viewCartFlyover;

    @FindBy(id ="gennav-trig-title")
    public WebElement myAccount;

    @FindBy(className ="userlogin")
    public WebElement logIn;

    BrowserBase browser= new BrowserBase();

    public void closeUiDialogWidget(){
        if(browser.isElementPresent(closeButtonDialogWidget)){
        closeButtonDialogWidget.click();
        }
    }
    public void searchFor(String keywords) {
        searchField.click();
        inputSearchField.sendKeys(keywords);
        searchButton.click();
    }

    public void clickViewCartButton(){
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        viewCartButton.click();
    }
}

