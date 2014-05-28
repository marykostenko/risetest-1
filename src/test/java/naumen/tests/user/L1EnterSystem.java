package naumen.tests.user;

import naumen.NaumenTest;
import naumen.framework.base.entities.User;
import naumen.framework.forms.LoginForm;
import naumen.framework.forms.MainForm;
import org.testng.annotations.Parameters;

public class L1EnterSystem extends NaumenTest {

    protected String email;
    protected String password;
    protected String name;
    protected String surname;
    private User user1 = new User();

    @Parameters({"email", "password", "name", "surname"})
    public L1EnterSystem(String mail, String psw, String nam, String snam){
        email = mail;
        password = psw;
        name = nam;
        surname = snam;
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
        LoginForm lf = new LoginForm();
        lf.login(user);
        MainForm mf = new MainForm();
        mf.checkTextOnForm("Выход", this);

        mf.clickLogoutBtn();
        mf.confirmLogout();

    }

    private void loginError(User user, String[] errorMsgArr1, String[] errorMsgArr2) {
        LoginForm lf = new LoginForm();
        lf.login(user);
        lf.checkTextOnForm(errorMsgArr1, errorMsgArr2, this);
    }

    private void loginError(User user, String errorMsg1, String errorMsg2) {
        LoginForm lf = new LoginForm();
        lf.login(user);
        lf.checkTextOnForm(errorMsg1, errorMsg2, this);
    }

    @Override
    public void runTest() {
        logStep("1", "USER-L-1.1	Удачный вход в систему");
        tryLogin(user);

        logStep("2", "USER-L-1.2	Попытка входа при пустом имени пользователя и пустом пароле");
        user1.setValue(User.USER.EMAIL, "");
        user1.setValue(User.USER.PASSWORD, "");
        loginError(user1, new String[]{"Имя пользователя является обязательным полем.", "Пароль является обязательным полем."},
        new String[]{"Password is a required field.", "Username is a required field."});

        logStep("3", "USER-L-1.3	Попытка входа при пустом имени пользователя и правильном пароле");
        user1.setValue(User.USER.PASSWORD, user.getValue(User.USER.PASSWORD));
        loginError(user1, "Имя пользователя является обязательным полем.", "Username is a required field.");

        logStep("4", "USER-L-1.4	Попытка входа при неправильном имени пользователя и правильном пароле");
        user1.setValue(User.USER.EMAIL, user.getValue(User.USER.EMAIL) + "1234");
        loginError(user1, "Имя пользователя или пароль не найдены. Попробуйте еще раз",
        "The credentials you provided cannot be determined to be authentic.");

        logStep("5", "USER-L-1.5	Попытка входа при правильном имени пользователя и неправильном пароле");
        user1.setValue(User.USER.EMAIL, user.getValue(User.USER.EMAIL));
        user1.setValue(User.USER.PASSWORD, user.getValue(User.USER.PASSWORD) + "1234");
        loginError(user1, "Имя пользователя или пароль не найдены. Попробуйте еще раз",
        "The credentials you provided cannot be determined to be authentic.");

        }
}
