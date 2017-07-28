package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.webdriver.jquery.ByJQuerySelector;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import pages.LumensHomePage;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;

public class Footer{
    LumensHomePage lumensHomePage;

    private By footerRow_1=new ByJQuerySelector(".row-1");
    private By footerNavigationLinks=new ByJQuerySelector("#foot-nav-link-row");
    private By footerCopyrightLumensLink = new ByJQuerySelector(".row-4");

    private By lowPriceGuaranteeLink=new ByJQuerySelector("a[href*='pricing.html']:contains('Low-Price Guarantee')");
    private By freeShippingLink=new ByJQuerySelector("a[href*='#']:contains('Free Shipping')");
    private By easyReturnsLink=new ByJQuerySelector(".row-1 a[href*='returns.html']:contains('Easy Returns')");
    private By expertServiceLink=new ByJQuerySelector("a[href*='expert-service.html']:contains('Expert Service')");

    private By aboutLumensLink=new ByJQuerySelector("a[href*='about-us.html']:contains('About Lumens')");
    private By customerServiceLink=new ByJQuerySelector("a[href*='faq.html']:contains('Customer Service')");
    private By contactUsLink=new ByJQuerySelector("a[href*='contact-us.html']:contains('Contact Us')");
    private By sacramentoShowroomLink=new ByJQuerySelector("a[href*='showroom.html']:contains('Sacramento Showroom')");
    private By becomeAnAffiliateLink=new ByJQuerySelector("a[href*='affiliates.html']:contains('Become an Affiliate')");
    private By siteMapLink=new ByJQuerySelector("a[href*='sitemap']:contains('Site Map')");
    private By returnsLink=new ByJQuerySelector("#foot-nav-link-row a[href*='returns.html']:contains('Returns')");
    private By privacyPolicyLink=new ByJQuerySelector("a[href*='privacy-policy.html']:contains('Privacy Policy')");
    private By shippingCostsLink=new ByJQuerySelector("a[href*='free-us-shipping']:contains('Shipping Costs')");
    private By internationalOrdersLink=new ByJQuerySelector("a[href*='international-ordering']:contains('International Orders')");
    private By careersLink=new ByJQuerySelector("a[href*='careers']:contains('Careers')");

    private By facebookIconSocial = new ByJQuerySelector("a[href*='facebook']");
    private By twitterIconSocial = new ByJQuerySelector("a[href*='twitter']");
    private By googlePlusIconSocial = new ByJQuerySelector("a[href*='plus.google']");
    private By houzzIconSocial = new ByJQuerySelector("a[href*='houzz']");
    private By pinterestIconSocial = new ByJQuerySelector("a[href*='pinterest']");
    private By instagramIconSocial = new ByJQuerySelector("a[href*='instagram']");
    private By youtubeIconSocial = new ByJQuerySelector("a[href*='youtube']");
    private By blogIconSocial = new ByJQuerySelector("a[href*='blog']");
    private By emailPreferenceIconSocial = new ByJQuerySelector("a[href*='email-preference']");

    private By dwellMagazineLink = new ByJQuerySelector("a[href*='dwell-magazine.html']");
    private By elleDecorMagazineLink = new ByJQuerySelector("a[href*='elle-decor-magazine.html']");
    private By architectualDigestLink = new ByJQuerySelector("a[href*='architectural-digest-magazine.html']");
    private By interiorDesignMagazineLink = new ByJQuerySelector("a[href*='interior-design-magazine.html']");

    private By bizrateRatingsForLumensLightLivingLink = new ByJQuerySelector("a[href='http://www.bizrate.com/ratings_guide/cust_reviews__mid--116578.html']");
    private By lumensLightsLivingBbbBusinessReviewLink = new ByJQuerySelector("a[href='http://www.bbb.org/northeast-california/business-reviews/lighting-fixtures-retail/lumens-lights-living-in-sacramento-ca-15003431/#bbbonlineclick']");
    private By americanLightingAssociation = new ByJQuerySelector("img[alt='American Lighting Association']");
    private By beOriginalMemberLink = new ByJQuerySelector("a[href*='be-original.html']");

