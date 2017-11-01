import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;

public class TestCandidatesData
{
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateSexEn;
    private String candidateCountry;
    private String candidatePassword;
    private String candidateState;
    private String candidatePromo;
    private String candidateStateName;
    private String candidateRegNumber;
    private String candidateId;

    private String candidateCountryId;
    private String candidatePlaceOfBirth;
    private String candidateDateOfBirth;
    private String candidateEducationLvl;
    private String candidateEducationLvlId;
    private String candidatePreviousEduOrganization;
    private String candidatePreviousEduOrganizationId;
    private String candidateCountryOfFinishedEducationOrganisation;
    private String candidateCountryOfFinishedEducationOrganisationId;
    private String candidateLvlId;
    private String candidateEduDirId;
    private String candidateFormAndCardTpl; //шаблон карточки кандидата
    private String candidateNationalSelectionId;
    private String candidateSourceOfSearchCode;
    private String candidateAgreeToContract;
    private String candidateStateCode;
    private String candidateEducationForm;
    private String candidateLanguagesWithDegrees;
    private String candidateLanguagesWithDegreesDegree;
    private String candidateLanguagesWithDegreesLanguage;
    private String candidateSelectedOrgId;
    private String candidateDocumentOfPassportId;
    private String candidateDocumentCopyOfTheEduCertificate;

    private static final String input = "input";
    private static final String onCheckId = "onCheck";
    private static final String inroducedId = "inroduced";
    private static final String invitedForTestsId = "invitedForTests";
    private static final String testsDoneId = "testsDone";
    private static final String selectedForQuotaId = "selectedForQuota";
    private static final String secondDistributionLevelId = "secondDistributionLevel";
    private static final String dossierFormedId = "dossierFormed";
    private static final String distributedQuotaId = "distributedQuota";
    private static final String directedQuotaId = "directedQuota";
    private static final String enrolledQuotaId = "enrolledQuota";
    private static final String completedQtId = "completedQt";
    private static final String enrolledSubfacultyQId = "enrolledSubfacultyQ";
    private static final String distributedSubfacultyQId = "distributedSubfacultyQ";
    private static final String expelledQuotaId = "expelledQuota";
    private static final String notPassedForQuotaId = "notPassedForQuota";
    private static final String fillingApplicationId = "fillingApplication";
    private static final String contractAcceptedId = "contractAccepted";
    private static final String onApprovalId = "onApproval";
    private static final String distributedId = "distributed";
    private static final String rejectedId = "rejected";
    private static final String enrolledTfId = "enrolledTf";
    private static final String completedTfId = "completedTf";
    private static final String enrolledId = "enrolled";
    private static final String graduatedId = "graduated";
    private static final String expelledId = "expelled";
    private static final String refusedId = "refused";

    //инициализируются данные пользователя по userId - строковый идентификатор пользователя, используемый в файле testCandidateData.properties

