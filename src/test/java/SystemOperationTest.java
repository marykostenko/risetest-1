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

            log("Проверяем шапку главной страницы");
            HomePageControl homePageControl = new HomePageControl();
            logErrors = homePageControl.checkHatHomePage(logErrors);

            log("Проверяем подвал главной страницы");
            logErrors = homePageControl.checkBasementHomePage(logErrors);

            log("Проверяем шапку и подвал на русскоязычность");
            logErrors = homePageControl.checkTextPageRu(logErrors);

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

            log("Проверяем, выполнен ли вход");
            logErrors = pageTopBottom.assertLoggingIn(logErrors);

            log("Переходим к системным дейсвиям");
            MenuContent menuContent = new MenuContent();
            menuContent.goToAdminActions();

            log("Меняем пароль на тестовый пользователю, которому будем менять логин");
            TestUserData testCandidateData = new TestUserData(getCandidateId());
            PageActions pageActions = new PageActions();
            pageActions.changeUserPassword(testCandidateData.getId(), testCandidateData.getUserPassword());

            log("Выходим из системы");
            pageTopBottom.logout();

            log("Нажимаем кнопку \"Вход\"");
            pageTopBottom.goToLogin();

            log("Проверяем, что есть форма логина");
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
