import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
}
