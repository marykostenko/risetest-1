import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by user nkorobicina on 14.12.2016.
 */
public class PageCandidateCard extends BasePage
{

    private WebElement statusLine = $(By.xpath("//div[@class='format-candidate-fields'][1]//div[contains(@class, 'span6')][2]"));
    private ElementsCollection agent = $$(By.xpath("//div[contains(text(),'Организация-агент')]//following::div[1]"));

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

    private ElementsCollection paymentButton = $$(By.xpath("//div[@class='user-block-content']//a[contains(@id, '_payment_link') and @class='btn btn-success']"));

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
     * Возвращает true, если статус кандидата совпадает с полученным
     */
    private boolean statusMeets(String state)
    {
        if (getCandidateStatus().contains(state))
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
    /**
     * Проверяет, есть ли на карточке информация об организации агенте, если есть - возвращает true
     */
    private boolean checkAgentInfo()
    {
        if (agent.isEmpty())
            return false;
        else
            return true;
    }

    /**
     * Открывает карточку квотного кандидата, отфильрованную по регномеру, сверяет стстаус с полученным и отсутствие наличия информации об агенте
     * Возвращает количество ошибок
     */
    public int checkQuotaCandidateCard(String regNumber, String state, int logErrors)
    {
        PageCandidateList pageCandidateList = new PageCandidateList();
        MenuContent menuContent = new MenuContent();

        log("Перейдём к списку кандидатов");
        menuContent.goToCandidatesList();

        pageCandidateList.selectCandidateByRegNumber(regNumber);
        if (!statusMeets(state))
        {
            log("ОШИБКА!!! Состояние кандидата должно было быть " + state);
            logErrors++;
        }
        if (checkAgentInfo())
        {
            log("ОШИБКА!!! На карточке отображается информация об агенте");
            logErrors++;
        }
        return logErrors;
    }

    private ElementsCollection lastNameEngDisabled =  $$(By.xpath("//div[@data-field-name='CandidateFormData_lastNameEng']//child::input[@disabled='true']"));
    private ElementsCollection firstNameEngDisabled =  $$(By.xpath("//div[@data-field-name='CandidateFormData_firstNameEng']//child::input[@disabled='true']"));

    /**
     * Проверяет, что нельзя редактировать ФИО латиницей  (когда кандидат в состояниях Заявление принято и Отобран по квоте)
     */
    public int checkBanEditingOfNameInLatin(int logErrors, String candidateStateName)
    {
        $(By.xpath("//a[@class='link-opacity pull-right']")).click();

        if (lastNameEngDisabled.isEmpty())
        {
            log("Ошибка! Фамилию латиницей представителю нельзя редактировать у кандидата в состоянии " + candidateStateName + ", сейчас поле открыто для редактирования");
            logErrors++;
        } else
            log("Представителю закрыта возможность редактировать фамилию латиницей у кандидата в состоянии " + candidateStateName);

        if (firstNameEngDisabled.isEmpty())
        {
            log("Ошибка! Имя латиницей представителю нельзя редактировать у кандидата в состоянии " + candidateStateName + ", сейчас поле открыто для редактирования");
            logErrors++;
        } else
            log("Представителю закрыта возможность редактировать имя латиницей у кандидата в состоянии" + candidateStateName);



        return logErrors;
    }

}
