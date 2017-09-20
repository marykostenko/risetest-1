import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 06.06.2017.
 */
public class PayServiceFeeTest extends BaseTest
{
    //PAYNENT-1. Оплата сервисного сбора

    //PAYMENT-1.1
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
         * НАЧАЛО ТЕСТА PAYMENT-1.1
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

        checkMistakes();

        log("Тест PAYMENT-1.1 завершен");
    }

    //PAYMENT-1.2
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
         * НАЧАЛО ТЕСТА PAYMENT-1.2
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

        log("Тест PAYMENT-1.2 завершен");
    }

    //PAYMENT-1.3
    @Test(priority = 3)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.3 Проверка кнопки Оплата у кандидатов в состоянии Заявка на контракт принята, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractIsAccepted();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.3 завершен");
    }

    //PAYMENT-1.4
    @Test(priority = 4)
    public void testCheckPaymentButtonApplicationForOnUniApprovalUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.4 Проверка кнопки Оплата у кандидатов в состоянии На рассмотрении вуза, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'На рассмотрении вуза', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForOnUniApproval();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.4 завершен");
    }

    //PAYMENT-1.5
    @Test(priority = 5)
    public void testCheckPaymentButtonApplicationForDistributedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.5 Проверка кнопки Оплата у кандидатов в состоянии Распределён, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'Не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForDistributed();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.5 завершен");
    }

    //PAYMENT-1.6
    @Test(priority = 6)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedPaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.6 Проверка отсутсвия кнопки Оплата у кандидатов в состоянии Заявка на контракт принята, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'Оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractIsAccepted();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.6 завершен");
    }

    //PAYMENT-1.7
    @Test(priority = 7)
    public void testCheckPaymentButtonApplicationForOnUniApprovalPaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.7 Проверка отсутствия кнопки Оплата у кандидатов в состоянии На рассмотрении вуза, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'На рассмотрении вуза', сервисный сбор 'оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForOnUniApproval();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.7 завершен");
    }

    //PAYMENT-1.8
    @Test(priority = 8)
    public void testCheckPaymentButtonApplicationForDistributedPaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.8 Проверка отсутствия кнопки Оплата у кандидатов в состоянии Распределён, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'Оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForDistributed();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест PAYMENT-1.8 завершен");
    }

}
