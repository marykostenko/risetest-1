package naumen.framework.forms;

import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.TextBox;
import naumen.framework.base.entities.User;
import naumen.framework.base.entities.User.USER;
import org.openqa.selenium.By;

/** логин форма
 */
public class LoginForm extends BaseAppForm{
    private final TextBox emailTxb = new TextBox(By.id("email"), "Email");
    private final TextBox passwordTxb = new TextBox(By.id("password"), "Пароль");
    private final Button signBtn = new Button(By.id("loginbutton"), "Войти");

	/** конструктор
	 */
	public LoginForm() {
		super(By.className("logo-page"), "Вход в систему");
	}

    /** return locator to form
     * @return locator to form
     */
    public static By getFormLocator(){
        return By.className("logo-page");
    }

    /** логин
	 * @param login login
	 * @param password password
	 */
	public void login(String login, String password){
        emailTxb.setText(login);
        passwordTxb.setText(password);
        signBtn.clickAndWait();
	}

	/** логин
	 * @param user пользователь
	 */
	public void login(User user){
		login(user.getValue(USER.EMAIL), user.getValue(USER.PASSWORD));
	}
}
