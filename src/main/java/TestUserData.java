import javax.mail.MessagingException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Reporter.log;

/**
 * Created by user nkorobicina on 07.12.2016.
 */
public class TestUserData
{

    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userFirstNameEng;
    private String userLastNameEng;
    private String userLogin;
    private String userPassword;
    private String userPost;
    private String userNewLogin;
    private String userNewPassword;
    private String userPhone;
    private String userEmail;
    private String userWorkPhone;
    private String newPost;
    private String id;
    private String incorrectPassword;
    private String sexRu;
    private String sexEn;
    private String country;
    private String countryId;
    private String placeOfBirth;
    private String dateOfBirth;
    private String educationLvl;
    private String educationLvlId;
    private String previousEduOrganization;
    private String previousEduOrganizationId;
    private String countryOfFinishedEducationOrganisation;
    private String countryOfFinishedEducationOrganisationId;
    private String lvlId;
    private String eduDirId;
    private String candidateFormAndCardTpl; //шаблон карточки кандидата
    private String nationalSelectionId;
    private String sourceOfSearchCode;
    private String agreeToContract;
    private String candidateStateCode;
    private String educationForm;
    private String languagesWithDegrees;
    private String languagesWithDegreesDegree;
    private String languagesWithDegreesLanguage;
    private String selectedOrgId;
    private String documentOfPassportId;
    private String documentCopyOfTheEduCertificate;

//инициализируются данные пользователя по userId - строковый идентификатор пользователя, используемый в файле userData.properties

    public TestUserData(String userId) throws IOException
    {
        userFirstName = this.initUserData(userId + "FirstName");
        userMiddleName = this.initUserData(userId + "MiddleName");
        userLastName = this.initUserData(userId + "LastName");
        userFirstNameEng = this.initUserData(userId + "FirstNameEng");
        userLastNameEng = this.initUserData(userId + "LastNameEng");
        userLogin = this.initUserData(userId + "Login");
        userPassword = this.initUserData(userId + "Password");
        userPost = this.initUserData(userId + "Post");
        userNewLogin = this.initUserData(userId + "NewLogin");
        userNewPassword = this.initUserData(userId + "NewPassword");
        userPhone = this.initUserData(userId + "Phone");
        userEmail = this.initUserData(userId + "Email");
        userWorkPhone = this.initUserData(userId + "WorkPhone");
        newPost = this.initUserData(userId + "NewPost");
        id = this.initUserData(userId + "Id");
        incorrectPassword = this.initUserData(userId + "IncorrectPassword");
        sexRu = this.initUserData(userId + "SexRu");
        sexEn = this.initUserData(userId + "SexEn");
        country = this.initUserData(userId + "Country");
        countryId = this.initUserData(userId + "CountryId");
        placeOfBirth = this.initUserData(userId + "PlaceOfBirth");
        dateOfBirth = this.initUserData(userId + "DateOfBirth");
        educationLvl = this.initUserData(userId + "EducationLvl");
        educationLvlId = this.initUserData(userId + "EducationLvlId");
        previousEduOrganization = this.initUserData(userId + "PreviousEduOrganization");
        previousEduOrganizationId = this.initUserData(userId + "PreviousEduOrganizationId");
        countryOfFinishedEducationOrganisation = this.initUserData(userId + "CountryOfFinishedEducationOrganisation");
        countryOfFinishedEducationOrganisationId = this.initUserData(userId + "CountryOfFinishedEducationOrganisationId");
        lvlId = this.initUserData(userId + "LvlId");
        eduDirId = this.initUserData(userId + "EduDirId");
        candidateFormAndCardTpl = this.initUserData(userId + "CandidateFormAndCardTpl");
        nationalSelectionId = this.initUserData(userId +"NationalSelectionId");
        sourceOfSearchCode = this.initUserData(userId + "SourceOfSearchCode");
        agreeToContract = this.initUserData(userId + "AgreeToContract");
        candidateStateCode = this.initUserData(userId + "CandidateStateCode");
        educationForm = this.initUserData(userId + "EducationForm");
        languagesWithDegrees = this.initUserData(userId + "LanguagesWithDegrees");
        languagesWithDegreesDegree = this.initUserData(userId + "LanguagesWithDegreesDegree");
        languagesWithDegreesLanguage = this.initUserData(userId + "LanguagesWithDegreesLanguage");
        selectedOrgId = this.initUserData(userId + "SelectedOrgId");

        documentOfPassportId = this.initUserData("documentOfPassportId");
        documentCopyOfTheEduCertificate = this.initUserData("documentCopyOfTheEduCertificate");
    }

