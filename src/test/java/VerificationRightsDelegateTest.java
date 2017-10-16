import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class VerificationRightsDelegateTest extends BaseTest
{
    // определяем связи с данными тестовых квотных канидадтов
    TestCandidatesData inputCandidate = new TestCandidatesData(getInputId());
    TestCandidatesData onCheckCandidate = new TestCandidatesData(getOnCheckId());
    TestCandidatesData inroducedCandidate = new TestCandidatesData(getInroducedId());
    TestCandidatesData invitedForTestsCandidate = new TestCandidatesData(getInvitedForTestsId());
    TestCandidatesData testsDoneCandidate = new TestCandidatesData(getTestsDoneId());
    TestCandidatesData selectedForQuotaCandidate = new TestCandidatesData(getSelectedForQuotaId());
    TestCandidatesData secondDistributionLevelCandidate = new TestCandidatesData(getSecondDistributionLevelId());
    TestCandidatesData dossierFormedCandidate = new TestCandidatesData(getDossierFormedId());
    TestCandidatesData distributedQuotaCandidate = new TestCandidatesData(getDistributedQuotaId());
    TestCandidatesData directedQuotaCandidate = new TestCandidatesData(getDirectedQuotaId());
    TestCandidatesData enrolledQuotaCandidate = new TestCandidatesData(getEnrolledQuotaId());
    TestCandidatesData completedQtCandidate = new TestCandidatesData(getСompletedQtId());
    TestCandidatesData enrolledSubfacultyQCandidate = new TestCandidatesData(getEnrolledSubfacultyQId());
    TestCandidatesData distributedSubfacultyQCandidate = new TestCandidatesData(getDistributedSubfacultyQId());
    TestCandidatesData expelledQuotaCandidate = new TestCandidatesData(getExpelledQuotaId());
    TestCandidatesData notPassedForQuotaCandidate = new TestCandidatesData(getNotPassedForQuotaId());

    // определяем связи с данными тестовых контратных кандидатов
    TestCandidatesData fillingApplicationCandidate = new TestCandidatesData(getFillingApplicationId());
    TestCandidatesData contractAcceptedCandidate = new TestCandidatesData(getContractAcceptedId());
    TestCandidatesData onApprovalCandidate = new TestCandidatesData(getOnApprovalId());
    TestCandidatesData distributedCandidate = new TestCandidatesData(getDistributedId());
    TestCandidatesData rejectedCandidate = new TestCandidatesData(getRejectedId());
    TestCandidatesData enrolledTfCandidate = new TestCandidatesData(getEnrolledTfId());
    TestCandidatesData completedTfId = new TestCandidatesData(getCompletedTfId());
    TestCandidatesData enrolledId = new TestCandidatesData(getEnrolledId());
    TestCandidatesData graduatedId = new TestCandidatesData(getGraduatedId());
    TestCandidatesData expelledId = new TestCandidatesData(getExpelledId());
    TestCandidatesData refusedId = new TestCandidatesData(getRefusedId());


    public VerificationRightsDelegateTest() throws IOException
    {
    }
    //USER-Rights-RS-1

    @Test (priority = 1)
    public void testVerificationQuotaCandidates() throws IOException, SQLException
    {

        TestCandidatesData testCandidatesData = new TestCandidatesData();
        TestUserData testAdminData = new TestUserData(getAdminId());
        TestUserData testUserData = new TestUserData();
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        PageCandidateList pageCandidateList = new PageCandidateList();

        log("Идёт подготовка тестовых данных для прохождения теста провекрки прав представителя...");
        log("Для выполнения данного теста добавляю тестовых кандидатов в систему:");
        testCandidatesData.createCandidateInAllQuotaStatues((getStandUrl("Ext")));

        log("Переиндексируем добавленых не через интерфейс кандидатов");
        pageCandidateList.reindexInputCandidate(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getCountry(), testRepresentativeData.getCountryId());

        log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
        testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getId(), testRepresentativeData.getUserPassword());

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

        logErrors = pageCandidateCard.checkQuotaCandidateCard(inputCandidate.getCandidateRegNumber(), inputCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(onCheckCandidate.getCandidateRegNumber(), onCheckCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(inroducedCandidate.getCandidateRegNumber(), inroducedCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(invitedForTestsCandidate.getCandidateRegNumber(), invitedForTestsCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(testsDoneCandidate.getCandidateRegNumber(), testsDoneCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(selectedForQuotaCandidate.getCandidateRegNumber(), selectedForQuotaCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(secondDistributionLevelCandidate.getCandidateRegNumber(), secondDistributionLevelCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(dossierFormedCandidate.getCandidateRegNumber(), dossierFormedCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(distributedQuotaCandidate.getCandidateRegNumber(), distributedQuotaCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(directedQuotaCandidate.getCandidateRegNumber(), directedQuotaCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(enrolledQuotaCandidate.getCandidateRegNumber(), enrolledQuotaCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(completedQtCandidate.getCandidateRegNumber(), completedQtCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(enrolledSubfacultyQCandidate.getCandidateRegNumber(), enrolledSubfacultyQCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(distributedSubfacultyQCandidate.getCandidateRegNumber(), distributedSubfacultyQCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(expelledQuotaCandidate.getCandidateRegNumber(), expelledQuotaCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(notPassedForQuotaCandidate.getCandidateRegNumber(), notPassedForQuotaCandidate.getCandidateStateName(), logErrors);


        checkMistakes();
    }

    @Test (priority = 2)
    public void testVerificationContractCandidates() throws IOException, SQLException
    {
        TestCandidatesData testCandidatesData = new TestCandidatesData();
        TestUserData testUserData = new TestUserData();
        TestUserData testAdminData = new TestUserData(getAdminId());
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageCandidateList pageCandidateList = new PageCandidateList();

        log("Идёт подготовка тестовых данных для прохождения теста провекрки прав представителя...");
        log("Для выполнения данного теста добавляю тестовых кандидатов в систему:");
        testCandidatesData.createCandidateInAllContractStatues((getStandUrl("Ext")));

        log("Переиндексируем добавленых не через интерфейс кандидатов");
        pageCandidateList.reindexInputCandidate(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getCountry(), testRepresentativeData.getCountryId());

        log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
        testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getId(), testRepresentativeData.getUserPassword());

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
        logErrors = pageCandidateList.canNotOpenACard(logErrors, fillingApplicationCandidate.getCandidateRegNumber(), fillingApplicationCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, contractAcceptedCandidate.getCandidateRegNumber(), contractAcceptedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, onApprovalCandidate.getCandidateRegNumber(), onApprovalCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, distributedCandidate.getCandidateRegNumber(), distributedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, rejectedCandidate.getCandidateRegNumber(), rejectedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, enrolledTfCandidate.getCandidateRegNumber(), enrolledTfCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, completedTfId.getCandidateRegNumber(), completedTfId.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, enrolledId.getCandidateRegNumber(), enrolledId.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, graduatedId.getCandidateRegNumber(), graduatedId.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, expelledId.getCandidateRegNumber(), expelledId.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, refusedId.getCandidateRegNumber(), refusedId.getCandidateStateName());

        checkMistakes();
    }

    @Test (priority = 3)
    public void testVerificationAddingToUnloading() throws IOException, SQLException
    {
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageCandidateList pageCandidateList = new PageCandidateList();
        MenuContent menuContent = new MenuContent();
        Downloads downloads = new Downloads();

        log("Перед выполнением данного теста необходимо очистить список отмеченных кандидатов для представителя и удалить ранее загруженные файлы");
        testDatabaseConnection.deleteCandidatesFromSelection(testRepresentativeData.getId());
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

        log("Отмечаем кандидатов во всех возможных состояниях");
        pageCandidateList.marksCandidate(inputCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(onCheckCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(inroducedCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(invitedForTestsCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(testsDoneCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(selectedForQuotaCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(secondDistributionLevelCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(dossierFormedCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(distributedQuotaCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(directedQuotaCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(enrolledQuotaCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(completedQtCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(enrolledSubfacultyQCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(distributedSubfacultyQCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(expelledQuotaCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(notPassedForQuotaCandidate.getCandidateRegNumber());

        pageCandidateList.marksCandidate(fillingApplicationCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(contractAcceptedCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(onApprovalCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(distributedCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(rejectedCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(enrolledTfCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(completedTfId.getCandidateRegNumber());
        pageCandidateList.marksCandidate(enrolledId.getCandidateRegNumber());
        pageCandidateList.marksCandidate(graduatedId.getCandidateRegNumber());
        pageCandidateList.marksCandidate(expelledId.getCandidateRegNumber());
        pageCandidateList.marksCandidate(refusedId.getCandidateRegNumber());

        log("Выгрузим отмеченных кандидатов");
        menuContent.goToCandidatesList();
        pageCandidateList.clickSelected();
        pageCandidateList.clickToExcelUpload();

        logErrors = downloads.checkUploadingStatesCandidates(logErrors);

        checkMistakes();
    }

    @Test (priority = 4)
    public void testVerificationDistributionOfMessages() throws IOException, SQLException
    {
        checkMistakes();
    }
}
