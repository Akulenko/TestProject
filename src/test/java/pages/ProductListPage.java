package pages;

import appmanager.BrowserBase;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class ProductListPage extends PageObject {

    private final BrowserBase browser = new BrowserBase();

    public By paginationPage = By.className("pagination");
}
