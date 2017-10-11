import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Reporter.log;

public class TestCandidatesData
{
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateSexEn;
    private String candidateCountryId;
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
        candidateCountryId = this.initUserData(userId + "CandidateCountryId");
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

    public TestCandidatesData()
    {

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

    public String getCandidateCountryId() { return candidateCountryId; }

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
    public void createCandidateInAllQuotaStatues(String standUrl) throws IOException, SQLException
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
        registrationAndActivateTestCandidate(standUrl, candidateInput);
        registrationAndActivateTestCandidate(standUrl, candidateOnCheck);
        registrationAndActivateTestCandidate(standUrl, candidateInroduced);
        registrationAndActivateTestCandidate(standUrl, candidateInvitedForTests);
        registrationAndActivateTestCandidate(standUrl, candidateTestsDone);
        registrationAndActivateTestCandidate(standUrl, candidateSelectedForQuota);
        registrationAndActivateTestCandidate(standUrl, candidateSecondDistributionLevel);
        registrationAndActivateTestCandidate(standUrl, candidateDossierFormed);
        registrationAndActivateTestCandidate(standUrl, candidateDistributedQuota);
        registrationAndActivateTestCandidate(standUrl, candidateDirectedQuota);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolledQuota);
        registrationAndActivateTestCandidate(standUrl, candidateСompletedQt);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolledSubfacultyQ);
        registrationAndActivateTestCandidate(standUrl, candidateDistributedSubfacultyQ);
        registrationAndActivateTestCandidate(standUrl, candidateExpelledQuota);
        registrationAndActivateTestCandidate(standUrl, candidateNotPassedForQuota);

    }

    /**
     * Создаёт кандидатов во всех возможных контрактных состояниях
     * @param standUrl
     * @throws IOException
     * @throws SQLException
     */
    public void createCandidateInAllContractStatues(String standUrl) throws IOException, SQLException
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
        registrationAndActivateTestCandidate(standUrl, candidateFillingApplication);
        registrationAndActivateTestCandidate(standUrl, candidateContractAccepted);
        registrationAndActivateTestCandidate(standUrl, candidateOnApproval);
        registrationAndActivateTestCandidate(standUrl, candidateDistributed);
        registrationAndActivateTestCandidate(standUrl, candidateRejected);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolledTf);
        registrationAndActivateTestCandidate(standUrl, candidateCompletedTf);
        registrationAndActivateTestCandidate(standUrl, candidateEnrolled);
        registrationAndActivateTestCandidate(standUrl, candidateGraduated);
        registrationAndActivateTestCandidate(standUrl, candidateExpelled);
        registrationAndActivateTestCandidate(standUrl, candidateRefused);
    }

    /**
     * Регистрирет и активирует тестового кандидата и меняет ему состояние. Записывет его регномер в файл
     */
    private void registrationAndActivateTestCandidate(String standUrl, String userId) throws IOException, SQLException
    {
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestCandidatesData candidate = new TestCandidatesData(userId);
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();

        String email = testRandomUserData.createRandomEmail();
        boolean contract = false;

        System.out.println();
        System.out.println("Зарегистрируем и активируем кадидата");
        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidate.getCandidateLastName(), candidate.getCandidateFirstName(), candidate.getCandidateSexEn(),
                candidate.getCandidateCountryId(), email, candidate.getCandidatePassword(), candidate.getCandidatePromo());
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
        TestCandidatesData candidateOnCheck = new TestCandidatesData(getOnCheckId());
        TestCandidatesData candidateInroduced = new TestCandidatesData(getInroducedId());
        TestCandidatesData candidateInvitedForTests = new TestCandidatesData(getInvitedForTestsId());
        TestCandidatesData candidateTestsDone = new TestCandidatesData(getTestsDoneId());
        TestCandidatesData candidateSelectedForQuota = new TestCandidatesData(getSelectedForQuotaId());
        TestCandidatesData candidateSecondDistributionLevel = new TestCandidatesData(getSecondDistributionLevelId());
        TestCandidatesData candidateDossierFormed = new TestCandidatesData(getDossierFormedId());
        TestCandidatesData candidateDistributedQuota = new TestCandidatesData(getDistributedQuotaId());
        TestCandidatesData candidateDirectedQuota = new TestCandidatesData(getDirectedQuotaId());
        TestCandidatesData candidateEnrolledQuota = new TestCandidatesData(getEnrolledQuotaId());
        TestCandidatesData candidateСompletedQt = new TestCandidatesData(getСompletedQtId());
        TestCandidatesData candidateEnrolledSubfacultyQ = new TestCandidatesData(getEnrolledSubfacultyQId());
        TestCandidatesData candidateDistributedSubfacultyQ = new TestCandidatesData(getDistributedSubfacultyQId());
        TestCandidatesData candidateExpelledQuota = new TestCandidatesData(getExpelledQuotaId());
        TestCandidatesData candidateNotPassedForQuota = new TestCandidatesData(getNotPassedForQuotaId());

        ArrayList<String> quotaStatesList = new ArrayList<String>();

        quotaStatesList.add(inputCandidate.getCandidateStateName());
        quotaStatesList.add(candidateOnCheck.getCandidateStateName());
        quotaStatesList.add(candidateInroduced.getCandidateStateName());
        quotaStatesList.add(candidateInvitedForTests.getCandidateStateName());
        quotaStatesList.add(candidateTestsDone.getCandidateStateName());
        quotaStatesList.add(candidateSelectedForQuota.getCandidateStateName());
        quotaStatesList.add(candidateSecondDistributionLevel.getCandidateStateName());
        quotaStatesList.add(candidateDossierFormed.getCandidateStateName());
        quotaStatesList.add(candidateDistributedQuota.getCandidateStateName());
        quotaStatesList.add(candidateDirectedQuota.getCandidateStateName());
        quotaStatesList.add(candidateEnrolledQuota.getCandidateStateName());
        quotaStatesList.add(candidateСompletedQt.getCandidateStateName());
        quotaStatesList.add(candidateEnrolledSubfacultyQ.getCandidateStateName());
        quotaStatesList.add(candidateDistributedSubfacultyQ.getCandidateStateName());
        quotaStatesList.add(candidateExpelledQuota.getCandidateStateName());
        quotaStatesList.add(candidateNotPassedForQuota.getCandidateStateName());

        return quotaStatesList;
    }
}
