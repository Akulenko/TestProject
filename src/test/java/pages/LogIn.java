package pages;

import appmanager.BrowserBase;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LogIn extends PageObject {

    private final BrowserBase browser = new BrowserBase();

    @FindBy(id = "dialogcontainer")
    public WebElement logInDialogWindow;

    @FindBy(className = "logincustomers")
    public WebElement logInCutomer;

    @FindBy(className = "globallogin")
    public WebElement createAccount;

    @FindBy(jquery = "ui-dialog-titlebar-close:visible")
    public WebElement closeButton;

    @FindBy(id = "createAccountBtn")
    public WebElement createAccountButton;

    @FindBy(css = ".logincustomers .email")
    public WebElement emailAddress;

    @FindBy(css = ".logincustomers .textinputpw.required")
    public WebElement passwordValue;

    public boolean logInDialogWindowIsPresent() {
        if (logInDialogWindow.isDisplayed()) {
            Assert.assertEquals("Log In", logInCutomer.findElement(By.cssSelector("h1")).getText());
            Assert.assertEquals("Returning customers, please enter your email address and password.",
                    logInCutomer.findElement(By.cssSelector("p")).getText());

            Assert.assertEquals("Create a New Account", createAccount.findElement(By.cssSelector("h1")).getText());
            Assert.assertEquals("Sign up for a Lumens.com account to check out faster, save projects, create a list of favorites, write reviews and more.",
                    createAccount.findElement(By.cssSelector("p")).getText());
            return true;
        }
        return false;
    }

    public void logIn(String email, String password) {
        Assert.assertTrue(logInDialogWindow.isDisplayed());
        browser.type(emailAddress, email);
        browser.type(passwordValue, password);
        logInCutomer.findElement(By.cssSelector(".global.pop-up")).click();
    }
}
