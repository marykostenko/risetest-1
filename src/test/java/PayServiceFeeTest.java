import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 06.06.2017.
 */
public class PayServiceFeeTest extends BaseTest
{
    //Service-Fee-1. Оплата сервисного сбора

    //Service-Fee-1.1
    @Test(priority = 1)
    public void testSuccessfulPayment() throws IOException, InterruptedException, MessagingException, SQLException {

        TestUserData regUsData = new TestUserData(getUserForPayFeeId());
        TestUserData testUserData = new TestUserData();
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        PageRequest pageRequest = new PageRequest();
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        TestCardData testCardData = new TestCardData();
        PageCandidateCard pageCandidateCard = new PageCandidateCard();


        String userId = getUserForPayFeeId();

        log("Подготовка тестового кандидата для оплаты сервисного сбора");

        String randomEmail = testUserData.creationTestCandidateForPayFee((getStandUrl("Ext")), userId);

        log("Проверяем, что тестовый кандиадат был создан и мы получли его email для входа");
        if (!testRandomUserData.randomEmailNotNull(randomEmail))
        {
            logErrors++;
            checkMistakes();
            return;
        }

        log("ТЕСТОВЫЙ КАНДИДАТ ГОТОВ К ОПЛАТЕ СЕРВИСНОГО СБОРА");
        log("_______________________________________________________________");

        /**
         * НАЧАЛО ТЕСТА Service-Fee-1.1
         */

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        log(randomEmail);
        pageLogin.fillLoginForm(randomEmail, regUsData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        log("Вводим тестовые данные для успешной оплаты");
        pagePaymentServiceFee.fillCardData(testCardData.getNumberCardForSuccessfulPay(), testCardData.getExptDateMounth(), testCardData.getExptDateYear(), testCardData.getCvv());

        logErrors = pagePaymentServiceFee.checkSuccessPaymentInIframe(logErrors);

        log("Возвращаемся в карточку кандидата");
        pageTopBottom.goToCandidateCard();

        log("Проверяем статус кандидата");
        if(!pageCandidateCard.isStatusContractAccepted())
        {
            log("Статус кандидата не изменился, нажимаем кнопку Отправить заявление");
            pageCandidateCard.clickSubmitApplication();
            pageCandidateCard.waitWhileStatusBecomesContractAccepted();
        }
        log("Статус кандидата изменился на Заявка на контракт принята");

        checkMistakes();

        log("Тест Service-Fee-1.1 завершен");
    }

    //Service-Fee-1.2
    @Test(priority = 2)
    public void testUnsuccessfulPayment() throws IOException, InterruptedException, MessagingException, SQLException {

        TestUserData regUsData = new TestUserData(getUserForPayFeeId());
        TestUserData testUserData = new TestUserData();
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        PageRequest pageRequest = new PageRequest();
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        TestCardData testCardData = new TestCardData();

        String userId = getUserForPayFeeId();

        log("Подготовка тестового кандидата для оплаты сервисного сбора");

        String randomEmail = testUserData.creationTestCandidateForPayFee((getStandUrl("Ext")), userId);

        log("Проверяем, что тестовый кандидат был создан и мы получили его email для входа");
        if (!testRandomUserData.randomEmailNotNull(randomEmail))
        {
            logErrors++;
            checkMistakes();
            return;
        }

        log("ТЕСТОВЫЙ КАНДИДАТ ГОТОВ К ОПЛАТЕ СЕРВИСНОГО СБОРА");
        log("_______________________________________________________________");

        /**
         * НАЧАЛО ТЕСТА Service-Fee-1.2
         */

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        log(randomEmail);
        pageLogin.fillLoginForm(randomEmail, regUsData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        log("Вводим тестовые данные для неуспешной оплаты");
        pagePaymentServiceFee.fillCardData(testCardData.getNumberCardForUnsuccessfulPay(), testCardData.getExptDateMounth(), testCardData.getExptDateYear(), testCardData.getCvv());

        logErrors = pagePaymentServiceFee.checkUnsuccessPaymentInIframe(logErrors);

        checkMistakes();

        log("Тест Service-Fee-1.2 завершен");
    }

}
