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

        log("Запущен тест USER-ACC-2.1");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Войти");
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
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        PageUsersList pageUsersList = new PageUsersList();
        TestUserData testUserForEditPersonalDataData = new TestUserData(getUserForEditPersonalDataId());
        pageUsersList.fillUserEmail(testUserForEditPersonalDataData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Данных");
        pageUsersList.chooseFilteredUser();

        log("Проверяем данные на карточке пользователя");
        log("Проверяем фамилию");
        AccountInformation accountInformation = new AccountInformation();
        log(accountInformation.getLastNameUser());
        logErrors = accountInformation.checkLastName(testUserForEditPersonalDataData.getUserLastName(), logErrors);

        log("Проверяем имя");
        log(accountInformation.getFirstNameUser());
        logErrors = accountInformation.checkFirstName(testUserForEditPersonalDataData.getUserFirstName(), logErrors);

        log("Проверяем отчество");
        log(accountInformation.getMiddleNameUser());
        logErrors = accountInformation.checkMiddleName(testUserForEditPersonalDataData.getUserMiddleName(), logErrors);

        log("Редактируем личную информацию от лица администратора");
        logErrors = accountInformation.editPersonalInfoFromAdmin(logErrors);

        log("Проверяем, что изменения сохранены");
        logErrors = accountInformation.checkEditsPersonalInfoFromAdmin(logErrors);

        log("Выйти из системы");
        pageTopBottom.logout();

        log("Нажимаем кнопку Войти");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testUserForEditPersonalData = new TestUserData(getUserForEditPersonalDataId());
        pageLogin.fillLoginForm(testUserForEditPersonalData.getUserLogin(), testUserForEditPersonalData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем измененные администратором данные на карточке пользователя");
        logErrors = accountInformation.checkEditsPersonalInfoFromAdmin(logErrors);

        log("Редактируем личную информацию от лица пользователя");
        logErrors = accountInformation.editPersonalInfoFromUser(logErrors);

        log("Проверяем, что изменения сохранены и возвращены в первоначальный вид");
        log("Проверяем фамилию");
        log(accountInformation.getLastNameUser());
        logErrors = accountInformation.checkLastName(testUserForEditPersonalDataData.getUserLastName(), logErrors);

        log("Проверяем имя");
        log(accountInformation.getFirstNameUser());
        logErrors = accountInformation.checkFirstName(testUserForEditPersonalDataData.getUserFirstName(), logErrors);

        log("Проверяем отчество");
        log(accountInformation.getMiddleNameUser());
        logErrors = accountInformation.checkMiddleName(testUserForEditPersonalDataData.getUserMiddleName(), logErrors);

        checkMistakes();

        log("Тест USER-ACC-2.1 завершен");

    }

    //USER-ACC-2.2
    @Test(priority =2)
    public void testChangeUserContactInfo() throws IOException
    {

        log("запущен тест USER-ACC-2.2");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Войти");
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
        PageUsersList pageUsersList = new PageUsersList();
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        TestUserData testUserForEditPersonalDataData = new TestUserData(getUserForEditPersonalDataId());
        pageUsersList.fillUserEmail(testUserForEditPersonalDataData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Данных");
        pageUsersList.chooseFilteredUser();

        log("Редактируем контактную информацию от лица администратора");
        AccountInformation accountInformation = new AccountInformation();
        logErrors = accountInformation.editContactInfoFromAdmin(logErrors);

        log("Проверяем изменения контактной информации");
        logErrors = accountInformation.checkContactInfo(logErrors);

        log("Выйти из системы");
        pageTopBottom.logout();

        log("Нажимаем кнопку Войти");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testUserForEditPersonalData = new TestUserData(getUserForEditPersonalDataId());
        pageLogin.fillLoginForm(testUserForEditPersonalData.getUserLogin(), testUserForEditPersonalData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Редактируем контактную информацию от лица пользователя");
        logErrors = accountInformation.editContactInfoFromUser(logErrors);

        checkMistakes();

        log("Тест USER-ACC-2.2 завершен");

    }

    //USER-ACC-2.3
    @Test(priority =3)
    public void testAddPostFromAdministrator() throws IOException
    {

        log("Тест USER-ACC-2.3 запущен");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Войти'");
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

        log("Переходим в справочник должностей");
        PageCatalogs testCatalogData = new PageCatalogs();
        testCatalogData.goToPostCatalog();

        log("Проверяем отсутсвие тестовой должности в справочнике");
        testCatalogData.checkAbsenceNewPost();

        log("Переход на страницу с пользователями");
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        PageUsersList pageUsersList = new PageUsersList();
        TestUserData testUserForEditPostData = new TestUserData(getUserForEditPostId());
        pageUsersList.fillUserEmail(testUserForEditPostData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Должности");
        pageUsersList.chooseFilteredUser();

        log("Добавляем тестовую должность");
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.addTestPostFromAdmin(logErrors);

        log("Переходим к справочнику должностей");
        testCatalogData.goToPostCatalog();

        log("Проверяем, была ли добавлена новая тестовая должность");
        logErrors = testCatalogData.checkNewPost(logErrors);

        checkMistakes();

        log("Тест USER-ACC-2.3 завершен");

    }


    //USER-ACC-2.4
    @Test(priority =4)
    public void testEditingPostAndWorkPhone() throws IOException
    {

        log("Запущен тест USER-ACC-2.4");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Войти'");
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
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        PageUsersList pageUsersList = new PageUsersList();
        TestUserData testUserForEditPostData = new TestUserData(getUserForEditPostId());
        pageUsersList.fillUserEmail(testUserForEditPostData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Должности");
        pageUsersList.chooseFilteredUser();

        log("Редактируем должность и рабочий телефон");
        AccountInformation accountInformation = new AccountInformation();
        logErrors = accountInformation.editPostAndPhoneFromAdmin(logErrors);

        checkMistakes();

        log("Тест USER-ACC-2.4 завершен");
    }

    //USER-ACC-2.5
    @Test(priority =5)
    public void testAddRoleFromAdministrator() throws IOException
    {

        log("Тест USER-ACC-2.5 запущен");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Войти'");
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
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        PageUsersList pageUsersList = new PageUsersList();
        TestUserData testUserForEditPersonalData = new TestUserData(getUserForEditPersonalDataId());
        pageUsersList.fillUserEmail(testUserForEditPersonalData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Личных Данных");
        pageUsersList.chooseFilteredUser();

        log("Проверяем отсутсвие роли администратора у пользователя");
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.checkAndDeleteAdminRole();

        log("Добавляем новую роль пользователю");
        accountInformation.addAndCheckRoleFromAdmin(logErrors);

        log("Входим под пользователем");
        PageUserProfile pageUserProfile = new PageUserProfile();
        pageUserProfile.startSimulateUser();

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

        log("Выход из симулирования пользователя");
        pageUserProfile.stopSimulateUser();

        log("Переход на страницу с пользователями");
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        pageUsersList.fillUserEmail(testUserForEditPersonalData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Личных Данных");
        pageUsersList.chooseFilteredUser();

        log("Удаление роли пользователя");
        accountInformation.deleteRoleAdmin();

        checkMistakes();

        log("Тест USER-ACC-2.5 завершен");
    }
}
