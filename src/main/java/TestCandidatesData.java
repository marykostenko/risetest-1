import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static org.testng.Reporter.log;

public class TestCandidatesData
{
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateSexEn;
    private String candidateCountryId;
    private String candidatePassword;
    private String candidateState;

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

    public String getCandidateFirstName() { return candidateFirstName; }

    public String getCandidateLastName() { return candidateLastName; }

    public String getCandidateSexEn() { return candidateSexEn; }

    public String getCandidateCountryId() { return candidateCountryId; }

    public String getCandidatePassword() { return candidatePassword; }

    public String getCandidateState() { return candidateState; }

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


    public void createCandidateInAllStatues(String standUrl) throws IOException, SQLException
    {
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
        TestCandidatesData candidateFillingApplication = new TestCandidatesData(getFillingApplicationId());
        TestCandidatesData candidateContractAccepted = new TestCandidatesData(getContractAcceptedId());
        TestCandidatesData candidateOnApproval = new TestCandidatesData(getOnApprovalId());
        TestCandidatesData candidateDistributed = new TestCandidatesData(getDistributedId());
        TestCandidatesData candidateRejected = new TestCandidatesData(getRejectedId());
        TestCandidatesData candidateEnrolledTf = new TestCandidatesData(getEnrolledTfId());
        TestCandidatesData candidateCompletedTf = new TestCandidatesData(getCompletedTfId());
        TestCandidatesData candidateEnrolled = new TestCandidatesData(getEnrolledId());
        TestCandidatesData candidateGraduated = new TestCandidatesData(getGraduatedId());
        TestCandidatesData candidateExpelled = new TestCandidatesData(getExpelledId());
        TestCandidatesData candidateRefused = new TestCandidatesData(getRefusedId());

        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();


        System.out.println("Сгенирируем 26 рандомных email'ов для последующей регистрации кандидатов");
        String onCheckCandidateEmail = testRandomUserData.createRandomEmail();
        String inroducedCandidateEmail = testRandomUserData.createRandomEmail();
        String invitedForTestsCandidateEmail = testRandomUserData.createRandomEmail();
        String testsDoneCandidateEmail = testRandomUserData.createRandomEmail();
        String selectedForQuotaCandidateEmail = testRandomUserData.createRandomEmail();
        String secondDistributionLevelCandidateEmail = testRandomUserData.createRandomEmail();
        String dossierFormedCandidateEmail = testRandomUserData.createRandomEmail();
        String distributedQuotaCandidateEmail = testRandomUserData.createRandomEmail();
        String directedQuotaCandidateEmail = testRandomUserData.createRandomEmail();
        String enrolledQuotaCandidateEmail = testRandomUserData.createRandomEmail();
        String completedQtCandidateEmail = testRandomUserData.createRandomEmail();
        String enrolledSubfacultyQCandidateEmail = testRandomUserData.createRandomEmail();
        String distributedSubfacultyQCandidateEmail = testRandomUserData.createRandomEmail();
        String expelledQuotaCandidateEmail = testRandomUserData.createRandomEmail();
        String notPassedForQuotaCandidateEmail = testRandomUserData.createRandomEmail();
        String fillingApplicationCandidateEmail = testRandomUserData.createRandomEmail();
        String contractAcceptedCandidateEmail = testRandomUserData.createRandomEmail();
        String onApprovalCandidateEmail = testRandomUserData.createRandomEmail();
        String distributedCandidateEmail = testRandomUserData.createRandomEmail();
        String rejectedCandidateEmail = testRandomUserData.createRandomEmail();
        String enrolledTfCandidateEmail = testRandomUserData.createRandomEmail();
        String completedTfCandidateEmail = testRandomUserData.createRandomEmail();
        String enrolledCandidateEmail = testRandomUserData.createRandomEmail();
        String graduatedCandidateEmail = testRandomUserData.createRandomEmail();
        String expelledCandidateEmail = testRandomUserData.createRandomEmail();
        String refusedCandidateEmail = testRandomUserData.createRandomEmail();

        boolean contract = false;

        System.out.println("Зарегистрируем 26 кандидатов с помощью отправки их данных пост запросами");
        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateOnCheck.getCandidateLastName(), candidateOnCheck.getCandidateFirstName(), candidateOnCheck.getCandidateSexEn(),
                candidateOnCheck.getCandidateCountryId(), onCheckCandidateEmail, candidateOnCheck.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateInroduced.getCandidateLastName(), candidateInroduced.getCandidateFirstName(),
                candidateInroduced.getCandidateSexEn(), candidateInroduced.getCandidateCountryId(), inroducedCandidateEmail, candidateInroduced.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateInvitedForTests.getCandidateLastName(), candidateInvitedForTests.getCandidateFirstName(),
                candidateInvitedForTests.getCandidateSexEn(), candidateInvitedForTests.getCandidateCountryId(), invitedForTestsCandidateEmail, candidateInvitedForTests.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateTestsDone.getCandidateLastName(), candidateTestsDone.getCandidateFirstName(),
                candidateTestsDone.getCandidateSexEn(), candidateTestsDone.getCandidateCountryId(), testsDoneCandidateEmail, candidateTestsDone.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateSelectedForQuota.getCandidateLastName(), candidateSelectedForQuota.getCandidateFirstName(),
                candidateSelectedForQuota.getCandidateSexEn(), candidateSelectedForQuota.getCandidateCountryId(), selectedForQuotaCandidateEmail, candidateSelectedForQuota.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateSecondDistributionLevel.getCandidateLastName(), candidateSecondDistributionLevel.getCandidateFirstName(),
                candidateSecondDistributionLevel.getCandidateSexEn(), candidateSecondDistributionLevel.getCandidateCountryId(), secondDistributionLevelCandidateEmail,
                candidateSecondDistributionLevel.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateDossierFormed.getCandidateLastName(), candidateDossierFormed.getCandidateFirstName(),
                candidateDossierFormed.getCandidateSexEn(), candidateDossierFormed.getCandidateCountryId(), dossierFormedCandidateEmail, candidateDossierFormed.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateDistributedQuota.getCandidateLastName(), candidateDistributedQuota.getCandidateFirstName(),
                candidateDistributedQuota.getCandidateSexEn(), candidateDistributedQuota.getCandidateCountryId(), distributedQuotaCandidateEmail, candidateDistributedQuota.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateDirectedQuota.getCandidateLastName(), candidateDirectedQuota.getCandidateFirstName(),
                candidateDirectedQuota.getCandidateSexEn(), candidateDirectedQuota.getCandidateCountryId(), directedQuotaCandidateEmail, candidateDirectedQuota.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateEnrolledQuota.getCandidateLastName(), candidateEnrolledQuota.getCandidateFirstName(),
                candidateEnrolledQuota.getCandidateSexEn(), candidateEnrolledQuota.getCandidateCountryId(), enrolledQuotaCandidateEmail, candidateEnrolledQuota.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateСompletedQt.getCandidateLastName(), candidateСompletedQt.getCandidateFirstName(),
                candidateСompletedQt.getCandidateSexEn(), candidateСompletedQt.getCandidateCountryId(), completedQtCandidateEmail, candidateСompletedQt.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateEnrolledSubfacultyQ.getCandidateLastName(), candidateEnrolledSubfacultyQ.getCandidateFirstName(),
                candidateEnrolledSubfacultyQ.getCandidateSexEn(), candidateEnrolledSubfacultyQ.getCandidateCountryId(), enrolledSubfacultyQCandidateEmail,
                candidateEnrolledSubfacultyQ.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateDistributedSubfacultyQ.getCandidateLastName(), candidateDistributedSubfacultyQ.getCandidateFirstName(),
                candidateDistributedSubfacultyQ.getCandidateSexEn(), candidateDistributedSubfacultyQ.getCandidateCountryId(), distributedSubfacultyQCandidateEmail,
                candidateDistributedSubfacultyQ.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateExpelledQuota.getCandidateLastName(), candidateExpelledQuota.getCandidateFirstName(),
                candidateExpelledQuota.getCandidateSexEn(), candidateExpelledQuota.getCandidateCountryId(), expelledQuotaCandidateEmail, candidateExpelledQuota.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateNotPassedForQuota.getCandidateLastName(), candidateNotPassedForQuota.getCandidateFirstName(),
                candidateNotPassedForQuota.getCandidateSexEn(), candidateNotPassedForQuota.getCandidateCountryId(), notPassedForQuotaCandidateEmail, candidateNotPassedForQuota.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateFillingApplication.getCandidateLastName(), candidateFillingApplication.getCandidateFirstName(),
                candidateFillingApplication.getCandidateSexEn(), candidateFillingApplication.getCandidateCountryId(), fillingApplicationCandidateEmail, candidateFillingApplication.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateContractAccepted.getCandidateLastName(), candidateContractAccepted.getCandidateFirstName(),
                candidateContractAccepted.getCandidateSexEn(), candidateContractAccepted.getCandidateCountryId(), contractAcceptedCandidateEmail, candidateContractAccepted.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateOnApproval.getCandidateLastName(), candidateOnApproval.getCandidateFirstName(),
                candidateOnApproval.getCandidateSexEn(), candidateOnApproval.getCandidateCountryId(), onApprovalCandidateEmail, candidateOnApproval.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateDistributed.getCandidateLastName(), candidateDistributed.getCandidateFirstName(),
                candidateDistributed.getCandidateSexEn(), candidateDistributed.getCandidateCountryId(), distributedCandidateEmail, candidateDistributed.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateRejected.getCandidateLastName(), candidateRejected.getCandidateFirstName(),
                candidateRejected.getCandidateSexEn(), candidateRejected.getCandidateCountryId(), rejectedCandidateEmail, candidateRejected.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateEnrolledTf.getCandidateLastName(), candidateEnrolledTf.getCandidateFirstName(),
                candidateEnrolledTf.getCandidateSexEn(), candidateEnrolledTf.getCandidateCountryId(), enrolledTfCandidateEmail, candidateEnrolledTf.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateCompletedTf.getCandidateLastName(), candidateCompletedTf.getCandidateFirstName(),
                candidateCompletedTf.getCandidateSexEn(), candidateCompletedTf.getCandidateCountryId(), completedTfCandidateEmail, candidateCompletedTf.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateEnrolled.getCandidateLastName(), candidateEnrolled.getCandidateFirstName(),
                candidateEnrolled.getCandidateSexEn(), candidateEnrolled.getCandidateCountryId(), enrolledCandidateEmail, candidateEnrolled.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateGraduated.getCandidateLastName(), candidateGraduated.getCandidateFirstName(),
                candidateGraduated.getCandidateSexEn(), candidateGraduated.getCandidateCountryId(), graduatedCandidateEmail, candidateGraduated.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateExpelled.getCandidateLastName(), candidateExpelled.getCandidateFirstName(),
                candidateExpelled.getCandidateSexEn(), candidateExpelled.getCandidateCountryId(), expelledCandidateEmail, candidateExpelled.getCandidatePassword());

