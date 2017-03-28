import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 24.03.2017.
 */
public class ChangingDataAccountTest extends BaseTest
{


    //USER-ACC-2.1
    @Test(priority =1)
    public void testChangeUserPersonalInfo() throws IOException
    {

        log("запущен тест USER-ACC-2.1");

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

        log("Переход на страницу с пользователями");
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.goToUsers();

        log("Заполняем email для поиска пользователя");
        TestUserData testUserForEditPersonalDataData = new TestUserData(getUserForEditPersonalDataId());
        accountInformation.fillEmail(testUserForEditPersonalDataData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        accountInformation.pushSearchButton();

        log("Переходим на страницу пользователя Изменение Данных");
        accountInformation.goToUserForEdit();

        log("Проверяем данные на карточке пользователя");
        logErrors = accountInformation.checkPersonalInfo(logErrors);

        log("Редактируем личную информацию от лица администратора");
        logErrors = accountInformation.editPersonalInfoFromAdmin(logErrors);

        log("Проверяем, что изменения сохранены");
        logErrors = accountInformation.checkEditsPersonalInfoFromAdmin(logErrors);

        checkMistakes();

        log("Тест USER-ACC-2.1 завершен");

    }

    //USER-ACC-2.2
    @Test(priority =2)
    public void testChangeUserContactInfo() throws IOException
    {



    }

    //USER-ACC-2.3
    @Test(priority =3)
    public void TestAddPostFromAdministrator() throws IOException
    {



    }


    //USER-ACC-2.4
    @Test(priority =4)
    public void TestEditingPostAndWorkPhone() throws IOException
    {



    }

    //USER-ACC-2.5
    @Test(priority =5)
    public void TestAddRoleFromAdministrator() throws IOException
    {



    }
}
