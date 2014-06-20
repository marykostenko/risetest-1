package naumen.tests.user;

import naumen.NaumenTest;
import naumen.framework.base.CommonFunctions;
import naumen.framework.base.MailUtils;
import naumen.framework.base.entities.User;
import naumen.framework.forms.MainForm;
import naumen.framework.forms.RegistrationForm;
import org.testng.annotations.Parameters;

public class R1UserRegistration extends NaumenTest {

    private MainForm mf;
    private RegistrationForm rf;
    private String email = "test" + CommonFunctions.getRandomLatinStringByDate() + "@naumen.ru";
    private String from = "standautotest@naumen.ru";
    private String password = "123qwe123";
    private String fname = "first" + CommonFunctions.getRandomStringByDate();
    private String mname = "middle" + CommonFunctions.getRandomStringByDate();
    private String lname = "last" + CommonFunctions.getRandomStringByDate();
    private User user;
    private String confirmationText = "На ваш E-Mail отправлено письмо со ссылкой, пройдите по ней для активации профиля. Ссылка действительна в течение 24 часов";
    MailUtils mu = new MailUtils("imap.yandex.ru", "einaumen@yandex.ru", "einaumen783", MailUtils.MAIL_PROTOCOLS.IMAP );

    @Parameters({"email", "password", "firstName", "lastName"})
    public R1UserRegistration(String mail, String psw, String fnam, String lnam){
        super(mail, psw, fnam, lnam);
    }

    /**
     * default constructor
     */
    public R1UserRegistration(){
        super();
    }

    public void runTest(){
        user = new User(email, password, fname, mname, lname);

        logStep("1", "USER-R-1.1	Открытие формы регистрации пользователя");
        mf = new MainForm();
        mf.clickRegisterBtn();

        logStep("2", "USER-R-1.2	Ввод данных на форме регистрации пользователя и регистрация");
        rf = new RegistrationForm();
        rf.setEmail(email);
        rf.setLastName(lname);
        rf.setFirstName(fname);
        rf.setPassword(password);
        rf.acceptTerms();
        makeScreen();
        rf.submitRegistration();

        logStep("3", "USER-R-1.3	Проверка успешности регистрации");
        makeScreen();
        mf.checkTextOnForm(confirmationText, this);
        String messageContent = mu.getMessageContent("Приветствуем вас в системе Экспир", from);
        logger.info("message = " + messageContent);

        logStep("4", "USER-R-1.4	Удаление сообщений в электронной почте о регистрации пользователя");
        mu.deleteMessagesFromInbox(from, "Приветствуем вас в системе Экспир");
    }
}
