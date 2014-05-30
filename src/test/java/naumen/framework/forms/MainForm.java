package naumen.framework.forms;

import naumen.framework.base.elements.Button;
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