    public TestCandidatesData (String userId) throws IOException
    {
        candidateFirstName = this.initCandidateData(userId + "CandidateFirstName");
        candidateLastName = this.initCandidateData(userId + "CandidateLastName");
        candidateSexEn = this.initCandidateData(userId + "CandidateSexEn");
        candidateCountry = this.initCandidateData(userId + "CandidateCountry");
        candidatePassword = this.initCandidateData(userId + "CandidatePassword");
        candidateState = this.initCandidateData(userId + "CandidateState");
        candidatePromo = this.initCandidateData(userId + "CandidatePromo");
        candidateStateName = this.initCandidateData(userId + "CandidateStateName");
        candidateRegNumber = this.initRegNumber( userId + "CandidateRegNumber");
        candidateId = this.initCandidateId(userId + "CandidateId");
        candidateCountryId = this.initCandidateData(userId + "CandidateCountryId");
        candidatePlaceOfBirth = this.initCandidateData(userId + "CandidatePlaceOfBirth");
        candidateDateOfBirth = this.initCandidateData(userId + "CandidateDateOfBirth");
        candidateEducationLvl = this.initCandidateData(userId + "CandidateEducationLvl");
        candidateEducationLvlId = this.initCandidateData(userId + "CandidateEducationLvlId");
        candidatePreviousEduOrganization = this.initCandidateData(userId + "CandidatePreviousEduOrganization");
        candidatePreviousEduOrganizationId = this.initCandidateData(userId + "CandidatePreviousEduOrganizationId");
        candidateCountryOfFinishedEducationOrganisation = this.initCandidateData(userId + "CandidateCountryOfFinishedEducationOrganisation");
        candidateCountryOfFinishedEducationOrganisationId = this.initCandidateData(userId + "CandidateCountryOfFinishedEducationOrganisationId");
        candidateLvlId = this.initCandidateData(userId + "CandidateLvlId");
        candidateEduDirId = this.initCandidateData(userId + "CandidateEduDirId");
        candidateFormAndCardTpl = this.initCandidateData(userId + "CandidateFormAndCardTpl");
        candidateNationalSelectionId = this.initCandidateData(userId +"CandidateNationalSelectionId");
        candidateSourceOfSearchCode = this.initCandidateData(userId + "CandidateSourceOfSearchCode");
        candidateAgreeToContract = this.initCandidateData(userId + "CandidateAgreeToContract");
        candidateStateCode = this.initCandidateData(userId + "CandidateStateCode");
        candidateEducationForm = this.initCandidateData(userId + "CandidateEducationForm");
        candidateLanguagesWithDegrees = this.initCandidateData(userId + "CandidateLanguagesWithDegrees");
        candidateLanguagesWithDegreesDegree = this.initCandidateData(userId + "CandidateLanguagesWithDegreesDegree");
        candidateLanguagesWithDegreesLanguage = this.initCandidateData(userId + "CandidateLanguagesWithDegreesLanguage");
        candidateSelectedOrgId = this.initCandidateData(userId + "CandidateSelectedOrgId");

        candidateDocumentOfPassportId = this.initCandidateData("documentOfPassportId");
        candidateDocumentCopyOfTheEduCertificate = this.initCandidateData("documentCopyOfTheEduCertificate");

    }

    public void setCandidateRegNumber(String userId, String regNumber) throws IOException
    {
        this.entryRegNumbers(userId + "CandidateRegNumber", regNumber);
    }

