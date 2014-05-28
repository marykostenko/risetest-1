package naumen.framework.forms;

import naumen.framework.base.elements.Button;
import org.openqa.selenium.By;

/**
 *
 * home page
 *
 */
public class MainForm extends BaseAppForm {

	private final Button logoutBtn = new Button(By.xpath("//button[contains(.,'Выход')]"), "Выход");
    private final Button backBtn = new Button(By.xpath("//button[contains(.,'Назад')]"), "Назад");

	/**
	 * Constructor by default
	 */
	public MainForm() {
        super(By.className("logo"), "Главная форма");
	}

    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public static By getFormLocator(){
        return By.className("logo");
    }

    /** Подтвердить выход из системы
     */
    public void confirmLogout(){
        new Button(By.xpath("//button[contains(.,'Да')]"), "Да").clickAndWait();
    }

    /** Отменить выход из системы
     */
    public void declineLogout(){
        new Button(By.xpath("//button[contains(.,'Да')]"), "Да").clickAndWait();
    }

	/** нажимает кнопку "Войти"
	 */
	public void clickLogoutBtn(){
        logoutBtn.clickAndWait();
	}
    /** нажимает кнопку "Назад"
     */
    public void clickBackBtn(){
        backBtn.clickAndWait();
    }

}
