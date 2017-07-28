package features.Navigation;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.NavigationUser;
import steps.ProductListPageSteps;
import steps.ProductPageSteps;
import webdriver.RunConfigurator;

@RunWith(SerenityRunner.class)
public class WhenNavigatingFromPDP {
    int pageNumber = 2;
    RunConfigurator runConfigurator=new RunConfigurator();
    private String urlCategory=runConfigurator.GetValue("urlCategory");

    @Steps
    NavigationUser mark;

    @Steps
    ProductPageSteps productPage;
    @Steps
    NavigationUser navigation;
    @Steps
    ProductListPageSteps PLP;

    @Managed
    WebDriver driver;

    @Test
    @Screenshots(onlyOnFailures = true)
    public void  shouldBeRedirectedBackToTheSameProductResultsPage (){
        //Given
        mark.isOnTheHomePage();
        //When
        productPage.openPageByUrl(urlCategory);

    }
}
