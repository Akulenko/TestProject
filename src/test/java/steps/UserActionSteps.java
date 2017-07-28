package steps;

import appmanager.BrowserBase;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogIn;
import pages.LumensHomePage;
import webdriver.RunConfigurator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class UserActionSteps {
    @Steps
    NavigationUser user;

    private LogIn logIn = new LogIn();

    RunConfigurator runConfigurator = new RunConfigurator();
    private final BrowserBase browser = new BrowserBase();
    LumensHomePage lumensHomePage;
    private List<String> createAccountDataSections = Lists.newArrayList("Name", "Login Information", "Password");
    private List<String> createAccountFieldsName = Lists.newArrayList("First Name", "Last Name", "Email", "Confirm Email", "Password", "Confirm Password");

    private String firstName = runConfigurator.GetValue("firstName");
    private String lastName = runConfigurator.GetValue("lastName");
    private String password = runConfigurator.GetValue("password");
    private String email;
    private By logInLink = By.className("userlogin");
    private By myAccount = By.id("gennav-trig-title");
    private By userName = new ByJQuerySelector("#my-account-link .username");
    private By myProject = By.cssSelector("a[href*='my-project-manager']");
    private By logo = By.className("logo");

    @FindBy(className = "accountlogin")
    WebElement accountLogin;

    @FindBy(id = "RegistrationForm")
    WebElement registrationForm;

    @FindBy(id = "createAccountBtn")
    public WebElement createAccountButton;


    private String generateEmail(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        String sdt = df.format(date);
        return sdt + "@gmail.com";
    }

    @Step
    public void shouldSeeAccountPage() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("Lumens/account"));
        Assert.assertEquals("Log In", getDriver().findElement(By.cssSelector(".logincustomers h1")).getText());
        Assert.assertEquals("Returning customers, please enter your email address and password.",
                getDriver().findElement(By.cssSelector(".logincustomers p")).getText());

        Assert.assertEquals("Create a New Account", getDriver().findElement(By.cssSelector(".registration h1")).getText());
        Assert.assertEquals("Sign up for a Lumens.com account to check out faster, save projects, create a list of favorites, write reviews and more.",
                getDriver().findElement(By.cssSelector(".registration p")).getText());

        List<String> nameUserDataSections = getDriver().findElements(By.cssSelector(".registration fieldset h4")).stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> nameUserDataFields = getDriver().findElements(By.cssSelector(".registration fieldset .labeltext")).stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertThat(createAccountDataSections,
                IsIterableContainingInOrder.contains(nameUserDataSections.toArray()));

        Assert.assertThat(createAccountFieldsName,
                IsIterableContainingInOrder.contains(nameUserDataFields.toArray()));

        Assert.assertEquals("CREATE ACCOUNT", getDriver().findElement(By.cssSelector(".accountlogin .medium")).getText());
    }

    @Step
    public void createNewAccount() {
        Date date = new Date();
        email = generateEmail(date);
        String[] dataUser = {firstName, lastName, email, email, password, password};
        int i = 0;
        List<WebElement> inputFields = getDriver().findElements(new ByJQuerySelector("#RegistrationForm fieldset .value input"));
        for (WebElement inputField : inputFields) {
            inputField.clear();
            inputField.sendKeys(dataUser[i]);
            i++;
        }
        getDriver().findElement(By.cssSelector("#RegistrationForm .medium")).click();
    }

    @Step
    public void shouldBeLoginWithCreatedAccount() {
        clickMyAccountButtonInNavigationMenu();
        if (browser.isElementPresent(logInLink)) {
            logIn(email, password);
        } else {
            Assert.assertEquals(firstName + " " + lastName, getDriver().findElement(userName).getText());
        }
    }

    @Step
    public void logIn(String email, String password) {
        if (browser.isElementPresent(logInLink)) {
            getDriver().findElement(logInLink).click();
            logIn.logIn(email, password);
            WebDriverWait wait = new WebDriverWait(getDriver(), 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(userName));
//            Assert.assertEquals(firstName + " " + lastName, getDriver().findElement(userName).getText());
        }
    }

    @Step
    public void clickLogInButtonInNavigationMenu() {
        if (browser.isElementPresent(logInLink)) {
            getDriver().findElement(logInLink).click();
            Assert.assertTrue(logIn.logInDialogWindowIsPresent());
        }
    }

    @Step
    public void clickMyAccountButtonInNavigationMenu() {
        if (browser.isElementPresent(myAccount)) {
            getDriver().findElement(myAccount).click();
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @Step
    public void clickCreateAccountButton() {
        if (logIn.logInDialogWindowIsPresent()) {
            Assert.assertEquals("CREATE ACCOUNT", logIn.createAccountButton.getText());
            logIn.createAccountButton.click();
        }
    }

    @Step
    public void openLogInAndCreateAccountDialogWindow() {
        clickMyAccountButtonInNavigationMenu();
        clickLogInButtonInNavigationMenu();
    }
}
