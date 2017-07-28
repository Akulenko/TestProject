package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductSetsPage extends PageObject{

    @FindBy(css="h1.productname")
    public WebElement mainProductName;

    @FindBy(className = "pdpTabContent")
    public List<WebElement> subPDPs;

    @FindBy(className = "addtocartmsg")
    public List<WebElement> addToCartMsgs;

    @FindBy(className = "globalquantityinput")
    public WebElement globalQTY;

    @FindBy(css = ".addtocart button")
    public WebElement addToCartButton;

    @FindBy(css=".productsetquantity button")
    public WebElement updateQtyButton;

    @FindBy(id = "mcBookMark")
    public WebElement cartLinkOnTopOfPage;

    @FindBy (className = "quantityinput")
    public List<WebElement> quantityOfRelatedProducts;

    public By addedToCartDialog = By.cssSelector("#dialogcontainer");

    @FindBy (jquery = ".productsetdetail .swatchContainer:visible")
    public List<WebElement> additionalOptionOfProductSet;
}
