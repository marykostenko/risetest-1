package naumen.framework.base.elements;

import naumen.framework.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.AssertJUnit;

import java.io.File;

/**
 * The class that describes an input field
 */
public class TextBox extends BaseElement {

	/**
	 * Constructor
	 * @param locator locator
	 * @param name name
	 */
	public TextBox(final By locator, final String name) {
		super(locator, name);
	}

	/**
	 * Constructor
	 * @param string locator
	 * @param name name
	 */
	public TextBox(final String string, final String name) {
		super(string, name);
	}

	/**
	 * Returns Element type
	 * @return Element type
	 */
	protected String getElementType() {
		return getLoc("loc.text.field");
	}

	/**
	 * Constructor
	 * @param locator locator
	 */
	public TextBox(final By locator) {
		super(locator);
	}

	/**
	 * Enter the text in the box
	 * @param value text
	 */
	public void type(final String value) {
		waitAndAssertIsPresent();
		info(String.format(getLoc("loc.text.typing") + " '%1$s'", value));
		element.sendKeys(value);
	}

	/** Attach file
	 * @param localFile local file for attach
	 */
	public void setFile(final File localFile){
		waitAndAssertIsPresent();
		RemoteWebElement fileUpload = (RemoteWebElement) element;
	    String filePathToUse = localFile.getAbsolutePath();
	    fileUpload.setFileDetector(new LocalFileDetector());
	    fileUpload.sendKeys(filePathToUse);
	}

	/**
	 * File browsing
	 * @param value Filepath
	 */
	public void typeFile(final String value) {
		waitNotVisible();
		info(String.format(getLoc("loc.text.typing") + " '%1$s'", value));
		element.sendKeys(value);
	}

	/**
	 * Clear field and type text
	 * @param value text
	 */
	public void setText(final String value) {
		waitAndAssertIsPresent();
		element.clear();
		type(value);
	}

	/**
	 * Clear field and type text
	 * @param value text
	 */
	public void setAndCheckValue(final String value) {
		waitAndAssertIsPresent();
//		for(int i = 0; i <= 2; i++){
			try{
				element.clear();
				type(value);
				if(element.getAttribute("value").equals(value)){
//					break;
                    return;
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
//		}
	}

	/**
	 * Clear field and type text
	 * @param value text
	 */
	public void setAndCheckText(final String value) {
		waitAndAssertIsPresent();
		info(String.format(getLoc("loc.text.typing") + " '%1$s'", value));
//		for(int i = 0; i <= 2; i++){
			try{
				element.clear();
				element.sendKeys(value);
				if(element.getText().equals(value)){
//					break;
                    return;
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
//		}
	}


	/**
	 * Submits the field
	 */
	public void submit() {
		element.submit();
	}

	/**
	 * Mouse Up
	 */
	public void mouseUp() {
		browser.getDriver().getMouse().mouseUp(element.getCoordinates());
	}

	/**
	 * Gets value of field
	 * @return value
	 */
	public String getValue() {
		String text;
		try{
			waitAndAssertIsPresent();
			text = element.getAttribute("value");
		}catch(Exception e){
			text = element.getAttribute("value");
		}
		return text;
	}

	/**
	 * asserts if Text is Present in field
	 * @param value Text
	 */
	public void assertIsTextPresent(final String value) {
		String text = getValue();
		AssertJUnit.assertTrue(value + " " + getLoc("loc.text.not.present"), text.contains(value));
	}

	/**
	 * Asserts max field length
	 * @param max value
	 */
	public void assertMaxLenght(final int max) {
		String attribute = getAttribute("maxlength");
		if (attribute.isEmpty() || attribute.equals(null)) {
			AssertJUnit.fail(getLoc("loc.text.not.limited"));
		}
		int current = Integer.parseInt(attribute);
		AssertJUnit.assertTrue(String.format(getLoc("loc.text.expected.template"), max, current), current == max);
		info(getLoc("loc.text.length.correct"));
	}

	/**
	 * Focuses on the element using send keys
	 */
	public void focus() {
		browser.getDriver().findElement(locator).sendKeys("");
	}

	/**
	 * Type using Java Script
	 * @param value Text
	 */
	public void jsType(final String value) {
		waitAndAssertIsPresent();
		info(String.format(getLoc("loc.text.typing") + " '%1$s'", value));
		((JavascriptExecutor) browser.getDriver()).executeScript(String.format("arguments[0].value='%1$s'", value), element);
	}

	/** Присваивает тегу некоторое значение(innerHTML) с помощью JS
	 * @param value значение
	 */
	public void setInnerHtml(String value){
		waitAndAssertIsPresent();
		element.click();
		info("Enter the text '" + value  + "'");
		// Очищаем содержимое поля
		((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].innerHTML=\"\";", element);
		//element.sendKeys(value);
		((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].innerHTML=\"" + value + "\";", element);
	}

	/** Проверяет, что текст бокс содержит нужное значение
	 * @param text текст
	 */
	public void assertTextContains(String text, BaseTest test){
		jassert.assertTextContains(getName(), text, getValue(), true, test);
	}

	@Override
	public Boolean isElementMissed(){
		return super.isElementMissed();
	}
}
