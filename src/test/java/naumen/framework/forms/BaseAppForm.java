package naumen.framework.forms;

import naumen.framework.base.BaseForm;
import naumen.framework.base.BaseTest;
import naumen.framework.base.Browser;
import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.Label;
import naumen.framework.base.elements.Link;
import naumen.framework.base.elements.TextBox;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

/** базовая форма для проекта
 */
public class BaseAppForm extends BaseForm{

	/** конструктор
	 * @param locator локатор
	 * @param formTitle наименование формы
	 */
	protected BaseAppForm(By locator, String formTitle) {
		super(locator, formTitle);
	}

	private final String checkErrorTmp = "//div[contains(@class,'error') and contains(.,'%1$s')]";
	//работа с комбобоксами
	private final String openComboboxTmp = "//div[@data-field-class][contains(.,'%1$s')]//div[contains(@class,'combobox-container')]//span[@data-dropdown='dropdown']";
	private final String itemTmp = "//ul[contains(@class,'dropdown-menu')]//a[contains(.,'%1$s')]";
	private final String scrollInputTmp = "//div[@data-field-class][contains(.,'%1$s')]//input[contains(@class,'add-scroll')]";
	private final String actionsDivTmp = "//div[contains(@class,'ei-list-item-row') and contains(.,'%1$s')]//div[@class='eilist-action-container']";
	private final String linkRowTmp = "//div[contains(@class,'ei-list-item-row') and contains(.,'%1$s')]";
	private final String linkDivTmp = "//a[contains(.,'%1$s')]";
	private final String tmplRow = "//div[contains(@class,'ei-list-item-row')]";
	// работа с поиском
	private final TextBox searchTxb = new TextBox(By.xpath("//span[@class='add-on']/following-sibling::input[@type='text']"), "Поиск");
    protected RemoteWebDriver driver = browser.getDriver();

	/** выполняет поиск
	 * @param text текст для поиска
	 * @return true если искомое нашлось
	*/
	public boolean search(String text){
		Link l = new Link(By.xpath(String.format("//a[contains(.,'%1$s')]", text)), text);
		searchTxb.setText(text);
		searchTxb.sendKeys(Keys.ENTER);
		browser.waitForPageToLoad();
		if(l.waitForIsElementPresent(browser.getTimeoutShortInt())){
			return true;
		}
		return false;
	}

	/** выполняет поиск
	 * @param txb текстовое поля для поиска
	 * @param text искомое значение
	 * @return true если искомое значение найдено
	 */
	public boolean search(TextBox txb, String text){
		txb.setAndCheckValue(text);
		Link l = new Link(By.xpath(String.format("//a[contains(.,'%1$s')]", text)), "Ссылка на событие " + text);
		txb.sendKeys(Keys.ENTER);
		if(l.waitForIsElementPresent(browser.getTimeoutShortInt())){
			return true;
		}
		return false;
	}

	/** выбирает значение из выпадающего списка
 	 * @param fieldName наименование поля со списком
	 * @param option выбираемое значение
	 */
	public void selectInCombobox(String fieldName, String option){
		Label l = new Label(By.xpath(String.format(itemTmp, option)), "Значение в комбобоксе: " + option);
		for(int i = 0; i <=2; i++){
			new Link(By.xpath(String.format(openComboboxTmp, fieldName)), "Открыть комбобокс: " + fieldName).clickAndWait();
			if(l.waitForIsElementPresent(browser.getTimeoutShortInt())){
				l.clickAndWait();
				break;
			}
		}
	}

	/** выбирает значение из выпадающего списка
 	 * @param fieldName наименование поля со списком
	 * @param option выбираемое значение
	 */
	public void setTextAndSelectInCombobox(String fieldName, String option){
		//new Link(By.xpath(String.format(openComboboxTmp, fieldName)), "Открыть комбобокс: " + fieldName).clickAndWait();
		TextBox txb = new TextBox(By.xpath(String.format(scrollInputTmp, fieldName)), fieldName);
		txb.setAndCheckValue(option);
		new Label(By.xpath(String.format(itemTmp, option)), "Значение в комбобоксе: " + option).clickAndWait();
	}

