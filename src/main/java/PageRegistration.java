import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 24.05.2017.
 */
public class PageRegistration extends BasePage

{
    /**
     * возвращает true, если адрес страницы содержит /registration
     */
    private boolean isRegistrationQutaPage()
    {
        String pageUrl = url();
        return pageUrl.contains("/registration");
    }
    /**
     * возвращает true, если адрес страницы содержит /registration?contract=true
     */
    private boolean isRegistrationContractPage()
    {
        String pageUrl = url();
        return pageUrl.contains("/registration?contract=true");
    }

    /**
     * проверка, что адрес страницы не содержит /registration, и запись в лог в противном случае
     */
    public int assertRegistrationQuota(int logErrors)
    {
        return checkAndLog(!isRegistrationQutaPage(), logErrors, "Ошибка: url неверный ");
    }

    /**
     * проверка, что адрес страницы не содержит /registration?contract=true, и запись в лог в противном случае
     */
    public int assertRegistrationContract(int logErrors)
    {
        return checkAndLog(!isRegistrationContractPage(), logErrors, "Ошибка: url неверный ");
    }

    public void isRegistrationForm()
    {
        $(By.xpath("//form[@action='/registration']")).shouldBe(Condition.appear);
    }

    public void isRegistrationContractForm()
    {
        $(By.xpath("//form[@action='/registration?contract=true']")).shouldBe(Condition.appear);
    }

    /**
     * заполняет Фамилию
     */
    private void fillLastName(String userLastName)
    {
        WebElement lastNameField = $(By.id("lastName"));
        lastNameField.sendKeys(userLastName);
    }

    /**
     * заплдняет Имя
     */
    private void fillFirstName(String userFirstName)
    {
        WebElement firstNameField = $(By.id("firstName"));
        firstNameField.sendKeys(userFirstName);
    }

    /**
     * заполняет Отчество
     */
    private void fillMiddleName(String userMiddleName)
    {
        WebElement middleNameField = $(By.id("middleName"));
        middleNameField.sendKeys(userMiddleName);
    }

    /**
     * заполняет пол
     */
    private void fillSex(String userSex)
    {
        WebElement sexField = $(By.id("sex"));
        sexField.sendKeys(userSex);
        $(By.xpath("//li[@class='active']//child::a[@href='#']")).click();
    }

    /**
     * заполняет Страну постоянного проживания
     */
    private void fillCountry(String userCountry)
    {
        WebElement countryField = $(By.id("countryId"));
        countryField.sendKeys(userCountry);
        $(By.xpath("//ul[contains(@style,'overflow-y')]//child::a[@href='#']")).click();
    }

    /**
     * заполняет email
     */
    private void fillEmail(String userEmail)
    {
        WebElement emailField = $(By.id("registrationEmail"));
        emailField.sendKeys(userEmail);
    }

    /**
     * заполняет Пароль
     */
    private void fillPassword(String userPassword)
    {
        WebElement passwordField = $(By.id("registrationPassword"));
        passwordField.sendKeys(userPassword);
    }

    /**
     * заполняет Потверждение пароля
     */
    private void fillConfirmPassword(String userPassword)
    {
        WebElement passwordField = $(By.id("registrationPasswordConfirm"));
        passwordField.sendKeys(userPassword);
    }

    public void partialFillingRegistrationForm(String userLastName, String userFirstName, String userSex, String userCountry, String userEmail, String userPassword)
    {
        fillLastName(userLastName);
        fillFirstName(userFirstName);
        fillSex(userSex);
        fillCountry(userCountry);
        fillEmail(userEmail);
        fillPassword(userPassword);
        fillConfirmPassword(userPassword);

    }
}
