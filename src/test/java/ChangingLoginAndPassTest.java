import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 14.04.2017.
 */
public class ChangingLoginAndPassTest extends BaseTest {
    //USER-ACC-3.1
    @Test(priority = 1)
    public void testChangingLogin() throws IOException, InterruptedException, MessagingException {
        log("Запущен тест USER-ACC-3.1");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Вход");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testUserForEditLogin = new TestUserData(getUserForEditLoginId());
        pageLogin.fillLoginForm(testUserForEditLogin.getUserLogin(), testUserForEditLogin.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в меню Настройки");
        PageUserProfile pageUserProfile = new PageUserProfile();
        pageUserProfile.goToAccount();

        log("Вводим новый логин");
        PageUserAccount pageUserAccount = new PageUserAccount();
        pageUserAccount.fillNewLogin(testUserForEditLogin.getUserNewLogin());

        log("Нажимаем кнопку Сохранить");
        pageUserAccount.clickSaveLogin();




        log("Проверяем, что последнее письмо в ящике - уведомление о смене электронного адреса  правильному адресату");
        TestMail testMail = new TestMail();
        int lettersCount = testMail.getLettersCount();
        String subjectEmailChangeNotification = testMail.getEmailChangeNotification();
        String subjectEmailChangeRequest = testMail.getEmailChangeRequest();



        /**
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectEmailChangeNotification), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectEmailChangeNotification);

        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserForEditLogin.getUserLogin()), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + testUserForEditLogin.getUserLogin());




        log("Проверяем, что предпоследнее письмо в ящике - подлтверждение о смене электронного адреса  правильному адресату");


        logErrors = testMail.checkAndLog(!testMail.isSubjectPenultCorrect(subjectEmailChangeRequest), logErrors,
                "Ошибка: неправильный заголовок предпоследнего письма - " + testMail.getSubjectMail(lettersCount - 1) + ". Ожидался: " + subjectEmailChangeRequest);

        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserForEditLogin.getUserLogin()), logErrors,
                "Ошибка: неправильный адресат в предпоследнем письме - " + testMail.getAddresseeMail(lettersCount - 1) + ". Ожидался: " + testUserForEditLogin.getUserLogin());

        log("Находим ссылку в письме о подтверждении смены пароля");
        String linkChangeLogin = testMail.getLinkFromPenultMail();

        log("Переходим по ссылке");
        open(linkChangeLogin);

        log("Возвращаем старый логин");
        pageUserAccount.fillNewLogin(testUserForEditLogin.getUserLogin());

        log("Нажимаем кнопку Сохранить");
        pageUserAccount.clickSaveLogin();

        logErrors = (testMail.checkAndLog(!testMail.isSubjectCorrect(subjectEmailChangeNotification), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectEmailChangeNotification))|
                (testMail.checkAndLog(!testMail.isSubjectCorrect(subjectEmailChangeNotification), logErrors,
                        "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectMail(lettersCount - 1) + ". Ожидался: " + subjectEmailChangeNotification));

        logErrors = (testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserForEditLogin.getUserLogin()), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + testUserForEditLogin.getUserLogin()))|
                (testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserForEditLogin.getUserLogin()), logErrors,
                        "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeMail(lettersCount - 1) + ". Ожидался: " + testUserForEditLogin.getUserLogin()));

        log("Проверяем, что предпоследнее письмо в ящике - подлтверждение о смене электронного адреса  правильному адресату");

        logErrors = (testMail.checkAndLog(!testMail.isSubjectCorrect(subjectEmailChangeRequest), logErrors,
                "Ошибка: неправильный заголовок предпоследнего письма - " + testMail.getSubjectMail(lettersCount - 1) + ". Ожидался: " + subjectEmailChangeRequest))|
                (testMail.checkAndLog(!testMail.isSubjectCorrect(subjectEmailChangeRequest), logErrors,
                        "Ошибка: неправильный заголовок предпоследнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectEmailChangeRequest));

        logErrors = (testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserForEditLogin.getUserLogin()), logErrors,
                "Ошибка: неправильный адресат в предпоследнем письме - " + testMail.getAddresseeMail(lettersCount - 1) + ". Ожидался: " + testUserForEditLogin.getUserLogin()))|
                (testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserForEditLogin.getUserLogin()), logErrors,
                        "Ошибка: неправильный адресат в предпоследнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + testUserForEditLogin.getUserLogin()));

        log("Находим ссылку в письме о подтверждении смены пароля");

        log("Переходим по ссылке");
        open(linkChangeLogin);
*/

        checkMistakes();

        log("Тест USER-L-2.1 завершен");

    }
        //USER-ACC-3.2
        @Test (priority = 2)
        public void testChangingPassword() throws IOException {
            log("Запущен тест USER-ACC-3.2");

            log("Переключаем язык страницы на русский");
            PageTopBottom pageTopBottom = new PageTopBottom();
            pageTopBottom.switchToRu();

            log("Нажимаем кнопку Вход");
            pageTopBottom.goToLogin();

            log("Проверяем, что открылась страница с url /login");
            PageLogin pageLogin = new PageLogin();
            log("Url страницы: " + url());
            logErrors = pageLogin.assertLoginUrl(logErrors);

            log("Проверяем, что есть форма логина");
            pageLogin.isLoginForm();

            log("Заполняем форму логина");
            TestUserData testUserForEditPassword = new TestUserData(getGetUserForEditPasswordId());
            pageLogin.fillLoginForm(testUserForEditPassword.getUserLogin(), testUserForEditPassword.getUserPassword());

            log("Нажимаем кнопку Войти");
            pageLogin.pushLoginButton();

            log("Проверяем, выполнен ли вход");
            logErrors = pageTopBottom.assertLoggingIn(logErrors);

            log("Переходим в меню Настройки");
            PageUserProfile pageUserProfile = new PageUserProfile();
            pageUserProfile.goToAccount();

            log("Заполняем поля старого, нового и повторного нового логина");
            PageUserAccount pageUserAccount = new PageUserAccount();
            pageUserAccount.fillPasswords(testUserForEditPassword.getUserPassword(), testUserForEditPassword.getUserNewPassword(), testUserForEditPassword.getUserNewPassword());

            log("Нажимаем кнопку Сохранить");
            pageUserAccount.clickSavePassword();

            log("Вызодим из системы");
            pageTopBottom.logout();

            log("Нажимаем кнопку Вход");
            pageTopBottom.goToLogin();

            log("Проверяем, что открылась страница с url /login");
            log("Url страницы: " + url());
            logErrors = pageLogin.assertLoginUrl(logErrors);

            log("Проверяем, что есть форма логина");
            pageLogin.isLoginForm();

            log("Заполняем форму логина с новым пароелм");
            pageLogin.fillLoginForm(testUserForEditPassword.getUserLogin(), testUserForEditPassword.getUserNewPassword());

            log("Нажимаем кнопку Войти");
            pageLogin.pushLoginButton();

            log("Проверяем, выполнен ли вход");
            logErrors = pageTopBottom.assertLoggingIn(logErrors);

            log("Переходим в меню Настройки");
            pageUserProfile.goToAccount();

            log("Возвращаем старый пароль");
            pageUserAccount.fillPasswords(testUserForEditPassword.getUserNewPassword(), testUserForEditPassword.getUserPassword(), testUserForEditPassword.getUserPassword());

            log("Нажимаем кнопку Сохранить");
            pageUserAccount.clickSavePassword();

            checkMistakes();

    }
}
