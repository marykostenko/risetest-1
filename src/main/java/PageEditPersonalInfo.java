import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 20.04.2017.
 */
public class PageEditPersonalInfo extends BasePage
{

    /**
     * чистит поля личной информации перед редактированием
     */
    public void clearFieldsPersonalInfo()
    {
        $(By.id("firstName")).clear();
        $(By.id("lastName")).clear();
        $(By.id("middleName")).clear();
        $(By.id("firstNameEng")).clear();
        $(By.id("lastNameEng")).clear();
    }

    /**
     *  заполняет поле имя при редактировании
     */
    private void fillFirstName (String changedFirstName)
    {
        WebElement firstNameField = $(By.id("firstName"));
        firstNameField.sendKeys(changedFirstName);
    }

    /**
     * заполняет поле фамилия при редактировании
     */
    private void fillLastName (String changedLastName)
    {
        WebElement lastNameField = $(By.id("lastName"));
        lastNameField.sendKeys(changedLastName);
    }

    /**
     * заполняет поле отчество при редактировании
     */
    private void fillMiddleName (String changedMaiddleName)
    {
        WebElement middleNameField = $(By.id("middleName"));
        middleNameField.sendKeys(changedMaiddleName);
    }

    /**
     * заполняет поле имя(eng) при редактировании
     */
    private void fillFirstNameEng (String changedFirstNameEng)
    {
        WebElement firstNameEngField = $(By.id("firstNameEng"));
        firstNameEngField.sendKeys(changedFirstNameEng);
    }

    /**
     * заполняет поле фамилия(eng) при редактировании
     */
    private void fillLastNameEng (String changeLastNameEng)
    {
        WebElement lastNameEngField = $(By.id("lastNameEng"));
        lastNameEngField.sendKeys(changeLastNameEng);
    }

    /**
     * заполняет форму редактирования личной информации
     */
    public void fillPersonalInfoForm (String userFirstName, String userLastName, String userMiddleName, String userFirstNameEng, String userLastNameEng)
    {
        fillFirstName(userFirstName);
        fillLastName(userLastName);
        fillMiddleName(userMiddleName);
        fillLastNameEng(userLastNameEng);
        fillFirstNameEng(userFirstNameEng);
    }

    /**
     * нажимает кнопку сохранить
     */
    public void savePersonalInfoChanges() { $(By.xpath("//button[@class='btn btn-primary']")).click(); }

}
