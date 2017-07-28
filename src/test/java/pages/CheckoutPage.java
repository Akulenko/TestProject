package pages;

import appmanager.BrowserBase;
import model.PaymentData;
import model.ShippingAddressData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.List;

public class CheckoutPage extends PageObject {
    ShoppingCartPage cart;
    private By _state = By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state");
    private By _year = By.id("dwfrm_billing_paymentMethods_creditCard_year");
    private final BrowserBase browser = new BrowserBase();

    @FindBy(css =".imagebuttonCO.submit.submit-order.submit-order-co")
    public WebElement submitOrderButton;

    public void fillShippingAddress(ShippingAddressData shippingAddressData){
        List<WebElement> shippingAddressFields = cart.checkoutData.findElements(By.cssSelector("#addressform .textinput"));
        browser.type(shippingAddressFields.get(0), shippingAddressData.getFirstName());
        browser.type(shippingAddressFields.get(1), shippingAddressData.getLastName());
        browser.type(shippingAddressFields.get(2), shippingAddressData.getBusinessName());
        browser.type(shippingAddressFields.get(3), shippingAddressData.getAddress1());
        browser.type(shippingAddressFields.get(4), shippingAddressData.getAddress2());
        browser.type(shippingAddressFields.get(5), shippingAddressData.getCity());
        browser.select(_state,shippingAddressData.getState());
        browser.type(shippingAddressFields.get(6), shippingAddressData.getZip());
        browser.type(shippingAddressFields.get(7), shippingAddressData.getShippingPhone());
    }

    public void fillPaymentSection(PaymentData paymentData) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR)+1;

        List<WebElement> paymentFields = cart.checkoutData.findElements(By.cssSelector("#paymentmethods .textinput"));
        browser.type(paymentFields.get(0), paymentData.getNameOnCard());
        browser.type(paymentFields.get(1), paymentData.getNumber());
        browser.type(paymentFields.get(2), paymentData.getSecurityCode());
        browser.select(_year,String.valueOf(year));
    }
}
