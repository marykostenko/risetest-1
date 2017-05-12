import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 20.12.2016.
 */
//USER-L-2
public class LoginUnsuccessfulTest extends BaseTest {

    //USER L-2.1
    @Test(priority =1)
    public void testUnsuccessfulLogin() throws IOException
    {
        log("Запущен тест USER-L-2.1");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
        TestUserData testAdminData = new TestUserData(getAdminId());
        TestUserData testUserLoserData = new TestUserData(getUserLoserId());
        TestUserData testUserData = new TestUserData();
        testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testUserLoserData.getId(), testUserLoserData.getUserPassword());
        log("Пароль пользователя изменен. Продолжаем выполнение теста");

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        PageLogin pageLogin = new PageLogin();
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, что есть алерт о неверном email или пароле");
        logErrors = pageLogin.alertLoginFail(logErrors);

        log("Снова заполняем пароль и нажимаем кнопку вход (повторяем дважды) до появления капчи");
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());
        pageLogin.pushLoginButton();

        log("Проверяем наличие капчи");
        logErrors = pageLogin.isCapcha(logErrors);

        log("Снова заполняем пароль и нажимаем кнопку вход (повторяем дважды) до появления алерта");
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());
        pageLogin.pushLoginButton();

        log("Проверяем наличие алерта о превышении допустимого числа ошибок");
        logErrors = pageLogin.alertExcessLoginUnsuccessfulAttempts(logErrors);

        checkMistakes();

        log("Тест USER-L-2.1 завершен");
    }

    //USER-L-2.2
    @Test (priority = 2)
    public void resetLoginAttemps() throws IOException
    {
        log("Запущен тест USER-L-2.2");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
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


        log("Открываем меню администратора");
        pageTopBottom.openAdminMenu();

        log("Переходим к списку всех пользователей системы");
        PageUsersList userControl = new PageUsersList();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        TestUserData testUserLoserData = new TestUserData(getUserLoserId());
        userControl.fillUserEmail(testUserLoserData.getUserLogin());

        log("Нажимаем кнопку поиск");
        userControl.clickUserSearchButton();

        log("Выбираем пользователя");
        userControl.chooseFilteredUser();

        log("Сбрасываем число попыток ввода пользователя до нуля");
        PageUserProfile pageUserProfile = new PageUserProfile();
        pageUserProfile.resetLoginAttempts();

        log("Разлогиниваемся");
        pageTopBottom.logout();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getIncorrectPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, что есть алерт о неверном email или пароле");
        logErrors = pageLogin.alertLoginFail(logErrors);

        checkMistakes();

        log("Тест USER-L-2.2 завершен");

    }

}
