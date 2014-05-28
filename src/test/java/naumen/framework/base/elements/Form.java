package naumen.framework.base.elements;

import org.openqa.selenium.By;

/**
 * Class describing the Form
 */
public class Form extends BaseElement{

    /**
     *
     * @param locator
     * @param name
     */
    public Form(final By locator, final String name) {
        super(locator, name);
    }


    public Form(String string, String name) {
        super(string, name);
    }


    protected String getElementType() {
        return getLoc("loc.form");
    }

    public boolean isEnabled(){
        return this.getElement().isEnabled();
    }

    public Form(By locator) {
        super(locator);
    }

    @Override
    public Boolean isElementMissed(){
        return super.isElementMissed();
    }
}
