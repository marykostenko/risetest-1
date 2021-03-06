import org.bouncycastle.util.test.TestRandomData;
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
        TestRandomUserData registrationQuotaPartialUserrandomData = new TestRandomUserData(getUserForRegistrationPartialQuotaId());

        pageLogin.fillLoginForm(registrationQuotaPartialUserrandomData.getRandomEmail(), registrationQuotaPartialUserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFICandidate());
        logErrors = pageTopBottom.assertFIOCandidateLoggedIn(registrationQuotaPartialUserData.getUserFirstName() + " " + registrationQuotaPartialUserData.getUserLastName(), logErrors);

        checkMistakes();

        log("Тест CAND-REG-3.1 завершен");
    }

    //CAND-REG-3.2
    @Test(priority = 2)
    public void testQuotaCandidateFullFillingLogin() throws IOException
    {
        log("Запущен тест CAND-REG-3.2");

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
        TestUserData registrationQuotaFullUserData = new TestUserData(getUserForRegistrationFullQuotaId());
        TestRandomUserData registrationQuotaFullUserRandomData = new TestRandomUserData(getUserForRegistrationFullQuotaId());

        pageLogin.fillLoginForm(registrationQuotaFullUserRandomData.getRandomEmail(), registrationQuotaFullUserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFICandidate());
        logErrors = pageTopBottom.assertFIOCandidateLoggedIn(registrationQuotaFullUserData.getUserFirstName() + " " + registrationQuotaFullUserData.getUserLastName(), logErrors);

        checkMistakes();

        log("Тест CAND-REG-3.2 завершен");
    }

    //CAND-REG-3.3
    @Test(priority = 3)
    public void testContractCandidatePartialFillingLogin() throws IOException
    {
        log("Запущен тест CAND-REG-3.3");

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
        TestUserData registrationContractPartialUserData = new TestUserData(getUserForRegistrationPartialContractId());
        TestRandomUserData registrationContractPartialUserRandomData = new TestRandomUserData(getUserForRegistrationPartialContractId());

        pageLogin.fillLoginForm(registrationContractPartialUserRandomData.getRandomEmail(), registrationContractPartialUserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFICandidate());
        logErrors = pageTopBottom.assertFIOCandidateLoggedIn(registrationContractPartialUserData.getUserFirstName() + " " + registrationContractPartialUserData.getUserLastName(), logErrors);

        checkMistakes();

        log("Тест CAND-REG-3.3 завершен");
    }

    //CAND-REG-3.4
    @Test(priority = 4)
    public void testContractCandidateFullFillingLogin() throws IOException
    {
        log("Запущен тест CAND-REG-3.4");

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
        TestUserData registrationContractFullUserData = new TestUserData(getUserForRegistrationFullContractId());
        TestRandomUserData registrationContractFullUserRandomData = new TestRandomUserData(getUserForRegistrationFullContractId());

        pageLogin.fillLoginForm(registrationContractFullUserRandomData.getRandomEmail(), registrationContractFullUserData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFICandidate());
        logErrors = pageTopBottom.assertFIOCandidateLoggedIn(registrationContractFullUserData.getUserFirstName() + " " + registrationContractFullUserData.getUserLastName(), logErrors);

        checkMistakes();

        log("Тест CAND-REG-3.4 завершен");
    }
}
