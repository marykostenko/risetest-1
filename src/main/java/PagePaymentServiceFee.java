import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Maria on 07.07.2017.
 */
public class PagePaymentServiceFee extends BasePage

{
    private ElementsCollection iframe = $$(By.xpath("//div[@class='wrapper__middle']"));

    /**
     * проверка наличия айфрейма
     * @param logErrors
     * @return
     */
    public int checkIframe (int logErrors)
    {
        logErrors = checkAndLog(iframe.isEmpty(), logErrors, "ОШИБКА: нет айфрейма Тинькофф ", "Айфрейм Тинькфф есть");
        return logErrors;
    }

    /**
     * заполняет номер карты
     */
    public void fillCardNumber(String cardNumber)
    {
        WebElement cardNumberField = $(By.id("form-card-number-input"));
        cardNumberField.sendKeys(cardNumber);
    }

    /**
     * заполняет месяц срока окончания действия карты
     */
    public void fillExpMonth(String expMonth)
    {
        WebElement expMonthField = $(By.xpath("//label[@class='form-label form-date__label form-date__label_month']"));
        expMonthField.sendKeys(expMonth);
    }

    /**
     * заполняет год срока окончания действия карты
     */
    public void fillExpYear(String expYear)
    {
        WebElement expYearField = $(By.xpath("//label[@class='form-label form-date__label form-date__label_year']"));
        expYearField.sendKeys(expYear);
    }

    /**
     * заполняет код cvv
     */
    public void fillCvv(String cvv)
    {
        WebElement cvvField = $(By.xpath("//label[@id='form-cvc-label']"));
        cvvField.sendKeys(cvv);
    }

    /**
     * заполняет данные карты
     */
    public void fillCardData(String cardNumber, String expMonth, String expYear, String cvv)
    {
        log("Заполняем данные тестовой карты");
        fillCardNumber(cardNumber);
        fillExpMonth(expMonth);
        fillExpYear(expYear);
        fillCvv(cvv);
        log("Нажимаем 'Оплатить'");
        $(By.xpath("//input[@id='form-submit']")).click();
        sleep(15000);
    }

    private ElementsCollection successPaymentInIframe = $$(By.xpath("//div[@class='wrapper__middle']//child::h1[contains(text(),'Платёж успешно совершён')]"));

    /**
     * проверяет, что в айфрейме появилась информация об успешности платежа
     */
    public int checkSuccessPaymentInIframe (int logErrors)
    {
        logErrors = checkAndLog(successPaymentInIframe.isEmpty(), logErrors, "ОШИБКА: нет подвтерждения успешности платежа в айфрейме ", "Платёж успешно совершён");
        return logErrors;
    }

    private ElementsCollection unsuccessPaymentInIframe = $$(By.xpath("//div[@class='wrapper__middle']//child::h1[contains(text(),'Платёж не прошёл')]"));

    /**
     * проверяет, что в айфрейме появилась инормация о том, что платёж не был совершён
     */
    public int checkUnsuccessPaymentInIframe(int logErrors)
    {
        logErrors = checkAndLog(unsuccessPaymentInIframe.isEmpty(), logErrors, "ОШИБКА: нет подвтерждения неуспешности платежа в айфрейме", "Платёж не прошёл");
        return logErrors;
    }

}