    // методу передаётся название поля в properties и метод записывает данные в поле
    public void entryRegNumbers(String fieldKey, String newData) throws IOException
    {
        FileInputStream in = new FileInputStream("src/main/resources/regNumbersCandidates.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        props.setProperty(fieldKey, newData);

        FileOutputStream out = new FileOutputStream("src/main/resources/regNumbersCandidates.properties");
        props.store(out, null);
        out.close();
    }


    public void setCandidateId(String userId, String candidateId) throws IOException
    {
        this.entryCandidateId(userId + "CandidateId", candidateId);
    }

    // методу передаётся название поля в properties и метод записывает данные в поле
    public void entryCandidateId(String fieldKey, String newData) throws IOException
    {
        FileInputStream in = new FileInputStream("src/main/resources/candidatesId.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        props.setProperty(fieldKey, newData);

        FileOutputStream out = new FileOutputStream("src/main/resources/candidatesId.properties");
        props.store(out, null);
        out.close();
    }


    public TestCandidatesData() throws IOException {

    }

    //методу передается название поля в properties и метод возвращает значение поля

    protected String initCandidateData(String fieldKey) throws IOException
    {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/testCandidateData.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }

    protected String initRegNumber (String fieldKey) throws IOException
    {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/regNumbersCandidates.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }

    protected String initCandidateId (String fieldKey) throws IOException
    {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/candidatesId.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }

    public String getCandidateFirstName() { return candidateFirstName; }

    public String getCandidateLastName() { return candidateLastName; }

    public String getCandidateSexEn() { return candidateSexEn; }

    public String getCandidateCountry() { return candidateCountry; }

    public String getCandidatePassword() { return candidatePassword; }

    public String getCandidateState() { return candidateState; }

    public String getCandidatePromo() { return candidatePromo; }

    public String getCandidateStateName() { return candidateStateName; }

    public String getCandidateRegNumber() { return candidateRegNumber; }

    public String getCandidateId() { return candidateId; }

    public String getCountryId() { return candidateCountryId; }

    public String getPlaceOfBirth() { return candidatePlaceOfBirth; }

    public String getDateOfBirth() { return  candidateDateOfBirth; }

    public String getEducationLvl() { return candidateEducationLvl; }

    public String getPreviousEduOrganization() { return candidatePreviousEduOrganization; }

    public String getCountryOfFinishedEducationOrganisation() { return candidateCountryOfFinishedEducationOrganisation; }

    public String getCountryOfFinishedEducationOrganisationId() { return candidateCountryOfFinishedEducationOrganisationId; }

    public String getLvlId() { return candidateLvlId; }

    public String getEduDirId() { return candidateEduDirId; }

    public String getCandidateFormAndCardTpl() { return candidateFormAndCardTpl; }

    public String getNationalSelectionId() { return candidateNationalSelectionId; }

    public String getEducationLvlId() { return candidateEducationLvlId; }

    public String getCountryPreviousEduOrganizationId() { return candidatePreviousEduOrganizationId; }

    public String getSourceOfSearchCode() { return candidateSourceOfSearchCode; }

    public String getPreviousEduOrganizationId() { return candidatePreviousEduOrganizationId; }

    public String getAgreeToContract() { return candidateAgreeToContract; }

    public String getCandidateStateCode() { return candidateStateCode; }

    public String getEducationForm() { return candidateEducationForm; }

    public String getLanguagesWithDegrees() { return candidateLanguagesWithDegrees; }

    public String getLanguagesWithDegreesDegree() { return candidateLanguagesWithDegreesDegree; }

    public String getLanguagesWithDegreesLanguage() { return candidateLanguagesWithDegreesLanguage; }

    public String getSelectedOrgId() { return candidateSelectedOrgId; }

    public String getDocumentOfPassportId() { return candidateDocumentOfPassportId; }

    public String getDocumentCopyOfTheEduCertificate() { return  candidateDocumentCopyOfTheEduCertificate; }

    public String getInputId() { return input;}
    public String getOnCheckId() { return onCheckId; }
    public String getInroducedId() { return inroducedId; }
    public String getInvitedForTestsId() { return invitedForTestsId; }
    public String getTestsDoneId() { return  testsDoneId; }
    public String getSelectedForQuotaId() { return selectedForQuotaId;}
    public String getSecondDistributionLevelId() { return secondDistributionLevelId; }
    public String getDossierFormedId() { return dossierFormedId; }
    public String getDistributedQuotaId() { return distributedQuotaId; }
    public String getDirectedQuotaId() { return directedQuotaId; }
    public String getEnrolledQuotaId() { return enrolledQuotaId; }
    public String getСompletedQtId() { return completedQtId; }
    public String getEnrolledSubfacultyQId() { return enrolledSubfacultyQId; }
    public String getDistributedSubfacultyQId() { return distributedSubfacultyQId; }
    public String getExpelledQuotaId() { return expelledQuotaId; }
    public String getNotPassedForQuotaId() { return notPassedForQuotaId; }
    public String getFillingApplicationId() { return fillingApplicationId; }
    public String getContractAcceptedId() { return contractAcceptedId; }
    public String getOnApprovalId() { return onApprovalId; }
    public String getDistributedId() { return distributedId; }
    public String getRejectedId() { return rejectedId; }
    public String getEnrolledTfId() { return enrolledTfId; }
    public String getCompletedTfId() { return completedTfId; }
    public String getEnrolledId() { return enrolledId; }
    public String getGraduatedId() { return graduatedId; }
    public String getExpelledId() { return expelledId; }
    public String getRefusedId() { return refusedId; }

    /**
     * Берёт email квотного кандидата, который указал промо код, и активирует его
     */
    public void activateQuotaCandidateWithPromoCode(String email, String standUrl, String promo) throws SQLException, IOException
    {
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(email);

        System.out.println("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),  testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(), queryActivationCode, testDatabaseConnection.getColumnActivation());

        System.out.println("Формируем активационную ссылку и переходим по ней");
        String activationLink = createActivationLinkForQuotaWithPromoCode(standUrl,activationCode, promo);
        System.out.println("Активационная ссылка: " + activationLink);
        open(activationLink);
    }


    /**
     * Создаёт кандидатов во всех возможных квотных состояниях
     * @param standUrl
     * @throws IOException
     * @throws SQLException
     */
    public void createCandidateInAllQuotaStatues(String standUrl, String promoCode) throws IOException, SQLException
    {
        String candidateInput = getInputId();
        String candidateOnCheck = getOnCheckId();
        String candidateInroduced =getInroducedId();
        String candidateInvitedForTests = getInvitedForTestsId();
        String candidateTestsDone = getTestsDoneId();
        String candidateSelectedForQuota = getSelectedForQuotaId();
        String candidateSecondDistributionLevel = getSecondDistributionLevelId();
        String candidateDossierFormed = getDossierFormedId();
        String candidateDistributedQuota = getDistributedQuotaId();
        String candidateDirectedQuota = getDirectedQuotaId();
        String candidateEnrolledQuota = getEnrolledQuotaId();
        String candidateСompletedQt = getСompletedQtId();
        String candidateEnrolledSubfacultyQ = getEnrolledSubfacultyQId();
        String candidateDistributedSubfacultyQ = getDistributedSubfacultyQId();
        String candidateExpelledQuota = getExpelledQuotaId();
        String candidateNotPassedForQuota = getNotPassedForQuotaId();

        /**
         * Зарегистрируем и добавим кандидатов во всех возможных квотных состояниях в системе
         */
        registrationAndActivateTestCandidate(standUrl, candidateInput, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateOnCheck, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateInroduced, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateInvitedForTests, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateTestsDone, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateSelectedForQuota, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateSecondDistributionLevel, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateDossierFormed, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateDistributedQuota, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateDirectedQuota, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolledQuota, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateСompletedQt, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolledSubfacultyQ, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateDistributedSubfacultyQ, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateExpelledQuota, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateNotPassedForQuota, promoCode);

    }

    /**
     * Создаёт кандидатов во всех возможных контрактных состояниях
     * @param standUrl
     * @throws IOException
     * @throws SQLException
     */
    public void createCandidateInAllContractStatues(String standUrl, String promoCode) throws IOException, SQLException
    {
        String candidateFillingApplication = getFillingApplicationId();
        String candidateContractAccepted = getContractAcceptedId();
        String candidateOnApproval = getOnApprovalId();
        String candidateDistributed = getDistributedId();
        String candidateRejected = getRejectedId();
        String candidateEnrolledTf = getEnrolledTfId();
        String candidateCompletedTf = getCompletedTfId();
        String candidateEnrolled = getEnrolledId();
        String candidateGraduated = getGraduatedId();
        String candidateExpelled = getExpelledId();
        String candidateRefused = getRefusedId();

        /**
         * Зарегистрируем и добавим кандидатов во всех возможных контрактных состояниях в системе
         */
        registrationAndActivateTestCandidate(standUrl, candidateFillingApplication, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateContractAccepted, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateOnApproval, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateDistributed, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateRejected, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolledTf, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateCompletedTf, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolled, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateGraduated, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateExpelled, promoCode);
        registrationAndActivateTestCandidate(standUrl, candidateRefused, promoCode);
    }

    /**
     * Регистрирет и активирует тестового кандидата и меняет ему состояние. Записывет его регномер в файл
     */
    private void registrationAndActivateTestCandidate(String standUrl, String userId, String promoCode) throws IOException, SQLException
    {
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestCandidatesData candidate = new TestCandidatesData(userId);
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();

        String randomEmail = testRandomUserData.createRandomEmail();
        boolean contract = false;

        String countryId = testDatabaseConnection.selectCountryId(candidate.getCandidateCountry());

        System.out.println();
        System.out.println("Зарегистрируем и активируем кадидата");
        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidate.getCandidateLastName(), candidate.getCandidateFirstName(), candidate.getCandidateSexEn(),
                countryId, randomEmail, candidate.getCandidatePassword(), promoCode);

        activateQuotaCandidateWithPromoCode(randomEmail, standUrl, candidate.getCandidatePromo());

        String idCandidate = testDatabaseConnection.selectCandidateId(randomEmail);
        System.out.println("ID канидадта в состоянии " + candidate.getCandidateStateName() + ": " + idCandidate);

        System.out.println("Записываем id кандидата в файл");
        setCandidateId(userId, idCandidate);

        fillingApplicationCandidate(standUrl, randomEmail, userId);

        System.out.println("Меняем состояние кандидату...");
        testDatabaseConnection.changeCandidateState(candidate.getCandidateState(), idCandidate);

        System.out.println("Получаем регномер кандидата");
        String regNumber = testDatabaseConnection.selectCandidateRegNumber(idCandidate);

        System.out.println("Записываем регномер в файл");
        setCandidateRegNumber(userId, regNumber);

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
        PageRegistration pageRegistration = new PageRegistration();

        boolean activation;
        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(randomEmail);

        System.out.println("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),  testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(), queryActivationCode, testDatabaseConnection.getColumnActivation());

        System.out.println("Формируем активационную ссылку и переходим по ней");
        String activationLink = createActivationLinkForContract(standUrl,activationCode);
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


    /**
     * Заполняет заявку кандидата
     */
    public boolean fillingApplicationCandidate (String standUrl, String randomEmail, String userId) throws IOException, SQLException
    {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        TestCandidatesData candidate = new TestCandidatesData(userId);

        boolean success;
        String queryCandidateId = testDatabaseConnection.requestSelectCandidateId(randomEmail);
        String querySourceOfSearchId = testDatabaseConnection.requestSelectCatalogElementId(candidate.getSourceOfSearchCode());

        System.out.println("Формируем адрес для POST запроса на логин");
        String urlForRequestLogin = pageEditCandidate.createUrlRequestForLogin(standUrl);

        System.out.println("Отправлем POST запрос с логином");
        testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForLogin(urlForRequestLogin, randomEmail, candidate.getCandidatePassword()));

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
        String urlForRequestFillCandidatePersonalData = pageEditCandidate.createUrlRequestForEditPersonalData(standUrl, candidateId,  candidate.getCandidateFormAndCardTpl(),
                candidate.getNationalSelectionId());

        System.out.println("Отправляем POST запрос с персональными данными кандидата");
        testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidatePersonalData(urlForRequestFillCandidatePersonalData,  candidate.getCandidateLastName(),
                candidate.getCandidateFirstName(),  candidate.getPlaceOfBirth(),  candidate.getDateOfBirth(),  candidate.getCandidateSexEn(), randomEmail,  candidate.getLvlId(),
                candidate.getPreviousEduOrganization(),  candidate.getCountryOfFinishedEducationOrganisationId(), sourceOfSearchId));

        System.out.println("Формируем адрес для POST зароса на отправку заявки кандидата");
        String urlForRequestFillCandidateRequest = pageEditCandidate.createUrlRequestForEditRequest(standUrl, candidateId,  candidate.getCandidateFormAndCardTpl(),
                candidate.getNationalSelectionId());

        System.out.println("Отправлем POST запрос с данными о заявке кандидата");
        testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestFillCandidateRequest(urlForRequestFillCandidateRequest,  candidate.getAgreeToContract(),
                candidate.getCandidateStateCode(),  candidate.getEduDirId(),  candidate.getEducationForm(),  candidate.getLanguagesWithDegrees(),
                candidate.getLanguagesWithDegreesDegree(),  candidate.getLanguagesWithDegreesLanguage(),  candidate.getLvlId(),  candidate.getSelectedOrgId()));

        System.out.println("Формируем адрес для POST зароса на отправку копии пасспорта");
        String urlForRequestForUploadCopyPassport = pageEditCandidate.createUrlRequestForUploadFile(standUrl,candidateId,  candidate.getDocumentOfPassportId());

        System.out.println("Отправляем POST запрос с копией пасспорта");
        testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyPassport));


