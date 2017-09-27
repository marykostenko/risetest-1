import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;
/**
 * Created by Maria on 07.07.2017.
 */
public class PagePaymentServiceFee extends BasePage

{
    private ElementsCollection iframe = $$(By.xpath("//iframe"));

    /**
     * проверка наличия айфрейма
     * @param logErrors
     * @return
     */
    public int checkIframe (int logErrors)
    {
        logErrors = checkAndLog(iframe.isEmpty(), logErrors, "ОШИБКА: нет айфрейма Тинькофф ", "Айфрейм Тинькофф есть");
        return logErrors;
    }

    /**
     * заполняет номер карты
     */
    public void fillCardNumber(String cardNumber)
    {
        WebElement cardNumberField = $(By.xpath("//input[@id='form-card-number-input']"));
        cardNumberField.sendKeys(cardNumber);
    }

    /**
     * заполняет месяц срока окончания действия карты
     */
    public void fillExpMonth(String expMonth)
    {
        WebElement expMonthField = $(By.xpath("//input[@class='form-field form-date__field form-date_month__field']"));
        expMonthField.sendKeys(expMonth);
    }

    /**
     * заполняет год срока окончания действия карты
     */
    public void fillExpYear(String expYear)
    {
        WebElement expYearField = $(By.xpath("//input[@class='form-field form-date__field form-date_year__field']"));
        expYearField.sendKeys(expYear);
    }

    /**
     * заполняет код cvv
     */
    public void fillCvv(String cvv)
    {
        WebElement cvvField = $(By.xpath("//input[@id='form-cvc-input']"));
        cvvField.sendKeys(cvv);
    }

    /**
     * заполняет данные карты
     */
    public void fillCardData(String cardNumber, String expMonth, String expYear, String cvv)
    {
        log("Переключаемся во фрейм Тинькофф");
        switchTo().defaultContent();
        switchTo().frame("c");
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
        switchTo().defaultContent();
        return logErrors;
    }

    private ElementsCollection unsuccessPaymentInIframe = $$(By.xpath("//div[@class='wrapper__middle']//child::h1[contains(text(),'Платёж не прошёл')]"));

    /**
     * проверяет, что в айфрейме появилась инормация о том, что платёж не был совершён
     */
    public int checkUnsuccessPaymentInIframe(int logErrors)
    {
        logErrors = checkAndLog(unsuccessPaymentInIframe.isEmpty(), logErrors, "ОШИБКА: нет подвтерждения неуспешности платежа в айфрейме", "Платёж не прошёл");
        switchTo().defaultContent();
        return logErrors;
    }

}
