import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 20.12.2016.
 */
//USER-L-2
public class LoginUnsuccessfulTest extends BaseTest {

    //USER L-2.1
    @Test(priority =1)
    public void testUnsuccessfulLogin() throws IOException {
        log("Запущен тест USER-L-2.1");

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
        TestUserData testUserLoserData = new TestUserData(getUserLoserId());
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, что есть алерт о неверном email или пароле");
        pageLogin.alertLoginFail(logErrors);

        log("Снова заполняем пароль и нажимаем кнопку вход (повторяем дважды) до появления капчи");
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());
        pageLogin.pushLoginButton();

        log("Проверяем наличие капчи");
        pageLogin.isCapcha();

        log("Снова заполняем пароль и нажимаем кнопку вход (повторяем дважды) до появления алерта");
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());
        pageLogin.pushLoginButton();
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());
        pageLogin.pushLoginButton();

        log("Проверяем наличие алерта о превышении допустимого числа ошибок");
        pageLogin.alertExcessLoginUnsuccessfulAttempts(logErrors);

        checkMistakes();

        log("Тест USER-L-2.1 завершен");
    }

    //USER-L-2.2
    @Test (priority = 2)
    public void resetLoginAttemps() throws IOException{

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
        pageTopBottom.adminMenu();

        log("Переходим к списку всех пользователей системы");
        UserControl userControl = new UserControl();
        userControl.usersPage();

        log("Заполняем email для поиска пользователя");
        TestUserData testUserLoserData = new TestUserData(getUserLoserId());
        userControl.fillUserEmail(testUserLoserData.getUserLogin());

        log("Нажимаем кнопку поиск");
        userControl.userSearchButton();

        log("Выбираем пользователя");
        userControl.filteredUser();

        log("Сбрасываем число попыток ввода пользователя до нуля");
        userControl.resetLoginAttempts();

        checkMistakes();

        log("Тест USER-L-2.2 завершен");

    }

    @Test (priority = 3)
    public void checkResetLogins() throws IOException{

        log("Запущен тест USER-L-2.3");

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
        TestUserData testUserLoserData = new TestUserData(getUserLoserId());
        pageLogin.fillLoginForm(testUserLoserData.getUserLogin(), testUserLoserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, что есть алерт о неверном email или пароле");
        pageLogin.alertLoginFail(logErrors);

        checkMistakes();

        log("Тест USER-L-2.3 завершен");

    }

}