	/** проверяет что произошла ошибка валидации данных в поле
	 * @param fieldname наименование поля, например "Фамилия"
	 */
	public void checkError(String fieldname){
		if(new Label(By.xpath(String.format(checkErrorTmp, fieldname)), fieldname + " - Это обязательное поле").isPresent()){
			logger.info("Возникла ошибка регистрации: " + fieldname + " - Это обязательное поле");
		}else{
			logger.warn("Отсутствует ошибка регистрации: " + fieldname + " - Это обязательное поле");
		}
	}

	/** проверяет наличие текста на форме
	 * @param text текст
	 */
	public void checkTextOnForm(String text, BaseTest test){
		Label l = new Label(By.xpath("//*[contains(.,'" + text + "')]"), text);
		if(l.waitForIsElementPresent(browser.getTimeoutShortInt())){
			logger.info("Текст '" + text + "' присутствует на форме!");
		}else{
			logger.warn("Текст '" + text + "' отсутствует на форме!");
			test.warn();
		}
	}

    /** проверяет наличие хотя бы одной строки из массива на форме
     * @param textArr массив строк
     */
    public void checkTextFromArrOnForm(String[] textArr, BaseTest test){
        for (int i=0; i<textArr.length; i++) {
            String text = textArr[i];
            Label l = new Label(By.xpath("//*[contains(.,'" + text + "')]"), text);
            if(l.waitForIsElementPresent(browser.getTimeoutShortInt())){
                l.moveMouseToElement();
                logger.info("Текст '" + text + "' присутствует на форме!");
                return;
            }
        }
        logger.warn("Текст из массива строк отсутствует на форме!");
        test.warn();
     }

    /**
     * проверяет, что текст отчета не пустой
     * @param report текст отчета
     */
    static public void checkReportNotEmpty(String report, BaseTest test) {
        if (report.isEmpty()) {
            logger.warn("отчет пустой!");
            test.warn();
        }
        else {
            logger.info("отчет не пустой");
        }
    }

    /** проверяет наличие хотя бы одного из
     *  вариантов текста на форме
     * @param text1 текст
     * @param text2 текст
     */
    public void checkTextOnForm(String text1, String text2, BaseTest test){
        Label l1 = new Label(By.xpath("//*[contains(.,'" + text1 + "')]"), text1);
        Label l2 = new Label(By.xpath("//*[contains(.,'" + text2 + "')]"), text2);
        if(l1.waitForIsElementPresent(browser.getTimeoutShortInt())){
            logger.info("Текст " + text1 + " присутствует на форме!");
        }
        else if(l2.waitForIsElementPresent(browser.getTimeoutShortInt())){
            logger.info("Текст " + text2 + " присутствует на форме!");
        }
        else{
            logger.warn("Текст " + text1 + " отсутствует на форме!");
            test.warn();
        }
    }

    /** проверяет отсутствие текста на форме
    * @param text текст
    */
    public void checkTextNotOnForm(String text, BaseTest test){
        Label l = new Label(By.xpath("//*[contains(.,'" + text + "')]"), text);
        if(l.isPresent()){
            logger.warn("Текст " + text + " присутствует на форме! Но не должен");
            test.warn();
        }else{
            logger.info("Текст " + text + " отсутствует на форме!");
        }
    }

    /** проверяет наличие текста на форме (массивы должны быть одной длины!)
	 * @param errorMsgArr1 массив строк (первый вариант)
     * @param errorMsgArr2 массив строк (второй вариант)
	 */
	public void checkTextOnForm(String[] errorMsgArr1, String[] errorMsgArr2, BaseTest test){
        int Length = Math.min(errorMsgArr1.length, errorMsgArr2.length);
        for(int i=0; i<Length; i++) {
            checkTextOnForm(errorMsgArr1[i], errorMsgArr2[i], test);
        }
	}