        testRequestsForHttp.registrationCandidateByPostRequest(contract, standUrl, candidateRefused.getCandidateLastName(), candidateRefused.getCandidateFirstName(), candidateRefused.getCandidateSexEn(),
                candidateRefused.getCandidateCountryId(), refusedCandidateEmail, candidateRefused.getCandidatePassword());

        System.out.println("Вытащим id кандидатов из базы");
        String idOnCheckCandidate = testDatabaseConnection.selectCandidateId(onCheckCandidateEmail);
        System.out.println(idOnCheckCandidate);
        String idInroducedCandidate = testDatabaseConnection.selectCandidateId(inroducedCandidateEmail);
        System.out.println(idInroducedCandidate);
        String idInvitedForTestsCandidate = testDatabaseConnection.selectCandidateId(invitedForTestsCandidateEmail);
        System.out.println(idInvitedForTestsCandidate);
        String idTestsDoneCandidate = testDatabaseConnection.selectCandidateId(testsDoneCandidateEmail);
        System.out.println(idTestsDoneCandidate);
        String idSelectedForQuotaCandidate = testDatabaseConnection.selectCandidateId(selectedForQuotaCandidateEmail);
        System.out.println(idSelectedForQuotaCandidate);
        String idSecondDistributionLevelCandidate = testDatabaseConnection.selectCandidateId(secondDistributionLevelCandidateEmail);
        System.out.println(idSecondDistributionLevelCandidate);
        String idDossierFormedCandidate = testDatabaseConnection.selectCandidateId(dossierFormedCandidateEmail);
        System.out.println(idDossierFormedCandidate);
        String idDistributedQuotaCandidate = testDatabaseConnection.selectCandidateId(distributedQuotaCandidateEmail);
        System.out.println(idDistributedQuotaCandidate);
        String idDirectedQuotaCandidate = testDatabaseConnection.selectCandidateId(directedQuotaCandidateEmail);
        System.out.println(idDirectedQuotaCandidate);
        String idEnrolledQuotaCandidate = testDatabaseConnection.selectCandidateId(enrolledQuotaCandidateEmail);
        System.out.println(idEnrolledQuotaCandidate);
        String idСompletedQtCandidate = testDatabaseConnection.selectCandidateId(completedQtCandidateEmail);
        System.out.println(idСompletedQtCandidate);
        String idEnrolledSubfacultyCandidate = testDatabaseConnection.selectCandidateId(enrolledSubfacultyQCandidateEmail);
        System.out.println(idEnrolledSubfacultyCandidate);
        String idDistributedSubfacultyQCandidate = testDatabaseConnection.selectCandidateId(distributedSubfacultyQCandidateEmail);
        System.out.println(idDistributedSubfacultyQCandidate);
        String idExpelledQuotaCandidate = testDatabaseConnection.selectCandidateId(expelledQuotaCandidateEmail);
        System.out.println(idExpelledQuotaCandidate);
        String idNotPassedForQuotaCandidate = testDatabaseConnection.selectCandidateId(notPassedForQuotaCandidateEmail);
        System.out.println(idNotPassedForQuotaCandidate);
        String idFillingApplicationCandidate = testDatabaseConnection.selectCandidateId(fillingApplicationCandidateEmail);
        System.out.println(idFillingApplicationCandidate);
        String idContractAcceptedCandidate = testDatabaseConnection.selectCandidateId(contractAcceptedCandidateEmail);
        System.out.println(idContractAcceptedCandidate);
        String idOnApprovalCandidate = testDatabaseConnection.selectCandidateId(onApprovalCandidateEmail);
        System.out.println(idOnApprovalCandidate);
        String idDistributedCandidate = testDatabaseConnection.selectCandidateId(distributedCandidateEmail);
        System.out.println(idDistributedCandidate);
        String idRejectedCandidate = testDatabaseConnection.selectCandidateId(rejectedCandidateEmail);
        System.out.println(idRejectedCandidate);
        String idEnrolledTfCandidate = testDatabaseConnection.selectCandidateId(enrolledTfCandidateEmail);
        System.out.println(idEnrolledTfCandidate);
        String idCompletedTfCandidate = testDatabaseConnection.selectCandidateId(completedTfCandidateEmail);
        System.out.println(idCompletedTfCandidate);
        String idEnrolledCandidate = testDatabaseConnection.selectCandidateId(enrolledCandidateEmail);
        System.out.println(idEnrolledCandidate);
        String idGraduatedCandidate = testDatabaseConnection.selectCandidateId(graduatedCandidateEmail);
        System.out.println(idGraduatedCandidate);
        String idExpelledCandidate = testDatabaseConnection.selectCandidateId(expelledCandidateEmail);
        System.out.println(idExpelledCandidate);
        String idRefusedCandidate = testDatabaseConnection.selectCandidateId(refusedCandidateEmail);
        System.out.println(idRefusedCandidate);

