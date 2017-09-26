import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.util.test.TestRandomData;
import org.omg.CORBA.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 01.06.2017.
 */
public class TestRandomUserData extends BasePage {

    private String randomEmail;


    public TestRandomUserData(String userId) throws IOException {
        randomEmail = this.initRandomUserData(userId + "RandomEmail");
    }

    public TestRandomUserData() {

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
    public String getUserForPayFeeRandomEmail() { return "userForPayFeeRandomEmail"; }

    public String getSourceOfSearchId()
    {
        String sourceOfSearchId = null;
        return sourceOfSearchId;
    }

    /**
     * создает рандомный email
     */
    public String createRandomEmail()
    {
        String email = "mail"+ RandomStringUtils.randomNumeric(10) + "@gmail.com";
        return email;
    }
    /**
     * Проверяет, не пустой ли сгенерирванный email, если пустой - позвращает ошибку
     * @param randomEmail
     * @return
     */
    public boolean randomEmailNotNull(String randomEmail)
    {

        if (randomEmail == null)
        {
            log("ОШИБКА! Тестовый кандидат не был создан, тест не моджет быть продолжен!");
            return false;
        }
        else return true;
    }

    /**
     * заполняет поле фамилия
     */
    private void fillLastName(String lastName)
    {
        WebElement lastNameField = $(By.id("lastName"));
        lastNameField.sendKeys(lastName);
    }

    /**
     * заполняет поле имя
     */
    private void fillFirstName(String firstName)
    {
        WebElement firstNameField = $(By.id("firstName"));
        firstNameField.sendKeys(firstName);
    }

    /**
     * заполняет поле email
     */
    private void fillEmail(String email)
    {
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(email);
    }

    /**
     * создаёт нового ранодомного пользователя, возвращает его userId
     */
    public String createNewRandomUserUser(String adminLogin, String adminPass, String userLastName, String userFirstName, String userEmail)
    {
        PageLogin pageLogin = new PageLogin();
        PageTopBottom pageTopBottom = new PageTopBottom();

        pageTopBottom.goToLogin();
        pageLogin.isLoginForm();
        pageLogin.fillLoginForm(adminLogin, adminPass);
        pageLogin.pushLoginButton();
        pageTopBottom.openAdminMenu();
        pageTopBottom.goToUsersList();

        $(By.xpath("//a[@href='/users/registrationByAdmin']")).click();

        fillLastName(userLastName);
        fillFirstName(userFirstName);
        fillEmail(userEmail);

        $(By.xpath("//span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='male']")).click();

        $(By.xpath("//button[contains(@class,'btn-primary')]")).click();

        String pageUrl = url();
        String userId = pageUrl.substring(pageUrl.lastIndexOf("/"));
        userId = userId.replace("/","");
        System.out.println(userId);

        pageTopBottom.logout();

        return userId;
    }

}
