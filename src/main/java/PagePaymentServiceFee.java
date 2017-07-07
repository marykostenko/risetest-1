import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Maria on 07.07.2017.
 */
public class PagePaymentServiceFee extends BasePage

{
    private ElementsCollection iframe = $$(By.xpath("//div[@class='wrapper__middle']"));

    public int checkIframe (int logErrors)
    {
        logErrors = checkAndLog(iframe.isEmpty(), logErrors, "ОШИБКА: нет айфрейма Тинькофф ", "Айфрейм Тинькфф есть");
        return logErrors;
    }

}
