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
        candidateFirstName = this.initUserData(userId + "CandidateFirstName");
        candidateLastName = this.initUserData(userId + "CandidateLastName");
        candidateSexEn = this.initUserData(userId + "CandidateSexEn");
        candidateCountry = this.initUserData(userId + "CandidateCountry");
        candidatePassword = this.initUserData(userId + "CandidatePassword");
        candidateState = this.initUserData(userId + "CandidateState");
        candidatePromo = this.initUserData(userId + "CandidatePromo");
        candidateStateName = this.initUserData(userId + "CandidateStateName");
        candidateRegNumber = this.initRegNumber( userId + "CandidateRegNumber");

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

    public TestCandidatesData() throws IOException {

    }

    //методу передается название поля в properties и метод возвращает значение поля

    protected String initUserData(String fieldKey) throws IOException
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

    public String getCandidateFirstName() { return candidateFirstName; }

    public String getCandidateLastName() { return candidateLastName; }

    public String getCandidateSexEn() { return candidateSexEn; }

    public String getCandidateCountry() { return candidateCountry; }

    public String getCandidatePassword() { return candidatePassword; }

    public String getCandidateState() { return candidateState; }

    public String getCandidatePromo() { return candidatePromo; }

    public String getCandidateStateName() { return candidateStateName; }

    public String getCandidateRegNumber() { return candidateRegNumber; }

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
        TestUserData testUserData = new TestUserData();

        String queryActivationCode = testDatabaseConnection.requestSelectActivationCode(email);

        System.out.println("Заходим в базу, берём активационный код");
        String activationCode = testDatabaseConnection.selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),  testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(), queryActivationCode, testDatabaseConnection.getColumnActivation());

        System.out.println("Формируем активационную ссылку и переходим по ней");
        String activationLink = testUserData.createActivationLinkForQuotaWithPromoCode(standUrl,activationCode, promo);
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

        String email = testRandomUserData.createRandomEmail();
        boolean contract = false;

        String countryId = testDatabaseConnection.selectCountryId(candidate.getCandidateCountry());

        System.out.println();
        System.out.println("Зарегистрируем и активируем кадидата");
        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidate.getCandidateLastName(), candidate.getCandidateFirstName(), candidate.getCandidateSexEn(),
                countryId, email, candidate.getCandidatePassword(), promoCode);

        activateQuotaCandidateWithPromoCode(email, standUrl, candidate.getCandidatePromo());

        String idCandidate = testDatabaseConnection.selectCandidateId(email);
        System.out.println("ID канидадта в состоянии " + candidate.getCandidateStateName() + ": " + idCandidate);

        System.out.println("Меняем состояние кандидату...");
        testDatabaseConnection.changeCandidateState(candidate.getCandidateState(), idCandidate);

        System.out.println("Получаем регномер кандидата");
        String regNumber = testDatabaseConnection.selectCandidateRegNumber(idCandidate);

        System.out.println("Записываем регномер в файл");
        setCandidateRegNumber(userId, regNumber);

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


    /**
     * отмечает кандидатов во всех возможных состояниях
     */
    public void marksCandidatesInAllStates() throws IOException
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

        PageCandidateList pageCandidateList = new PageCandidateList();
        PageTopBottom pageTopBottom = new PageTopBottom();

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
        pageCandidateList.marksCandidate(completedTfCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(enrolledCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(graduatedCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(expelledCandidate.getCandidateRegNumber());
        pageCandidateList.marksCandidate(refusedCandidate.getCandidateRegNumber());

        pageTopBottom.logout();
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
//логировать, что происходит
        logErrors = pageCandidateCard.checkQuotaCandidateCard(inputCandidate.getCandidateRegNumber(), inputCandidate.getCandidateStateName(), logErrors);

        logErrors = pageCandidateCard.checkQuotaCandidateCard(onCheckCandidate.getCandidateRegNumber(), onCheckCandidate.getCandidateStateName(), logErrors);

        logErrors = pageCandidateCard.checkQuotaCandidateCard(inroducedCandidate.getCandidateRegNumber(), inroducedCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkBanEditingOfNameInLatin(logErrors, inroducedCandidate.getCandidateStateName());

        logErrors = pageCandidateCard.checkQuotaCandidateCard(invitedForTestsCandidate.getCandidateRegNumber(), invitedForTestsCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(testsDoneCandidate.getCandidateRegNumber(), testsDoneCandidate.getCandidateStateName(), logErrors);

        logErrors = pageCandidateCard.checkQuotaCandidateCard(selectedForQuotaCandidate.getCandidateRegNumber(), selectedForQuotaCandidate.getCandidateStateName(), logErrors);
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
}
