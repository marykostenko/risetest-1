import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 14.04.2017.
 */
public class ChangingLoginAndPassTest extends BaseTest {
    //USER-ACC-3.1
    @Test(priority = 1)
    public void testChangingLogin() throws IOException, InterruptedException, MessagingException {

        PageTopBottom pageTopBottom = new PageTopBottom();
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestUserData testAdminData = new TestUserData(getAdminId());
        TestUserData testUserForEditLogin = new TestUserData(getUserForEditLoginId());
        TestUserData testUserData = new TestUserData();
        PageLogin pageLogin = new PageLogin();
        PageUserProfile pageUserProfile = new PageUserProfile();
        TestMail testMail = new TestMail();

        String randomEmail = String.valueOf(testRandomUserData.createRandomEmail());

        log("Запущен тест USER-ACC-3.1");

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Добавим нового пользователя в систему");
        String userId = testRandomUserData.createNewRandomUserUser(testAdminData.getUserLogin(), testAdminData.getUserPassword(),testUserForEditLogin.getUserLastName(),
                testUserForEditLogin.getUserFirstName(), randomEmail);

        log("Сменим пароль пользователя на тестовый");
        testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), userId, testUserForEditLogin.getUserPassword());
        log("Пароль пользователя изменен. Продолжаем выполнение теста");

        log("Нажимаем кнопку Вход");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(randomEmail, testUserForEditLogin.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в меню Настройки");
        pageUserProfile.goToAccount();

        String newLogin = testMail.checkMailAndChangeLogin();

        if (!testRandomUserData.randomEmailNotNull(newLogin))
        {
            log("ОШИБКА! Тест не может быть выполнен, так как необходимые для смены логина письма не были получены!");
            logErrors++;
            checkMistakes();
            return;
        }

        log("Выходим из системы");
        pageTopBottom.logout();

        log("Проверяем новый логин");
        log("Нажимаем кнопку Вход");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(newLogin, testUserForEditLogin.getUserPassword());
        log(newLogin);

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        checkMistakes();

        log("Тест USER-ACC-3.1 завершен");

    }
        //USER-ACC-3.2
        @Test (priority = 2)
        public void testChangingPassword() throws IOException {
            log("Запущен тест USER-ACC-3.2");

            log("Переключаем язык страницы на русский");
            PageTopBottom pageTopBottom = new PageTopBottom();
            pageTopBottom.switchToRu();

            log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
            TestUserData testAdminData = new TestUserData(getAdminId());
            TestUserData testUserForEditPassword = new TestUserData(getGetUserForEditPasswordId());
            TestUserData testUserData = new TestUserData();
            testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testUserForEditPassword.getId(), testUserForEditPassword.getUserPassword());
            log("Пароль пользователя изменен. Продолжаем выполнение теста");

            log("Нажимаем кнопку Войти");
            pageTopBottom.goToLogin();

            log("Нажимаем кнопку Вход");
            pageTopBottom.goToLogin();

            log("Проверяем, что открылась страница с url /login");
            log("Url страницы: " + url());
            PageLogin pageLogin = new PageLogin();
            logErrors = pageLogin.assertLoginUrl(logErrors);

            log("Проверяем, что есть форма логина");
            pageLogin.isLoginForm();

            log("Заполняем форму логина");
            pageLogin.fillLoginForm(testUserForEditPassword.getUserLogin(), testUserForEditPassword.getUserPassword());

            log("Нажимаем кнопку Войти");
            pageLogin.pushLoginButton();

            log("Проверяем, выполнен ли вход");
            logErrors = pageTopBottom.assertLoggingIn(logErrors);

            log("Переходим в профиль пользователя");
            pageTopBottom.goToUserProfile();

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

            log("Переходим в профиль пользователя");
            pageTopBottom.goToUserProfile();

            log("Переходим в меню Настройки");
            pageUserProfile.goToAccount();

            log("Возвращаем старый пароль");
            pageUserAccount.fillPasswords(testUserForEditPassword.getUserNewPassword(), testUserForEditPassword.getUserPassword(), testUserForEditPassword.getUserPassword());

            log("Нажимаем кнопку Сохранить");
            pageUserAccount.clickSavePassword();

            checkMistakes();

            log("Тест USER-ACC-3.2 завершен");

    }
}
