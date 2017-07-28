package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends PageObject {

    @FindBy(css = ".continuecheckout")
    public WebElement proceedToCheckoutButton;

    @FindBy(className = "checkout")
    public WebElement checkoutData;
}
