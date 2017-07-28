package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class ShareWithAFriendOverlay extends PageObject {

    @FindBy(id = "stfMessage")
    public WebElement messageContent;

    @FindBy (id="dwfrm_sendtofriend_message")
    public WebElement sendToFriendMessage;

}
