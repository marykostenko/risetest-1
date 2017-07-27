import javax.mail.MessagingException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private String sex;
    private String country;
    private String placeOfBirth;
    private String dateOfBirth;
    private String educationLvl;
    private String previousEduOrganization;
    private String countryOfFinishedEducationOrganisation;
    private String lvlId;
    private String eduDirId;
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
        sex = this.initUserData(userId + "Sex");
        country = this.initUserData(userId + "Country");
        placeOfBirth = this.initUserData(userId + "PlaceOfBirth");
        dateOfBirth = this.initUserData(userId + "DateOfBirth");
        educationLvl = this.initUserData(userId + "EducationLvl");
        previousEduOrganization = this.initUserData(userId + "PreviousEduOrganization");
        countryOfFinishedEducationOrganisation = this.initUserData(userId + "CountryOfFinishedEducationOrganisation");
        lvlId = this.initUserData(userId + "LvlId");
        eduDirId = this.initUserData(userId + "EduDirId");
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

    public String getSex() { return sex; }

    public String getCountry() { return country; }

    public String getPlaceOfBirth() { return placeOfBirth; }

    public String getDateOfBirth() { return  dateOfBirth; }

    public String getEducationLvl() { return educationLvl; }

    public String getPreviousEduOrganization() { return previousEduOrganization; }

    public String getCountryOfFinishedEducationOrganisation() { return countryOfFinishedEducationOrganisation; }

    public String getLvlId() { return lvlId; }

    public String getEduDirId() { return eduDirId; }



    /**
     * смена пароля пользователя на тестовый
     */
    public void changePassForTest(String adminLogin, String adminPass, String userId, String userPass)
    {
        PageLogin pageLogin = new PageLogin();
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToLogin();
        pageLogin.isLoginForm();
        pageLogin.fillLoginForm(adminLogin, adminPass);
        pageLogin.pushLoginButton();
        MenuContent menuContent = new MenuContent();
        menuContent.goToAdminActions();
        PageActions pageActions = new PageActions();
        pageActions.changeUserPassword(userId, userPass);
        pageTopBottom.logout();
    }

    /**
     * регистрация тестового контрактного кандидата для теста оплаты сервисного сбора
     */
    public void registrationCandidateForPayFeeTest(String userEmail, String userLastName, String userFirstName, String userSex, String userCountry, String userPassword)
            throws IOException, InterruptedException, MessagingException
    {
        log("Нажимаем кнопку Регистрация");
        PageMain pageMain = new PageMain();
        pageMain.goToRegistrationFromBlockContractTraining();

        PageRegistration pageRegistration = new PageRegistration();
        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Сохраняем email для последующего входа под этим кандидатом");
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        testRandomUserData.entryUserData(userEmail, randomEmail);

        log("Заполняем обязательные поля");
        pageRegistration.partialFillingRegistrationForm(userLastName, userFirstName, userSex, userCountry, randomEmail, userPassword);

        TestMail testMail = new TestMail();
        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMailForRegistration();
        open(linkRegistration);

    }
}
