import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class NewRegistrationTest extends BaseTest
{

    @Test(priority = 1)
    public void testSuccessfulPaymentNEW() throws IOException, InterruptedException, MessagingException, SQLException {

        PageRegistration pageRegistration = new PageRegistration();
        //определяем связь с рандомным email кандидата, для записи его в файл
        TestRandomUserData registrationUserForPayFeeRandomEmail = new TestRandomUserData(getUserForPayFeeId());
        //определяем связь с данными для регистрации кандидата для оплаты сервисного сбора
        TestUserData registrationUserForPayFeeData = new TestUserData(getUserForPayFeeId());
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestRequestsForHttp testRequestsForHttp1 = new TestRequestsForHttp();
        TestRequestsForHttp testRequestsForHttp2 = new TestRequestsForHttp();
        TestUserData testUserData = new TestUserData();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnection testDatabaseConnection1 = new TestDatabaseConnection();
        HomePageControl homePageControl = new HomePageControl();
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PageEditCandidate pageEditCandidate = new PageEditCandidate();


        // создаём url, на который будем отправлять пост запрос при регистрации
        String urlRequestForRegistration = (getStandUrl("Ext")+"/registration?contract=true");
        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());
        //создаём запрос к базе данных
        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(randomEmail);
        String queryCandidateId = testDatabaseConnection.requestSelectCandidateId(randomEmail);


        log("Сохраняем email для последующего входа под этим кандидатом");
        registrationUserForPayFeeRandomEmail.entryUserData(registrationUserForPayFeeRandomEmail.getUserForPayFeeRandomEmail(), randomEmail);

        log("Заполняем обязательные поля в POST запросе и отправляем данные на регистрацию");
        testRequestsForHttp.postRequestForRegistrationWithPartialFilling(urlRequestForRegistration, registrationUserForPayFeeData.getUserLastName(), registrationUserForPayFeeData.getUserFirstName(),
                registrationUserForPayFeeData.getSexEn(), registrationUserForPayFeeData.getCountryId(), randomEmail, registrationUserForPayFeeData.getUserPassword());

        log("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryActivationCode,
                testDatabaseConnection.getColumnActivation());

        log("Формируем активационную ссылку и переходим по ней");
        String activationLink = testUserData.createActivationLinkForContract(getStandUrl("Ext"),activationCode);
        log("Активационная ссылка: " + activationLink);
        open(activationLink);

        log("Проверяем что открылась главная страница сайта");
        logErrors = homePageControl.isHomePage(logErrors);

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        log(randomEmail);
        pageLogin.fillLoginForm(randomEmail, registrationUserForPayFeeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Заходим в базу, берём id канидадта");
        String candidateId = testDatabaseConnection1.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryCandidateId,
                testDatabaseConnection.getColumnCandidateId());
        log(candidateId);

        log("Формируем адрес для POST запроса на отправку персональных данных кандидата");
        String urlForRequestFillCandidatePersonalData = pageEditCandidate.createUrlRequestForEditPersonalData((getStandUrl("Ext")), candidateId,
                registrationUserForPayFeeData.getCandidateFormAndCardTpl(), registrationUserForPayFeeData.getNationalSelectionId());
        log(urlForRequestFillCandidatePersonalData);

        log("Отправляем POST запрос с персональными данными кандидата");
        testRequestsForHttp1.postRequestFillCandidatePersonalData(urlForRequestFillCandidatePersonalData, registrationUserForPayFeeData.getUserLastName(),
                registrationUserForPayFeeData.getUserFirstName(), registrationUserForPayFeeData.getPlaceOfBirth(), registrationUserForPayFeeData.getDateOfBirth(),
                registrationUserForPayFeeData.getSexEn(), randomEmail, registrationUserForPayFeeData.getLvlId(), registrationUserForPayFeeData.getPreviousEduOrganization(),
                registrationUserForPayFeeData.getCountryPreviousEduOrganizationId(), registrationUserForPayFeeData.getSourceOfSearch());

        log("Формируем адрес для POST зароса на отправку заявки кандидата");
        String urlForRequestFillCandidateRequest = pageEditCandidate.createUrlRequestForEditRequest((getStandUrl("Ext")), candidateId, registrationUserForPayFeeData.getCandidateFormAndCardTpl(),
                registrationUserForPayFeeData.getNationalSelectionId());
        log(urlForRequestFillCandidateRequest);

        log("Отправлем POST запрос с данными о заявке кандидата");
        testRequestsForHttp2.postRequestFillCandidateRequest(urlForRequestFillCandidateRequest, registrationUserForPayFeeData.getAgreeToContract(), registrationUserForPayFeeData.getCandidateStateCode(), registrationUserForPayFeeData.getEduDirId(),
                registrationUserForPayFeeData.getEducationForm(), registrationUserForPayFeeData.getLanguagesWithDegrees(), registrationUserForPayFeeData.getLanguagesWithDegreesDegree(),
                registrationUserForPayFeeData.getLanguagesWithDegreesLanguage(), registrationUserForPayFeeData.getLvlId(), registrationUserForPayFeeData.getSelectedOrgId());


        checkMistakes();

    }
}
