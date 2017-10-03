import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by user nkorobicina on 17.11.2016.
 */
public abstract class BaseTest {

    protected String initStandUrl(String standkey) throws IOException {
        Properties standUrl = new Properties();
        File propertyFile = new File("src/main/resources/standsUrl.properties");
        standUrl.load(new FileReader(propertyFile));
        return standUrl.getProperty(standkey);
    }

    private String standUrl;

    //идентификаторы пользовательских данных
    private static final String adminId = "admin";
    private static final String candidateId = "candidate";
    private static final String representativeId = "representative";
    private static final String curatorId = "curator";
    private static final String agreementId = "agreement";
    private static final String adminOrgId = "adminOrg";
    private static final String mailRecoveryId = "mailRecovery";
    private static final String userLoserId = "userLoser";
    private static final String userForEditLoginId = "userForEditLogin";
    private static final String userForEditPasswordId = "userForEditPassword";
    private static final String userForEditPersonalDataId = "userForEditPersonalData";
    private static final String userForEditPostId = "userForEditPost";
    private static final String changedUserForEditPersonalDataId = "changedUserForEditPersonalData";
    private static final String userForAddRoleId = "userForAddRole";
    private static final String userForRegistrationPartialQuotaId = "userForRegistrationPartialQuota";
    private static final String userForRegistrationFullQuotaId = "userForRegistrationFullQuota";
    private static final String userForRegistrationPartialContractId = "userForRegistrationPartialContract";
    private static final String userForRegistrationFullContractId = "userForRegistrationFullContract";
    private static final String userForPayFeeId = "userForPayFee";

    //тестовые кандидаты
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

    public String getAdminId() {
        return adminId;
    }

    public String getCandidateId() { return candidateId; }

    public String getRepresentativeId() {
        return representativeId;
    }

    public String getCuratorId() {
        return curatorId;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public String getAdminOrgId() {
        return adminOrgId;
    }

    public String getMailRecoveryId() {
        return mailRecoveryId;
    }

    public String getUserLoserId() {
        return userLoserId;
    }

    public String getUserForEditLoginId() {
        return userForEditLoginId;
    }

    public String getGetUserForEditPasswordId() {
        return userForEditPasswordId;
    }

    public String getUserForEditPersonalDataId() {
        return userForEditPersonalDataId;
    }

    public String getUserForEditPostId() {
        return userForEditPostId;
    }

    public String getChangedUserPersonalDataId() {
        return changedUserForEditPersonalDataId;
    }

    public String getUserForAddRoleId() {
        return userForAddRoleId;
    }

    public String getUserForRegistrationPartialQuotaId() {
        return userForRegistrationPartialQuotaId;
    }

    public String getUserForRegistrationFullQuotaId() {
        return userForRegistrationFullQuotaId;
    }

    public String getUserForRegistrationPartialContractId() {
        return userForRegistrationPartialContractId;
    }

    public String getUserForRegistrationFullContractId() {
        return userForRegistrationFullContractId;
    }

    public String getUserForPayFeeId() {
        return userForPayFeeId;
    }

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

    //Контрактники:

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

    protected String flagForStandUrl = "Ext";

    public String getStandUrl(String flag) throws IOException {
        standUrl = this.initStandUrl("stand" + flag + "Url");
        return standUrl;
    }

    protected int logErrors;

    @BeforeClass
    public void beforeSuite() throws IOException {
        open(getStandUrl(flagForStandUrl));
    }

    @BeforeMethod
    public void beforeTest() {
        logErrors = 0;
    }

    public void log(String textLog) {
        System.out.println(textLog);
    }

    protected void checkMistakes() {
        log("Проверяем число найденных ошибок");
        if (logErrors > 0) {
            Assert.fail("Найдено " + logErrors + " ошибок");
        }
        log("Ошибки не найдены");
    }

    @AfterMethod
    public void afterTest() {
        //проверка, что залогинены и разлогинивание
        PageTopBottom pageTopBottom = new PageTopBottom();
        if (pageTopBottom.isLoggedIn()) {
            log("Залогинены, разлогиниваемся");
            pageTopBottom.logout();
        }
    }
}