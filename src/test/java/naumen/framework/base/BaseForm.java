package naumen.framework.base;

import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.Form;
import naumen.framework.base.elements.Label;
import org.openqa.selenium.By;


public abstract class BaseForm extends BaseEntity {

	private final String valueBtn = "//input[contains(@value,'%1$s')]";
	private final String valueEqualsBtn = "//input[@value='%1$s']";

	/** возвращает кнопку
	 * @param value значение или его часть аттрибута @value кнопки
	 * @return возвращает кнопку
	 */
	public Button getBtnWithValue(String value){
		return new Button(By.xpath(String.format(valueBtn, value)), value);
	}

	/** возвращает кнопку
	 * @param value точное значение аттрибута @value кнопки
	 * @return возвращает кнопку
	 */
	public Button getBtnWithValueEquals(String value){
		return new Button(By.xpath(String.format(valueEqualsBtn, value)), value);
	}

	/** Нажимает кнопку
	 * @param value значение или его часть аттрибута @value кнопки
	 */
	public void clickBtnWithValue(String value){
		Button btn = getBtnWithValue(value);
		btn.jsClickAndWait();
		if(!btn.waitForIsElementPresent(browser.getTimeoutShortInt())){
			return;
		}
	}

	/** Нажимает кнопку
	 * @param value значение или его часть аттрибута @value кнопки
	 */
	public void clickBtnWithValueEqulas(String value){
		getBtnWithValueEquals(value).jsClickAndWait();
	}

	/** проверяет наличие строки на странице
	 * @param locator локатор
	 * @param text наименование
	 */
	protected void assertRow(By locator, String text, BaseTest test){
		Label l = new Label(locator, text);
		if(l.isPresent()){
			logger.info(text + " присутствует на форме!");
		}else{
			logger.warn(text + " отсутствует на форме на форме!");
			test.warn();
		}
	}

	/**
	 * @uml.property name="titleLocator"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	protected By titleLocator;
	/**
	 * @uml.property name="title"
	 */
	protected String title;
	/**
	 * @uml.property name="name"
	 */
	protected String name;

	/**
	 * @param locator
	 * @param formTitle
	 */
	protected BaseForm(final By locator, final String formTitle) {
		init(locator, formTitle);
		assertIsOpen();
	}

	/**
	 * @param message
	 * @return
	 */
	protected String formatLogMsg(final String message) {
		return message;
	}

	/**
	 * @param locator
	 * @param formTitle
	 */
	private void init(final By locator, final String formTitle) {
		titleLocator = locator;
		title = formTitle;
		name = String.format(getLoc("loc.form") + " '%1$s'", this.title);
	}

	/**
	 * Check the opening form If the form is not open, the test stops working
	 */
	public void assertIsOpen() {
		Form elem = new Form(titleLocator, title);
		try {
			elem.waitAndAssertIsPresent();
			info(String.format(getLoc("loc.form.appears"), title));
		} catch (Throwable e) {
			fatal(String.format(getLoc("loc.form.doesnt.appears"), title));
		}
	}

	/**
	 * Check the opening form If the form is not open, the test stops working
	 */
	public void assertIsClosed() {
		Label elem = new Label(titleLocator, title);
		elem.assertIsAbsent();
	}
}
