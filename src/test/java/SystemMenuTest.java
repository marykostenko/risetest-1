import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 14.03.2017.
 */
public class SystemMenuTest extends BaseTest{

    //SYS-MENU-1.1
    @Test(priority =1)
    public void checkTheAdminMenu() throws IOException {
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
        if (pageTopBottom.isAnyMenuAppear(1))
        {
            log("Пункт 'План приема' есть");
            $(By.xpath("//a[contains(@href,'/plans/')]")).click();
            MenuContent menuContent = new MenuContent();
            logErrors = menuContent.menuPlans(logErrors);

        }
        else log("ОШИБКА: Пункт 'План приема' не найден");

        log("Проверка меню 'Организации'");
        if (pageTopBottom.isAnyMenuAppear(2))
        {
            log("Пкнкт 'Организации' есть");
            $(By.xpath("//a[contains(@href,'/organizations')]")).click();
            MenuContent menuContent = new MenuContent();
            logErrors = menuContent.menuOrg(logErrors);
        }
        else log("ОШИБКА: Пункт 'Организации' не найден");

        log("Проверка меню 'Кандидаты'");
        if (pageTopBottom.isAnyMenuAppear(3))
        {
            log("Пкнкт 'Кандидаты' есть");
            $(By.xpath("//a[@href='/candidates']")).click();
            MenuContent menuContent = new MenuContent();
            logErrors = menuContent.menuCand(logErrors);
        }
        else log("ОШИБКА: Пункт 'Кандидаты' не найден");

        log("Проверка меню 'Страны'");
        if (pageTopBottom.isAnyMenuAppear(4))
        {
            log("Пкнкт 'Страны' есть");
            $(By.xpath("//a[@href='/countries']")).click();
            MenuContent menuContent = new MenuContent();
            logErrors = menuContent.menuCoutntries(logErrors);
        }
        else log("ОШИБКА: Пункт 'Страны' не найден");

        log("Проверка меню 'Визы'");
        if (pageTopBottom.isAnyMenuAppear(5))
        {
            log("Пкнкт 'Визы' есть");
            $(By.xpath("//a[@href='/visaRequest/list']")).click();
        }
        else log("ОШИБКА: Пункт 'Визы' не найден");

        log("Проверка меню 'Агенты'");
        if (pageTopBottom.isAnyMenuAppear(6))
        {
            log("Пкнкт 'Агенты' есть");
            $(By.xpath("//a[@href='/agent/list']")).click();
            MenuContent menuContent = new MenuContent();
            logErrors = menuContent.menuAgents(logErrors);
        }
        else log("ОШИБКА: Пункт 'Агенты' не найден");

        log("Проверка меню 'Администрирование'");
        if (pageTopBottom.isAnyMenuAppear(7))
        {
            log("Пкнкт 'Администрирование' есть");
            $(By.xpath("//a[@class='dropdown-toggle padding-right-0']")).click();
        }
        else log("ОШИБКА: Пункт 'Администрирование' не найден");

        checkMistakes();

        log("Тест SYS-MENU-1.1 завершен");


    }

    //SYS-MENU-1.1
    @Test(priority =2)
    public void checkTheCuratorMenu() throws IOException {
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
        if (pageTopBottom.isAnyMenuAppear(2))
        {
            log("Пкнкт 'Организации' есть");
            $(By.xpath("//a[contains(@href,'/organizations')]")).click();
        }
        else log("ОШИБКА: Пункт 'Организации' не найден");

        log("Проверка меню 'Кандидаты'");
        if (pageTopBottom.isAnyMenuAppear(3))
        {
            log("Пкнкт 'Кандидаты' есть");
            $(By.xpath("//a[@href='/candidates']")).click();
        }
        else log("ОШИБКА: Пункт 'Кандидаты' не найден");

        log("Проверка меню 'Страны'");
        if (pageTopBottom.isAnyMenuAppear(4))
        {
            log("Пкнкт 'Страны' есть");
            $(By.xpath("//a[@href='/countries']")).click();
        }
        else log("ОШИБКА: Пункт 'Страны' не найден");

        log("Проверка меню 'Визы'");
        if (pageTopBottom.isAnyMenuAppear(1))
        {
            log("Пкнкт 'Визы' есть");
            $(By.xpath("//a[@href='/visaRequest/list']")).click();
        }
        else log("ОШИБКА: Пункт 'Визы' не найден");

        log("Проверка меню 'Администрирование'");
        if (pageTopBottom.isAnyMenuAppear(7))
        {
            log("Пкнкт 'Администрирование' есть");
            $(By.xpath("//a[@class='dropdown-toggle padding-right-0']")).click();
        }
        else log("ОШИБКА: Пункт 'Администрирование' не найден");

        checkMistakes();

        log("Тест SYS-MENU-1.2 завершен");

    }

    }
