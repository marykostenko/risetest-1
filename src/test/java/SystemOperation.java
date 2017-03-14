import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 28.12.2016.
 */

//SYS-MP-1
public class SystemOperation extends BaseTest {

        //SYS-MP-1.1
        @Test(priority =1)
        public void checkTheHomePageRu() throws IOException {
            log("Запущен тест SYS-MP-1.1");

            log("Переключаем язык страницы на русский");
            PageTopBottom pageTopBottom = new PageTopBottom();
            pageTopBottom.switchToRu();

            log("Проверяем шапку главной страницы");
            HomePageControl homePageControl = new HomePageControl();
            logErrors = homePageControl.hatHomePage(logErrors);

            log("Проверяем подвал главной страницы");
            logErrors = homePageControl.basementHomePage(logErrors);

            log("Проверяем шапку и подвал на русскоязычность");
            logErrors = homePageControl.textPageRu(logErrors);

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
            logErrors = homePageControl.hatHomePage(logErrors);

            log("Проверяем подвал главной страницы");
            logErrors = homePageControl.basementHomePage(logErrors);

            log("Проверяем шапку и подвал на русскоязычность");
            logErrors = homePageControl.textPageRu(logErrors);

            checkMistakes();

            log("Тест SYS-MP-1.1 завершен");
        }

    //SYS-MP-1.2
    @Test(priority =2)
    public void checkTheHomePageEn() throws IOException {
        log("Запущен тест SYS-MP-1.2");

        log("Переключаем язык страницы на английский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToEn();

        log("Проверяем шапку главной страницы");
        HomePageControl homePageControl = new HomePageControl();
        logErrors = homePageControl.hatHomePage(logErrors);

        log("Проверяем подвал главной страницы");
        logErrors = homePageControl.basementHomePage(logErrors);

        log("Проверяем шапку и подвал на русскоязычность");
        logErrors = homePageControl.textPageEn(logErrors);

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
        logErrors = homePageControl.hatHomePage(logErrors);

        log("Проверяем подвал главной страницы");
        logErrors = homePageControl.basementHomePage(logErrors);

        log("Проверяем шапку и подвал на англоязычность");
        logErrors = homePageControl.textPageEn(logErrors);

        log("Переключаем язык страницы на русский");

        pageTopBottom.switchToRu();

        checkMistakes();

        log("Тест SYS-MP-1.2 завершен");
    }
}
