package naumen.tests.user;

import naumen.NaumenTest;
import naumen.framework.base.entities.User;
import naumen.framework.forms.LoginForm;
import naumen.framework.forms.MainForm;
import org.testng.annotations.Parameters;

public class L1EnterSystem extends NaumenTest {

    private User user1 = new User();

    @Parameters({"email", "password", "firstName", "lastName"})
    public L1EnterSystem(String mail, String psw, String nam, String snam){
        super(mail, psw, nam, snam);
    }

    /**
     * default constructor
     */
    public L1EnterSystem(){
        super();
    }

    /** Тест на успешный / неуспешный вход в систему
     */
    private void tryLogin(User user) {
        MainForm mf = new MainForm();
        mf.clickLoginBtn();
        LoginForm lf = new LoginForm();
        lf.login(user);
        doAssertNotifyOnly(mf.wasLoginSuccessful(user), "Вход в систему успешно выполнен", "Вход в систему не выполнен");
        makeScreen();
        mf.clickLogoutBtn();
    }

    private void loginError(User user) {
        MainForm mf = new MainForm();
        mf.clickLoginBtn();
        LoginForm lf = new LoginForm();
        lf.login(user);
        doAssertNotifyOnly(lf.wasLoginIncorrect(), "Вход в систему не выполнен", "Вход в систему был выполнен");
        makeScreen();
        lf.clickLogo();
    }

    @Override
    public void runTest() {
        logStep("1", "USER-L-1.1	Удачный вход в систему");
        tryLogin(user);

        logStep("2", "USER-L-1.2	Проверка ошибки входа при вводе логина, незарегистрированного в системе, и верного пароля");
        user1.setValue(User.USER.EMAIL, "auttotest@naumen.ru");
        user1.setValue(User.USER.PASSWORD, user.getPassword());
        loginError(user1);

        logStep("3", "USER-L-1.3	Проверка ошибки входа при вводе верного логина и пустого пароля");
        user1.setValue(User.USER.EMAIL, user.getEmail());
        user1.setValue(User.USER.PASSWORD, "");
        loginError(user1);

        logStep("4", "USER-L-1.4	Проверка ошибки входа при пустом поле «Логин»");
        user1.setValue(User.USER.EMAIL, "");
        user1.setValue(User.USER.PASSWORD, user.getPassword());
        loginError(user1);

        logStep("5", "USER-L-1.5	Проверка ошибки входа при вводе верного логина и неверного пароля");
        user1.setValue(User.USER.EMAIL, user.getEmail());
        user1.setValue(User.USER.PASSWORD, user.getPassword() + "111");
        loginError(user1);
    }
}
