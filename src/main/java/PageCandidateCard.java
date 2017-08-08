import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 14.12.2016.
 */
public class PageCandidateCard extends BasePage
{

    /**
    * проверка, что меню карточки кандидата содержит нужные вкладки
     */
    public int assertCandidateCardMenu(int logErrors)
    {

        logErrors = checkAndLog($$(By.linkText("Личные данные")).isEmpty(), logErrors, "Ошибка: нет вкладки Личные данные");

        logErrors = checkAndLog($$(By.linkText("Заявка")).isEmpty(),logErrors,"Ошибка: нет вкладки Заявка");

        logErrors = checkAndLog($$(By.linkText("Сообщения")).isEmpty(),logErrors, "Ошибка: нет вкладки Сообщения");

        return logErrors;

    }

    private ElementsCollection paymentButton = $$(By.xpath("//a[contains(@href, '/pay/00')]"));

    /**
     * проверка наличия кнопки "Оплатить", переход по ней на форму оплаты
     */
    public int checkPaymentButton(int logErrors)
    {
        logErrors = checkAndLog(paymentButton.isEmpty(), logErrors, "ОШИБКА: нет кнопки 'Оплатить' на карточке кандидата", "Кнопка 'Оплатить' на карточке кандидата есть");
        $(By.xpath("//a[contains(@href, '/pay/00')]")).click();
        return logErrors;
    }

    /**
     * провертка отсутствия кнопки "Оплатить"
     */
    public int checkLackOfPaymentButton(int logErrors)
    {
        logErrors = checkAndLog(!paymentButton.isEmpty(), logErrors, "ОШИБКА: нет кнопки 'Оплатить' на карточке кандидата", "Кнопка 'Оплатить' на карточке кандидата есть");
        return logErrors;
    }

}
