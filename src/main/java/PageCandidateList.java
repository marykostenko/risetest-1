import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static junit.runner.BaseTestRunner.setPreference;
import static net.sourceforge.htmlunit.corejs.javascript.Context.enter;

/**
 * Created by Maria on 10.07.2017.
 */
public class PageCandidateList extends BasePage {

    /**
     * Нажимает кнопку "Отмеченные"
     */
    public void clickSelected()
    {

        $(By.xpath("//a[contains(text(),'Отмеченные')]")).click();
        sleep(3000);
    }

    /**
     * Нажимает кнопку "Выгрузить в Excel"
     */
    public void clickToExcelUpload() throws FileNotFoundException
    {
        $(By.xpath("//a[@id='excel-table-upload']")).click();
        sleep(10000);
    }

    /**
     * находит кандидата в списке, состояние которого 'Заполнение заявки на контракт'
     */
    public void filterApplicationForAContractFilling() {
        log("В фильтре 'Состояние' выбираем 'Заполнение заявки на контракт'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='FILLING_APPLICATION']")).click();
    }

    /**
     * находит кандидата в списке, состояние которого 'Заявка на контракт принята'
     */
    public void filterApplicationForAContractIsAccepted() {
        log("В фильтре 'Состояние' выбираем 'Заявка на контракт принята'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='CONTRACT_ACCEPTED']")).click();
    }

    /**
     * находит кандидата в списке, состояние которого 'На рассмотрении вуза', сервисный сюор не оплачен
     */
    public void filterApplicationForOnUniApproval() {
        log("В фильтре 'Состояние' выбираем 'На рассмотрении вуза'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='ON_APPROVAL']")).click();
    }

    /**
     * находит кандидата в списке, состояние которого 'Распределён', сервисный сбор не оплачен
     */
    public void filterApplicationForDistributed() {
        log("В фильтре 'Состояние' выбираем 'Распределен'");
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='DISTRIBUTED']")).click();
    }

    /**
     * фильтрует сервисный сбор как "Оплачен"
     */
    public void filterServiceFeePaid() {
        log("В фильтре 'Сервисный сбор' выбираем 'Оплачен'");
        $(By.xpath("//div[@id='serviceFeePaymentStatusOrMethodCode']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='Paid']")).click();
    }

    /**
     * фильтрует сервисный сбор как "Не оплачен"
     */
    public void filterServiceFeeUnpaid() {
        log("В фильтре 'Сервисный сбор' выбираем 'Не оплачен'");
        $(By.xpath("//div[@id='serviceFeePaymentStatusOrMethodCode']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='NotPaid']")).click();
    }

    /**
     * выбирает первого кандидата из списка
     */
    public void selectFirstCandidate() {
        sleep(5000);
        log("Открываем первого кандидата из списка");
        $(By.xpath("//tr[1]//child::a[contains(@href,'/candidates/')]")).click();
    }

    /**
     * Осуществляет поиск по регномеру
     */
    private void searchByRegNumber(String regNumber) {
        WebElement emailField = $(By.id("title"));
        emailField.sendKeys(regNumber);
        emailField.sendKeys(Keys.ENTER);
    }

    /**
     * Вводит регномер кандидата и открывает отфильрованного
     */
    public void selectCandidateByRegNumber(String regNumber) {
        searchByRegNumber(regNumber);
        selectFirstCandidate();
    }

    private ElementsCollection firstActiveCandidateInList = $$(By.xpath("//tr[1]//child::a[contains(@href,'/candidates/')]"));

    /**
     * Проверяет,что  пользователь не может открыть карточку кандидата, которую отфильровал по регномеру
     */
    public int canNotOpenACard(int logErrors, String regNumber, String state) {
        MenuContent menuContent = new MenuContent();

        menuContent.goToCandidatesList();

        searchByRegNumber(regNumber);
        sleep(3000);

        if (!firstActiveCandidateInList.isEmpty()) {
            log("ОШИБКА! Карточка кандидата открыта для просмотра");
            logErrors++;
        } else
            log("Карточка кандидата в состоянии " + state + " не доступна для просмотра");

        return logErrors;
    }

    /**
     * Отмечает кандидата, отфильтрованного по регномеру
     */
    public void marksCandidate(String regNumber)
    {
        MenuContent menuContent = new MenuContent();

        menuContent.goToCandidatesList();

        searchByRegNumber(regNumber);
        sleep(3000);

        $(By.xpath("//tr[1]//child::i[contains(@href,'/selectInList')]")).click();
    }

    /**
     * Переиндексирует отфильтрованных кандидатов канидатов
     */
    private void reindexCandidates()
    {
        $(By.xpath("//a[@id='reindexCandidates']")).click();
    }

    /**
     * Переиндексирует всех кандидатов канидатов
     */
    private void reindexAllCandidates()
    {
        $(By.xpath("//a[@id='reindexAllCandidates']")).click();
    }

    /**
     * Фильтрует по стране
     */
    private void сountryFilter(String country, String countryId)
    {
        WebElement emailField = $(By.xpath("//input[@placeholder='Страна']"));
        emailField.sendKeys(country);
        $(By.xpath("//a[@data-id='" + countryId +"']")).click();
    }

    /**
     * Переиндексирует кандидатов в состоянии Заполнение заявления
     */
    public void reindexInputCandidate(String adminLogin, String adminPass, String country, String counryId)
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        MenuContent menuContent = new MenuContent();

        pageTopBottom.goToLogin();
        pageLogin.isLoginForm();
        pageLogin.fillLoginForm(adminLogin, adminPass);
        pageLogin.pushLoginButton();
        menuContent.goToCandidatesList();
        $(By.xpath("//div[@id='stateCode_field']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[@data-id='INPUT']")).click();
        сountryFilter(country, counryId);
        reindexCandidates();
        pageTopBottom.logout();

    }
}
