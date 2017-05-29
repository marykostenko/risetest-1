import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 29.05.2017.
 */
public class EntryUnderRegistretedCandidatesTest extends BaseTest

{

    //CAND-REG-3.1
    @Test(priority = 1)
    public void testQuotaCandidatePartialFillingLogin() throws IOException
    {
        log("Запущен тест CAND-REG-3.1");

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
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        PageRegistration pageRegistration = new PageRegistration();


   //     pageLogin.fillLoginForm(userLogin, registrationQuotaPartialUserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFILoggedUser());
        logErrors = pageTopBottom.assertFIOLoggedIn(registrationQuotaPartialUserData.getUserFirstName() + " " + registrationQuotaPartialUserData.getUserLastName(), logErrors);

        checkMistakes();

        log("Тест CAND-REG-3.1 завершен");
    }
}
