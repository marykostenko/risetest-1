import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 20.04.2017.
 */
public class PageEditContactInfo extends BasePage
{


    /**
     * чистит поля телефон и email
     */
    public void clearFieldsContactInfo()
    {
        $(By.id("phone")).clear();
        $(By.id("email")).clear();
    }

    /**
     * заполняет поле Мобильный телефон при редактировании контактной информации
     */
    private void fillPhone (String changePhone)
    {
        WebElement phoneField = $(By.id("phone"));
        phoneField.sendKeys(changePhone);
    }

    /**
     * заполняет поле email при редактировании контактной информации
     */
    private  void fillEmail (String changeEmail)
    {
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(changeEmail);
    }

    /**
     * заполняет форму редактирования контактной информации
     */
    public void fillContactInfoForm (String userPhone, String userEmail)
    {
        fillPhone(userPhone);
        fillEmail(userEmail);
    }

    /**
     * нажимает кнопку сохранить
     */
    public void saveContactInfoChanges() { $(By.xpath("//button[@class='btn btn-primary']")).click(); }
}
