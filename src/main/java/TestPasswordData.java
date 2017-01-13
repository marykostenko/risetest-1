import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user nkorobicina on 09.01.2017.
 */
public class TestPasswordData {

    private String shortPassword;
    private String weakPassword;
    private String middlePassword;
    private String strongPassword;
    private String veryStrongPassword;
    private String newRecoveryPassword;

    public TestPasswordData() throws IOException {
        shortPassword = this.initPasswordData("short");
        weakPassword = this.initPasswordData("weak");
        middlePassword = this.initPasswordData("middle");
        strongPassword = this.initPasswordData("strong");
        veryStrongPassword = this.initPasswordData("veryStrong");
        newRecoveryPassword = this.initPasswordData("newRecoveryPassword");

    }


    protected String initPasswordData(String fieldKey) throws IOException {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/passwordData.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }

    public String getShortPassword(){ return shortPassword;}

    public String getWeakPassword() {return weakPassword;}

    public String getMiddlePassword() { return middlePassword;}

    public String getStrongPassword() { return strongPassword; }

    public String getVeryStrongPassword() { return veryStrongPassword; }

    public String getNewRecoveryPassword(){ return newRecoveryPassword; }

}
