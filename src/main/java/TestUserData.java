import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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

    public String getUserPassword()
    {
        return userPassword;
    }

    public String getUserPost()
    {
        return userPost;
    }


    public String getUserNewLogin() { return userNewLogin; }

    public String getUserNewPassword() { return userNewPassword; }

    public String getUserPhone() {return userPhone; }

    public String getUserEmail () {return userEmail; }

    public String getUserWorkPhone () { return userWorkPhone; }

    public String getNewPost () { return newPost; }

    public String getId() { return id; }

    public String getIncorrectPassword() { return incorrectPassword; }

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
}
