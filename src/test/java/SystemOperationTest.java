import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 28.12.2016.
 */

//SYS-MP-1
public class SystemOperationTest extends BaseTest
{

        //SYS-MP-1.1
        @Test(priority =1)
        public void testTheHomePageRu() throws IOException
        {

            log("Запущен тест SYS-MP-1.1");

            log("Переключаем язык страницы на русский");
            PageTopBottom pageTopBottom = new PageTopBottom();
            pageTopBottom.switchToRu();

            log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
            TestUserData testAdminData = new TestUserData(getAdminId());
            TestUserData testCandidateData = new TestUserData(getCandidateId());
            TestUserData testUserData = new TestUserData();
            testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testCandidateData.getId(), testCandidateData.getUserPassword());
            log("Пароль пользователя изменен. Продолжаем выполнение теста");

            log("Проверяем шапку главной страницы");
            HomePageControl homePageControl = new HomePageControl();
            logErrors = homePageControl.checkHatHomePage(logErrors);

            log("Проверяем подвал главной страницы");
            logErrors = homePageControl.checkBasementHomePage(logErrors);

            log("Проверяем шапку и подвал на русскоязычность");
            logErrors = homePageControl.checkTextPageRu(logErrors);

            log("Нажимаем кнопку \"Вход\"");
            pageTopBottom.goToLogin();

            log("Проверяем, что есть форма логина");
            PageLogin pageLogin = new PageLogin();
            pageLogin.isLoginForm();

            log("Заполняем форму логина");
            pageLogin.fillLoginForm(testCandidateData.getUserLogin(), testCandidateData.getUserPassword());

            log("Нажимаем кнопку Войти");
            pageLogin.pushLoginButton();

            log("Переходим на главную страницу сайта");
            pageTopBottom.goToHomePage();

            log("Проверяем шапку главной страницы");
            logErrors = homePageControl.checkHatHomePage(logErrors);

            log("Проверяем подвал главной страницы");
            logErrors = homePageControl.checkBasementHomePage(logErrors);

            log("Проверяем шапку и подвал на русскоязычность");
            logErrors = homePageControl.checkTextPageRu(logErrors);

            checkMistakes();

            log("Тест SYS-MP-1.1 завершен");
        }

    //SYS-MP-1.2
    @Test(priority =2)

    public void testTheHomePageEn() throws IOException
    {

        log("Запущен тест SYS-MP-1.2");

        log("Переключаем язык страницы на английский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToEn();

        log("Проверяем шапку главной страницы");
        HomePageControl homePageControl = new HomePageControl();
        logErrors = homePageControl.checkHatHomePage(logErrors);

        log("Проверяем подвал главной страницы");
        logErrors = homePageControl.checkBasementHomePage(logErrors);

        log("Проверяем шапку и подвал на русскоязычность");
        logErrors = homePageControl.checkTextPageEn(logErrors);

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что есть форма логина");
        PageLogin pageLogin = new PageLogin();
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testCandidateData = new TestUserData(getCandidateId());
        pageLogin.fillLoginForm(testCandidateData.getUserLogin(), testCandidateData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Переходим на главную страницу сайта");
        pageTopBottom.goToHomePage();

        log("Проверяем шапку главной страницы");
        logErrors = homePageControl.checkHatHomePage(logErrors);

        log("Проверяем подвал главной страницы");
        logErrors = homePageControl.checkBasementHomePage(logErrors);

        log("Проверяем шапку и подвал на англоязычность");
        logErrors = homePageControl.checkTextPageEn(logErrors);

        log("Переключаем язык страницы на русский");

        pageTopBottom.switchToRu();

        checkMistakes();

        log("Тест SYS-MP-1.2 завершен");
    }
}
