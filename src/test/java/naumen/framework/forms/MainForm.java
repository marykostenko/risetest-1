package naumen.framework.forms;

import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.Label;
import naumen.framework.base.entities.User;
import org.openqa.selenium.By;

/**
 *
 * home page
 *
 */
public class MainForm extends BaseAppForm {
    private final Button loginBtn = new Button(By.xpath("//a[contains(@class,'login-link') and contains(.,'Вход')]"), "Вход");
	private final Button logoutBtn = new Button(By.xpath("//a[contains(@class,'login-link') and contains(.,'Выход')]"), "Выход");
    private final Button registerBtn = new Button(By.xpath("//a[contains(@class,'registration-link')]"), "Регистрация");
    private final Label financeLbl = new Label(By.xpath("//a[contains(.,'Финансирование')]"), "Финансирование");

	/**
	 * Constructor by default
	 */
	public MainForm() {
        super(By.className("logo"), "Главная страница");
	}

    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public static By getFormLocator(){
        return By.className("logo");
    }

    /** нажимает кнопку "Вход"
     */
    public void clickLoginBtn(){
        loginBtn.click();
    }

	/** нажимает кнопку "Выход"
	 */
	public void clickLogoutBtn(){
        logoutBtn.click();
	}
    /** нажимает кнопку "Регистрация"
     */
    public void clickRegisterBtn(){
        registerBtn.click();
    }

    public void assertIsLoginBtnPresent(){
        loginBtn.doAssert(loginBtn.isPresent(), "присутствует", "не найдена");
    }

    public void assertIsRegisterBtnPresent(){
        registerBtn.doAssert(registerBtn.isPresent(), "присутствует", "не найдена");
    }

    public void financeLblClick(){
        financeLbl.click();
    }

    public void lotSubscriptionClick(){
        getDriver().findElementByXPath("//a[contains(.,'Подписка на конкурсы')]").click();
    }

    public void financeSearchClick(){
        getDriver().findElementByXPath("//a[contains(.,'Поиск финансирования')]").click();
    }

    /**
     * проверяет, был ли выполнен вход в систему
     * @param user пользователь
     * @return
     */
    public boolean wasLoginSuccessful(User user){
        return getDriver().findElementsByXPath(String.format("//a[contains(.,'%s')]",
                user.getFirstName() + " " + user.getLastName())).size() > 0;
    }

}
