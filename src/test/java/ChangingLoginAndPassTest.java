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

        log("Нажимаем кнопку Войти");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим к системным дейсвиям");
        MenuContent menuContent = new MenuContent();
        menuContent.goToAdminActions();

        log("Меняем пароль на тестовый пользователю, которому будем менять логин");
        TestUserData testUserForEditLogin = new TestUserData(getUserForEditLoginId());
        PageActions pageActions = new PageActions();
        pageActions.changeUserPassword(testUserForEditLogin.getId(), testUserForEditLogin.getUserPassword());

        log("Выходим из системы");
        pageTopBottom.logout();

        log("Нажимаем кнопку Вход");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
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

        log("Проверяем почту");
        TestMail testMail = new TestMail();
        logErrors = testMail.checkMailAndChangeLogin(logErrors);

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
        pageLogin.fillLoginForm(testUserForEditLogin.getUserNewLogin(), testUserForEditLogin.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в меню Настройки");
        pageUserProfile.goToAccount();

        log("Возвращаем старый логин");
        pageUserAccount.fillNewLogin(testUserForEditLogin.getUserLogin());

        log("Нажимаем кнопку Сохранить");
        pageUserAccount.clickSaveLogin();

        log("Проверяем почту");
        TestMail testMail2 = new TestMail();
        logErrors = testMail2.checkMailAndChangeLogin(logErrors);

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

            log("Нажимаем кнопку Войти");
            pageTopBottom.goToLogin();

            log("Проверяем, что открылась страница с url /login");
            PageLogin pageLogin = new PageLogin();
            log("Url страницы: " + url());
            logErrors = pageLogin.assertLoginUrl(logErrors);

            log("Проверяем, что есть форма логина");
            pageLogin.isLoginForm();

            log("Заполняем форму логина");
            TestUserData testAdminData = new TestUserData(getAdminId());
            pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

            log("Нажимаем кнопку Войти");
            pageLogin.pushLoginButton();

            log("Проверяем, выполнен ли вход");
            logErrors = pageTopBottom.assertLoggingIn(logErrors);

            log("Переходим к системным дейсвиям");
            MenuContent menuContent = new MenuContent();
            menuContent.goToAdminActions();

            log("Меняем пароль на тестовый пользователю, которому будем менять логин");
            TestUserData testUserForEditPassword = new TestUserData(getGetUserForEditPasswordId());
            PageActions pageActions = new PageActions();
            pageActions.changeUserPassword(testUserForEditPassword.getId(), testUserForEditPassword.getUserPassword());

            log("Выходим из системы");
            pageTopBottom.logout();

            log("Нажимаем кнопку Вход");
            pageTopBottom.goToLogin();

            log("Проверяем, что открылась страница с url /login");
            log("Url страницы: " + url());
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

    }
}
