import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

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



    public void goToUsers()
    {

        $(By.xpath("//div[@class='clearfix']//child::a[@class='dropdown-toggle padding-right-0']")).click();
        $(By.xpath("//a[@href='/users']")).click();

    }

    public int checkUserProfile (int logErrors)
    {


        PageLogin pageLogin = new PageLogin();

    }

}
