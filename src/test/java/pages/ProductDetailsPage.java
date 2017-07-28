package pages;

import appmanager.BrowserBase;
import model.ProductData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductDetailsPage extends PageObject {
    BrowserBase browser = new BrowserBase();

    public By nameOption = By.cssSelector(".label");
    public By valueOption = By.cssSelector(".displayValue");
    public By swatchOption = By.cssSelector(".swatchanchor");

    @FindBy(css = "#s7flyout > div:nth-child(1)")
    public WebElement productDetailImage;

    @FindBy(css = ".addtocartbutton")
    public WebElement addToCartButton;

    @FindBy(css = "#pdpLiveChatLink")
    public WebElement liveChatLink;

    @FindBy(css = ".printerfriendly")
    public WebElement printerFriendlyLink;

    @FindBy(css = ".sendtofriend")
    public WebElement sendToAFriendLink;

    @FindBy(css = ".emailaquestion")
    public WebElement emailAQuestionLink;

    @FindBy(css = ".scrollToAvailability")
    public WebElement checkAvailabilityLink;

    @FindBy(css = ".specsheet")
    public WebElement specSheetLink;

    @FindBy(css = ".productname")
    public WebElement productName;

    @FindBy(jquery = "a.brand:last")
    public WebElement brand;

    @FindBy(css = ".itemNo.productid")
    public WebElement productID;

    @FindBy(css = ".price .salesprice")
    public WebElement priceAddToCard;

    @FindBy(css = ".salesprice")
    public WebElement price;

    @FindBy(css = "#spaceController .priceTop")
    public WebElement rangePrice;

    @FindBy(css = "#soc-facebook>img")
    public
    WebElement facebookIcon;

    @FindBy(css = "#soc-pinterest>img")
    public
    WebElement pinterestIcon;

    @FindBy(css = "#soc-houzz>img")
    public
    WebElement houzzIcon;

    @FindBy(css = ".quantityinput")
    public
    WebElement quantity;

    @FindBy(css = ".optionsContainer")
    public List<WebElement> options;

    @FindBy(css = ".optionsContainer .emptyswatch.selected")
    public List<WebElement> selectedOptions;

    @FindBy(css = ".selectionsContainer .selection")
    public List<WebElement> selectionOptions;

    @FindBy(css = "#pdpTabsDiv .pdpTab")
    public List<WebElement> navigationTabs;

    @FindBy(css = "#cert-recs h2")
    public WebElement youMayAlsoLikeSection;

    @FindBy(css = "a[href*='add-to-project']")
    public WebElement addToProjectLink;

    @FindBy(className = "flag-sale")
    public WebElement saleBadge;

    @FindBy(className = "enlargedialogbox")
    public WebElement enlargeDialogBox;

    @FindBy(className = "itemsContainer")
    public WebElement availableProductsTable;

    @FindBy(id = "breadcrumb")
    public WebElement breadcrumb;

    @FindBy(css = "h1")
    public WebElement header;

    @FindBy(css = ".productnavigation div")
    public List<WebElement> productNavigationLink;

    @FindBy(className = "seeMore")
    public WebElement seeMoreLink;

    @FindBy(className = "jspHorizontalBar")
    public WebElement horizontalScrollBar;

    @FindBy(jquery = ".non-return:visible")
    public WebElement returnableMessage;

    @FindBy(className = "shipsfree")
    public WebElement shippingPromotion;

    public ProductData selectOptions() {
        ProductData product = new ProductData();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try{
            for (int i = 0; i < options.size(); i++) {
                WebElement element = options.get(i);
                model.AdditionalOptions option = new model.AdditionalOptions();
                option.setNameOption(element.findElement(nameOption).getText().replace("Select ", ""));
                option.setValueOption(element.findElement(valueOption).getText());
                element.findElement(swatchOption).click();
                product.getOptions().add(option);
            }
        }
        catch (Exception ex){}
        return product;
    }

    public void inputQuantityOfProduct(CharSequence qty) {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        quantity.clear();
        quantity.sendKeys(qty);
    }

    public void clickAddToCartButton() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        addToCartButton.click();
    }

    public String getNameOfProduct(){
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return productName.getText();
    }


    public ProductData fillProductData(ProductData product) {
        product.setName(productName.getText());
        product.setPrice(priceAddToCard.getText());
        product.setBrand(brand.getText());
        product.setProductID(productID.getText());
        String url = getDriver().getCurrentUrl();
        String productUrl = url.substring(0, url.indexOf("html") + 4);
        product.setUrl(productUrl);
        return product;
    }

    public String cutID(String id) {
        return id.substring(id.lastIndexOf(" ") + 1, id.length());

    }
}
