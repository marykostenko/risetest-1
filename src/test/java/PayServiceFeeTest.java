import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.WebDriverRunner.webdriverContainer;

/**
 * Created by Maria on 06.06.2017.
 */
public class PayServiceFeeTest extends BaseTest
{
    //PAYNENT-1. Оплата сервисного сбора

    //PAYMENT-1.1
    @Test(priority = 1)
    public void testSuccessfulPayment() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        log("Запущен тест PAYMENT-1.1");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Регистрируем тестового контрактного кандидата для оплаты сервисного сбора");
        TestUserData testUserData = new TestUserData();
        TestUserData userDataForPayFee = new TestUserData(getUserForPayFeeId());
        TestRandomUserData userDataForPayFeeRandomEmail = new TestRandomUserData(getUserForPayFeeId());
        testUserData.registrationCandidateForPayFeeTest(userDataForPayFeeRandomEmail.getUserForPayFeeRandomEmail(),userDataForPayFee.getUserLastName(), userDataForPayFee.getUserFirstName(), userDataForPayFee.getSex(), userDataForPayFee.getCountry(),
                userDataForPayFee.getUserPassword());

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(userDataForPayFeeRandomEmail.getRandomEmail(), userDataForPayFee.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Заполняем обязательные поля в заявке кандидата и сохраняем заявку");
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        pageEditCandidate.fillCandidateRequest(userDataForPayFee.getPlaceOfBirth(), userDataForPayFee.getDateOfBirth(), userDataForPayFee.getEducationLvl(),
                userDataForPayFee.getPreviousEduOrganization(), userDataForPayFee.getCountryOfFinishedEducationOrganisation(), userDataForPayFee.getLvlId(), userDataForPayFee.getEduDirId());

        PageRequest pageRequest = new PageRequest();
        log("Добавляем копию паспорта");
        pageRequest.loadingCopyOfPassport();

        log("Добавлем копию документа об образовании");
        pageRequest.loadingCopyOfTheDocumentOnEducation();

        log("Переходим к личным данным");
        pageRequest.goToPersonalData();

        log("Добавляем фото профиля");
        pageRequest.addPhoto();

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        log("Вводим тестовые данные для успешной оплаты");
        TestCardData testCardData = new TestCardData();
        pagePaymentServiceFee.fillCardData(testCardData.getNumberCardForSuccessfulPay(), testCardData.getExptDateMounth(), testCardData.getExptDateYear(), testCardData.getCvv());

        logErrors = pagePaymentServiceFee.checkSuccessPaymentInIframe(logErrors);

        checkMistakes();
    }

    //PAYMENT-1.2
    @Test(priority = 2)
    public void testUnsuccessfulPayment() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        log("Запущен тест PAYMENT-1.2");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Регистрируем тестового контрактного кандидата для оплаты сервисного сбора");
        TestUserData testUserData = new TestUserData();
        TestUserData userDataForPayFee = new TestUserData(getUserForPayFeeId());
        TestRandomUserData userDataForPayFeeRandomEmail = new TestRandomUserData(getUserForPayFeeId());
        testUserData.registrationCandidateForPayFeeTest(userDataForPayFeeRandomEmail.getUserForPayFeeRandomEmail(),userDataForPayFee.getUserLastName(), userDataForPayFee.getUserFirstName(), userDataForPayFee.getSex(), userDataForPayFee.getCountry(),
                userDataForPayFee.getUserPassword());

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(userDataForPayFeeRandomEmail.getRandomEmail(), userDataForPayFee.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Заполняем обязательные поля в заявке кандидата и сохраняем заявку");
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        pageEditCandidate.fillCandidateRequest(userDataForPayFee.getPlaceOfBirth(), userDataForPayFee.getDateOfBirth(), userDataForPayFee.getEducationLvl(),
                userDataForPayFee.getPreviousEduOrganization(), userDataForPayFee.getCountryOfFinishedEducationOrganisation(), userDataForPayFee.getLvlId(), userDataForPayFee.getEduDirId());

        PageRequest pageRequest = new PageRequest();
        log("Добавляем копию паспорта");
        pageRequest.loadingCopyOfPassport();

        log("Добавлем копию документа об образовании");
        pageRequest.loadingCopyOfTheDocumentOnEducation();

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        log("Вводим тестовые данные для успешной оплаты");
        TestCardData testCardData = new TestCardData();
        pagePaymentServiceFee.fillCardData(testCardData.getNumberCardForUnsuccessfulPay(), testCardData.getExptDateMounth(), testCardData.getExptDateYear(), testCardData.getCvv());

        logErrors = pagePaymentServiceFee.checkUnsuccessPaymentInIframe(logErrors);

        checkMistakes();
    }

    //PAYMENT-1.3
    @Test(priority = 3)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.3");

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
        pageCandidateList.searchCandidateApplicationForAContractIsAcceptedUnpaidServiceFee();

        log("Проверяем наличме кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();
    }

    //PAYMENT-1.4
    @Test(priority = 4)
    public void testCheckPaymentButtonApplicationForOnUniApprovalUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.4");

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
        pageCandidateList.searchCandidateApplicationForOnUniApprovalUnpaidServiceFee();

        log("Проверяем наличме кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();
    }

    //PAYMENT-1.5
    @Test(priority = 5)
    public void testCheckPaymentButtonApplicationForDistributedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест PAYMENT-1.5");

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

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.searchCandidateApplicationForDistributedUnpaidServiceFee();

        log("Проверяем наличме кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();
    }
}
