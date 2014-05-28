package naumen.framework.base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

/**
 * Class describing the combobox (dropdown list)
 */
public class ComboBox extends BaseElement {

	public ComboBox(By locator, String name) {
		super(locator, name);
	}

	public ComboBox(By locator) {
		super(locator);
	}

	public ComboBox(final String locator, final String name) {
		super(locator, name);
	}
	protected String getElementType() {
		return getLoc("loc.combobox");
	}

	/**
	 * Check availability and get a combo box selected value (label)
	 */
	public String getSelectedLabel() {
		assertIsPresent();
		return new Select(element).getFirstSelectedOption().getText();
	}

	/**
	 * Choose from a list
	 * 
	 * @param optionLocator
	 *            Locator value, which is necessary to choose
	 */
	public void select(String optionLocator) {
		waitAndAssertIsPresent();
		info(String.format(getLoc("loc.selecting.value")+" '%1$s'", optionLocator));
		new Select(element).selectByVisibleText(optionLocator);
	}
	
	public void selectByText(String value){
	    waitAndAssertIsPresent();
		info(String.format(getLoc("loc.selecting.value")+ " '%1$s'", value));
		new Select(element).selectByVisibleText(value);
		browser.waitForPageToLoad();
	}

	public void selectByValue(String value){
	    waitAndAssertIsPresent();
		info(String.format(getLoc("loc.selecting.value")+" '%1$s'", value));
		new Select(element).selectByValue(value);
	}

	public String getSelectedText(){
		waitAndAssertIsPresent();
		return (new Select(element)).getFirstSelectedOption().getText();
	}

	/** Выбирает первое доступное значение из комбобокса
	 * @return выбранное значение
	 */
	public String selectRandomByText(){
		String text = "";
		waitAndAssertIsPresent();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		// Проверка, что выпадающий список не пуст
		if(options.size() == 1 && (options.get(0).getText().equals("") || options.get(0).getText().equals("Не выбрано"))){
			fatal("Выпадающий список пуст!");
		}else if(options.size() == 1){
			text = options.get(0).getText();
		}else{
			int index = new Random().nextInt(options.size() - 1) + 1;
			text = options.get(index).getText();
		}
		selectByText(text);
		return text;
	}

	/** Выбирает первое доступное значение из комбобокса
	 * @return выбранное значение
	 */
	public String selectFirstOption(){
		waitAndAssertIsPresent();
		Select select = new Select(element);
		String text = select.getOptions().get(0).getText();
		select.selectByIndex(0);
		return text;
	}


	/** Выбирает случайное значение из комбобокса
	 * @return value выбранного значения
	 */
	public String selectRandomByValue(){
		String value = "";
		waitAndAssertIsPresent();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		// Проверка, что выпадающий список не пуст
		if(options.size() == 1 && (options.get(0).getAttribute("value").equals(""))){
			fatal("Выпадающий список пуст!");
		}else if(options.size() == 1){
			value = options.get(0).getAttribute("value");
		}else{
			int index = new Random().nextInt(options.size() - 1) + 1;
			value = options.get(index).getAttribute("value");
		}
		info("Выбираемое значение из выпадающего списка value = '" +  value + "'");
		selectByValue(value);
		return value;
	}

	/** Выбирает значение из списка по аттрибуту label
	 * @param label значение аттрибута label
	 */
	public void selectByLabel(String label){
		String value = null;
		waitAndAssertIsPresent();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for(int i = 0; i < options.size(); i++){
			if(options.get(i).getAttribute("label").equals(label)){
				value = options.get(i).getAttribute("value");
				info("Выбираемое значение из выпадающего списка value = '" +  value + "'");
				selectByValue(value);
				return;
			}
		}
		logger.fatal("В комбобоксе нету элемента со значением аттрибута label равным '" + label + "'");
	}

	/** Select value from cmb by some part of option's attribute
	 * @param attribute attribute of option element
	 * @param value some part value of attribute
	 */
	public void selectByAttributeContains(String attribute, String value){
		waitAndAssertIsPresent();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for(int i = 0; i < options.size(); i++){
			if(options.get(i).getAttribute(attribute).contains(value)){
				value = options.get(i).getAttribute("value");
				info("Выбираемое значение из выпадающего списка value = '" +  value + "'");
				selectByValue(value);
				selectByText(value);
				return;
			}
		}
		logger.fatal("В комбобоксе нету элемента со значением аттрибута " + attribute + " равным '" + value + "'");
	}

	/** Select value from cmb by some part of option's text
	 * @param text some part of option's text
	 * @return выбранный текст
	 */
	public String selectByTextContains(String text){
		waitAndAssertIsPresent();
		browser.waitCondition(this, "selectContainsText", new Class[]{String.class}, new Object[]{text}, "Комбобокс не содержит текст: " + text);
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for(int i = 0; i < options.size(); i++){
			if(options.get(i).getText().contains(text)){
				text = options.get(i).getText();
				//info("Выбираемое значение из выпадающего списка text = '" +  text + "'");
				selectByText(text);
				browser.waitForPageToLoad();
				return text;
			}
		}
		logger.fatal("В комбобоксе нету элемента с текстом равным '" + text + "'");
		return null;
	}

	/** проверяет содержит ли комбобокс некий текст
	 * @param text текст
	 * @return true если содержит
	 */
	public Boolean selectContainsText(String text){
		List<WebElement> options = new Select(element).getOptions();
		for(WebElement o : options){
			if(o.getText().contains(text)){
				return true;
			}
		}
		return false;
	}

	/** возвращает количество значений в выпадающем списке
	 * @return количество значений в выпадающего списка
	 */
	public int getSize(){
		waitAndAssertIsPresent();
		int size = new Select(element).getOptions().size();
		logger.info("Количество элементов в комбобоксе: " + size);
		return size;
	}

	@Override
	public Boolean isElementMissed(){
		return super.isElementMissed();
	}
}
