package naumen.framework.base.elements;

import org.openqa.selenium.By;

import java.util.Random;

/**
 * Class describing the checkbox
 */
public class CheckBox extends BaseElement {

	public CheckBox(final By locator, final String name) {
		super(locator, name);
	}

	protected String getElementType() {
		return getLoc("loc.checkbox");
	}

	public CheckBox(By locator) {
		super(locator);
	}

	public CheckBox(String stringLocator, String name) {
		super(stringLocator, name);
	}

	/**
	 * Checking the checkbox value when an invalid value test stops
	 * @param expectedState expected value
	 */
	public void assertState(boolean expectedState) {
		assertEquals(formatLogMsg(getLoc("loc.checkbox.wrong.value")), expectedState, isChecked());
	}

	/**
	 * set true
	 */
	public void check() {
		check(true);
	}

	/**
	 * Set value
	 * @param state value (true/false)
	 */
	public void check(boolean state) {
		waitAndAssertIsPresent();
		info(String.format(getLoc("loc.setting.value") + " '%1$s'", state));
		for(int i = 0; i <= 2; i++){
			try{
				if (state != element.isSelected()) {
					element.click();
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Set a random value
	 * @return set value
	 */
	public boolean checkRandom() {
		boolean state = new Random().nextBoolean();
		check(state);
		return state;
	}

	/**
	 * Get the value of the checkbox (true / false)
	 */
	public boolean isChecked() {
		waitAndAssertIsPresent();
		return element.isSelected();
	}

	/**
	 * reverse state
	 */
	public void toggle() {
		check(!isChecked());
	}

	/**
	 * Set the checkbox to false
	 */
	public void uncheck() {
		check(false);
	}

	@Override
	public Boolean isElementMissed(){
		return super.isElementMissed();
	}
}