package webdriver.elements;

import org.openqa.selenium.By;
import webdriver.BaseElement;

/**
 * Created by user on 12.10.2016.
 */
public class Label extends BaseElement {

    public Label(final By locator, final String name) {
        super(locator, name);
    }

    public Label(String string, String name) {
        super(string, name);
    }



    public Label(By locator) {
        super(locator);
    }

    protected void getElementType() {
    }

}
