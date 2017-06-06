import org.bouncycastle.util.test.TestRandomData;

import java.io.*;
import java.util.Properties;

/**
 * Created by Maria on 01.06.2017.
 */
public class TestRandomUserData {

    private String randomEmail;

    public TestRandomUserData(String userId) throws IOException {
        randomEmail = this.initRandomUserData(userId + "RandomEmail");
    }


    protected String initRandomUserData(String fieldKey) throws IOException {
        Properties randomUserData = new Properties();
        File propertyFile = new File("src/main/resources/randomUserData.properties");
        randomUserData.load(new FileReader(propertyFile));
        return randomUserData.getProperty(fieldKey);
    }

    // методу передаётся название поля в properties и метод записывает данные в поле
    public void entryUserData(String fieldKey, String newData) throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/randomUserData.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        props.setProperty(fieldKey, newData);

        FileOutputStream out = new FileOutputStream("src/main/resources/randomUserData.properties");
        props.store(out, null);
        out.close();
    }

    public String getRandomEmail() {
        return randomEmail;
    }

    /**
     * возвращают названия полей для записи рандомных email'ов
     */
    public String getPartialQuotaRandomEmail(){ return "userForRegistrationPartialQuotaRandomEmail"; }
    public String getFullQuotaRandomEmail(){ return "userForRegistrationFullQuotaRandomEmail"; }
    public String getPartialContractRandomEmail(){ return   "userForRegistrationPartialContractRandomEmail"; }
    public String getFullContractRandomEmail(){ return   "userForRegistrationFullContractRandomEmail"; }
}
