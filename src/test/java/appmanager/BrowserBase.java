package appmanager;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BrowserBase extends PageObject {
    public boolean isElementPresent(By locatorKey) {
        try {
            getDriver().findElement(locatorKey);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isElementVisible(String cssLocator){
        return getDriver().findElement(By.cssSelector(cssLocator)).isDisplayed();
    }


    public void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void type(By locator, String text) {
        getDriver().findElement(locator).click();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(text != null){
            String existingText = getDriver().findElement(locator).getAttribute("value");
            if(!text.equals(existingText)){
                getDriver().findElement(locator).clear();
                getDriver().findElement(locator).sendKeys(text);
            }
        }
    }

    public void type(WebElement element, String text) {
        element.click();
        if(text != null){
            String existingText = element.getAttribute("value");
            if(!text.equals(existingText)){
                element.clear();
                element.sendKeys(text);
            }
        }
    }

    public void select(By locator, String text) {
        if(text != null){
            Select droplist = new Select(getDriver().findElement(locator));
            droplist.selectByVisibleText(text);
        }
    }

    public void moveToElement(By element)
    {
        WebDriver driver = getDriver();
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(element);
        action.moveByOffset(100,100).build().perform();
        action.moveToElement(elem).build().perform();
        this.sleep(2);
    }

    public void moveToElement(WebElement element)
    {
        WebDriver driver = getDriver();
        Actions action = new Actions(driver);
        action.moveByOffset(100,100).build().perform();
        action.moveToElement(element).build().perform();
        this.sleep(2);
    }

    public void sleep(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }

    public String getText(By element){
        if(isElementPresent(element)){
            return  getDriver().findElement(element).getText();
        }
        return null;
    }

    public String getText(WebElement element){
        if(isElementPresent(element)){
            return  element.getText();
        }
        return null;
    }

    public void selectFromDropdown(By element, String option){
        Select dropdown = new Select(getDriver().findElement(element));
        dropdown.selectByVisibleText(option);
        this.sleep(2);
    }

    public void selectFromDropdown(WebElement element, String option){
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(option);
        this.sleep(2);
    }
}

