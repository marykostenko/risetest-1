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
public abstract class BaseTest
{

    protected String initStandUrl(String standkey) throws IOException
    {
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

    public String getAdminId() { return adminId; }
    public String getCandidateId()
    {
        return candidateId;
    }
    public String getRepresentativeId()
    {
        return representativeId;
    }
    public String getCuratorId()
    {
        return curatorId;
    }
    public String getAgreementId()
    {
        return agreementId;
    }
    public String getAdminOrgId()
    {
        return adminOrgId;
    }
    public String getMailRecoveryId()
    {
        return mailRecoveryId;
    }
    public String getUserLoserId ()
    {
        return userLoserId;
    }
    public String getUserForEditLoginId () { return userForEditLoginId; }
    public String getGetUserForEditPasswordId () { return userForEditPasswordId; }
    public String getUserForEditPersonalDataId () { return userForEditPersonalDataId; }
    public String getUserForEditPostId () { return userForEditPostId; }
    public String getChangedUserPersonalDataId() { return changedUserForEditPersonalDataId;}
    public String getUserForAddRoleId() { return userForAddRoleId; }
    public String getUserForRegistrationPartialQuotaId() { return userForRegistrationPartialQuotaId; }
    public String getUserForRegistrationFullQuotaId() { return userForRegistrationFullQuotaId; }
    public String getUserForRegistrationPartialContractId() { return userForRegistrationPartialContractId; }
    public String getUserForRegistrationFullContractId() { return userForRegistrationFullContractId; }
    public String getUserForPayFeeId() { return userForPayFeeId; }


    public String getStandUrl(String flag) throws IOException
    {
        standUrl = this.initStandUrl("stand" + flag + "Url");
        return standUrl;
    }

    protected int logErrors;

    @BeforeClass
    public void beforeSuite() throws IOException
    {
        open(getStandUrl("Ext"));
    }

    @BeforeMethod
    public void beforeTest()
    {
        logErrors = 0;
    }

    public void log(String textLog)
    {
        System.out.println(textLog);
    }

    protected void checkMistakes()
    {
        log("Проверяем число найденных ошибок");
        if(logErrors>0)
        {
             Assert.fail("Найдено " + logErrors + " ошибок");
        }
        log("Ошибки не найдены");
    }

    @AfterMethod
    public void afterTest()
    {
        //проверка, что залогинены и разлогинивание
        PageTopBottom pageTopBottom = new PageTopBottom();
        if(pageTopBottom.isLoggedIn())
        {
            log("Залогинены, разлогиниваемся");
            pageTopBottom.logout();
        }
    }

    @AfterClass
    public void afterSuite()
    {
        log("Закрываем браузер");
        close();
    }

}
