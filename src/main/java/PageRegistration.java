import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

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
}