    @Step
    public void shouldSeeFooterOnThePage(){
        lumensHomePage.closeUiDialogWidget();
        Assert.assertThat("LOW-PRICE GUARANTEE\n" +
                "FREE SHIPPING\n" +
                "EASY RETURNS\n" +
                "EXPERT SERVICE", is(getDriver().findElement(footerRow_1).getText()));

        assertThat(getDriver().findElement(lowPriceGuaranteeLink).isDisplayed());
        assertThat(getDriver().findElement(freeShippingLink).isDisplayed());
        assertThat(getDriver().findElement(easyReturnsLink).isDisplayed());
        assertThat(getDriver().findElement(expertServiceLink).isDisplayed());


        Assert.assertThat("About Lumens Customer Service Contact Us Sacramento Showroom Become an Affiliate Site Map Returns Privacy Policy (updated) Shipping Costs\n" +
                "Careers", is(getDriver().findElement(footerNavigationLinks).getText()));
        assertThat(getDriver().findElement(aboutLumensLink).isDisplayed());
        assertThat(getDriver().findElement(customerServiceLink).isDisplayed());
        assertThat(getDriver().findElement(contactUsLink).isDisplayed());
        assertThat(getDriver().findElement(sacramentoShowroomLink).isDisplayed());
        assertThat(getDriver().findElement(becomeAnAffiliateLink).isDisplayed());
        assertThat(getDriver().findElement(siteMapLink).isDisplayed());
        assertThat(getDriver().findElement(returnsLink).isDisplayed());
        assertThat(getDriver().findElement(privacyPolicyLink).isDisplayed());
        assertThat(getDriver().findElement(shippingCostsLink).isDisplayed());
      //  assertThat(getDriver().findElement(internationalOrdersLink).isDisplayed());
        assertThat(getDriver().findElement(careersLink).isDisplayed());


        assertThat(getDriver().findElement(facebookIconSocial).isDisplayed());
        assertThat(getDriver().findElement(twitterIconSocial).isDisplayed());
        assertThat(getDriver().findElement(googlePlusIconSocial).isDisplayed());
        assertThat(getDriver().findElement(pinterestIconSocial).isDisplayed());
        assertThat(getDriver().findElement(instagramIconSocial).isDisplayed());
        assertThat(getDriver().findElement(houzzIconSocial).isDisplayed());
        assertThat(getDriver().findElement(youtubeIconSocial).isDisplayed());
        assertThat(getDriver().findElement(blogIconSocial).isDisplayed());
        assertThat(getDriver().findElement(emailPreferenceIconSocial).isDisplayed());

        assertThat(getDriver().findElement(dwellMagazineLink).isDisplayed());
        assertThat(getDriver().findElement(elleDecorMagazineLink).isDisplayed());
        assertThat(getDriver().findElement(architectualDigestLink).isDisplayed());
        assertThat(getDriver().findElement(interiorDesignMagazineLink).isDisplayed());

        assertThat(getDriver().findElement(bizrateRatingsForLumensLightLivingLink).isDisplayed());
      //  assertThat(getDriver().findElement(lumensLightsLivingBbbBusinessReviewLink).isDisplayed());
        assertThat(getDriver().findElement(americanLightingAssociation).isDisplayed());
        assertThat(getDriver().findElement(beOriginalMemberLink).isDisplayed());

        JavascriptExecutor js=(JavascriptExecutor) getDriver();
        Object val=js.executeScript("return $('.row-4 p').clone().children().remove().end().text().trim()");
        Assert.assertThat("© Copyright 2017  by Lumens Light + Living, all rights reserved. \"Lumens\" and \"Light + Living\" are registered trademarks.", containsString(val.toString()));
        assertThat(getDriver().findElement(footerCopyrightLumensLink).isDisplayed());
    }
}