    public TestUserData() {

    }

    //методу передается название поля в properties и метод возвращает значение поля

    protected String initUserData(String fieldKey) throws IOException
    {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/userData.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }


    public String getUserFirstName() { return userFirstName; }

    public String getUserMiddleName()
    {
        return userMiddleName;
    }

    public String getUserLastName()
    {
        return userLastName;
    }

    public String getUserFirstNameEng() { return userFirstNameEng; }

    public String getUserLastNameEng() { return userLastNameEng; }

    public String getUserLogin() { return userLogin; }

    public String getUserPassword() { return userPassword; }

    public String getUserPost() { return userPost; }

    public String getUserNewLogin() { return userNewLogin; }

    public String getUserNewPassword() { return userNewPassword; }

    public String getUserPhone() {return userPhone; }

    public String getUserEmail () {return userEmail; }

    public String getUserWorkPhone () { return userWorkPhone; }

    public String getNewPost () { return newPost; }

    public String getId() { return id; }

    public String getIncorrectPassword() { return incorrectPassword; }

    public String getSexRu() { return sexRu; }

    public String getSexEn() { return sexEn; }

    public String getCountry() { return country; }

    public String getCountryId() { return countryId; }

    public String getPlaceOfBirth() { return placeOfBirth; }

    public String getDateOfBirth() { return  dateOfBirth; }

    public String getEducationLvl() { return educationLvl; }

    public String getPreviousEduOrganization() { return previousEduOrganization; }

    public String getCountryOfFinishedEducationOrganisation() { return countryOfFinishedEducationOrganisation; }

    public String getCountryOfFinishedEducationOrganisationId() { return countryOfFinishedEducationOrganisationId; }

    public String getLvlId() { return lvlId; }

    public String getEduDirId() { return eduDirId; }

    public String getCandidateFormAndCardTpl() { return candidateFormAndCardTpl; }

    public String getNationalSelectionId() { return nationalSelectionId; }

    public String getEducationLvlId() { return educationLvlId; }

    public String getCountryPreviousEduOrganizationId() { return previousEduOrganizationId; }

    public String getSourceOfSearchCode() { return sourceOfSearchCode; }

    public String getPreviousEduOrganizationId() { return previousEduOrganizationId; }

    public String getAgreeToContract() { return agreeToContract; }

    public String getCandidateStateCode() { return candidateStateCode; }

    public String getEducationForm() { return educationForm; }

    public String getLanguagesWithDegrees() { return languagesWithDegrees; }

    public String getLanguagesWithDegreesDegree() { return languagesWithDegreesDegree; }

    public String getLanguagesWithDegreesLanguage() { return languagesWithDegreesLanguage; }

    public String getSelectedOrgId() { return selectedOrgId; }


    public String getDocumentOfPassportId() { return documentOfPassportId; }

    public String getDocumentCopyOfTheEduCertificate() { return  documentCopyOfTheEduCertificate; }

