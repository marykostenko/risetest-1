import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Set;

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
        WebElement sexField = $(By.xpath("//div[@id='sex']//child::input[@type='text']"));
        sexField.sendKeys(userSex);
    }

    /**
     * заполняет Страну постоянного проживания
     */
    private void fillCountry(String userCountry)
    {
        WebElement countryField = $(By.xpath("//div[@id='countryId']//child::input[@type='text']"));
        countryField.sendKeys(userCountry);
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

    /**
     * создает рандомный email для регистрации
     */
    public String createRandomEmail()
    {
        String email = "mail"+RandomStringUtils.randomNumeric(10) + "@gmail.com";
        return email;
    }

    /**
     * частичное заполненние полей при  регистрации (только обязательные поля)
     */
    public void partialFillingRegistrationForm(String userLastName, String userFirstName, String userSex, String userCountry,String email, String userPassword)
    {
        fillLastName(userLastName);
        fillFirstName(userFirstName);
        fillSex(userSex);
        $(By.xpath("//ul[contains(@style,'top')]//child::a[@href='#']")).click();
        fillCountry(userCountry);
        $(By.xpath("//ul[contains(@style,'overflow-y')]//child::a[@href='#']")).click();
        fillEmail(email);
        fillPassword(userPassword);
        fillConfirmPassword(userPassword);
        $(By.id("licenseAgreement")).click();
        $(By.id("pdnAgreement")).click();
        $(By.xpath("//input[@value='ЗАРЕГИСТРИРОВАТЬСЯ']")).click();
        sleep(10000);
    }

    /**
     * полное заполнение полей при регистрации (все, кроме тех, что касаются агентов)
     */
    public void fullFillingRegistrationForm(String userLastName, String userFirstName, String userMiddleName,String userSex, String userCountry, String email, String userPassword)
    {
        fillLastName(userLastName);
        fillFirstName(userFirstName);
        fillMiddleName(userMiddleName);
        fillSex(userSex);
        $(By.xpath("//ul[contains(@style,'top')]//child::a[@href='#']")).click();
        fillCountry(userCountry);
        $(By.xpath("//ul[contains(@style,'overflow-y')]//child::a[@href='#']")).click();
        fillEmail(email);
        fillPassword(userPassword);
        fillConfirmPassword(userPassword);
        $(By.id("licenseAgreement")).click();
        $(By.id("pdnAgreement")).click();
        $(By.xpath("//input[@value='ЗАРЕГИСТРИРОВАТЬСЯ']")).click();
    }

    /**
     * проверка, что страница - та, на которую переход по ссылке регистрации
     */
    private boolean isFirstRegistrationPage()
    {
        return url().contains("/activate/");
    }

    /**
     * проверка, что урл страницы содержит /activate/. Если нет, то увеличивается число найденных ошибок и пишется в лог
     */
    public int checkUrlFirstRegistrationPage(int logErrors)
    {
        return checkAndLog(!isFirstRegistrationPage(), logErrors, "Ошибка: url неверный -" + url());
    }
}
