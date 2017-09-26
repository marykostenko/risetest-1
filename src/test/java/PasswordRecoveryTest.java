import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by user nkorobicina on 22.12.2016.
 */
//Восстановление пароля пользователя, USER-ACC-1
public class PasswordRecoveryTest extends BaseTest
{

    //USER-ACC-1.1 Восстановление пароля пользователя: получение письма
    @Test(priority = 1)
    public void testRecoveryPasswordReceivingMail() throws IOException, InterruptedException, MessagingException
    {
        TestUserData testUserData = new TestUserData(getMailRecoveryId());
        TestMail testMail = new TestMail();

        log("Запущен тест USER-ACC-1.1");

        if (!testMail.checkRecoveryMail(testUserData.getUserLogin()))
        {
            log("ОШИБКА! Тест не может быть выполнен, так как не удалось получить письмо!");
            logErrors++;
            checkMistakes();
            return;
        }

        checkMistakes();

        log("Тест USER-ACC-1.1 завершен");
    }



    //USER-ACC-1.2. Восстановление пароля: изменение и проверка нового пароля
    @Test(priority = 2)
    public void testRestorePassword() throws IOException, InterruptedException, MessagingException
    {
        TestUserData testUserData = new TestUserData(getMailRecoveryId());
        TestMail testMail = new TestMail();
        PagePasswordRecovery pagePasswordRecovery = new PagePasswordRecovery();
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageUserProfile pageUserProfile = new PageUserProfile();
        PageUserAccount pageUserAccount = new PageUserAccount();

        log("Запущен тест USER-ACC-1.2");

        if (!testMail.checkRecoveryMail(testUserData.getUserLogin()))
        {
            log("ОШИБКА! Тест не может быть выполнен, так как не удалось получить письмо!");
            logErrors++;
            checkMistakes();
            return;
        }

        checkMistakes();

        log("Находим ссылку из последнего письма в ящике");
        String linkRecovery = testMail.getLinkFromLastMail();

        open(linkRecovery);
        log("Проверяем url страницы");
        logErrors = pagePasswordRecovery.checkUrlLinkRecoveryPage(logErrors);

        log("Вводим новый пароль");
        TestPasswordData testPasswordData = new TestPasswordData();
        String newPassword = testPasswordData.getNewRecoveryPassword();
        pagePasswordRecovery.fillNewPassword(newPassword);

        log("Вводим подтверждение нового пароля");
        pagePasswordRecovery.fillPasswordConfirmation(newPassword);

        log("Нажимаем кнопку Сохранить");
        pagePasswordRecovery.clickSaveNewPassword();

        log("Проверяем сообщение об успешном изменении пароля");
        logErrors = pagePasswordRecovery.checkMessageSuccessfulAppear(logErrors);

        log("Логинимся с новым паролем");
        pageTopBottom.login(testUserData.getUserLogin(), newPassword);

        log("Проверяем, что залогинены");
        logErrors = pageTopBottom.checkAndLog(!pageTopBottom.isLoggedIn(), logErrors,
                "Ошибка: не залогинились с новым паролем");

        log("Изменяем пароль обратно");
        pageTopBottom.goToUserProfile();
        pageUserProfile.goToAccount();
        pageUserAccount.changePassword(newPassword, testUserData.getUserPassword());
        checkMistakes();
        log("Тест USER-ACC-1.2 завершен");
    }

    @Test(priority = 3)
    public void testMessageOnEmailPage() throws IOException
    {
        log("Запущен тест USER-ACC-1.3");

        log("Переходим на форму логина");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToLogin();

        log("Нажимаем на ссылку 'Забыли пароль?'");
        PageLogin pageLogin = new PageLogin();
        pageLogin.goToRecoveryPage();

        log("Вводим некорректный email");
        TestUserData testUserData = new TestUserData(getMailRecoveryId());
        String incorrectEmail[] = testUserData.getUserLogin().split("@", 0);
        PagePasswordRecovery pagePasswordRecovery = new PagePasswordRecovery();
        pagePasswordRecovery.fillEmail(incorrectEmail[0]);

        log("Проверяем наличие и содержание алерта");
        logErrors = pagePasswordRecovery.checkMessageIncorrectEmailAppear(logErrors);

        log("Проверяем, что поле красное");
        logErrors = pagePasswordRecovery.checkEmailFieldRed(logErrors);

        log("Вводим правильный email");
        pagePasswordRecovery.fillEmail(testUserData.getUserLogin());

        log("Проверяем, что поле зеленое");
        logErrors = pagePasswordRecovery.checkEmailFieldGreen(logErrors);

        checkMistakes();
        log("Тест USER-ACC-1.3 завершен");
    }


}
