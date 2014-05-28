package naumen.framework.forms;

import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.Label;
import org.openqa.selenium.By;

/** диалоговое окно
 */
public class Popup extends BaseAppForm{

	/** конструктор
	 */
	public Popup() {
		super(By.xpath("//div[@role='dialog']"), "Диалоговое окно");
	}

	private final String btnTmp = "//div[@role='dialog']//a[contains(.,'%1$s')]";

	/** жмет кнопку в диалоговом окне
	 * @param btnName название кнопки
	 * @return содержимое диалогового сообщения
	 */
	public String clickBtn(String btnName){
		String text = new Label(By.xpath("//div[@role='dialog']"), "Содержимое диалогового окна").getText();
		Button btn = new Button(By.xpath(String.format(btnTmp, btnName)), btnName);
		btn.clickAndWait();
		btn.waitForElementIsNotPresent(browser.getTimeoutShortInt());
		return text;
	}

	@Override
	protected String formatLogMsg(String message) {
		return super.formatLogMsg(getLoc("loc.modal") + message);
	}
}
