package features.Navigation;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.Category;
import pages.PrimaryCategory;
import steps.Footer;
import steps.NavigationUser;

@RunWith(SerenityRunner.class)
public class WhenBrowsingProductCategories {

    @Steps
    NavigationUser navigationUser;
    @Steps
    Footer footer;

    @Managed
    WebDriver driver;


    @Test
    @Screenshots(onlyOnFailures=true)
    public void shouldBeAbleNavigateToTheMainCategory(){
        //given
        navigationUser.isOnTheHomePage();
        // When
        for(Category page:Category.values()) {
            navigationUser.navigatesToMainCategory(page, page.label());
            // Then
            navigationUser.shouldSeePageTitleContaining(page.name());
            navigationUser.shouldSeeURLPageContaining(page.label());
            navigationUser.should_use_https_for_page();
            footer.shouldSeeFooterOnThePage();
        }
    }

    @Test
    @Pending
    @Screenshots(onlyOnFailures=true)
    public void shouldBeAbleNavigateToThePrimaryCategory() {
        //given
        navigationUser.isOnTheHomePage();
        // When
        for(PrimaryCategory page:PrimaryCategory.values()) {
            navigationUser.navigatesToPrimaryCategory(page, page.label());
            // Then
            navigationUser.shouldSeePageTitleContaining(page.name());
            navigationUser.shouldSeeURLPageContaining(page.label());
            navigationUser.should_use_https_for_page();
            footer.shouldSeeFooterOnThePage();
        }
    }
}
