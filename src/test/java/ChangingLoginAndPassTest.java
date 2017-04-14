import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 14.04.2017.
 */
public class ChangingLoginAndPassTest extends BaseTest
{
    //USER-L-2.1
    @Test(priority = 1)
    public void testChangingLogin() throws IOException
    {
        log("Запущен тест USER-L-1.1");

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

        checkMistakes();

        log("Тест USER-L-2.1 завершен");
}
