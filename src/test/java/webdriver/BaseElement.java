package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

import java.lang.reflect.Field;

/**
 * Created by user on 07.10.2016.
 */
public abstract class BaseElement extends BaseEntity {

    private static final String LINK = "link=";

    private static final String ID = "id=";
    private static final String CSS = "css=";
    private static final int TIMEOUT_WAIT_0 = 0;


    /**
     * @uml.property name="name"
     */
    protected String name;
    /**
     * @uml.property name="locator"
     * @uml.associationEnd multiplicity="(1 1)"
     */
    protected By locator;
    /**
     * @uml.property name="element"
     * @uml.associationEnd
     */
    protected RemoteWebElement element;

    /**
     * @uml.property name="element"
     * @uml.associationEnd
     */



    /**
     * @return
     * @uml.property name="element"
     * @return RemoteWebElement
     */
    /*
    public RemoteWebElement getElement() {
        waitForIsElementPresent();
        return element;
    }

    /**
     * @param elementToSet RemoteWebElement
     * @uml.property name="element"
     */
    public void setElement(final RemoteWebElement elementToSet) {
        element = elementToSet;
    }





    /**
     * The simple constructor, name will be extracted
     * @param loc By Locator
     */
    protected BaseElement(final By loc) {
        locator = loc;
    }

    /**
     * The main constructor
     * @param loc By Locator
     * @param nameOf Output in logs
     */
    protected BaseElement(final By loc, final String nameOf) {
        locator = loc;
        name = nameOf;
    }




    /**
     * using different locators for different stages DON'T FORGET TO INIT {@link BaseEntity#stageController}
     * @param locatorStageA First locator
     * @param locatorStageB Second locator
     * @param nameOfElement Name
     */


    /**
     * easy adapting from Selenium RC locators. CSS, ID, LINK
     * @param stringLocator String locator
     * @param nameOfElement Name
     */
    protected BaseElement(String stringLocator, final String nameOfElement) {
        String clearLoc = null;
        if (stringLocator.contains(CSS)) {
            clearLoc = stringLocator.replaceFirst(CSS, "");
            locator = By.cssSelector(clearLoc);
            name = nameOfElement;
        } else if (stringLocator.contains(ID)) {
            clearLoc = stringLocator.replaceFirst(ID, "");
            locator = By.id(clearLoc);
            name = nameOfElement;
        } else if (stringLocator.contains(LINK)) {
            clearLoc = stringLocator.replaceFirst(LINK, "");
            locator = By.linkText(clearLoc);
            name = nameOfElement;
        }
    }



    /**
     * @return
     * @uml.property name="locator"
     * @return Locator
     */
    public By getLocator() {
        return locator;
    }



    /**
     * The implementation of an abstract method for logging of BaseEntity
     * @param message Message to display in the log
     * @return Formatted message (containing the name and type of item)
     */


    /**
     * The method returns the element type (used for logging)
     * @uml.property name="elementType"
     * @return Type of element
     */
    protected abstract void getElementType();

    /**
     * Send keys.
     */

    /*
    public void sendKeys(Keys key) {
        waitForIsElementPresent();
       new Driver().findElement(locator).sendKeys(key);
    }

    /**
     * Wait for element is present.
     */

    /*
    public void waitForIsElementPresent() {

        isPresent(Integer.valueOf(browser.getTimeoutForCondition()));
        // troubleshooting if element is not found
        if (!element.isDisplayed()) {//Browser.getTroubleShooting
            performTroubleShooting();
        }
        Assert.assertTrue(element.isDisplayed());
    }


    /**
     * Performing troubleshooting via changing active locator, output log and report.
     */
    /*
    private void performTroubleShooting(){
        int length = decrementLocator(locator).toString().length();
        try {
            for (int i = 0; i < length; i++) {
                decrementLocator(locator);
                Boolean result = isPresent(TIMEOUT_WAIT_0);
                if (result) {
                    break;
                }
            }
        } catch (Exception e) {
        }
        finally {
        }
    }

    /**
     * Decrement type of locator (troubleshooting).
     * @param locator
     * @return By
     */
    private By decrementLocator(By locator){
        for (Field field : locator.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String strLocator = (String)field.get(locator);
                field.set(locator,strLocator.substring(0, strLocator.length()-1));
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return locator;
    }


    /**
     * Click on the item.
     */
    /*
    public void click() {
        waitForIsElementPresent();
        browser.getDriver().getMouse().mouseMove(element.getCoordinates());
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    };




    /**
     * Get the item text (inner text).
     * @return Text of element
     */
    /*
    public String getText() {
        waitForIsElementPresent();
        return element.getText();
    }



    protected String id;

    /**
     * Check for is element present on the page.
     * @return Is element present
     */
    /*
    public boolean isPresent() {
        boolean isPresent = isPresent(TIMEOUT_WAIT_0);
        return isPresent;
    }
    /**
     * Check for is element present on the page.
     * @return Is element present
     */

    /*
    public boolean isPresent(int timeout) {

        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
        browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_WAIT_0, TimeUnit.SECONDS);
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        List<WebElement> list = driver.findElements(locator);
                        for (WebElement el : list) {
                            if (el instanceof RemoteWebElement && el.isDisplayed()) {
                                element = (RemoteWebElement) el;
                                return element.isDisplayed();
                            }
                        }
                        element = (RemoteWebElement) driver.findElement(locator);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
        }
        return false;
    }
*/




}