    /**
     * смена пароля пользователя на тестовый
     */
    public void changePassForTest(String adminLogin, String adminPass, String userId, String userPass)
    {
        PageLogin pageLogin = new PageLogin();
        PageTopBottom pageTopBottom = new PageTopBottom();
        MenuContent menuContent = new MenuContent();
        PageActions pageActions = new PageActions();

        pageTopBottom.goToLogin();
        pageLogin.isLoginForm();
        pageLogin.fillLoginForm(adminLogin, adminPass);
        pageLogin.pushLoginButton();
        menuContent.goToAdminActions();
        pageActions.changeUserPassword(userId, userPass);
        pageTopBottom.logout();
    }


    /**
     * Генерация активационной ссылки для квотников
     */
    public String createActivationLinkForQuota(String stand, String activationCode)
    {
        String activateLink = stand + activationCode +"?contract=false";
        return activateLink;
    }
    /**
     * Генерация активационной ссылки для контрактников
     */
    public String createActivationLinkForContract(String stand, String activationCode)
    {
        String activateLink = stand + "activate/" + activationCode +"?contract=true";
        return activateLink;
    }

    /**
     * Генерация активационной ссылки для квотников c указанием агента
     */
    public String createActivationLinkForQuotaWithPromoCode(String stand, String activationCode, String promo)
    {
        String activateLink = stand + "activate/" + activationCode +"?contract=false&promoCode=" + promo;
        return activateLink;
    }


    /**
     * Активирует кандидата
     */
    public boolean activationCandidate(String standUrl, String randomEmail) throws IOException, SQLException
    {
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        TestUserData testUserData = new TestUserData();
        PageRegistration pageRegistration = new PageRegistration();

        boolean activation;
        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(randomEmail);

        System.out.println("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),  testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(), queryActivationCode, testDatabaseConnection.getColumnActivation());

        System.out.println("Формируем активационную ссылку и переходим по ней");
        String activationLink = testUserData.createActivationLinkForContract(standUrl,activationCode);
        System.out.println("Активационная ссылка: " + activationLink);
        open(activationLink);

        if (!pageRegistration.checkConfirmRegistrationText())
        {
            System.out.println("Ошибка! Кандидат не был активирован!");
            activation = false;
        } else
            {
                System.out.println("Кандидат активирован");
                activation =  true;
            }

            return activation;
    }

    public String creationTestCandidateForPayFee (String standUrl, String userId) throws SQLException, IOException
    {
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        TestUserData userForPayFeeId = new TestUserData(userId);

        String randomEmail = String.valueOf(testRandomUserData.createRandomEmail());
        String queryCandidateId = testDatabaseConnection.requestSelectCandidateId(randomEmail);
        String querySourceOfSearchId = testDatabaseConnection.requestSelectCatalogElementId(userForPayFeeId.getSourceOfSearchCode());
        boolean success; // проверка успешности POST запроса

        System.out.println("Формируем адрес для POST запроса на регистрацию");
        String urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationContract(standUrl);

        System.out.println("Заполняем обязательные поля в POST запросе и отправляем данные на регистрацию");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForRegistrationWithPartialFilling(urlForRequestRegistration, userForPayFeeId.getUserLastName(),
                userForPayFeeId.getUserFirstName(), userForPayFeeId.getSexEn(), userForPayFeeId.getCountryId(), randomEmail, userForPayFeeId.getUserPassword(), null));
        if (!success)
            randomEmail = null;

        success = activationCandidate(standUrl, randomEmail);

        System.out.println("Формируем адрес для POST запроса на логин");
        String urlForRequestLogin = pageEditCandidate.createUrlRequestForLogin(standUrl);

        System.out.println("Отправлем POST запрос с логином");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForLogin(urlForRequestLogin, randomEmail, userForPayFeeId.getUserPassword()));

        if (!success)
            randomEmail = null;

