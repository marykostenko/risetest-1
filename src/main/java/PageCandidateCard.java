import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;
/**
 * Created by user nkorobicina on 14.12.2016.
 */
public class PageCandidateCard extends BasePage
{

    private WebElement statusLine = $(By.xpath("//div[@class='format-candidate-fields'][1]//div[contains(@class, 'span6')][2]"));

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

    /**
     * Находит строку, содержащую статус кандидата
     * @return
     */


    public String getCandidateStatus()
    {

        log("Текущий статус кандидата: " + statusLine.getText());
        return statusLine.getText();
    }

    /**
     * Возвращает true, если статус кандидата "Заявка на контракт принята"
     * @return
     */
    public boolean isStatusContractAccepted()
    {
        if (getCandidateStatus().contains("Заявка на контракт принята"))
            return true;
        else
            return false;
    }

    /**
     * Нажимает кнопку "Отправить заявление" в состоянии "Заполнение заявки на контракт"
     */
    public void clickSubmitApplication()
    {
        $(By.xpath("//a[contains(@id, 'from_FILLING_APPLICATION_to_CONTRACT_ACCEPTED')]")).click();
    }

    /**
     * Ждет, пока статус не изменится на "Заявка на контракт принята"
     */

    public void waitWhileStatusBecomesContractAccepted()
    {
        $(By.xpath("//div[@class='format-candidate-fields'][1]//div[contains(@class, 'span6')][2]")).shouldHave(text("Заявка на контракт принята"));
    }

}
