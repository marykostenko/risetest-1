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
        TestUserData testUserForEditPersonalData = new TestUserData(getUserForEditPersonalDataId());
        pageUsersList.fillUserEmail(testUserForEditPersonalData.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя");
        pageUsersList.chooseFilteredUser();

        log("Проверяем данные на карточке пользователя");
        log("Проверяем фамилию");
        PageUserProfile pageUserProfile = new PageUserProfile();

        log(pageUserProfile.getLastNameUser());
        logErrors = pageUserProfile.checkLastName(testUserForEditPersonalData.getUserLastName(), logErrors);

        log("Проверяем имя");
        log(pageUserProfile.getFirstNameUser());
        logErrors = pageUserProfile.checkFirstName(testUserForEditPersonalData.getUserFirstName(), logErrors);

        log("Проверяем отчество");
        log(pageUserProfile.getMiddleNameUser());
        logErrors = pageUserProfile.checkMiddleName(testUserForEditPersonalData.getUserMiddleName(), logErrors);

        log("Редактируем личную информацию от лица администратора");
        pageUserProfile.goToEditPersonalInfo();
        PageEditPersonalInfo pageEditPersonalInfo = new PageEditPersonalInfo();
        TestUserData testChangedUserPersonalData = new TestUserData(getChangedUserPersonalDataId());
        pageEditPersonalInfo.clearFieldsPersonalInfo();
        pageEditPersonalInfo.fillPersonalInfoForm(testChangedUserPersonalData.getUserFirstName(), testChangedUserPersonalData.getUserLastName(), testChangedUserPersonalData.getUserMiddleName(),
                testChangedUserPersonalData.getUserFirstNameEng(), testChangedUserPersonalData.getUserLastNameEng());
        pageEditPersonalInfo.savePersonalInfoChanges();

        log("Проверяем, что изменения сохранены");
        log(pageUserProfile.getLastNameUser());
        logErrors = pageUserProfile.checkLastName(testChangedUserPersonalData.getUserLastName(), logErrors);
        log(pageUserProfile.getFirstNameUser());
        logErrors = pageUserProfile.checkFirstName(testChangedUserPersonalData.getUserFirstName(), logErrors);
        log(pageUserProfile.getMiddleNameUser());
        logErrors = pageUserProfile.checkMiddleName(testChangedUserPersonalData.getUserMiddleName(), logErrors);
        log(pageUserProfile.getLastNameUser());
        logErrors = pageUserProfile.checkLastNameEng(testChangedUserPersonalData.getUserLastNameEng(), logErrors);
        log(pageUserProfile.getFirstNameUserEng());
        logErrors = pageUserProfile.checkFirstNameEng(testChangedUserPersonalData.getUserFirstNameEng(), logErrors);
        log(pageUserProfile.getLastNameUserEng());
        logErrors = pageUserProfile.checkLastNameEng(testChangedUserPersonalData.getUserLastNameEng(), logErrors);

        log("Входим под пользователем, которого редактируем");
        pageUserProfile.startSimulateUser();

        log("Редактируем личную информацию от лица пользователя");
        pageUserProfile.goToEditPersonalInfo();
        pageEditPersonalInfo.clearFieldsPersonalInfo();
        pageEditPersonalInfo.fillPersonalInfoForm(testUserForEditPersonalData.getUserFirstName(), testUserForEditPersonalData.getUserLastName(), testUserForEditPersonalData.getUserMiddleName(),
                testUserForEditPersonalData.getUserFirstNameEng(), testUserForEditPersonalData.getUserLastNameEng());
        pageEditPersonalInfo.savePersonalInfoChanges();

        log("Проверяем, что изменения сохранены и возвращены в первоначальный вид");
        log(pageUserProfile.getLastNameUser());
        logErrors = pageUserProfile.checkLastName(testUserForEditPersonalData.getUserLastName(), logErrors);
        log(pageUserProfile.getFirstNameUser());
        logErrors = pageUserProfile.checkFirstName(testUserForEditPersonalData.getUserFirstName(), logErrors);
        log(pageUserProfile.getMiddleNameUser());
        logErrors = pageUserProfile.checkMiddleName(testUserForEditPersonalData.getUserMiddleName(), logErrors);
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

        log("Проверяем, что контактных данных нет");
        PageUserProfile pageUserProfile = new PageUserProfile();
        log(pageUserProfile.getPhoneUser());
        logErrors = pageUserProfile.checkPhoneUser(testUserForEditPersonalDataData.getUserPhone(), logErrors);
        log(pageUserProfile.getEmailUser());
        logErrors = pageUserProfile.checkEmailUser(testUserForEditPersonalDataData.getUserEmail(), logErrors);

        log("Редактируем контактную информацию от лица администратора");
        pageUserProfile.goToEditContactInfo();
        PageEditContactInfo pageEditContactInfo = new PageEditContactInfo();
        TestUserData testChangedUserPersonalData = new TestUserData(getChangedUserPersonalDataId());
        pageEditContactInfo.clearFieldsContactInfo();
        pageEditContactInfo.fillContactInfoForm(testChangedUserPersonalData.getUserPhone(), testChangedUserPersonalData.getUserEmail());
        pageEditContactInfo.saveContactInfoChanges();


        log("Проверяем изменения контактной информации");
        log(pageUserProfile.getPhoneUser());
        logErrors = pageUserProfile.checkPhoneUser(testChangedUserPersonalData.getUserPhone(), logErrors);
        log(pageUserProfile.getEmailUser());
        logErrors = pageUserProfile.checkEmailUser(testChangedUserPersonalData.getUserEmail(), logErrors);

        log("Входим под пользователем, которому меняем данные");
        pageUserProfile.startSimulateUser();

        log("Редактируем контактную информацию от лица пользователя");
        pageUserProfile.goToEditContactInfo();
        pageEditContactInfo.clearFieldsContactInfo();
        pageEditContactInfo.saveContactInfoChanges();

        log("Проверяем изменения контактной информации");
        log(pageUserProfile.getPhoneUser());
        logErrors = pageUserProfile.checkPhoneUser(testUserForEditPersonalDataData.getUserPhone(), logErrors);
        log(pageUserProfile.getEmailUser());
        logErrors = pageUserProfile.checkEmailUser(testUserForEditPersonalDataData.getUserEmail(), logErrors);

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
        PageCatalogs pageCatalogs = new PageCatalogs();
        pageCatalogs.goToPostCatalog();

        log("Проверяем отсутсвие тестовой должности в справочнике");
        pageCatalogs.checkAbsenceNewPost();

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

        log("Добавляем тестовую должность");
        PageUserProfile pageUserProfile = new PageUserProfile();
        pageUserProfile.goToEditJob();
        PageEditJob pageEditJob = new PageEditJob();
        pageEditJob.clearFieldsJob();
        pageEditJob.fillPost(testUserForEditPostData.getNewPost());
        pageEditJob.saveJobInfoChanges();

        log("Возвращаем должность пользователя в первоначальное состояние");
        pageUserProfile.goToEditJob();
        pageEditJob.clearFieldsJob();
        pageEditJob.fillJobForm(testUserForEditPostData.getUserPost(), testUserForEditPostData.getUserWorkPhone());
        pageEditJob.saveJobInfoChanges();

        log("Переходим к справочнику должностей");
        pageCatalogs.goToPostCatalog();

        log("Проверяем, была ли добавлена новая тестовая должность");
        log(pageCatalogs.getNewPost());
        logErrors = pageCatalogs.checkNewPost(testUserForEditPostData.getNewPost(), logErrors);

        log("Удаляем новую должность");
        pageCatalogs.deleteNewPost();

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

        log("Проверяем первоначальные рабочие данные");
        PageUserProfile pageUserProfile = new PageUserProfile();
        log(pageUserProfile.getPostUser());
        logErrors = pageUserProfile.checkPostUser(testUserForEditPostData.getUserPost(), logErrors);
        log(pageUserProfile.getWorkPhoneUser());
        logErrors = pageUserProfile.checkWorkPhone(testUserForEditPostData.getUserWorkPhone(), logErrors);

        log("Редактируем должность и рабочий телефон");
        pageUserProfile.goToEditJob();
        PageEditJob pageEditJob = new PageEditJob();
        pageEditJob.clearFieldsJob();
        TestUserData testChangedUserPersonalData = new TestUserData(getChangedUserPersonalDataId());
        pageEditJob.fillJobForm(testChangedUserPersonalData.getUserPost(), testChangedUserPersonalData.getUserWorkPhone());
        pageEditJob.saveJobInfoChanges();

        log("Проверка изменения рабочей информации");
        log(pageUserProfile.getPostUser());
        logErrors = pageUserProfile.checkPostUser(testChangedUserPersonalData.getUserPost(), logErrors);
        log(pageUserProfile.getWorkPhoneUser());
        logErrors = pageUserProfile.checkWorkPhone(testChangedUserPersonalData.getUserWorkPhone(), logErrors);

        log("Входим под польхователем, которому меняли данные");
        pageUserProfile.startSimulateUser();

        log("Переходим к профилю пользователя");
        pageTopBottom.goToUserProfile();

        log("Возвращаем должность и рабочий телефон");
        pageUserProfile.goToEditJob();
        pageEditJob.clearFieldsJob();
        pageEditJob.fillJobForm(testUserForEditPostData.getUserPost(), testUserForEditPostData.getUserWorkPhone());
        pageEditJob.saveJobInfoChanges();

        log("Проверка изменения рабочей информации");
        log(pageUserProfile.getPostUser());
        logErrors = pageUserProfile.checkPostUser(testUserForEditPostData.getUserPost(), logErrors);
        log(pageUserProfile.getWorkPhoneUser());
        logErrors = pageUserProfile.checkWorkPhone(testUserForEditPostData.getUserWorkPhone(), logErrors);

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
        TestUserData testUserForAddRole = new TestUserData(getUserForAddRoleId());
        pageUsersList.fillUserEmail(testUserForAddRole.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Личных Данных");
        pageUsersList.chooseFilteredUser();

        log("Проверяем отсутсвие роли администратора у пользователя");
        PageUserProfile pageUserProfile = new PageUserProfile();
        pageUserProfile.checkAndDeleteAdminRole();

        log("Добавляем новую роль пользователю");
        pageUserProfile.addAndCheckRoleFromAdmin(logErrors);

        log("Входим под пользователем");
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



        log("Проверка меню 'Группы'");
        logErrors = menuContent.checkMenuGroups(logErrors);


        log("Проверка меню 'Агенты'");
        logErrors = menuContent.checkMenuAgents(logErrors);

        log("Проверка меню 'Администрирование'");
        logErrors = menuContent.checkMenuAdmin(logErrors);

        log("Выход из симулирования пользователя");
        pageTopBottom.stopSimulateUser();

        log("Переход на страницу с пользователями");
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        log("Заполняем email для поиска пользователя");
        pageUsersList.fillUserEmail(testUserForAddRole.getUserLogin());

        log("Нажимаем кнопку Поиск");
        pageUsersList.clickUserSearchButton();

        log("Переходим на страницу пользователя Изменение Личных Данных");
        pageUsersList.chooseFilteredUser();

        log("Удаление роли пользователя");
        pageUserProfile.deleteRoleAdmin();

        checkMistakes();

        log("Тест USER-ACC-2.5 завершен");
    }
}