        System.out.println("Формируем адрес для POST зароса на отправку копии документа об образовании");
        String urlForRequestForUploadCopyOfTheEduCertificate = pageEditCandidate.createUrlRequestForUploadFile(standUrl, candidateId,  candidate.getDocumentCopyOfTheEduCertificate());

        System.out.println("Отправляем POST запрос с копией документа об образовании");
        testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForUploadFile(urlForRequestForUploadCopyOfTheEduCertificate));

        System.out.println("Формируем адрес для POST запроса на отправку фото кандидата на сервер");
        String urlForRequestForUploadPhoto = pageEditCandidate.createUrlRequestForUploadPhoto(standUrl, candidateId);

        System.out.println("Отправляем POST запрос с фото и сохраняем временную переменную из ответа для сохранения фото");
        String temporaryImage = testRequestsForHttp.postRequestForUploadPhoto(urlForRequestForUploadPhoto);

        System.out.println("Формируем POST запрос для сохранения фото кандитата");
        String urlForRequestForSavePhoto = pageEditCandidate.createUrlRequestForSavePhoto(standUrl, candidateId);

        System.out.println("Сохраняем фото кандитата");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForSavePhoto(urlForRequestForSavePhoto, temporaryImage));

        return success;
    }

    /**
     * Создаёт тестового кандидата для оплаты сервисного сбора
     */
    public String creationTestCandidateForPayFee (String standUrl, String userId) throws IOException, SQLException
    {
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestCandidatesData candidatesData = new TestCandidatesData(userId);

        String randomEmail = String.valueOf(testRandomUserData.createRandomEmail());
        boolean success; // проверка успешности POST запроса

        System.out.println("Формируем адрес для POST запроса на регистрацию");
        String urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationContract(standUrl);

        System.out.println("Заполняем обязательные поля в POST запросе и отправляем данные на регистрацию");
        success = testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForRegistrationWithPartialFilling(urlForRequestRegistration, candidatesData.getCandidateLastName(),
                candidatesData.getCandidateFirstName(), candidatesData.getCandidateSexEn(), candidatesData.getCountryId(), randomEmail, candidatesData.getCandidatePassword(), null));
        if (!success)
            randomEmail = null;

        success = activationCandidate(standUrl, randomEmail);
        if (!success)
            randomEmail = null;

        success = fillingApplicationCandidate(standUrl, randomEmail, userId);

        if (!success)
            randomEmail = null;

        return randomEmail;
    }

    /**
     * Выводит массив квотных состояний
     */
    public ArrayList quotaStates () throws IOException
    {

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

        ArrayList<String> quotaStatesList = new ArrayList<String>();

        quotaStatesList.add(inputCandidate.getCandidateStateName());
        quotaStatesList.add(onCheckCandidate.getCandidateStateName());
        quotaStatesList.add(inroducedCandidate.getCandidateStateName());
        quotaStatesList.add(invitedForTestsCandidate.getCandidateStateName());
        quotaStatesList.add(testsDoneCandidate.getCandidateStateName());
        quotaStatesList.add(selectedForQuotaCandidate.getCandidateStateName());
        quotaStatesList.add(secondDistributionLevelCandidate.getCandidateStateName());
        quotaStatesList.add(dossierFormedCandidate.getCandidateStateName());
        quotaStatesList.add(distributedQuotaCandidate.getCandidateStateName());
        quotaStatesList.add(directedQuotaCandidate.getCandidateStateName());
        quotaStatesList.add(enrolledQuotaCandidate.getCandidateStateName());
        quotaStatesList.add(completedQtCandidate.getCandidateStateName());
        quotaStatesList.add(enrolledSubfacultyQCandidate.getCandidateStateName());
        quotaStatesList.add(distributedSubfacultyQCandidate.getCandidateStateName());
        quotaStatesList.add(expelledQuotaCandidate.getCandidateStateName());
        quotaStatesList.add(notPassedForQuotaCandidate.getCandidateStateName());

        return quotaStatesList;
    }

    public int checkAllQuotaCandidateCanOpenCard(int logErrors) throws IOException
    {
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

        PageCandidateCard pageCandidateCard = new PageCandidateCard();

        logErrors = pageCandidateCard.checkQuotaCandidateCard(inputCandidate.getCandidateRegNumber(), inputCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkContactInfo(logErrors);

        logErrors = pageCandidateCard.checkQuotaCandidateCard(onCheckCandidate.getCandidateRegNumber(), onCheckCandidate.getCandidateStateName(), logErrors);

        logErrors = pageCandidateCard.checkQuotaCandidateCard(inroducedCandidate.getCandidateRegNumber(), inroducedCandidate.getCandidateStateName(), logErrors);
        System.out.println("Пробуем редактировать фамилию имя латиницей");
        logErrors = pageCandidateCard.checkBanEditingOfNameInLatin(logErrors, inroducedCandidate.getCandidateStateName());

        logErrors = pageCandidateCard.checkQuotaCandidateCard(invitedForTestsCandidate.getCandidateRegNumber(), invitedForTestsCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(testsDoneCandidate.getCandidateRegNumber(), testsDoneCandidate.getCandidateStateName(), logErrors);

        logErrors = pageCandidateCard.checkQuotaCandidateCard(selectedForQuotaCandidate.getCandidateRegNumber(), selectedForQuotaCandidate.getCandidateStateName(), logErrors);
        System.out.println("Пробуем редактировать фамилию имя латиницей");
        logErrors = pageCandidateCard.checkBanEditingOfNameInLatin(logErrors, selectedForQuotaCandidate.getCandidateStateName());

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

        return logErrors;
    }

    public int checkAllContractCandidateCanNotOpenCard(int logErrors) throws IOException
    {
        TestCandidatesData fillingApplicationCandidate = new TestCandidatesData(getFillingApplicationId());
        TestCandidatesData contractAcceptedCandidate = new TestCandidatesData(getContractAcceptedId());
        TestCandidatesData onApprovalCandidate = new TestCandidatesData(getOnApprovalId());
        TestCandidatesData distributedCandidate = new TestCandidatesData(getDistributedId());
        TestCandidatesData rejectedCandidate = new TestCandidatesData(getRejectedId());
        TestCandidatesData enrolledTfCandidate = new TestCandidatesData(getEnrolledTfId());
        TestCandidatesData completedTfCandidate = new TestCandidatesData(getCompletedTfId());
        TestCandidatesData enrolledCandidate = new TestCandidatesData(getEnrolledId());
        TestCandidatesData graduatedCandidate = new TestCandidatesData(getGraduatedId());
        TestCandidatesData expelledCandidate = new TestCandidatesData(getExpelledId());
        TestCandidatesData refusedCandidate = new TestCandidatesData(getRefusedId());

        PageCandidateList pageCandidateList = new PageCandidateList();

        logErrors = pageCandidateList.canNotOpenACard(logErrors, fillingApplicationCandidate.getCandidateRegNumber(), fillingApplicationCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, contractAcceptedCandidate.getCandidateRegNumber(), contractAcceptedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, onApprovalCandidate.getCandidateRegNumber(), onApprovalCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, distributedCandidate.getCandidateRegNumber(), distributedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, rejectedCandidate.getCandidateRegNumber(), rejectedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, enrolledTfCandidate.getCandidateRegNumber(), enrolledTfCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, completedTfCandidate.getCandidateRegNumber(), completedTfCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, enrolledCandidate.getCandidateRegNumber(), enrolledCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, graduatedCandidate.getCandidateRegNumber(), graduatedCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, expelledCandidate.getCandidateRegNumber(), expelledCandidate.getCandidateStateName());
        logErrors = pageCandidateList.canNotOpenACard(logErrors, refusedCandidate.getCandidateRegNumber(), refusedCandidate.getCandidateStateName());

        return logErrors;
    }

    /**
     * Отмечает в базе всех кандидатов для представителя
     */
    public void marksAllStatesCandidatesForDelegate(String userId) throws IOException
    {
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

        TestCandidatesData fillingApplicationCandidate = new TestCandidatesData(getFillingApplicationId());
        TestCandidatesData contractAcceptedCandidate = new TestCandidatesData(getContractAcceptedId());
        TestCandidatesData onApprovalCandidate = new TestCandidatesData(getOnApprovalId());
        TestCandidatesData distributedCandidate = new TestCandidatesData(getDistributedId());
        TestCandidatesData rejectedCandidate = new TestCandidatesData(getRejectedId());
        TestCandidatesData enrolledTfCandidate = new TestCandidatesData(getEnrolledTfId());
        TestCandidatesData completedTfCandidate = new TestCandidatesData(getCompletedTfId());
        TestCandidatesData enrolledCandidate = new TestCandidatesData(getEnrolledId());
        TestCandidatesData graduatedCandidate = new TestCandidatesData(getGraduatedId());
        TestCandidatesData expelledCandidate = new TestCandidatesData(getExpelledId());
        TestCandidatesData refusedCandidate = new TestCandidatesData(getRefusedId());

        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();

        testDatabaseConnection.insertCandidatesFromSelection("1", inputCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("2", onCheckCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("3", inroducedCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("4", invitedForTestsCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("5", testsDoneCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("6", selectedForQuotaCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("7", secondDistributionLevelCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("8", dossierFormedCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("9", distributedQuotaCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("10", directedQuotaCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("11", enrolledQuotaCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("12", completedQtCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("13", enrolledSubfacultyQCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("14", distributedSubfacultyQCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("15", expelledQuotaCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("16", notPassedForQuotaCandidate.getCandidateId(), userId);

        testDatabaseConnection.insertCandidatesFromSelection("17", fillingApplicationCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("18", contractAcceptedCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("19", onApprovalCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("20", distributedCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("21", rejectedCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("22", enrolledTfCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("23", completedTfCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("24", enrolledCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("25", graduatedCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("26", expelledCandidate.getCandidateId(), userId);
        testDatabaseConnection.insertCandidatesFromSelection("27", refusedCandidate.getCandidateId(), userId);

        System.out.println();
    }
}
