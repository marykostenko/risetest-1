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
    public void testTheAdminMenu() throws IOException
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
        logErrors = menuContent.checkMenuPlans(logErrors);


        log("Проверка меню 'Организации'");
        logErrors = menuContent.checkMenuOrg(logErrors);


        log("Проверка меню 'Кандидаты'");
        logErrors = menuContent.checkMenuCand(logErrors);


        log("Проверка меню 'Страны'");
        logErrors = menuContent.checkMenuCountries(logErrors);


        log("Проверка меню 'Визы'");
        logErrors = menuContent.checkMenuVisa(logErrors);

        log("Проверка меню 'Агенты'");
        logErrors = menuContent.checkMenuAgents(logErrors);

        log("Проверка меню 'Администрирование'");
        logErrors = menuContent.checkMenuAdmin(logErrors);

        PageActions pageActions = new PageActions();

        log("Меняем пароль представителю на тестовый для выполнения следующего теста");
        menuContent.goToActions();
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        pageActions.changeUserPassword(testRepresentativeData.getId(), testRepresentativeData.getUserPassword());

        log("Меняем пароль куратору на тестовый для выполнения следующего теста");
        menuContent.goToAdminActions();
        TestUserData testCuratorData = new TestUserData(getCuratorId());
        pageActions.changeUserPassword(testCuratorData.getId(), testCuratorData.getUserPassword());

        log("Меняем пароль администратору организации для выполнения следующиего теста");
        menuContent.goToAdminActions();
        TestUserData testAdminOrgData = new TestUserData(getAdminOrgId());
        pageActions.changeUserPassword(testAdminOrgData.getId(), testAdminOrgData.getUserPassword());


        checkMistakes();

        log("Тест SYS-MENU-1.1 завершен");


    }

    //SYS-MENU-1.2
    @Test(priority = 2)
    public void testTheCuratorMenu() throws IOException
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
        logErrors = menuContent.checkMenuOrg(logErrors);

        log("Проверка меню 'Кандидаты'");
        logErrors = menuContent.checkMenuCand(logErrors);

        log("Проверка меню 'Страны'");
        logErrors = menuContent.checkMenuCountries(logErrors);

        log("Проверка меню 'Визы'");


        log("Проверка меню 'Администрирование'");
        logErrors = menuContent.checkMenuAdminForCurator(logErrors);

        checkMistakes();

        log("Тест SYS-MENU-1.2 завершен");

    }

    //SYS-MENU-1.3
    @Test(priority = 3)
    public void testTheAdminOrgMenu() throws IOException
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
        logErrors = menuContent.checkMenuAdminOrg(logErrors);


        checkMistakes();

        log("Тест SYS-MENU-1.3 завершен");

    }

    //SYS-MENU-1.4
    @Test(priority = 4)
    public void testTheRepresentativeMenu() throws IOException
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
        logErrors = menuContent.checkMenuCand(logErrors);

        log("Проверка меню 'Страны'");
        logErrors = menuContent.checkMenuCountries(logErrors);

        checkMistakes();

        log("Тест SYS-MENU-1.4 завершен");

    }
}