        System.out.println("Меняем в базе состояния кандидатов");
        testDatabaseConnection.changeCandidateState(candidateOnCheck.getCandidateState(), idOnCheckCandidate);
        testDatabaseConnection.changeCandidateState(candidateInroduced.getCandidateState(), idInroducedCandidate);
        testDatabaseConnection.changeCandidateState(candidateInvitedForTests.getCandidateState(), idInvitedForTestsCandidate);
        testDatabaseConnection.changeCandidateState(candidateTestsDone.getCandidateState(), idTestsDoneCandidate);
        testDatabaseConnection.changeCandidateState(candidateSelectedForQuota.getCandidateState(), idSelectedForQuotaCandidate);
        testDatabaseConnection.changeCandidateState(candidateSecondDistributionLevel.getCandidateState(), idSecondDistributionLevelCandidate);
        testDatabaseConnection.changeCandidateState(candidateDossierFormed.getCandidateState(), idDossierFormedCandidate);
        testDatabaseConnection.changeCandidateState(candidateDistributedQuota.getCandidateState(), idDistributedQuotaCandidate);
        testDatabaseConnection.changeCandidateState(candidateDirectedQuota.getCandidateState(), idDirectedQuotaCandidate);
        testDatabaseConnection.changeCandidateState(candidateEnrolledQuota.getCandidateState(), idEnrolledQuotaCandidate);
        testDatabaseConnection.changeCandidateState(candidateСompletedQt.getCandidateState(), idСompletedQtCandidate);
        testDatabaseConnection.changeCandidateState(candidateEnrolledSubfacultyQ.getCandidateState(), idEnrolledSubfacultyCandidate);
        testDatabaseConnection.changeCandidateState(candidateDistributedSubfacultyQ.getCandidateState(), idDistributedSubfacultyQCandidate);
        testDatabaseConnection.changeCandidateState(candidateExpelledQuota.getCandidateState(), idExpelledQuotaCandidate);
        testDatabaseConnection.changeCandidateState(candidateNotPassedForQuota.getCandidateState(), idNotPassedForQuotaCandidate);
        testDatabaseConnection.changeCandidateState(candidateFillingApplication.getCandidateState(), idFillingApplicationCandidate);
        testDatabaseConnection.changeCandidateState(candidateContractAccepted.getCandidateState(), idContractAcceptedCandidate);
        testDatabaseConnection.changeCandidateState(candidateOnApproval.getCandidateState(), idOnApprovalCandidate);
        testDatabaseConnection.changeCandidateState(candidateDistributed.getCandidateState(), idDistributedCandidate);
        testDatabaseConnection.changeCandidateState(candidateRejected.getCandidateState(), idRejectedCandidate);
        testDatabaseConnection.changeCandidateState(candidateEnrolledTf.getCandidateState(), idEnrolledTfCandidate);
        testDatabaseConnection.changeCandidateState(candidateCompletedTf.getCandidateState(), idCompletedTfCandidate);
        testDatabaseConnection.changeCandidateState(candidateEnrolled.getCandidateState(), idEnrolledCandidate);
        testDatabaseConnection.changeCandidateState(candidateGraduated.getCandidateState(), idGraduatedCandidate);
        testDatabaseConnection.changeCandidateState(candidateExpelled.getCandidateState(), idExpelledCandidate);
        testDatabaseConnection.changeCandidateState(candidateRefused.getCandidateState(), idRefusedCandidate);

    }
}
