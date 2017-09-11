import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 06.06.2017.
 */
public class PayServiceFeeTest extends BaseTest
{
    //PAYNENT-1. Оплата сервисного сбора

    //PAYMENT-1.1
    @Test(priority = 1)
    public void testSuccessfulPayment() throws IOException, InterruptedException, MessagingException, SQLException {

        log("Подготовка тестового кандидата для оплаты сервисного сбора");

        PageRegistration pageRegistration = new PageRegistration();
        //определяем связь с рандомным email кандидата, для записи его в файл
        TestRandomUserData registrationUserForPayFeeRandomEmail = new TestRandomUserData(getUserForPayFeeId());
        //определяем связь с данными для регистрации кандидата для оплаты сервисного сбора
        TestUserData registrationUserForPayFeeData = new TestUserData(getUserForPayFeeId());
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestUserData testUserData = new TestUserData();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnection testDatabaseConnection1 = new TestDatabaseConnection();
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        PageRequest pageRequest = new PageRequest();
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        TestCardData testCardData = new TestCardData();

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());
        //создаём запрос к базе данных
        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(randomEmail);
        String queryCandidateId = testDatabaseConnection.requestSelectCandidateId(randomEmail);
        boolean success; // проверка успешности POST запроса


        log("Сохраняем email для последующего входа под этим кандидатом");
        registrationUserForPayFeeRandomEmail.entryUserData(registrationUserForPayFeeRandomEmail.getUserForPayFeeRandomEmail(), randomEmail);
        log("Сгенерироованный email: " + randomEmail);

        log("Формируем адрес для POST запроса на регистрацию");
        String urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationContract((getStandUrl("Ext")));

