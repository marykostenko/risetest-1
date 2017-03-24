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


    public int editPersonalInfoFromAdmin (int logErrors)
    {

        if (!buttonEditPersonalInfo.isEmpty())
        {



        } else
        {

            logErrors++;
            log("Пиктограмы 'Редактировать' в блоке личной информации нет");

        }

        return logErrors;

    }

}
