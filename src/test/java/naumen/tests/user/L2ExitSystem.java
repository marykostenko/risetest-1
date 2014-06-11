package naumen.tests.user;

import naumen.NaumenTest;
import naumen.framework.base.entities.User;
import naumen.framework.forms.LoginForm;
import naumen.framework.forms.MainForm;
import org.testng.annotations.Parameters;

public class L2ExitSystem extends NaumenTest {

    private MainForm mf;
    private LoginForm lf;

    @Parameters({"email", "password", "firstName", "lastName"})
    public L2ExitSystem(String mail, String psw, String nam, String snam){
        super(mail, psw, nam, snam);
    }

    /**
     * default constructor
     */
    public L2ExitSystem(){
        super();
    }

    /** Тест на успешный / неуспешный вход в систему
     */
    private void testLogin(User user) {
        mf = new MainForm();
        mf.clickLoginBtn();
        LoginForm lf = new LoginForm();
        lf.login(user);
        doAssertNotifyOnly(mf.wasLoginSuccessful(user), "Вход в систему успешно выполнен", "Вход в систему не выполнен");
        makeScreen();
    }

    public void runTest(){
        logStep("1", "USER-L-2.1	Вход в систему");
        testLogin(user);

        logStep("2", "USER-L-2.1	Удачный выход из системы");
        mf.clickLogoutBtn();
        makeScreen();
        mf.assertIsLoginBtnPresent();
        mf.assertIsRegisterBtnPresent();

        logStep("3", "USER-L-2.1	Проверка доступности формы входа в систему");
        mf.clickLoginBtn();
        lf = new LoginForm();
        makeScreen();
    }
}
