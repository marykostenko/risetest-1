import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 14.03.2017.
 */
public class SystemMenuTest extends BaseTest
{

    //SYS-MENU-1.1
    @Test(priority = 1)
    public void checkTheAdminMenu() throws IOException
    {
        log("Запущен тест SYS-MENU-1.1");

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

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем меню администратора");

        log("Проверка меню 'Плана приема'");
        MenuContent menuContent = new MenuContent();
        logErrors = menuContent.CheckMenuPlans(logErrors);


        log("Проверка меню 'Организации'");
        logErrors = menuContent.CheckMenuOrg(logErrors);


        log("Проверка меню 'Кандидаты'");
        logErrors = menuContent.CheckMenuCand(logErrors);


        log("Проверка меню 'Страны'");
        logErrors = menuContent.CheckMenuCountries(logErrors);


        log("Проверка меню 'Визы'");
        logErrors = menuContent.CheckMenuVisa(logErrors);

        log("Проверка меню 'Агенты'");
        logErrors = menuContent.CheckMenuAgents(logErrors);

        log("Проверка меню 'Администрирование'");
        logErrors = menuContent.CheckMenuAdmin(logErrors);

        checkMistakes();

        log("Тест SYS-MENU-1.1 завершен");


    }

    //SYS-MENU-1.2
    @Test(priority = 2)
    public void checkTheCuratorMenu() throws IOException
    {
        log("Запущен тест SYS-MENU-1.2");
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
        TestUserData testCuratorData = new TestUserData(getCuratorId());
        pageLogin.fillLoginForm(testCuratorData.getUserLogin(), testCuratorData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем меню куратора");

        log("Проверка меню 'Организации'");
        MenuContent menuContent = new MenuContent();
        logErrors = menuContent.CheckMenuOrg(logErrors);

        log("Проверка меню 'Кандидаты'");
        logErrors = menuContent.CheckMenuCand(logErrors);

        log("Проверка меню 'Страны'");
        logErrors = menuContent.CheckMenuCountries(logErrors);

        log("Проверка меню 'Визы'");


        log("Проверка меню 'Администрирование'");
        logErrors = menuContent.CheckMenuAdminForCurator(logErrors);

        checkMistakes();

        log("Тест SYS-MENU-1.2 завершен");

    }

    //SYS-MENU-1.3
    @Test(priority = 3)
    public void checkTheAdminOrgMenu() throws IOException
    {
        log("Запущен тест SYS-MENU-1.3");
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
        TestUserData testCuratorData = new TestUserData(getRepresentativeId());
        pageLogin.fillLoginForm(testCuratorData.getUserLogin(), testCuratorData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем меню администратора организации");

        log("Проверяем организацию:");
        MenuContent menuContent = new MenuContent();
        logErrors = menuContent.CheckMenuAdminOrg(logErrors);


        checkMistakes();

        log("Тест SYS-MENU-1.3 завершен");

    }

    //SYS-MENU-1.4
    @Test(priority = 4)
    public void checkTheRepresentativeMenu() throws IOException
    {
        log("Запущен тест SYS-MENU-1.4");
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
        TestUserData testCuratorData = new TestUserData(getRepresentativeId());
        pageLogin.fillLoginForm(testCuratorData.getUserLogin(), testCuratorData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем меню представителя");

        log("Проверка меню 'Кандидаты'");
        MenuContent menuContent = new MenuContent();
        logErrors = menuContent.CheckMenuCand(logErrors);

        log("Проверка меню 'Страны'");
        logErrors = menuContent.CheckMenuCountries(logErrors);

        checkMistakes();

        log("Тест SYS-MENU-1.4 завершен");

    }
}
