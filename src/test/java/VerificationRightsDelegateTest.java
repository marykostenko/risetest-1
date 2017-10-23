import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class VerificationRightsDelegateTest extends BaseTest
{


    @Test (priority = 1)
    public void dataPreparationForVerificationRightsDelegateTest() throws IOException, SQLException
    {
        TestCandidatesData testCandidatesData = new TestCandidatesData();
        TestUserData testAdminData = new TestUserData(getAdminId());
        TestUserData testUserData = new TestUserData();
        PageCandidateList pageCandidateList = new PageCandidateList();
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();

        log("Идёт подготовка тестовых данных для прохождения теста провекрки прав представителя...");

        log("Для выполнения данного теста добавляю тестовых КВОТНЫХ кандидатов в систему:");
        testCandidatesData.createCandidateInAllQuotaStatues((getStandUrl("Ext")));

        log("Для выполнения данного теста добавляю тестовых КОНТРАКТНЫХ кандидатов в систему:");
        testCandidatesData.createCandidateInAllContractStatues((getStandUrl("Ext")));

        log("Переиндексируем добавленых не через интерфейс кандидатов");
        pageCandidateList.reindexInputCandidate(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getCountry(), testRepresentativeData.getCountryId());

        log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
        testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getId(), testRepresentativeData.getUserPassword());

        log("Перед выполнением данного теста необходимо очистить список отмеченных кандидатов для представителя");
        testDatabaseConnection.deleteCandidatesFromSelection(testRepresentativeData.getId());

        log("Отмечаем кандидатов во всех возможных состояниях для представителя");
        open(getStandUrl("Ext"));
        pageTopBottom.goToLogin();
        pageLogin.fillLoginForm(testRepresentativeData.getUserLogin(), testRepresentativeData.getUserPassword());
        pageLogin.pushLoginButton();
        testCandidatesData.marksCandidatesInAllStates();

        checkMistakes();
    }

    //USER-Rights-RS-1

    @Test (priority = 2)
    public void testVerificationQuotaCandidates() throws IOException, SQLException
    {
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        TestCandidatesData testCandidatesData = new TestCandidatesData();

        log("Запущен тест USER-Rights-1.1");

        log("Открываем главную страницу");
        open(getStandUrl("Ext"));

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(testRepresentativeData.getUserLogin(), testRepresentativeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверим, что можем открыть карточки всех квотных кандидатов и не увидим в них информации об агентах");

        logErrors = testCandidatesData.checkAllQuotaCandidateCanOpenCard(logErrors);

        checkMistakes();
    }

    @Test (priority = 3)
    public void testVerificationContractCandidates() throws IOException, SQLException
    {
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        TestCandidatesData testCandidatesData = new TestCandidatesData();

        log("Запущен тест USER-Rights-1.2");

        log("Открываем главную страницу");
        open(getStandUrl("Ext"));

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(testRepresentativeData.getUserLogin(), testRepresentativeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Перейдём к списку кандидатов и проверим, что не можем открыть карточки всех контрактных кандидатов");
        logErrors = testCandidatesData.checkAllContractCandidateCanNotOpenCard(logErrors);

        checkMistakes();
    }

    @Test (priority = 4)
    public void testVerificationAddingToUnloading() throws IOException, SQLException
    {
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageCandidateList pageCandidateList = new PageCandidateList();
        MenuContent menuContent = new MenuContent();
        Downloads downloads = new Downloads();

        downloads.deleteAllDownloads();

        log("Запущен тест USER-Rights-1.3");

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(testRepresentativeData.getUserLogin(), testRepresentativeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Выгрузим отмеченных кандидатов");
        menuContent.goToCandidatesList();
        pageCandidateList.clickSelected();
        pageCandidateList.clickToExcelUpload();

        logErrors = downloads.checkUploadingStatesCandidates(logErrors);

        checkMistakes();
    }

}
