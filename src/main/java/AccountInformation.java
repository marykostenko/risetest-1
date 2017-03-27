import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


/**
 * Created by Maria on 24.03.2017.
 */
public class AccountInformation extends BasePage
{

    private ElementsCollection buttonEditPersonalInfo = $$(By.xpath("//a[contains(@href,'/editPersonalInfo')]"));
    private ElementsCollection buttonEditContactOnfo = $$(By.xpath("//a[contains(@href,'/editContactInfo')]"));
    private ElementsCollection buttonAddRole = $$(By.xpath("//a[@href='#add_modal_']"));


    //переход на страницу с пользователями
    public void goToUsers()
    {

        $(By.xpath("//div[@class='clearfix']//child::a[@class='dropdown-toggle padding-right-0']")).click();
        $(By.xpath("//a[@href='/users']")).click();

    }

    //заполняем email для поиска пользователя
    public void fillEmail(String userEmail)
    {
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(userEmail);
    }

    //нажимает кнопку "Найти"
    public void pushSearchButton()
    {
        $(By.id("submit_search")).click();
    }


    //переходит на страницу пользователя Изменение Данных
    public void goToUserForEdit()
    {
        $(By.xpath("//td//child::a[contains(@href,'/user/')]")).click();
    }


    //чистит поля личной информации перед редактированием
    private void clearFieldsPersonalInfo()
    {

        $(By.id("firstName")).clear();
        $(By.id("lastName")).clear();
        $(By.id("middleName")).clear();
        $(By.id("firstNameEng")).clear();
        $(By.id("lastNameEng")).clear();

    }

    //заполняет поле имя при редактировании
   private void fillFirstName (String changedFirstName)
    {
        WebElement firstNameField = $(By.id("firstName"));
        firstNameField.sendKeys(changedFirstName);
    }

     //заполняет поле фамилия при редактировании
    private void fillLastName (String changedLastName)
    {
        WebElement lastNameField = $(By.id("lastName"));
        lastNameField.sendKeys(changedLastName);
    }

    //заполняет поле отчество при редактировании
    private void fillMiddleName (String changedMaiddleName)
    {
        WebElement middleNameField = $(By.id("middleName"));
        middleNameField.sendKeys(changedMaiddleName);
    }

    //заполняет поле имя(eng) при редактировании
    private void fillFirstNameEng (String changedFirstNameEng)
    {
        WebElement firstNameEngField = $(By.id("firstNameEng"));
        firstNameEngField.sendKeys(changedFirstNameEng);
    }

    //заполняет поле фамилия(eng) при редактировании
    private void fillLastNameEng (String changeLastNameEng)
    {
        WebElement lastNameEngField = $(By.id("lastNameEng"));
        lastNameEngField.sendKeys(changeLastNameEng);
    }


    //проверяет пиктограмму редактирования личной информации, в случае если пиктограмма есть, меняет личные данные и сохраняет их
    public int editPersonalInfoFromAdmin (int logErrors)
    {

        if (!buttonEditPersonalInfo.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editPersonalInfo')]")).click();
            log("Перешли на форму редактирования личных данных");
            log("Очистили поля перед редактированием");
            clearFieldsPersonalInfo();
            log("Меняем имя");
            fillFirstName("ТЕСТИК");
            log("Меняем фамилию");
            fillLastName("тестик");
            log("Меняем отчество");
            fillMiddleName("Тестик");
            log("Меняем Имя(Eng)");
            fillFirstNameEng("TESTIK");
            log("Меняем Фамилию(Eng)");
            fillLastNameEng("testik");


        } else
        {

            logErrors++;
            log("Пиктограмы 'Редактировать' в блоке личной информации нет");

        }

        return logErrors;

    }


}
