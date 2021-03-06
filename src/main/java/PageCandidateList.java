import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Maria on 10.07.2017.
 */
public class PageCandidateList extends BasePage
{

    /**
     * находит кандидата в списке, состояние которого 'Заявка на контракт принята', сервисный сбор не оплачен
     */
    public void filterApplicationForAContractIsAccepted()
    {
        log("В фильтре 'Состояние' выбираем 'Заявка на контракт принята'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='CONTRACT_ACCEPTED']")).click();
    }

    /**
     * находит кандидата в списке, состояние которого 'На рассмотрении вуза', сервисный сюор не оплачен
     */
    public void filterApplicationForOnUniApproval()
    {
        log("В фильтре 'Состояние' выбираем 'На рассмотрении вуза'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='ON_APPROVAL']")).click();
    }

    /**
     * находит кандидата в списке, состояние которого 'Распределён', сервисный сбор не оплачен
     */
    public void filterApplicationForDistributed()
    {
        log("В фильтре 'Состояние' выбираем 'Распределен'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='DISTRIBUTED']")).click();
    }

    /**
     * фильтрует сервисный сбор как "Оплачен"
     */
    public void filterServiceFeePaid()
    {
        log("В фильтре 'Сервисный сбор' выбираем 'Оплачен'");
        $(By.xpath("//div[@id='serviceFeePaymentStatusOrMethodCode']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='Paid']")).click();
    }

    /**
     * фильтрует сервисный сбор как "Не оплачен"
     */
    public void filterServiceFeeUnpaid()
    {
        log("В фильтре 'Сервисный сбор' выбираем 'Не оплачен'");
        $(By.xpath("//div[@id='serviceFeePaymentStatusOrMethodCode']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='NotPaid']")).click();
    }

    /**
     * выбирает первого кандидата из списка
     */
    public void selectFirstCandidate()
    {
        sleep(5000);
        log("Открываем первого кандидата из списка");
        $(By.xpath("//tr[1]//child::a[contains(@href,'/candidates/')]")).click();
    }
}
