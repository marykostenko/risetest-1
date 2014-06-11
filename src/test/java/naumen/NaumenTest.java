package naumen;

import com.google.common.base.Strings;
import naumen.framework.base.BaseTest;
import naumen.framework.base.Browser;
import naumen.framework.base.entities.User;
import naumen.framework.forms.LoginForm;
import naumen.framework.forms.MainForm;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Base test class
 *
 */
public abstract class NaumenTest extends BaseTest {
	protected static String userEmail, userPassword, userName, userLastName;
	protected static User user = new User();
    protected Random rand = new Random();

    /** возвращает пользователя
	 * @return пользователь
	 */
	public static User getUser(){
		return user;
	}

    @Parameters({"email", "password", "firstName", "lastName"})
    public NaumenTest(String mail, String psw, String fnam, String lnam){
        userEmail = mail;
        userPassword = psw;
        userName = fnam;
        userLastName = lnam;

        user.setValue(User.USER.EMAIL, userEmail);
        user.setValue(User.USER.PASSWORD, userPassword);
        user.setValue(User.USER.FIRSTNAME, userName);
        user.setValue(User.USER.LASTNAME, userLastName);
    }
	public NaumenTest(){

	}

	/** возвращает значение по ключу
	 * @param key ключ
	 * @return значение по ключу
	 */
	public static String getProperty(String key){
		String value = System.getProperty(key);
		if(Strings.isNullOrEmpty(value)){
			value = Browser.props.getProperty(key);
		}
		return value;
	}

    /** задержка
     * @param sec время в секундах
     */
    @Deprecated
    public void sleep(int sec){
        try {
            Thread.sleep(sec * 1000);
        }
        catch (InterruptedException ie) {
            return;
        }
    }

	/** возвращает login пользователя
	 * @return login пользователя
	 */
	public static String getUserEmail(){
		return userEmail;
	}

	/**  возвращает пароль пользователя
	 * @return пароль пользователя
	 */
	public static String getUserPassword(){
		return userPassword;
	}

    /* (non-Javadoc)
	 * @see webdriver.IAnalys#invokeAnalys(java.lang.Throwable, java.lang.String)
	 */
	@Override
	public void invokeAnalys(final Throwable exc, final String bodyText) throws Throwable {
	}

	/** логгирование шагов
	 * @param step номер шага
	 * @param info информация о выполняемых действиях
	 */
	public void logStep(String step, String info){
		logStep(Integer.parseInt(step));
		info("--------==[" + info + "]==--------");
	}

	@Override
	protected void info(String message) {
		// некорректно отображается буква "И" на винде
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            super.info(message.replace("И", "и"));
        else
            super.info(message);
	}

    /* (non-Javadoc)
 * @see webdriver.IAnalys#shouldAnalys()
 */
    @Override
    public boolean shouldAnalys() {
        return false;
    }

    /** Возвращает текущий веб-драйвер
     * @return webDriver
     */
    public RemoteWebDriver getDriver() {
        return browser.getDriver();
    }

    /**
     * вход в систему
     * и инициализация Documents Tree
     */
    public void enterSystem() {
        LoginForm lf = new LoginForm();
        lf.login(user);
    }
    /** Тест
     * @throws Throwable Throwable
     */
    @Test
    public void initializeTestData() throws Throwable{
        xTest();
    }

    /**
     * залогиниться в системе под пользователем user
     * @param user
     */
    public void login(User user){
        MainForm mf = new MainForm();
        mf.clickLoginBtn();
        LoginForm lf = new LoginForm();
        lf.login(user);
    }

    /**
     * выход из системы
     */
    public void logout(){
        MainForm mf = new MainForm();
        mf.clickLogoutBtn();
    }

}
