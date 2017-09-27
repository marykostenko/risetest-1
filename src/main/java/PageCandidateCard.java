import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

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

    private ElementsCollection paymentButton = $$(By.xpath("//div[@class='user-block-content']//a[contains(@href, '/payment/') and @class='btn btn-success']"));

    /**
     * проверка наличия кнопки "Оплатить", переход по ней на форму оплаты
     */
    public int checkPaymentButton(int logErrors)
    {
        logErrors = checkAndLog(paymentButton.isEmpty(), logErrors, "ОШИБКА: нет кнопки 'Оплатить' на карточке кандидата", "Кнопка 'Оплатить' на карточке кандидата есть");
        paymentButton.get(0).click();
        return logErrors;
    }

    /**
     * проверка отсутствия кнопки "Оплатить"
     */
    public int checkLackOfPaymentButton(int logErrors)
    {
        logErrors = checkAndLog(!paymentButton.isEmpty(), logErrors, "ОШИБКА: есть кнопка 'Оплатить' на карточке кандидата", "Кнопки 'Оплатить' на карточке кандидата нет");
        return logErrors;
    }

}
