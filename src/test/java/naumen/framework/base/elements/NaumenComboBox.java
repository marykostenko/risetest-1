package naumen.framework.base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * Class describing the combobox (dropdown list)
 */
public class NaumenComboBox extends ComboBox {

	private final String tmlOption = "//li/a[contains(.,'%s')]";

	public NaumenComboBox(By locator, String name) {
		super(locator, name);
	}

	public NaumenComboBox(By locator) {
		super(locator);
	}

	public NaumenComboBox(final String locator, final String name) {
		super(locator, name);
	}
	protected String getElementType() {
		return getLoc("loc.combobox");
	}

	public void selectByText(String value){
	    waitAndAssertIsPresent();
		info(String.format(getLoc("loc.selecting.value")+ " '%1$s'", value));
		element.findElementByTagName("i").click();

        String[] vs = value.split(" ");
        String[] vSpl = new String[vs.length * 3];
        int j = 0;
        for(int i=0; i<3; i++) {
            System.arraycopy(vs, 0, vSpl, j, vs.length);
            j += vs.length;
        }
        String xPathString = String.format("//li/a[contains(.,'%1$s') and contains(.,'%2$s') and contains(.,'%3$s')]", vSpl);
		Link option = new Link(By.xpath(xPathString),value);
		new Actions(browser.getDriver()).moveToElement(option.getElement()).click(option.getElement()).build().perform();
		browser.waitForPageToLoad();
	}


}