    /**
     * есть ли текст на форме?
     * @param text
     * @return true, если текст присутсвует на форме
     */
    public static boolean isTextOnForm(String text){
        Label l = new Label(By.xpath("//*[contains(.,'" + text + "')]"), text);
        return l.waitForIsElementPresent(browser.getTimeoutShortInt());
    }

	/** жмет по элементу button с текстом
	 * @param text текст
	 */
	public void clickBtnWithText(String text){
		new Button(By.xpath("//button[contains(.,'" + text + "')]"), text).jsClickAndWait();
	}

	/** делает блок действий видимым
	 * @param text текст ссылки
	 */
	public void changeActionsStyleToVisible(String text){
		browser.waitCondition(this, "isActionsForLinkPresent", new Class[]{String.class}, new Object[]{text}, "Блок действий для записи " + text);
		for(int i = 0; i <= 2; i++){
			try{
				WebElement e = browser.getDriver().findElement(By.xpath(String.format(actionsDivTmp, text)));
				((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].className='';", e);
			}catch (StaleElementReferenceException exp) {
				WebElement e = browser.getDriver().findElement(By.xpath(String.format(actionsDivTmp, text)));
				((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].className='';", e);
			}
			if(browser.getDriver().findElements(By.xpath(String.format(actionsDivTmp, text))).size() == 0){
				break;
			}
		}
		if(Browser.currentBrowser.toString().equalsIgnoreCase("iexplore")){
			Link l = new Link(By.xpath(String.format(linkRowTmp, text)), "Запись " + text);
			l.click();
		}else{
			Link l = new Link(By.xpath(String.format(linkDivTmp, text)), "Запись " + text);
			((JavascriptExecutor)browser.getDriver()).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('mouseup', true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);  arguments[0].dispatchEvent(evt);", l.getElement());
			((JavascriptExecutor)browser.getDriver()).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('mouseover', true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);  arguments[0].dispatchEvent(evt);", l.getElement());
		}
	}

	/** проверяет, имеется ли блок с действиями для записи
	 * @param text текст ссылки
	 * @return true если имеется
	 */
	public Boolean isActionsForLinkPresent(String text){
		new Actions(browser.getDriver()).moveToElement(browser.getDriver().findElement(By.xpath(tmplRow))).build().perform();
		return browser.getDriver().findElements(By.xpath(String.format(actionsDivTmp, text))).size() > 0;
	}

	/** Нажимает ссылку (например "Удалить", "Участвовать") в строке списка с текстом
	 * @param text текст в строке
	 * @param linkText ссылка
	 */
	public void clickLinkInList(final String text, final String linkText){
		new Actions(browser.getDriver()).moveToElement(browser.getDriver().findElement(By.xpath(String.format(linkRowTmp, text)))).build().perform();
		new Actions(browser.getDriver()).moveToElement(new Link(By.xpath(String.format(linkRowTmp + "//a[contains(.,'%2$s')]", text, linkText)), linkText + " в строке " + text).getElement(),2,2).click().build().perform();

	}

    public void jsclickHiddenLink(final String text, final String linkText){
        ((JavascriptExecutor)browser.getDriver()).executeScript(String.format("$('div#%1$s').hover();", text));
        browser.getDriver().findElement(By.linkText(linkText)).click();
    }

    /** кликает по ссылке с текстом
	 * @param text наименование события
	 */
	public void clickLink(String text){
        String[] ts = text.split(" ");
        String[] tSpl = new String[ts.length * 3];
        int j = 0;
        for(int i=0; i<3; i++) {
            System.arraycopy(ts, 0, tSpl, j, ts.length);
            j += ts.length;
        }
        String xPathString = String.format("//a[contains(.,'%1$s') and contains(.,'%2$s') and contains(.,'%3$s')]", tSpl);
        new Link(By.xpath(xPathString), text).jsClickAndWait();
	}

    /** Возвращает текущий веб-драйвер
     * @return webDriver
     */
    public RemoteWebDriver getDriver() {
        return driver;
    }
}