        log("Заполняем обязательные поля в POST запросе и отправляем данные на регистрацию");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForRegistrationWithPartialFilling(urlForRequestRegistration, registrationUserForPayFeeData.getUserLastName(), registrationUserForPayFeeData.getUserFirstName(),
                registrationUserForPayFeeData.getSexEn(), registrationUserForPayFeeData.getCountryId(), randomEmail, registrationUserForPayFeeData.getUserPassword()));
        if (success == false) { return; }

        log("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryActivationCode,
                testDatabaseConnection.getColumnActivation());

        log("Формируем активационную ссылку и переходим по ней");
        String activationLink = testUserData.createActivationLinkForContract(getStandUrl("Ext"),activationCode);
        log("Активационная ссылка: " + activationLink);
        open(activationLink);

        log("Формируем адрес для POST запроса на логин");
        String urlForRequestLogin = pageEditCandidate.createUrlRequestForLogin((getStandUrl("Ext")));

        log("Отправлем POST запрос с логином");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForLogin(urlForRequestLogin, randomEmail, registrationUserForPayFeeData.getUserPassword()));
        if (success == false) { return; }

        log("Заходим в базу, берём id канидадта");
        String candidateId = testDatabaseConnection1.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryCandidateId,
                testDatabaseConnection.getColumnCandidateId());
        log("ID кандидата: " + candidateId);

        log("Формируем адрес для POST запроса на отправку персональных данных кандидата");
        String urlForRequestFillCandidatePersonalData = pageEditCandidate.createUrlRequestForEditPersonalData((getStandUrl("Ext")), candidateId,
                registrationUserForPayFeeData.getCandidateFormAndCardTpl(), registrationUserForPayFeeData.getNationalSelectionId());

        log("Отправляем POST запрос с персональными данными кандидата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidatePersonalData(urlForRequestFillCandidatePersonalData, registrationUserForPayFeeData.getUserLastName(),
                registrationUserForPayFeeData.getUserFirstName(), registrationUserForPayFeeData.getPlaceOfBirth(), registrationUserForPayFeeData.getDateOfBirth(),
                registrationUserForPayFeeData.getSexEn(), randomEmail, registrationUserForPayFeeData.getLvlId(), registrationUserForPayFeeData.getPreviousEduOrganization(),
                registrationUserForPayFeeData.getCountryOfFinishedEducationOrganisationId(), registrationUserForPayFeeData.getSourceOfSearch()));
        if (success == false) { return; }

        log("Формируем адрес для POST зароса на отправку заявки кандидата");
        String urlForRequestFillCandidateRequest = pageEditCandidate.createUrlRequestForEditRequest((getStandUrl("Ext")), candidateId, registrationUserForPayFeeData.getCandidateFormAndCardTpl(),
                registrationUserForPayFeeData.getNationalSelectionId());

        log("Отправлем POST запрос с данными о заявке кандидата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidateRequest(urlForRequestFillCandidateRequest, registrationUserForPayFeeData.getAgreeToContract(), registrationUserForPayFeeData.getCandidateStateCode(), registrationUserForPayFeeData.getEduDirId(),
                registrationUserForPayFeeData.getEducationForm(), registrationUserForPayFeeData.getLanguagesWithDegrees(), registrationUserForPayFeeData.getLanguagesWithDegreesDegree(),
                registrationUserForPayFeeData.getLanguagesWithDegreesLanguage(), registrationUserForPayFeeData.getLvlId(), registrationUserForPayFeeData.getSelectedOrgId()));
        if (success == false) { return; }

        log("Формируем адрес для POST зароса на отправку копии пасспорта");
        String urlForRequestForUploadCopyPassport = pageEditCandidate.createUrlRequestForUploadFile((getStandUrl("Ext")), candidateId, registrationUserForPayFeeData.getDocumentOfPassportId());

        log("Отправляем POST запрос с копией пасспорта");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyPassport));
        if (success == false) { return; }

        log("Формируем адрес для POST зароса на отправку копии документа об образовании");
        String urlForRequestForUploadCopyOfTheEduCertificate = pageEditCandidate.createUrlRequestForUploadFile((getStandUrl("Ext")), candidateId,
                registrationUserForPayFeeData.getDocumentCopyOfTheEduCertificate());

        log("Отправляем POST запрос с копией документа об образовании");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyOfTheEduCertificate));
        if (success == false) { return; }

        log("Формируем адрес для POST запроса на отправку фото кандидата на сервер");
        String urlForRequestForUploadPhoto = pageEditCandidate.createUrlRequestForUploadPhoto((getStandUrl("Ext")), candidateId);

        log("Отправляем POST запрос с фото и сохраняем временную переменную из ответа для сохранения фото");
        String temporaryImage = testRequestsForHttp.postRequestForUploadPhoto(urlForRequestForUploadPhoto);

        log("Формируем POST запрос для сохранения фото кандитата");
        String urlForRequestForSavePhoto = pageEditCandidate.createUrlRequestForSavePhoto((getStandUrl("Ext")), candidateId);

        log("Сохраняем фото кандитата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForSavePhoto(urlForRequestForSavePhoto, temporaryImage));
        if (success == false) { return; }


        log("ТЕСТОВЫЙ КАНДИДАТ ГОТОВ К ОПЛАТЕ СЕРВИСНОГО СБОРА");
        log("_______________________________________________________________");

        /**
         * НАЧАЛО ТЕСТА PAYMENT-1.1
         */

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        log(registrationUserForPayFeeRandomEmail.getRandomEmail());
        pageLogin.fillLoginForm(registrationUserForPayFeeRandomEmail.getRandomEmail(), registrationUserForPayFeeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);


        log("Вводим тестовые данные для успешной оплаты");
        pagePaymentServiceFee.fillCardData(testCardData.getNumberCardForSuccessfulPay(), testCardData.getExptDateMounth(), testCardData.getExptDateYear(), testCardData.getCvv());

        logErrors = pagePaymentServiceFee.checkSuccessPaymentInIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.1 завершен");
    }

    //PAYMENT-1.2
    @Test(priority = 2)
    public void testUnsuccessfulPayment() throws IOException, InterruptedException, MessagingException, SQLException {
        log("Подготовка тестового кандидата для оплаты сервисного сбора");

        PageRegistration pageRegistration = new PageRegistration();
        //определяем связь с рандомным email кандидата, для записи его в файл
        TestRandomUserData registrationUserForPayFeeRandomEmail = new TestRandomUserData(getUserForPayFeeId());
        //определяем связь с данными для регистрации кандидата для оплаты сервисного сбора
        TestUserData registrationUserForPayFeeData = new TestUserData(getUserForPayFeeId());
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestUserData testUserData = new TestUserData();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnection testDatabaseConnection1 = new TestDatabaseConnection();
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        PageRequest pageRequest = new PageRequest();
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        TestCardData testCardData = new TestCardData();

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());
        //создаём запрос к базе данных
        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(randomEmail);
        String queryCandidateId = testDatabaseConnection.requestSelectCandidateId(randomEmail);
        boolean success; // проверка успешности POST запроса


        log("Сохраняем email для последующего входа под этим кандидатом");
        registrationUserForPayFeeRandomEmail.entryUserData(registrationUserForPayFeeRandomEmail.getUserForPayFeeRandomEmail(), randomEmail);
        log("Сгенерироованный email: " + randomEmail);

        log("Формируем адрес для POST запроса на регистрацию");
        String urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationContract((getStandUrl("Ext")));

        log("Заполняем обязательные поля в POST запросе и отправляем данные на регистрацию");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForRegistrationWithPartialFilling(urlForRequestRegistration, registrationUserForPayFeeData.getUserLastName(), registrationUserForPayFeeData.getUserFirstName(),
                registrationUserForPayFeeData.getSexEn(), registrationUserForPayFeeData.getCountryId(), randomEmail, registrationUserForPayFeeData.getUserPassword()));
        if (success == false) { return; }

        log("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryActivationCode,
                testDatabaseConnection.getColumnActivation());

        log("Формируем активационную ссылку и переходим по ней");
        String activationLink = testUserData.createActivationLinkForContract(getStandUrl("Ext"),activationCode);
        log("Активационная ссылка: " + activationLink);
        open(activationLink);

        log("Формируем адрес для POST запроса на логин");
        String urlForRequestLogin = pageEditCandidate.createUrlRequestForLogin((getStandUrl("Ext")));

        log("Отправлем POST запрос с логином");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForLogin(urlForRequestLogin, randomEmail, registrationUserForPayFeeData.getUserPassword()));
        if (success == false) { return; }

        log("Заходим в базу, берём id канидадта");
        String candidateId = testDatabaseConnection1.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryCandidateId,
                testDatabaseConnection.getColumnCandidateId());
        log("ID кандидата: " + candidateId);

        log("Формируем адрес для POST запроса на отправку персональных данных кандидата");
        String urlForRequestFillCandidatePersonalData = pageEditCandidate.createUrlRequestForEditPersonalData((getStandUrl("Ext")), candidateId,
                registrationUserForPayFeeData.getCandidateFormAndCardTpl(), registrationUserForPayFeeData.getNationalSelectionId());

        log("Отправляем POST запрос с персональными данными кандидата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidatePersonalData(urlForRequestFillCandidatePersonalData, registrationUserForPayFeeData.getUserLastName(),
                registrationUserForPayFeeData.getUserFirstName(), registrationUserForPayFeeData.getPlaceOfBirth(), registrationUserForPayFeeData.getDateOfBirth(),
                registrationUserForPayFeeData.getSexEn(), randomEmail, registrationUserForPayFeeData.getLvlId(), registrationUserForPayFeeData.getPreviousEduOrganization(),
                registrationUserForPayFeeData.getCountryOfFinishedEducationOrganisationId(), registrationUserForPayFeeData.getSourceOfSearch()));
        if (success == false) { return; }

        log("Формируем адрес для POST зароса на отправку заявки кандидата");
        String urlForRequestFillCandidateRequest = pageEditCandidate.createUrlRequestForEditRequest((getStandUrl("Ext")), candidateId, registrationUserForPayFeeData.getCandidateFormAndCardTpl(),
                registrationUserForPayFeeData.getNationalSelectionId());

        log("Отправлем POST запрос с данными о заявке кандидата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidateRequest(urlForRequestFillCandidateRequest, registrationUserForPayFeeData.getAgreeToContract(), registrationUserForPayFeeData.getCandidateStateCode(), registrationUserForPayFeeData.getEduDirId(),
                registrationUserForPayFeeData.getEducationForm(), registrationUserForPayFeeData.getLanguagesWithDegrees(), registrationUserForPayFeeData.getLanguagesWithDegreesDegree(),
                registrationUserForPayFeeData.getLanguagesWithDegreesLanguage(), registrationUserForPayFeeData.getLvlId(), registrationUserForPayFeeData.getSelectedOrgId()));
        if (success == false) { return; }

        log("Формируем адрес для POST зароса на отправку копии пасспорта");
        String urlForRequestForUploadCopyPassport = pageEditCandidate.createUrlRequestForUploadFile((getStandUrl("Ext")), candidateId, registrationUserForPayFeeData.getDocumentOfPassportId());

        log("Отправляем POST запрос с копией пасспорта");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyPassport));
        if (success == false) { return; }

        log("Формируем адрес для POST зароса на отправку копии документа об образовании");
        String urlForRequestForUploadCopyOfTheEduCertificate = pageEditCandidate.createUrlRequestForUploadFile((getStandUrl("Ext")), candidateId,
                registrationUserForPayFeeData.getDocumentCopyOfTheEduCertificate());

        log("Отправляем POST запрос с копией документа об образовании");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyOfTheEduCertificate));
        if (success == false) { return; }

        log("Формируем адрес для POST запроса на отправку фото кандидата на сервер");
        String urlForRequestForUploadPhoto = pageEditCandidate.createUrlRequestForUploadPhoto((getStandUrl("Ext")), candidateId);

        log("Отправляем POST запрос с фото и сохраняем временную переменную из ответа для сохранения фото");
        String temporaryImage = testRequestsForHttp.postRequestForUploadPhoto(urlForRequestForUploadPhoto);

        log("Формируем POST запрос для сохранения фото кандитата");
        String urlForRequestForSavePhoto = pageEditCandidate.createUrlRequestForSavePhoto((getStandUrl("Ext")), candidateId);

        log("Сохраняем фото кандитата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForSavePhoto(urlForRequestForSavePhoto, temporaryImage));
        if (success == false) { return; }


        log("ТЕСТОВЫЙ КАНДИДАТ ГОТОВ К ОПЛАТЕ СЕРВИСНОГО СБОРА");
        log("_______________________________________________________________");

        /**
         * НАЧАЛО ТЕСТА PAYMENT-1.2
         */

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        log(registrationUserForPayFeeRandomEmail.getRandomEmail());
        pageLogin.fillLoginForm(registrationUserForPayFeeRandomEmail.getRandomEmail(), registrationUserForPayFeeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        log("Вводим тестовые данные для успешной оплаты");
        pagePaymentServiceFee.fillCardData(testCardData.getNumberCardForUnsuccessfulPay(), testCardData.getExptDateMounth(), testCardData.getExptDateYear(), testCardData.getCvv());

        logErrors = pagePaymentServiceFee.checkUnsuccessPaymentInIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.2 завершен");
    }

    //PAYMENT-1.3
    @Test(priority = 3)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.3 Проверка кнопки Оплата у кандидатов в состоянии Заявка на контракт принята, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractIsAccepted();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.3 завершен");
    }

    //PAYMENT-1.4
    @Test(priority = 4)
    public void testCheckPaymentButtonApplicationForOnUniApprovalUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.4 Проверка кнопки Оплата у кандидатов в состоянии На рассмотрении вуза, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'На рассмотрении вуза', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForOnUniApproval();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.4 завершен");
    }

    //PAYMENT-1.5
    @Test(priority = 5)
    public void testCheckPaymentButtonApplicationForDistributedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.5 Проверка кнопки Оплата у кандидатов в состоянии Распределён, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'Не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForDistributed();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.5 завершен");
    }

    //PAYMENT-1.6
    @Test(priority = 6)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedPaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.6 Проверка отсутсвия кнопки Оплата у кандидатов в состоянии Заявка на контракт принята, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'Оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractIsAccepted();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.6 завершен");
    }

    //PAYMENT-1.7
    @Test(priority = 7)
    public void testCheckPaymentButtonApplicationForOnUniApprovalPaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.7 Проверка отсутствия кнопки Оплата у кандидатов в состоянии На рассмотрении вуза, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'На рассмотрении вуза', сервисный сбор 'оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForOnUniApproval();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.7 завершен");
    }

    //PAYMENT-1.8
    @Test(priority = 8)
    public void testCheckPaymentButtonApplicationForDistributedPaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.8 Проверка отсутствия кнопки Оплата у кандидатов в состоянии Распределён, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'Оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForDistributed();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.8 завершен");
    }

}
