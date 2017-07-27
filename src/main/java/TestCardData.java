import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Maria on 07.07.2017.
 */
public class TestCardData
{
    private String numberCardForSuccessfulPay;
    private String numberCardForUnsuccessfulPay;
    private String exptDateMounth;
    private String exptDateYear;
    private String cvv;

    public TestCardData() throws IOException
    {
        numberCardForSuccessfulPay = this.initCardData("numberCardForSuccessfulPay");
        numberCardForUnsuccessfulPay = this.initCardData("numberCardForUnsuccessfulPay");
        exptDateMounth = this.initCardData("exptDateMounth");
        exptDateYear = this.initCardData("exptDateYear");
        cvv = this.initCardData("cvv");
    }


    //методу передается название поля в properties и метод возвращает значение поля
    protected String initCardData(String fieldKey) throws IOException
    {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/cardData.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }

    public String getNumberCardForSuccessfulPay() { return numberCardForSuccessfulPay; }
    public String getNumberCardForUnsuccessfulPay() { return numberCardForUnsuccessfulPay; }
    public String getExptDateMounth() { return exptDateMounth; }
    public String getExptDateYear() { return exptDateYear; }
    public String getCvv() { return cvv; }

}
