package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class ReviewsSection extends PageObject {
    public List<String> ratingLabels = Arrays.asList("Overall", "Appearance", "Construction", "Features", "Material", "Value");

    @FindBy(css="#BVRRQuickTakeSummaryID")
    public
    WebElement summaryCustomerRatingAndReview;

    @FindBy(jquery="div[id*='BVSubmissionPopupContainer']")
    public
    WebElement productReviewContent;

    public By writeReviewButton = By.id("BVRRRatingSummaryLinkWriteID");

    @FindBy(css="#brRSwidget")
    public
    WebElement relatedSearchesAndProductsSection;
}

