package steps;

import appmanager.BrowserBase;
import model.ProductData;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LumensHomePage;
import pages.ProductDetailsPage;
import pages.ReviewsSection;
import pages.SearchResultsPage;
import webdriver.RunConfigurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProductPage {

    ProductDetailsPage productDetailsPage;
    LumensHomePage lumensHomePage;
    SearchResultsPage searchPage;
    BrowserBase browser;
    ReviewsSection reviewsSection;

    RunConfigurator runConfigurator = new RunConfigurator();

    private String[] navigationTabs = {"DETAILS", "AVAILABILITY", "DIAGRAM", "REVIEWS", "PRODUCT FAMILY"};
    private String[] liveChatTitle = {"Chat with us", "Contact us"};
    private String[] liveChatInputFields = {"Name*", "Email Address*", "I have a question about...*", "Phone Number (numbers only - no hyphen)", "Message:*"};
    private String[] shareFriendInputFields = {"Friend's Name", "Friend's Email", "Your First Name", "Your Email"};


    //  private String priceOfProduct = runConfigurator.GetValue("priceOfProduct");
    private String quantityOfProduct = runConfigurator.GetValue("quantityOfProduct");
    private By salePrice = By.xpath("//*[@id='spaceController']/div[1]/div[2]/div/div");
    private By addedToCartDialog = By.cssSelector("#dialogcontainer");
    private By continueShoppingButton = new ByJQuerySelector(".minicartclose:contains('CONTINUE SHOPPING'):visible");
    private By checkoutButton = new ByJQuerySelector(".minicart-btn-viewcart:contains('CHECKOUT'):visible");
    private By addedQtyProduct = new ByJQuerySelector(".quickviewqty:visible");
    private By priceProduct = new ByJQuerySelector(".productprice:visible");
    private By nameProduct = By.cssSelector("#dialogcontainer .name a");
    private By closeCartDialogButton = new ByJQuerySelector(".ui-dialog .ui-dialog-titlebar-close:visible");
    //  private By valueOption = By.cssSelector(".swatchanchor");

    private By facebookSocialPlatformButton = By.cssSelector("#soc-facebook-show.soc-show.opened");
    private By pinterestSocialPlatformButton = By.cssSelector("#soc-pinterest-show.soc-show.opened");
    private By houzzSocialPlatformButton = By.cssSelector("#soc-houzz-show.soc-show.opened");
    private By contactUsWindow = By.cssSelector(".lp_pages_area");
    private By contactUsWindowName = By.xpath("//*[@id='lpChat']//div[2]/span");
    private By closeButtonContactUsWindow = new ByJQuerySelector(".lp_maximized .lp_close");
    private By minimizeButtonContactUsWindow = new ByJQuerySelector(".lp_maximized .lp_minimize-icon");
    private By contactUsInputFieldName = By.cssSelector(".lp_question_label");
    private By contactUsInputField = By.cssSelector(".lp_input-field");
    private By contactUsQuestionField = By.cssSelector(".lp_select_field");
    private By contactUsMessageTextField = By.cssSelector(".lp_textarea_field");
    private By canceButtonContactUsWindow = new ByJQuerySelector(".lp_cancel_button");
    private By submitButtonContactUsWindow = new ByJQuerySelector(".lp_submit_button");
    private By seeMoreDetailsLink = new ByJQuerySelector("a:contains('See More Details')");
    private By dialogWidow = new ByJQuerySelector(".ui-dialog:visible");
    private By dialogWindowLabelName = new ByJQuerySelector(".ui-dialog:visible .labeltext");
    private By activeNavigationTab = By.cssSelector("#pdpTabsDiv .pdpTab.ui-tabs-active");
    private By lumensLightingDesignGlossaryLink = By.cssSelector("a[href*='glossary']");
    private By printThisTabLink = new ByJQuerySelector(".printTab:visible");
    private By sendMeCopyEmailCheckbox_ShareFriend = new ByJQuerySelector(".ui-dialog:visible .copyMe");
    private By subscribeMeCheckbox_ShareFriend = new ByJQuerySelector(".ui-dialog:visible .emailsignup");
    private By addAnotherFriend_ShareFriend = new ByJQuerySelector(".ui-dialog:visible .addFriendCont a:eq(1)");
    private By messageTextBox_ShareFriend = By.cssSelector("#dwfrm_sendtofriend_message");
    private By privacyPolicyLink_ShareFriend = new ByJQuerySelector(".ui-dialog:visible a[href*='privacy']");
    private By readReviewLink = By.cssSelector(".productreview #BVRRRatingSummaryLinkReadID .BVRRCount");
    private By textNoReviews = By.cssSelector(".BVRRCustomDisplayContentTitleNoReviews");
    private By writeFirstReviewLinkToReviewTab = By.className("BVRRCustomWriteFirstReviewLinkText");
    private By writeFirstReviewLinkInSummarySection = By.id("BVRRRatingSummaryLinkWriteFirstID");
    private By checkMark = By.cssSelector(".selected .selectCheck");


    public void openPageByUrl(String url, WebDriver driver) {
        driver.navigate().to(driver.getCurrentUrl() + url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        lumensHomePage.closeUiDialogWidget();
    }

    @Step
    public void openPageByUrl(String url) {
        getDriver().navigate().to(getDriver().getCurrentUrl() + url);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        lumensHomePage.closeUiDialogWidget();
    }

    @Step
    public void clickOnProduct(boolean isOnSale, boolean hasRating) {
        List<WebElement> listingSearch;
        if (isOnSale == true && hasRating == true) {
            searchPage.filterByNavigationGroup("By Promotion");
            searchPage.filterByNavigationGroup("By Customer Rating");
            listingSearch = searchPage.listingSearch;
            listingSearch.get(0).click();
        }
        if (isOnSale == true && hasRating == false) {
            searchPage.filterByNavigationGroup("By Promotion");
            listingSearch = getDriver().findElements(By.cssSelector(".product .flag-sale"));
            //listingSearch = searchPage.listingSearch;
            listingSearch.get(0).findElement(By.xpath("../..")).findElement(By.cssSelector(".image")).click();
        }
        if (isOnSale == false && hasRating == true) {
            searchPage.filterByNavigationGroup("By Customer Rating");
            List<WebElement> products = getDriver().findElements(By.className("product"));
            for (WebElement product : products) {
                if (product.findElement(By.className("promo-icons")).getText().equals("")) {
                    product.findElement(By.cssSelector("h3")).click();
                    break;
                }
            }
        }
        if (isOnSale == false && hasRating == false) {
            listingSearch = searchPage.listingSearch;
            listingSearch.get(0).click();
        }
    }

    @Step("Product details page is opened")
    public void openProductPageByUrl(String url) {
        getDriver().navigate().to(getDriver().getCurrentUrl() + url);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        lumensHomePage.closeUiDialogWidget();
    }

    @Step
    public void shouldSeeElementsOnThePage() {
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        lumensHomePage.closeUiDialogWidget();
        assertThat(productDetailsPage.productDetailImage.isDisplayed());
        int width = productDetailsPage.productDetailImage.getSize().getWidth();
        int hight = productDetailsPage.productDetailImage.getSize().getHeight();
        Assert.assertEquals(width, 345);
        Assert.assertEquals(hight, 345);

        assertThat(productDetailsPage.addToCartButton.isDisplayed());
        assertThat(productDetailsPage.liveChatLink.isDisplayed());
        assertThat(productDetailsPage.printerFriendlyLink.isDisplayed());
        assertThat(productDetailsPage.sendToAFriendLink.isDisplayed());
        assertThat(productDetailsPage.emailAQuestionLink.isDisplayed());
        assertThat(productDetailsPage.checkAvailabilityLink.isDisplayed());
        //  assertThat(productDetailsPage.specSheetLink.isDisplayed());
        assertThat(productDetailsPage.facebookIcon.isDisplayed());
        assertThat(productDetailsPage.pinterestIcon.isDisplayed());
        assertThat(productDetailsPage.houzzIcon.isDisplayed());
        assertThat(productDetailsPage.productName.isDisplayed());
        assertThat(productDetailsPage.brand.isDisplayed());
        assertThat(productDetailsPage.price.isDisplayed());
        assertThat(productDetailsPage.quantity.isDisplayed());
        assertThat(productDetailsPage.addToProjectLink.isDisplayed());

        List<WebElement> nameTabs = productDetailsPage.navigationTabs;
        for (int i = 0; i < nameTabs.size(); i++) {
            String nameTab = nameTabs.get(i).getText();
            if (nameTab != null && !nameTab.isEmpty()) {
                assertTrue(Arrays.asList(navigationTabs).contains(nameTab));
            }
        }
        shouldBePresentYouMayAlsoLikeSection();
    }

    @Step
    @Ignore
    @Title("The step 'Should be present you may also like section' was ignored according with issue #ES-3342")
    public void shouldBePresentYouMayAlsoLikeSection(){
        WebElement parent = productDetailsPage.youMayAlsoLikeSection.findElement(By.xpath(".."));
        assertThat(parent.isDisplayed());
        Assert.assertEquals("YOU MAY ALSO LIKE", productDetailsPage.youMayAlsoLikeSection.getText());
    }

    @Step("Select first value of additional options of product")
    public ProductData selectFirstValueOfAdditionalOption() {
        ProductData product = productDetailsPage.selectOptions();
        productDetailsPage.inputQuantityOfProduct(quantityOfProduct);
        productDetailsPage.fillProductData(product);
        return product;
    }

    @Step
    public void clickAddToCartButton() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        productDetailsPage.addToCartButton.click();
    }


    @Step("The cart dialog with added product is opened")
    public void shouldSeeAddedToYourCartDialog(ProductData product) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addedToCartDialog));
        assertThat(getDriver().findElement(addedToCartDialog).isDisplayed());
        assertThat("Added to your cart");
        assertThat("This item ships FREE");
        assertThat(getDriver().findElement(continueShoppingButton).isDisplayed());
        assertThat(getDriver().findElement(checkoutButton).isDisplayed());
        for (int i = 0; i < product.getOptions().size(); i++) {
            assertThat(product.getOptions().get(i).getValueOption());
        }
        assertThat(product.getName().contains(getDriver().findElement(nameProduct).getText()));
        Assert.assertEquals("Qty: " + quantityOfProduct, getDriver().findElement(addedQtyProduct).getText());
        Assert.assertEquals(product.getPrice(), getDriver().findElement(priceProduct).getText());
        assertThat("By " + product.getBrand());
        getDriver().findElement(closeCartDialogButton).click();
    }

    @Step
    @Screenshots(onlyOnFailures = true)
    public void shouldSeeFlyoverWithAddedProduct(ProductData product) {
        shouldSeeHeaderInfo();
        shouldSeeValueOfOptionsInTheFlyover(product);
        shouldSeeSalesPriceInTheFlyover();
        shouldSeeQtyInTheFlyover(product);
        shouldSeeNameProductInTheFlyover(product);
        shouldSeeBrandNameInTheFlyover(product);
        shouldSeeSubtotalInTheFlyover(product);
        shouldSeeProductIdInTheFlyover(product);
        shouldSeeProductImageInTheFlyover();
        shouldSeeContinueShoppingButtonInTheFlyover();
        shouldSeeViewCartButtonInTheFlyover();
        shouldSeeFreeShippingMessageInTheFlyover();
        shouldSeeLowPriceMessageInTheFlyover();
    }

    @Step
    public void shouldSeeHeaderInfo() {
        if (!browser.isElementVisible(lumensHomePage.miniProductInfo)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        Assert.assertThat("Item\n" +
                "Price\n" +
                "QTY\n" +
                "Amount", is(getDriver().findElement(lumensHomePage.miniProductInfo).getText()));
    }

    @Step
    public void shouldSeeValueOfOptionsInTheFlyover(ProductData product) {
        if (!browser.isElementVisible(lumensHomePage.valueOptionsProductInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        for (int i = 0; i < product.options.size(); i++) {
            assertTrue(browser.getText(lumensHomePage.valueOptionsProductInFlyover).contains(product.options.get(i).getValueOption()));
        }
    }

    @Step
    public void shouldSeeSalesPriceInTheFlyover() {
        if (!browser.isElementVisible(lumensHomePage.viewCartFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        if (browser.isElementPresent(By.cssSelector("#spaceController .promo"))) {
            assertTrue(browser.isElementPresent(lumensHomePage.saleProductPriceInFlyover));
        }
    }

    @Step
    public void shouldSeeQtyInTheFlyover(ProductData product) {
        if (!browser.isElementVisible(lumensHomePage.qtyOfProductInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        String qty = browser.getText(lumensHomePage.qtyOfProductInFlyover);
        Assert.assertEquals(quantityOfProduct, qty);
    }

    @Step
    public void shouldSeeNameProductInTheFlyover(ProductData product) {
        if (!browser.isElementVisible(lumensHomePage.nameProductInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        String name = browser.getText(lumensHomePage.nameProductInFlyover);
        assertThat(product.getName().contains(name));
    }

    @Step
    public void shouldSeeSubtotalInTheFlyover(ProductData product) {
        if (!browser.isElementVisible(lumensHomePage.subtotalInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        String total = browser.getText(lumensHomePage.subtotalInFlyover);
        Assert.assertEquals(product.getPrice(), total);
    }

    @Step
    public void shouldSeeBrandNameInTheFlyover(ProductData product) {
        if (!browser.isElementVisible(lumensHomePage.brandOfProductInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        String brand = browser.getText(lumensHomePage.brandOfProductInFlyover);
        Assert.assertEquals("By " + product.getBrand(), brand);
    }

    @Step
    public void shouldSeeProductIdInTheFlyover(ProductData product) {
        if (!browser.isElementVisible(lumensHomePage.productIdInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        String id = browser.getText(lumensHomePage.productIdInFlyover);
        Assert.assertTrue(product.getProductID().equalsIgnoreCase(id));
    }

    @Step
    public void shouldSeeProductImageInTheFlyover() {
        if (!browser.isElementVisible(lumensHomePage.imageOfProductInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        assertTrue(browser.isElementPresent(lumensHomePage.imageOfProductInFlyover));
    }

    @Step
    public void shouldSeeContinueShoppingButtonInTheFlyover() {
        if (!browser.isElementVisible(lumensHomePage.continueShoppingButtonInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        Assert.assertEquals("CONTINUE SHOPPING", browser.getText(continueShoppingButton));
    }

    @Step
    public void shouldSeeViewCartButtonInTheFlyover() {
        if (!browser.isElementVisible(lumensHomePage.viewCartButtonInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        Assert.assertEquals("VIEW CART", browser.getText(lumensHomePage.viewCartButtonInFlyover));
    }

    @Step
    public void shouldSeeFreeShippingMessageInTheFlyover() {
        if (!browser.isElementVisible(lumensHomePage.freeShippingLinkInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        Assert.assertThat("FREE SHIPPING\n" +
                "on orders of $75 or more*", containsString(browser.getText(lumensHomePage.freeShippingLinkInFlyover)));
        assertTrue(browser.isElementPresent(lumensHomePage.freeShippingLinkInFlyover));
    }

    @Step
    public void shouldSeeLowPriceMessageInTheFlyover() {
        if (!browser.isElementVisible(lumensHomePage.lumensGuaranteeLinkInFlyover)) {
            browser.moveToElement(lumensHomePage.viewCartButton);
        }
        Assert.assertThat("Lumens Low-Price Guarantee", containsString(browser.getText(lumensHomePage.lumensGuaranteeLinkInFlyover)));
        assertThat(browser.isElementPresent(lumensHomePage.lumensGuaranteeLinkInFlyover));
    }

    @Step
    public void shouldSee() {
        //assertThat(productDetailsPage.facebookIcon.isDisplayed());
        assertThat(productDetailsPage.pinterestIcon.isDisplayed());
        assertThat(productDetailsPage.houzzIcon.isDisplayed());

        //     productDetailsPage.facebookIcon.click();
        //  assertThat(getDriver().findElement(facebookSocialPlatformButton).isDisplayed());

//facebook click
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(facebookSocialPlatformButton));
        WebElement frame = getDriver().findElement(By.cssSelector(".soc-show.opened iframe"));
        getDriver().switchTo().frame(frame);
        WebElement srt = getDriver().findElement(new By.ByCssSelector(".pluginButtonImage"));
        assertThat(srt.isDisplayed());
        getDriver().switchTo().defaultContent();

        productDetailsPage.pinterestIcon.click();
        assertThat(getDriver().findElement(pinterestSocialPlatformButton).isDisplayed());
        //      WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pinterestSocialPlatformButton));
        assertThat(getDriver().findElement(pinterestSocialPlatformButton).isDisplayed());

        productDetailsPage.houzzIcon.click();
        assertThat(getDriver().findElement(houzzSocialPlatformButton).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(houzzSocialPlatformButton));
        assertThat(getDriver().findElement(houzzSocialPlatformButton).isDisplayed());


    }

    @Step
    public void clickFacebookSocialIcon() {
        assertThat(productDetailsPage.facebookIcon.isDisplayed());
        productDetailsPage.facebookIcon.click();
    }

    @Step
    public void shouldSlideFacebookSocialPlatformButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(facebookSocialPlatformButton));
        assertThat(getDriver().findElement(facebookSocialPlatformButton).isDisplayed());
    }

    @Step
    public void clickPinterestSocialIcon() {
        assertThat(productDetailsPage.pinterestIcon.isDisplayed());
        productDetailsPage.pinterestIcon.click();
    }

    @Step
    public void shouldSlidePinterestSocialPlatformButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pinterestSocialPlatformButton));
        assertThat(getDriver().findElement(pinterestSocialPlatformButton).isDisplayed());
    }

    @Step
    public void clickHouzzSocialIcon() {
        assertThat(productDetailsPage.houzzIcon.isDisplayed());
        productDetailsPage.houzzIcon.click();
    }

    @Step
    public void shouldSlideHouzzSocialPlatformButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(houzzSocialPlatformButton));
        assertThat(getDriver().findElement(houzzSocialPlatformButton).isDisplayed());
    }

    @Step
    public void clickLiveChatLink() {
        By linkButton = By.cssSelector("#pdpLiveChatLink");
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkButton));
        productDetailsPage.liveChatLink.click();
    }

    @Step
    public void shouldOpenContactUsWindow() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactUsWindow));
        assertThat(getDriver().findElement(contactUsWindow).isDisplayed());
        assertTrue(Arrays.asList(liveChatTitle).contains(getDriver().findElement(contactUsWindowName).getText()));
        assertThat(getDriver().findElement(closeButtonContactUsWindow).isDisplayed());
        assertThat(getDriver().findElement(minimizeButtonContactUsWindow).isDisplayed());
        List<WebElement> nameInputFields = getDriver().findElements(contactUsInputFieldName);
        for (int i = 0; i < nameInputFields.size(); i++) {
            String nameInputField = nameInputFields.get(i).getText();
            assertThat(Arrays.asList(liveChatInputFields).contains(nameInputField));
        }
        //assert count of input field
        List<WebElement> countInputFields = getDriver().findElements(contactUsInputField);
        int countInputField = countInputFields.size();

        if (browser.isElementPresent(contactUsQuestionField)) {
            WebElement selectQuestionDropdown = getDriver().findElement(contactUsQuestionField);
            Assert.assertThat("Choose item from the list\n" +
                    "an existing order\n" +
                    "a product I am interested in purchasing", is(selectQuestionDropdown.getText()));
            countInputField += 1;
        }
        if (browser.isElementPresent(contactUsMessageTextField)) {
            countInputField += 1;
        }
        Assert.assertEquals(nameInputFields.size(), countInputField);
        assertThat(getDriver().findElement(canceButtonContactUsWindow).isDisplayed());
        assertThat(getDriver().findElement(submitButtonContactUsWindow).isDisplayed());
    }

    @Step
    public void clickSeeMoreDetailsLink() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(seeMoreDetailsLink));
        getDriver().findElement(seeMoreDetailsLink).click();
    }

    @Step
    public void shouldOpenDetailsNavigationTab() {
        Assert.assertEquals("DETAILS", getDriver().findElement(activeNavigationTab).getText());
        shouldBeOpenLumensLightingDesignGlossaryPage();
        shouldHavePrintThisTabLink();
    }

    @Step
    public void shouldBeOpenLumensLightingDesignGlossaryPage() {
        assertThat(getDriver().findElement(lumensLightingDesignGlossaryLink).isDisplayed());
        // Store the current window handle
        String winHandleBefore = getDriver().getWindowHandle();
        getDriver().findElement(lumensLightingDesignGlossaryLink).click();
        // Switch to new window opened
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        Assert.assertEquals("A | Lighting and Design Glossary at Lumens.com", getDriver().getTitle());
        getDriver().close();
        // Switch back to original browser (first window)
        getDriver().switchTo().window(winHandleBefore);
    }

    @Step
    public void shouldHavePrintThisTabLink() {
        assertThat(getDriver().findElement(printThisTabLink).isDisplayed());
    }


    @Step
    public void shouldOpenAvailabilityNavigationTab() {
        Assert.assertEquals("AVAILABILITY", getDriver().findElement(activeNavigationTab).getText());
        shouldHavePrintThisTabLink();
    }

    @Step
    public void clickSendToFriendLink() {
        productDetailsPage.sendToAFriendLink.click();
    }

    @Step
    public void shouldOpenShareWithFriendDialog() {
        shouldSeeTitleDialogWindow("Share With a Friend");
        shouldSeeNameInputFieldToDialogWindow(shareFriendInputFields);
        shouldSeeNameElement("Send me a copy of this email.", sendMeCopyEmailCheckbox_ShareFriend);
        shouldSeeNameElement("Yes! I want in on email-only offers, sale alerts, new products and more. Privacy Policy", subscribeMeCheckbox_ShareFriend);
        shouldSeeNameElement("ADD ANOTHER FRIEND", addAnotherFriend_ShareFriend);
        shouldBeAbbleAddAnotherFriendShareFriendDialog();
        shouldSeePreviewSendCancelButtonsToShareFriendDialog();
        messageShouldNotBeMore250Characters();
        shouldBeOpenPrivacyAndSecurityPoliciesPage();
    }

    @Step("Should see the title '{0}'")
    public void shouldSeeTitleDialogWindow(String title) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialogWidow));
        assertThat(getDriver().findElement(dialogWidow).getText().contains(title));
    }

    @Step("Should see name of input fields in dialog window {0}")
    public void shouldSeeNameInputFieldToDialogWindow(String[] listInputFields) {
        List<WebElement> nameInputFields = getDriver().findElements(dialogWindowLabelName);
        for (int i = 0; i < nameInputFields.size(); i++) {
            String nameInputField = nameInputFields.get(i).getText();
            assertThat(Arrays.asList(listInputFields).contains(nameInputField));
        }
    }

    @Step("Should see '{0}' checkbox")
    public void shouldSeeNameElement(String name, By locator) {
        String text = getDriver().findElement(locator).getText().trim();
        Assert.assertEquals(name, getDriver().findElement(locator).getText().trim());
    }

    @Step
    public void shouldBeAbbleAddAnotherFriendShareFriendDialog() {
        int count = getDriver().findElements(dialogWindowLabelName).size();
        getDriver().findElement(addAnotherFriend_ShareFriend).click();
        Assert.assertEquals(count + 2, getDriver().findElements(dialogWindowLabelName).size());
    }

    @Step
    public void shouldSeePreviewSendCancelButtonsToShareFriendDialog() {
        Assert.assertTrue(browser.isElementPresent(By.cssSelector("#previewBtn")));
        Assert.assertTrue(browser.isElementPresent(By.cssSelector("#sendBtn")));
        Assert.assertTrue(browser.isElementPresent(By.cssSelector("#cancelBtn")));
    }

    @Step
    public void messageShouldNotBeMore250Characters() {
        getDriver().findElement(messageTextBox_ShareFriend).sendKeys("gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggendhhhhhhhhhh");
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String ss = (String) js.executeScript("return $('#dwfrm_sendtofriend_message').val();");
        assertEquals("gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggend", ss);
    }

    @Step
    @Screenshots(forEachAction = true)
    public void shouldBeOpenPrivacyAndSecurityPoliciesPage() {
        String winHandleBefore = getDriver().getWindowHandle();
        getDriver().findElement(privacyPolicyLink_ShareFriend).click();
        // Switch to new window opened
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), "Privacy and Security Policies"));
        Serenity.takeScreenshot();
        Assert.assertEquals("Privacy Policy | Privacy & Security at Lumens.com", getDriver().getTitle());
        getDriver().close();
        // Switch back to original browser (first window)
        getDriver().switchTo().window(winHandleBefore);
    }

    @Step
    public void clickReadReviewLink() {
        if (browser.isElementPresent(readReviewLink)) {
            getDriver().findElement(readReviewLink).click();
        } else {
            Assert.assertEquals("Be the First to Write a Review", getDriver().findElement(writeFirstReviewLinkInSummarySection).getText().trim());
            List<WebElement> nameTabs = productDetailsPage.navigationTabs;
            nameTabs.get(3).click();
        }
    }

    @Step
    public void shouldOpenReviewNavigationTab() {
        Assert.assertEquals("REVIEWS", getDriver().findElement(activeNavigationTab).getText());
        Assert.assertEquals("Check out what other customers think...", getDriver().findElement(By.className("revText")).getText());
        if (browser.isElementPresent(readReviewLink)) {
            shouldSeeSummaryOfCustomerRatingsAndReviewsSection();
            shouldSeeProductReviewsSection();
        } else {
            Assert.assertEquals("There are no reviews for this product.", getDriver().findElement(textNoReviews).getText());
            Assert.assertEquals("Be the first to write a Review.", getDriver().findElement(writeFirstReviewLinkToReviewTab).getText());
            Assert.assertTrue(getDriver().findElement(writeFirstReviewLinkToReviewTab).isDisplayed());
        }
        shouldHavePrintThisTabLink();
        shouldSeeRelatedSearchesAndProductsSection();

    }

    @Step
    public void shouldSeeSummaryOfCustomerRatingsAndReviewsSection() {
        Assert.assertEquals("Summary of Customer Ratings & Reviews", getDriver().findElement(By.cssSelector(".BVRRTitle.BVRRQuickTakeTitle")).getText());
        List<String> resultTitles = reviewsSection.summaryCustomerRatingAndReview.findElements(By.className("BVRRRatingNormalLabel")).stream().map(element -> element.getText()).collect(Collectors.toList());
        Assert.assertThat(reviewsSection.ratingLabels, is(resultTitles));
        Assert.assertEquals(resultTitles.size(), reviewsSection.summaryCustomerRatingAndReview.findElements(By.className("BVImgOrSprite")).size());
        String prosTitle = getDriver().findElement(By.cssSelector("#BVRRQuickTakeProTagsID .BVRRLabel")).getText();
        Assert.assertTrue(prosTitle.equalsIgnoreCase("pros"));
        String consTitle = getDriver().findElement(By.cssSelector("#BVRRQuickTakeConTagsID .BVRRLabel")).getText();
        Assert.assertTrue(consTitle.equalsIgnoreCase("cons"));
        //  assertTrue(reviewsSection.summaryCustomerRatingAndReview.findElement(reviewsSection.writeReviewButton).isDisplayed());
    }

    @Step
    public void shouldSeeProductReviewsSection() {
        Assert.assertEquals("Product Reviews", getDriver().findElement(By.cssSelector(".BVRRCustomDisplayContentTitle")).getText());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRNickname")).isDisplayed());
        List<String> productReviewlabel = reviewsSection.productReviewContent.findElements(By.cssSelector(".BVRRReviewRatingsContainer .BVRRLabel")).stream().map(element -> element.getText()).collect(Collectors.toList());
        Assert.assertThat(reviewsSection.ratingLabels, is(productReviewlabel));
        Assert.assertEquals(productReviewlabel.size(), reviewsSection.summaryCustomerRatingAndReview.findElements(By.className("BVImgOrSprite")).size());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRReviewDateContainer")).isDisplayed());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRReviewTitle")).isDisplayed());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRReviewProsContainer")).isDisplayed());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRReviewText")).isDisplayed());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRReviewFeedbackLinksContainer")).isDisplayed());
        Assert.assertTrue(reviewsSection.productReviewContent.findElement(By.className("BVRRReviewSocialLinksContainer")).isDisplayed());
    }

    @Step
    public void shouldSeeRelatedSearchesAndProductsSection() {
        String title = reviewsSection.relatedSearchesAndProductsSection.findElement(By.cssSelector("h3")).getText();
        Assert.assertThat("Related Searches and Products", containsString(title));
        Assert.assertTrue(reviewsSection.relatedSearchesAndProductsSection.findElement(By.id("brRSWproducts")).isDisplayed());
        Assert.assertTrue(reviewsSection.relatedSearchesAndProductsSection.findElement(By.id("br-related-searches-widget")).isDisplayed());
    }

    @Step
    public void addProductToTheEmptyShippingCart(String url) {
        if (browser.isElementPresent(By.cssSelector(".emptycart"))) {
            openPageByUrl(url);
            selectFirstValueOfAdditionalOption();
            clickAddToCartButton();
        }
    }

    @Step
    public void shouldSeeSaleBadgeAndSalePrice() {
        Assert.assertTrue(productDetailsPage.saleBadge.isDisplayed());
        Assert.assertEquals("SALE", productDetailsPage.saleBadge.getText());
        String price = getDriver().findElement(salePrice).getText();
        assertThat(price.contains("Sale"));
    }

    @Step
    public ProductData clickValueOfAdditionalOption() {
        return productDetailsPage.selectOptions();
    }

    @Step("Should see selected option")
    public void shouldSelectOptions(ProductData product) {
        optionsAreSelected(product, productDetailsPage.selectedOptions);
        selectedOptionsHaveCheckMark(productDetailsPage.options);
        selectedOptionIsAppearedToTheRightSection(product);
        theAddToCartButtonIsAvailable();
        productIdIsEqualPID(product);
    }

    @Step
    private void optionsAreSelected(ProductData product, List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            Assert.assertEquals(product.options.get(i).getValueOption(), elements.get(i).findElement(By.cssSelector(".displayValue")).getText());
        }
    }

    @Step("Selected options should have the check mark")
    public void selectedOptionsHaveCheckMark(List<WebElement> elements) {
        for (WebElement element : elements) {
            boolean visible = element.findElement(checkMark).isDisplayed();
            Assert.assertTrue(visible);
        }
    }

    @Step("Selected option is appeared to the right section")
    public void selectedOptionIsAppearedToTheRightSection(ProductData product) {
        selectedOptionDisplaysInsteadOfPleaseSelectAnOptionText(product);
        selectedOptionsHaveCheckMark(productDetailsPage.selectionOptions);
    }

    @Step("Selected option displays instead of the 'Please Select an Option' text")
    public void selectedOptionDisplaysInsteadOfPleaseSelectAnOptionText(ProductData product) {
        optionsAreSelected(product, productDetailsPage.selectionOptions);
        for (WebElement selected : productDetailsPage.selectionOptions) {
            Assert.assertNotEquals("Please Select an Option", selected.getText());
        }
    }

    @Step
    public void theAddToCartButtonIsAvailable() {
        String visibleButton = productDetailsPage.addToCartButton.getAttribute("disabled");
        Assert.assertEquals(null, visibleButton);
    }

    @Step
    public void shouldDeselectOptions() {
        optionsAreDeselected();
        optionsHaveNotCheckMark();
        pleaseSelectAnOptionTextToTheRightSection();
        theAddToCartButtonIsDisabled();
    }

    @Step
    public void theAddToCartButtonIsDisabled() {
        String visibleButton = productDetailsPage.addToCartButton.getAttribute("disabled");
        Assert.assertEquals("true", visibleButton);
    }

    @Step("'Please Select an Option' text to the right section")
    public void pleaseSelectAnOptionTextToTheRightSection() {
        for (WebElement selection : productDetailsPage.selectionOptions) {
            Assert.assertEquals("Please Select an Option", selection.getText());
        }
    }

    @Step("Deselected options haven't the check mark")
    public void optionsHaveNotCheckMark() {
        Assert.assertFalse(browser.isElementPresent(checkMark));
    }

    @Step
    public void optionsAreDeselected() {
        Assert.assertTrue(productDetailsPage.selectedOptions.size() == 0);
    }

    @Step
    public void openUrlWithAChangedPid(ProductData product) {
        String currentUrl = getDriver().getCurrentUrl();
        String id = productDetailsPage.cutID(product.getProductID());
        String newUrl = currentUrl.substring(0, currentUrl.lastIndexOf("-") + 1) + id + ".html";
        getDriver().navigate().to(newUrl);
        productIdIsEqualPID(product);
    }

    @Step
    public void productIdIsEqualPID(ProductData product) {
        String pid = productDetailsPage.cutID(product.getProductID());
        String id = productDetailsPage.cutID(productDetailsPage.productID.getText());
        Assert.assertEquals(pid, id);
    }

    @Step
    public void ShouldEnlargeOptionValue() {
        if (productDetailsPage.options.size() > 0) {
            WebElement option = productDetailsPage.options.get(0);
            String name = option.findElement(productDetailsPage.valueOption).getText();
            option.findElement(By.className("enlarge")).click();
            browser.waitFor(productDetailsPage.enlargeDialogBox);
            Assert.assertEquals(name, productDetailsPage.enlargeDialogBox.findElement(By.className("h2-enlarge")).getText());
            Assert.assertTrue(productDetailsPage.enlargeDialogBox.findElement(By.className("enlargeimage")).isDisplayed());
        }
    }

    @Step
    public void clickOnCheckAvailabilityLink() {
        productDetailsPage.checkAvailabilityLink.click();

    }

    @Step
    public void clickAddToCartButtonForProductInAvailableList() {


    }

    @Step
    public void shouldAddProductToCartFromAvailableList() {
        if (browser.isElementPresent(productDetailsPage.availableProductsTable)) {
            String PID = productDetailsPage.availableProductsTable.findElement(By.cssSelector("td")).getText();
            WebElement firstProduct = getDriver().findElement(By.cssSelector(".itemaddtocartbutton"));
            firstProduct.click();
            browser.waitFor(getDriver().findElement(addedToCartDialog));
            String link = getDriver().findElement(nameProduct).getAttribute("href");
            Assert.assertThat(link, containsString(PID));
        }
    }

    @Step
    public void shouldSeeListOfAvailableProduct() {
        WebElement table = productDetailsPage.availableProductsTable;
        browser.waitFor(table);
        String nameOption;
        ArrayList<String> columns = new ArrayList<>();
        List<WebElement> options = productDetailsPage.options;

        List<WebElement> listColumns = table.findElements(By.cssSelector("th"));
        for (WebElement listColumn : listColumns) {
            columns.add(listColumn.getText());
        }

        ArrayList<String> expectedColumns = new ArrayList<>();
        expectedColumns.add("Item ID");
        expectedColumns.add("MFR ID");
        for (int i = 0; i < options.size(); i++) {
            nameOption = options.get(i).findElement(productDetailsPage.nameOption).getText().replace("Select ", "").replace(":", "");
            expectedColumns.add(i + 2, nameOption);
        }
        expectedColumns.add("Price");
        expectedColumns.add("Availability");
        expectedColumns.add("Qty");

        Assert.assertEquals(columns.size(), expectedColumns.size());
        Assert.assertEquals(columns, expectedColumns);
    }

    @Step
    public void shouldSeeBrandNameOnPDPage() {
        assertTrue(productDetailsPage.brand.isDisplayed());
    }

    @Step
    public String clickOnBrandName() {
        productDetailsPage.waitFor(productDetailsPage.brand);
        String brandName = productDetailsPage.brand.getText();
        productDetailsPage.clickOn(productDetailsPage.brand);
        return brandName;
    }

    @Step
    public void shouldOpenBrandPage(String brand) {
        productDetailsPage.waitForTextToAppear(brand);
        String url = getLastUrlSegment();
        Assert.assertTrue(url.equalsIgnoreCase(brand));
        Assert.assertTrue(brand.equalsIgnoreCase(productDetailsPage.header.getText()));
    }

    private String getLastUrlSegment() {
        String url = getDriver().getCurrentUrl();
        url = StringUtils.stripEnd(url, "/");
        url = url.substring(url.lastIndexOf("/") + 1, url.length());
        return url;
    }

    @Step
    public void shouldNavigateByBreadcrumb() {
        productDetailsPage.waitFor(productDetailsPage.breadcrumb);
        ArrayList<WebElement> breadcrumbLinks = (ArrayList<WebElement>) productDetailsPage.breadcrumb.findElements(By.cssSelector("a"));
        for (int i = 0; i < breadcrumbLinks.size(); i++) {
            String breadcrumbURL = breadcrumbLinks.get(i).getAttribute("href");
            breadcrumbLinks.get(i).click();
            browser.waitForPageLoad(getDriver());
            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, breadcrumbURL);
            getDriver().navigate().back();
            breadcrumbLinks = (ArrayList<WebElement>) productDetailsPage.breadcrumb.findElements(By.cssSelector("a"));
        }
    }

    @Step
    public void clickSeeMoreLinkForAdditionalOption() {
        browser.waitForPageLoad(getDriver());
        productDetailsPage.seeMoreLink.click();
    }

    @Step
    public void shouldSeeScrollBarAndSeeMoreLink() {
        browser.waitForPageLoad(getDriver());
        Assert.assertTrue(productDetailsPage.seeMoreLink.isDisplayed());
        Assert.assertTrue(productDetailsPage.seeMoreLink.getText().contains("See More"));
        Assert.assertTrue(productDetailsPage.horizontalScrollBar.isDisplayed());
    }

    @Step
    public void shouldSeeSeeLessLinkAndHideScrollBar() {
        browser.waitForPageLoad(getDriver());
        Assert.assertTrue(productDetailsPage.seeMoreLink.isDisplayed());
        Assert.assertTrue(productDetailsPage.seeMoreLink.getText().contains("See Less"));
        Assert.assertFalse(browser.isElementPresent(productDetailsPage.horizontalScrollBar));
    }

    @Step
    public void clickSeeLessLinkForAdditionalOption() {
        browser.waitForPageLoad(getDriver());
        productDetailsPage.seeMoreLink.click();
    }

    @Step
    public void ShouldDisplayReturnableMessaging() {
        assertTrue(browser.isElementPresent(productDetailsPage.returnableMessage));
    }

    @Step
    public void ShouldNotDisplayReturnableMessaging() {
        assertFalse(browser.isElementPresent(productDetailsPage.returnableMessage));
    }

    @Step("Select first value of additional options of product")
    public void clickFirstValueOfAdditionalOption() {
        productDetailsPage.selectOptions();
    }

    @Step
    public void shouldBeDisplayBackToProductResultsAndNextLinksOnPDP() {
        browser.waitForPageLoad(getDriver());
        Assert.assertTrue(productDetailsPage.productNavigationLink.get(0).getText().contains("Back to product results"));
        Assert.assertTrue(productDetailsPage.productNavigationLink.get(1).getText().contains("Next"));
    }

    @Step
    public void clickNextNavigationLinkOnThePDP() {
        int lastIndex = productDetailsPage.productNavigationLink.size() - 1;
        productDetailsPage.productNavigationLink.get(lastIndex).click();
    }

    @Step
    public void shouldBeDisplayBackToProductResultsAndPreviousAndNextLinksOnPDP() {
        browser.waitForPageLoad(getDriver());
        Assert.assertTrue(productDetailsPage.productNavigationLink.get(0).getText().contains("Back to product results"));
        Assert.assertTrue(productDetailsPage.productNavigationLink.get(1).getText().contains("Previous"));
        Assert.assertTrue(productDetailsPage.productNavigationLink.get(2).getText().contains("Next"));

    }

    @Step
    public void clickBackToProductResultsLinkOnThePDP() {
        browser.waitForPageLoad(getDriver());
        productDetailsPage.productNavigationLink.get(0).click();
    }

    @Step
    public void clickOnAddToProjectButton() {
        browser.waitForPageLoad(getDriver());
        productDetailsPage.addToProjectLink.click();
    }
}