        System.out.println("Заходим в базу, берём id канидадта");
        String candidateId = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryCandidateId,
                testDatabaseConnection.getElementId());
        System.out.println("ID кандидата: " + candidateId);

        System.out.println("Берём в базе id источника поиска");
        String sourceOfSearchId = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(), querySourceOfSearchId,
                testDatabaseConnection.getElementId());
        System.out.println("ID источника поиска: " + sourceOfSearchId);

        System.out.println("Формируем адрес для POST запроса на отправку персональных данных кандидата");
        String urlForRequestFillCandidatePersonalData = pageEditCandidate.createUrlRequestForEditPersonalData(standUrl, candidateId,  userForPayFeeId.getCandidateFormAndCardTpl(),
                userForPayFeeId.getNationalSelectionId());

        System.out.println("Отправляем POST запрос с персональными данными кандидата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidatePersonalData(urlForRequestFillCandidatePersonalData,  userForPayFeeId.getUserLastName(),
                userForPayFeeId.getUserFirstName(),  userForPayFeeId.getPlaceOfBirth(),  userForPayFeeId.getDateOfBirth(),  userForPayFeeId.getSexEn(), randomEmail,  userForPayFeeId.getLvlId(),
                userForPayFeeId.getPreviousEduOrganization(),  userForPayFeeId.getCountryOfFinishedEducationOrganisationId(), sourceOfSearchId));

        if (!success)
            randomEmail = null;
        System.out.println("Формируем адрес для POST зароса на отправку заявки кандидата");
        String urlForRequestFillCandidateRequest = pageEditCandidate.createUrlRequestForEditRequest(standUrl, candidateId,  userForPayFeeId.getCandidateFormAndCardTpl(),
                userForPayFeeId.getNationalSelectionId());

        System.out.println("Отправлем POST запрос с данными о заявке кандидата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidateRequest(urlForRequestFillCandidateRequest,  userForPayFeeId.getAgreeToContract(),
                userForPayFeeId.getCandidateStateCode(),  userForPayFeeId.getEduDirId(),  userForPayFeeId.getEducationForm(),  userForPayFeeId.getLanguagesWithDegrees(),
                userForPayFeeId.getLanguagesWithDegreesDegree(),  userForPayFeeId.getLanguagesWithDegreesLanguage(),  userForPayFeeId.getLvlId(),  userForPayFeeId.getSelectedOrgId()));

        if (!success)
            randomEmail = null;

        System.out.println("Формируем адрес для POST зароса на отправку копии пасспорта");
        String urlForRequestForUploadCopyPassport = pageEditCandidate.createUrlRequestForUploadFile(standUrl,candidateId,  userForPayFeeId.getDocumentOfPassportId());

        System.out.println("Отправляем POST запрос с копией пасспорта");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyPassport));

        if (!success)
            randomEmail = null;

        System.out.println("Формируем адрес для POST зароса на отправку копии документа об образовании");
        String urlForRequestForUploadCopyOfTheEduCertificate = pageEditCandidate.createUrlRequestForUploadFile(standUrl, candidateId,  userForPayFeeId.getDocumentCopyOfTheEduCertificate());

        System.out.println("Отправляем POST запрос с копией документа об образовании");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyOfTheEduCertificate));

        if (!success)
            randomEmail = null;

        System.out.println("Формируем адрес для POST запроса на отправку фото кандидата на сервер");
        String urlForRequestForUploadPhoto = pageEditCandidate.createUrlRequestForUploadPhoto(standUrl, candidateId);

        System.out.println("Отправляем POST запрос с фото и сохраняем временную переменную из ответа для сохранения фото");
        String temporaryImage = testRequestsForHttp.postRequestForUploadPhoto(urlForRequestForUploadPhoto);

        System.out.println("Формируем POST запрос для сохранения фото кандитата");
        String urlForRequestForSavePhoto = pageEditCandidate.createUrlRequestForSavePhoto(standUrl, candidateId);

        System.out.println("Сохраняем фото кандитата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForSavePhoto(urlForRequestForSavePhoto, temporaryImage));
        if (!success)
            randomEmail = null;

        return randomEmail;
    }

}
